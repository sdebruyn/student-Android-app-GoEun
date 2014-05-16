package be.muel.sa.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.muel.sa.R;
import be.muel.sa.entities.PlaceOfInterest;
import be.muel.sa.entities.Promotion;

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
        View itemView = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (itemView == null)
            itemView = inflater.inflate(R.layout.poiadapter_row, null);

        PlaceOfInterest placeOfInterest = getListPOIAdapter().get(position);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewPOI);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        TextView tvDescription = (TextView) itemView.findViewById(R.id.textViewDescription);

        tvTitle.setText(placeOfInterest.getName());
        tvDescription.setText(placeOfInterest.getType().toString().toLowerCase());

        if (placeOfInterest.getPhotos().size() > 0)
            imageView.setImageBitmap(placeOfInterest.getPhotos().get(0).getBitmap());

        return itemView;

    }

    public List<PlaceOfInterest> getListPOIAdapter() {
        return listPOIAdapter;
    }

    public void setListPOIAdapter(List<PlaceOfInterest> listPOIAdapter) {
        this.listPOIAdapter = listPOIAdapter;
    }
}
