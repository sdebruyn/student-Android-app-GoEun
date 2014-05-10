package be.muel.sa.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import be.muel.sa.R;
import be.muel.sa.data.ApiRequestTask;
import be.muel.sa.data.RequestType;
import be.muel.sa.data.URLRequestTask;
import be.muel.sa.entities.Room;

/**
 * Created by DaniyarTalipov on 30/04/14.
 */
public class CustomRoomAdapter extends ArrayAdapter<Room>{

    public CustomRoomAdapter(Context context, int resource,List<Room> listRooms) {
        super(context, resource,listRooms);
        setListRoomsAdapter(listRooms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (itemView == null)
            itemView = inflater.inflate(R.layout.item_row, null);

        final Room currentRoom = listRoomsAdapter.get(position);

        final ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewRoom);
        TextView txtTitel = (TextView) itemView.findViewById(R.id.textViewTitel);
        TextView txtType = (TextView) itemView.findViewById(R.id.textViewType);
        TextView txtPrijs = (TextView) itemView.findViewById(R.id.textViewPrijs);


        URLRequestTask URLTask = new URLRequestTask(){

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                try {
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };


        URLTask.execute(currentRoom.getPhotos().get(0).getLink());
        try {
            URLTask.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        //imageView.setImageBitmap(currentRoom.getPhotos().get(0).getBitmapLink());
        txtTitel.setText(currentRoom.getName());

        txtType.setText(currentRoom.getDescription());

        NumberFormat nm = NumberFormat.getNumberInstance();
        txtPrijs.setText(nm.format(currentRoom.getPrice())+"€ the night");

        return itemView;
    }

    private List<Room> listRoomsAdapter = null;

    public void setListRoomsAdapter(List<Room> listRoomsAdapter) {
        this.listRoomsAdapter = listRoomsAdapter;
    }


}
