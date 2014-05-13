package be.muel.sa.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import be.muel.sa.entities.Address;
import be.muel.sa.entities.OpeningHour;
import be.muel.sa.entities.Photo;
import be.muel.sa.entities.PlaceOfInterest;

/**
 * Created by Samuel on 13/05/2014.
 */
public class NearbyPOITask extends AsyncTask<Double, Void, List<PlaceOfInterest>>{

    private final static double DEFAULT_RANGE = 50.0;
    private final static String DEBUG_TAG = "BBappNearbyAPI";

    @Override
    protected List<PlaceOfInterest> doInBackground(Double... doubles) {
        if(doubles.length < 2)
            return null;

        double latitude = doubles[0];
        double longitude = doubles[1];
        double range = DEFAULT_RANGE;

        if(doubles.length == 3) {
            range = doubles[2];
        }

        return getNearbyPlaces(latitude, longitude, range);
    }

    private List<PlaceOfInterest> getNearbyPlaces(double latitude, double longitude, double range){
        List<PlaceOfInterest> result = new LinkedList<PlaceOfInterest>();
        try {
            JSONObject response = NetworkRequest.getResponse("places_of_interest/nearby/" + String.valueOf(latitude) + "/" + String.valueOf(longitude) + "/" + String.valueOf(range));
            JSONArray iResponse = response.getJSONArray("places_of_interest");
            for(int i = 0; i < iResponse.length(); i++){
                JSONObject poi = iResponse.getJSONObject(i).getJSONObject("PlaceOfInterest");
                int poiID = poi.getInt("id");
                PlaceOfInterest  poiO = getPlaceOfInterestForId(poiID);
                if(poiO != null){
                    result.add(poiO);
                }
            }
        }catch(Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    private PlaceOfInterest getPlaceOfInterestForId(int id){
        PlaceOfInterest poi = null;
        try{
            JSONObject response = NetworkRequest.getResponse("places_of_interest/view/" + String.valueOf(id));
            JSONObject iResponse = response.getJSONObject("place_of_interest");
            poi = NetworkRequest.parsePOI(iResponse.getJSONObject("PlaceOfInterest"));
            Address addr = NetworkRequest.parseAddress(iResponse.getJSONObject("Address"));
            if(addr != null){
                poi.setAddress(addr);
            }
            JSONArray iPhotos = iResponse.getJSONArray("Photo");
            JSONArray iOpeningH = iResponse.getJSONArray("OpeningHour");
            for(int i = 0; i < iPhotos.length(); i++){
                Photo photo = NetworkRequest.parsePhoto(iPhotos.getJSONObject(i));
                if(photo != null){
                    photo.setPlaceOfInterest(poi);
                    poi.addPhoto(photo);
                }
            }
            for(int i = 0; i < iOpeningH.length(); i++){
                OpeningHour oHour = NetworkRequest.parseOpeningHour(iOpeningH.getJSONObject(i));
                if(oHour != null){
                    oHour.setPlaceOfInterest(poi);
                    poi.addOpeningHour(oHour);
                }
            }
        }catch(Exception e){
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return poi;
    }


}
