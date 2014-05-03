package be.muel.sa.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import be.muel.sa.R;
import be.muel.sa.data.ApiRequestTask;
import be.muel.sa.data.RequestType;
import be.muel.sa.entities.Information;
import be.muel.sa.entities.Room;

/**
 * Created by Samuel on 29/04/2014.
 */
public class RoomsFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public RoomsFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RoomsFragment newInstance(int sectionNumber) {
        RoomsFragment fragment = new RoomsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_rooms, container, false);

        ApiRequestTask roomsTask = new ApiRequestTask(){

            @Override
            protected void onPostExecute(Object o) {
                List<Room> listRooms = (List<Room>) o;

                ArrayAdapter<Room> adapter = new CustomRoomAdapter(getActivity(), R.layout.item_row, listRooms);
                    ListView lv = (ListView) rootView.findViewById(R.id.listView);
                    lv.setAdapter(adapter);
                }
        };
        roomsTask.execute(RequestType.ROOMS, null, null);


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
