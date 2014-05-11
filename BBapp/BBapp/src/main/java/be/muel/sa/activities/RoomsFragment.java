package be.muel.sa.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import be.muel.sa.R;
import be.muel.sa.data.ApiRequestTask;
import be.muel.sa.data.RequestType;
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

    private SwipeRefreshLayout swipeRefreshLayout;

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
        View rootView = inflater.inflate(R.layout.fragment_rooms, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.room_swipe_layout);
        if(swipeRefreshLayout != null)
            swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiRequestTask roomsTask = new ApiRequestTask(){
            @Override
            protected void onPostExecute(Object o) {
                List<Room> listRooms = (List<Room>) o;

                if(view != null){
                    ArrayAdapter<Room> adapter = new CustomRoomAdapter(getActivity(), R.layout.roomadapater_row, listRooms);
                    ListView lv = (ListView) view.findViewById(R.id.listView);
                    lv.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };

        swipeRefreshLayout.setRefreshing(true);
        roomsTask.execute(RequestType.ROOMS, null, null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
