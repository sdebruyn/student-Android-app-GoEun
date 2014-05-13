package be.muel.sa.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.muel.sa.entities.Address;
import be.muel.sa.entities.Information;
import be.muel.sa.entities.OpeningHour;
import be.muel.sa.entities.Photo;
import be.muel.sa.entities.PlaceOfInterest;
import be.muel.sa.entities.Promotion;
import be.muel.sa.entities.Room;

/**
 * Created by Samuel on 19/04/2014.
 */
public class ApiRequestTask extends AsyncTask<RequestType, Void, Object> {

    private final static String DEBUG_TAG = "BBappAPI";

    @Override
    protected Object doInBackground(RequestType... requestTypes) {
        switch (requestTypes[0]) {
            case ROOMS:
                return getRooms();
            case POI:
                return getPlacesOfInterest();
            default:
            case INFORMATION:
                return getInformation();

        }

    }

    private List<PlaceOfInterest> getPlacesOfInterest() {
        if (CachedApiObjects.getInstance().getpOIList() == null) {

            List<PlaceOfInterest> result = new ArrayList<PlaceOfInterest>();
            try {
                JSONObject response = NetworkRequest.getResponse("places_of_interest");
                JSONArray iResponse = response.getJSONArray("places_of_interest");
                for (int i = 0; i < iResponse.length(); i++) {
                    JSONObject obj = iResponse.getJSONObject(i);
                    PlaceOfInterest placeOfInterest = NetworkRequest.parsePOI(obj.getJSONObject("PlaceOfInterest"));
                    if (placeOfInterest != null) {
                        Address address = NetworkRequest.parseAddress(obj.getJSONObject("Address"));
                        placeOfInterest.setAddress(address);
                        JSONArray jOHours = obj.getJSONArray("OpeningHour");
                        for (int j = 0; j < jOHours.length(); j++) {
                            OpeningHour oHour = NetworkRequest.parseOpeningHour(jOHours.getJSONObject(j));
                            if (oHour != null) {
                                oHour.setPlaceOfInterest(placeOfInterest);
                                placeOfInterest.addOpeningHour(oHour);
                            }
                        }
                        JSONArray jPhotos = obj.getJSONArray("Photo");
                        for (int k = 0; k < jPhotos.length(); k++) {
                            Photo photo = NetworkRequest.parsePhoto(jPhotos.getJSONObject(k));
                            if (photo != null) {
                                photo.setPlaceOfInterest(placeOfInterest);
                                placeOfInterest.addPhoto(photo);
                            }
                        }
                        result.add(placeOfInterest);
                    }
                }
            } catch (Exception e) {
                Log.d(DEBUG_TAG, e.toString(), e);
            }
            CachedApiObjects.getInstance().setpOIList(result);
        }
        return CachedApiObjects.getInstance().getpOIList();
    }

    private Information getInformation() {
        if (CachedApiObjects.getInstance().getInfoObject() == null) {

            Information inf = null;
            try {
                JSONObject response = NetworkRequest.getResponse("information");
                JSONObject iResponse = response.getJSONObject("information").getJSONObject("Information");
                String telephone = iResponse.getString("telephone");
                String cell = iResponse.getString("cell_phone");
                String mail = iResponse.getString("email");
                String desc = iResponse.getString("description");
                String bf = iResponse.getString("breakfast");
                inf = new Information(telephone, cell, mail, desc, bf);
            } catch (Exception e) {
                Log.d(DEBUG_TAG, e.toString(), e);
            }
            CachedApiObjects.getInstance().setInfoObject(inf);
        }
        return CachedApiObjects.getInstance().getInfoObject();
    }

    private List<Room> getRooms() {
        if (CachedApiObjects.getInstance().getRoomList() == null) {

            List<Room> result = new ArrayList<Room>();
            try {
                JSONObject response = NetworkRequest.getResponse("rooms");
                JSONArray iResponse = response.getJSONArray("rooms");
                for (int i = 0; i < iResponse.length(); i++) {
                    JSONObject obj = iResponse.getJSONObject(i);
                    Room room = NetworkRequest.parseRoom(obj.getJSONObject("Room"));
                    if (room != null) {
                        JSONArray jPhotos = obj.getJSONArray("Photo");
                        for (int j = 0; j < jPhotos.length(); j++) {
                            Photo photo = NetworkRequest.parsePhoto(jPhotos.getJSONObject(j));
                            if (photo != null) {
                                photo.setRoom(room);
                                room.addPhoto(photo);
                            }
                        }
                        JSONArray jPromotions = obj.getJSONArray("Promotion");
                        for (int k = 0; k < jPromotions.length(); k++) {
                            JSONObject jCurrentPromo = jPromotions.getJSONObject(k);
                            Promotion promotion = NetworkRequest.parsePromotion(jCurrentPromo);
                            if (promotion != null) {
                                promotion.setRoom(room);
                                room.addPromotion(promotion);
                            }
                            JSONArray jPromoPhotos = jCurrentPromo.getJSONArray("Photo");
                            for(int l = 0; l < jPromoPhotos.length(); l++){
                                Photo promoPhoto = NetworkRequest.parsePhoto(jPromoPhotos.getJSONObject(l));
                                if(promoPhoto != null){
                                    promoPhoto.setPromotion(promotion);
                                    promotion.addPhoto(promoPhoto);
                                }
                            }
                        }
                        result.add(room);
                    }
                }
            } catch (Exception e) {
                Log.d(DEBUG_TAG, e.toString(), e);
            }
            CachedApiObjects.getInstance().setRoomList(result);
        }
        return CachedApiObjects.getInstance().getRoomList();
    }


}
