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
import be.muel.sa.entities.Promotion;

/**
 * Created by DaniyarTalipov on 13/05/14.
 */
public class CustomPromotionAdapter extends ArrayAdapter {

    private List<Promotion> listPromotionsAdapter = null;

    public CustomPromotionAdapter(Context context, int resource, List<Promotion> listPromotionsAdapter) {
        super(context, resource, listPromotionsAdapter);
        setListPromotionsAdapter(listPromotionsAdapter);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (itemView == null)
            itemView = inflater.inflate(R.layout.roomadapater_row, null);

        Promotion currentPromotion = getListPromotionsAdapter().get(position);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewPromoRoom);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        TextView tvDescription = (TextView) itemView.findViewById(R.id.textViewDescription);

        tvTitle.setText(currentPromotion.getRoom().getName());
        tvDescription.setText(currentPromotion.getDescription());

        if (currentPromotion.getPhotos().size() > 0)
            imageView.setImageBitmap(currentPromotion.getPhotos().get(0).getBitmap());

        return itemView;
    }


    public List<Promotion> getListPromotionsAdapter() {
        return listPromotionsAdapter;
    }

    public void setListPromotionsAdapter(List<Promotion> listPromotionsAdapter) {
        this.listPromotionsAdapter = listPromotionsAdapter;
    }
}
