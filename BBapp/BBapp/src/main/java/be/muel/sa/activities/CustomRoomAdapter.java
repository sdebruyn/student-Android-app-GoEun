package be.muel.sa.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;

import be.muel.sa.R;
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
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        if(itemView == null)
            itemView = inflater.inflate( R.layout.item_row, null );

        Room currentRoom = listRoomsAdapter.get(position);

        ImageView imgView = (ImageView) itemView.findViewById(R.id.item_icon);
        //imgView.setImageURI();

        TextView txtTitel = (TextView) itemView.findViewById(R.id.textViewTitel);
        txtTitel.setText(currentRoom.getName());

        TextView txtType = (TextView) itemView.findViewById(R.id.textViewType);
        txtType.setText(currentRoom.getDescription());

        TextView txtPrijs = (TextView) itemView.findViewById(R.id.textViewPrijs);
        NumberFormat nm = NumberFormat.getNumberInstance();
        txtPrijs.setText(nm.format(currentRoom.getPrice())+"€ the night");

        return itemView;
    }

    private List<Room> listRoomsAdapter = null;

    public void setListRoomsAdapter(List<Room> listRoomsAdapter) {
        this.listRoomsAdapter = listRoomsAdapter;
    }
}
