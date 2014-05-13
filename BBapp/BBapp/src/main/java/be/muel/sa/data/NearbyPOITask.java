package be.muel.sa.data;

import android.os.AsyncTask;

import java.util.List;

import be.muel.sa.entities.PlaceOfInterest;

/**
 * Created by Samuel on 13/05/2014.
 */
public class NearbyPOITask extends AsyncTask<Double, Void, List<PlaceOfInterest>>{

    @Override
    protected List<PlaceOfInterest> doInBackground(Double... doubles) {
        return null;
    }
}
