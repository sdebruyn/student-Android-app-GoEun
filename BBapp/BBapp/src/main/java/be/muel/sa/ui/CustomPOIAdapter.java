package be.muel.sa.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import be.muel.sa.entities.PlaceOfInterest;

/**
 * Created by DaniyarTalipov on 13/05/14.
 */
public class CustomPOIAdapter extends ArrayAdapter{


    private List<PlaceOfInterest> listPOIAdapter = null;

    public CustomPOIAdapter(Context context, int resource, List<PlaceOfInterest> listPOIAdapter) {
        super(context, resource, listPOIAdapter);
        setListPOIAdapter(listPOIAdapter);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }


    public List<PlaceOfInterest> getListPOIAdapter() {
        return listPOIAdapter;
    }

    public void setListPOIAdapter(List<PlaceOfInterest> listPOIAdapter) {
        this.listPOIAdapter = listPOIAdapter;
    }
}
