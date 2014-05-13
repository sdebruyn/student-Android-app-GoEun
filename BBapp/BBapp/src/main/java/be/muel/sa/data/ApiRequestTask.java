package be.muel.sa.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import be.muel.sa.entities.Address;
import be.muel.sa.entities.Country;
import be.muel.sa.entities.Information;
import be.muel.sa.entities.OpeningHour;
import be.muel.sa.entities.POIType;
import be.muel.sa.entities.Photo;
import be.muel.sa.entities.PlaceOfInterest;
import be.muel.sa.entities.Promotion;
import be.muel.sa.entities.Room;
import be.muel.sa.entities.WeekDay;

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
                    PlaceOfInterest placeOfInterest = parsePOI(obj.getJSONObject("PlaceOfInterest"));
                    if (placeOfInterest != null) {
                        Address address = parseAddress(obj.getJSONObject("Address"));
                        placeOfInterest.setAddress(address);
                        JSONArray jOHours = obj.getJSONArray("OpeningHour");
                        for (int j = 0; j < jOHours.length(); j++) {
                            OpeningHour oHour = parseOpeningHour(jOHours.getJSONObject(j));
                            if (oHour != null) {
                                oHour.setPlaceOfInterest(placeOfInterest);
                                placeOfInterest.addOpeningHour(oHour);
                            }
                        }
                        JSONArray jPhotos = obj.getJSONArray("Photo");
                        for (int k = 0; k < jPhotos.length(); k++) {
                            Photo photo = parsePhoto(jPhotos.getJSONObject(k));
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
                    Room room = parseRoom(obj.getJSONObject("Room"));
                    if (room != null) {
                        JSONArray jPhotos = obj.getJSONArray("Photo");
                        for (int j = 0; j < jPhotos.length(); j++) {
                            Photo photo = parsePhoto(jPhotos.getJSONObject(j));
                            if (photo != null) {
                                photo.setRoom(room);
                                room.addPhoto(photo);
                            }
                        }
                        JSONArray jPromotions = obj.getJSONArray("Promotion");
                        for (int k = 0; k < jPromotions.length(); k++) {
                            JSONObject jCurrentPromo = jPromotions.getJSONObject(k);
                            Promotion promotion = parsePromotion(jCurrentPromo);
                            if (promotion != null) {
                                promotion.setRoom(room);
                                room.addPromotion(promotion);
                            }
                            JSONArray jPromoPhotos = jCurrentPromo.getJSONArray("Photo");
                            for(int l = 0; l < jPromoPhotos.length(); l++){
                                Photo promoPhoto = parsePhoto(jPromoPhotos.getJSONObject(l));
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

    private Bitmap downloadImage(String url) {
        Bitmap result = null;
        try {
            URL photoUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) photoUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = (InputStream) photoUrl.getContent();
            result = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    private Room parseRoom(JSONObject jObj) {
        Room result = null;
        try {
            int id = jObj.getInt("id");
            String name = jObj.getString("name");
            double price = jObj.getDouble("price");
            BigDecimal pDec = BigDecimal.valueOf(price);
            String desc = jObj.getString("description");
            int type = jObj.getInt("type");
            result = new Room(id, name, desc, type, pDec);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    private PlaceOfInterest parsePOI(JSONObject jObj) {
        PlaceOfInterest result = null;
        try {
            int id = jObj.getInt("id");
            String name = jObj.getString("name");
            String telephone = jObj.getString("telephone");
            int type = jObj.getInt("type");
            POIType pType = POIType.fromInt(type);
            result = new PlaceOfInterest(id, name, telephone, pType);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    private Country getCountry(int id) {
        Country result = null;
        try {
            JSONObject response = NetworkRequest.getResponse("countries/" + String.valueOf(id)).getJSONObject("country").getJSONObject("Country");
            String name = response.getString("name");
            result = new Country(id, name);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    private Address parseAddress(JSONObject jObj) {
        Address result = null;
        try {
            int id = jObj.getInt("id");
            int countryID = jObj.getInt("country_id");
            Country country = getCountry(countryID);
            String name = jObj.getString("name");
            String aL1 = "";
            String aL2 = "";
            String aL3 = "";
            String aL4 = "";
            String locality = "";
            String region = "";
            String zipCode = "";
            if (!jObj.isNull("address_line_1")) {
                aL1 = jObj.getString("address_line_1");
            }
            if (!jObj.isNull("address_line_2")) {
                aL2 = jObj.getString("address_line_2");
            }
            if (!jObj.isNull("address_line_3")) {
                aL3 = jObj.getString("address_line_3");
            }
            if (!jObj.isNull("address_line_4")) {
                aL4 = jObj.getString("address_line_4");
            }
            if (!jObj.isNull("locality")) {
                locality = jObj.getString("locality");
            }
            if (!jObj.isNull("region")) {
                region = jObj.getString("region");
            }
            if (!jObj.isNull("zipcode")) {
                zipCode = jObj.getString("zipcode");
            }
            result = new Address(id, name, aL1, aL2, aL3, aL4, locality, region, zipCode, country);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    private OpeningHour parseOpeningHour(JSONObject jObj) {
        OpeningHour result = null;
        try {
            int id = jObj.getInt("id");
            int dId = jObj.getInt("weekday");
            WeekDay wDay = WeekDay.fromInt(dId);
            String startStr = jObj.getString("start");
            String endStr = jObj.getString("end");
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Calendar start = Calendar.getInstance();
            start.setTime(dateFormat.parse(startStr));
            Calendar end = Calendar.getInstance();
            end.setTime(dateFormat.parse(endStr));
            result = new OpeningHour(id, start, end, wDay);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }


    private Photo parsePhoto(JSONObject jObj) {
        Photo photo = null;
        try {
            int id = jObj.getInt("id");
            String link = jObj.getString("link");
            photo = new Photo(id);
            Bitmap content = downloadImage(link);
            photo.setBitmap(content);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return photo;
    }

    private Promotion parsePromotion(JSONObject jObj) {
        Promotion promotion = null;
        try {
            int id = jObj.getInt("id");
            String desc = jObj.getString("description");
            promotion = new Promotion(id, desc);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return promotion;
    }
}
