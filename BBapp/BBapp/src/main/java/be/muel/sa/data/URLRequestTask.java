package be.muel.sa.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import be.muel.sa.R;
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
 * Created by DaniyarTalipov on 02/05/14.
 */
public class URLRequestTask extends AsyncTask<String,Void,Bitmap>{


    @Override
    protected Bitmap doInBackground(String... urls) {
        InputStream is = null;
        try{
            URL myFileUrl = new URL (urls[0]);
            HttpURLConnection conn =
                (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            is = (InputStream) myFileUrl.getContent();

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is);

    }
}
