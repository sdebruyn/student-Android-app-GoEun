package be.muel.sa.data;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import be.muel.sa.entities.Information;
import be.muel.sa.entities.Photo;
import be.muel.sa.entities.Promotion;
import be.muel.sa.entities.Room;

/**
 * Created by Samuel on 19/04/2014.
 */
public class ApiRequestTask extends AsyncTask<RequestType, Void, Object> {

    private final static String DEBUG_TAG = "BBappAPI";
    private static final String baseUrl = "http://mi4.sa.muel.be/";
    private static final AndroidHttpClient client = AndroidHttpClient.newInstance("Android BBApp");

    @Override
    protected Object doInBackground(RequestType... requestTypes) {
        switch (requestTypes[0]) {
            case ROOMS:
                return getRooms();
            default:
            case INFORMATION:
                return getInformation();
        }
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }

    private Information getInformation() {
        Information inf = null;
        try {
            JSONObject response = getResponse("information");
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
        return inf;
    }

    private List<Room> getRooms() {
        List<Room> result = new ArrayList<Room>();
        try {
            JSONObject response = getResponse("rooms");
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
                    for(int k = 0; k < jPromotions.length(); k++){
                        Promotion promotion = parsePromotion(jPromotions.getJSONObject(k));
                        if(promotion != null){
                            promotion.setRoom(room);
                            room.addPromotion(promotion);
                        }
                    }
                    result.add(room);
                }
            }
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
            int price = jObj.getInt("price");
            String desc = jObj.getString("description");
            int type = jObj.getInt("type");
            result = new Room(id, name, desc, type, price);
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
            photo = new Photo(id, link);
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

    private JSONObject getResponse(String relativeUrl) {
        HttpGet request = new HttpGet(getAbsoluteUrl(relativeUrl));
        request.addHeader("Accept", "application/json, text/json");
        request.addHeader("Accept-Charset", "utf-8");
        try {
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 300)
                return null;

            HttpEntity entity = response.getEntity();
            InputStreamReader iReader = new InputStreamReader(entity.getContent(), "UTF-8");
            BufferedReader bReader = new BufferedReader(iReader);
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = bReader.readLine()) != null) {
                sb.append(str);
            }
            String result = sb.toString();
            JSONObject jResult = new JSONObject(result);
            return jResult;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
