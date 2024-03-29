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
import be.muel.sa.entities.Room;

/**
 * Created by DaniyarTalipov on 30/04/14.
 */
public class CustomRoomAdapter extends ArrayAdapter<Room> {

    private List<Room> listRoomsAdapter = null;

    public CustomRoomAdapter(Context context, int resource, List<Room> listRooms) {
        super(context, resource, listRooms);
        setListRoomsAdapter(listRooms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (itemView == null)
            itemView = inflater.inflate(R.layout.roomadapater_row, null);

        Room currentRoom = getListRoomsAdapter().get(position);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewRoom);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        TextView tvType = (TextView) itemView.findViewById(R.id.textViewDescription);
        TextView tvPrice = (TextView) itemView.findViewById(R.id.textViewPrice);

        tvTitle.setText(currentRoom.getName());
        tvType.setText(currentRoom.getDescription());
        tvPrice.setText(getContext().getString(R.string.price_per_night, currentRoom.getPrice()));

        if (currentRoom.getPhotos().size() > 0)
            imageView.setImageBitmap(currentRoom.getPhotos().get(0).getBitmap());

        return itemView;
    }

    public List<Room> getListRoomsAdapter() {
        return listRoomsAdapter;
    }

    public void setListRoomsAdapter(List<Room> listRoomsAdapter) {
        this.listRoomsAdapter = listRoomsAdapter;
    }


}
