package be.muel.sa.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import be.muel.sa.entities.Address;
import be.muel.sa.entities.Country;
import be.muel.sa.entities.OpeningHour;
import be.muel.sa.entities.POIType;
import be.muel.sa.entities.Photo;
import be.muel.sa.entities.PlaceOfInterest;
import be.muel.sa.entities.Promotion;
import be.muel.sa.entities.Room;
import be.muel.sa.entities.WeekDay;

/**
 * Created by Samuel on 13/05/2014.
 */
public class NetworkRequest {

    private static final String baseUrl = "http://mi4.sa.muel.be/";
    private static final AndroidHttpClient client = AndroidHttpClient.newInstance("Android BBApp");
    private static final String DEBUG_TAG = "BB_NetworkRequests";

    public static JSONObject getResponse(String relativeUrl) {
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

    public static String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }

    private static Bitmap downloadImage(String url) {
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

    static Room parseRoom(JSONObject jObj) {
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

    static PlaceOfInterest parsePOI(JSONObject jObj) {
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

    private static Country getCountry(int id) {
        Country result = null;
        try {
            JSONObject response = getResponse("countries/" + String.valueOf(id)).getJSONObject("country").getJSONObject("Country");
            String name = response.getString("name");
            result = new Country(id, name);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    static Address parseAddress(JSONObject jObj) {
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
            double latitude = 0;
            double longitude = 0;
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
            if(!jObj.isNull("latitude")){
                latitude = jObj.getDouble("latitude");
            }
            if(!jObj.isNull("longitude")){
                longitude = jObj.getDouble("longitude");
            }
            result = new Address(id, name, aL1, aL2, aL3, aL4, locality, region, zipCode, country);
            result.setLatitude(latitude);
            result.setLongitude(longitude);
        } catch (Exception e) {
            Log.d(DEBUG_TAG, e.toString(), e);
        }
        return result;
    }

    static OpeningHour parseOpeningHour(JSONObject jObj) {
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

    static Photo parsePhoto(JSONObject jObj) {
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

    static Promotion parsePromotion(JSONObject jObj) {
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
