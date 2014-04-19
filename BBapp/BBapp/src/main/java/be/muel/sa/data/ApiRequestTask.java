package be.muel.sa.data;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import be.muel.sa.entities.Information;

/**
* Created by Samuel on 19/04/2014.
*/
public class ApiRequestTask extends AsyncTask<RequestType, Void, Object> {

    @Override
    protected Object doInBackground(RequestType... requestTypes) {
        switch (requestTypes[0]){
            case INFORMATION:
            default:
                return getInformation();
        }
    }

    private static final String baseUrl = "http://mi4.sa.muel.be/";
    private static final AndroidHttpClient client = AndroidHttpClient.newInstance("Android BBApp");

    private String getAbsoluteUrl(String relativeUrl){
        return baseUrl + relativeUrl;
    }

    private Information getInformation(){
        JSONObject response = getResponse("information");
        return null;
    }

    private JSONObject getResponse(String relativeUrl){
        HttpGet request = new HttpGet(getAbsoluteUrl(relativeUrl));
        request.addHeader("Accept", "application/json, text/json");
        request.addHeader("Accept-Charset", "utf-8");
        try {
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code >= 300)
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
