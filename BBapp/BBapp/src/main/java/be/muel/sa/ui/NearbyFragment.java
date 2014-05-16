package be.muel.sa.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.muel.sa.R;
import be.muel.sa.data.NearbyPOITask;
import be.muel.sa.entities.PlaceOfInterest;

/**
 * Created by Samuel on 29/04/2014.
 */
public class NearbyFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String DEBUG_TAG = "BB_Nearby";

    private SwipeRefreshLayout swipeRefreshLayout;

    public LocationProviderActivity getLocationProviderActivity() {
        return locationProviderActivity;
    }

    public void setLocationProviderActivity(LocationProviderActivity locationProviderActivity) {
        this.locationProviderActivity = locationProviderActivity;
    }

    private LocationProviderActivity locationProviderActivity;

    public NearbyFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NearbyFragment newInstance(int sectionNumber, LocationProviderActivity locationProviderActivity) {
        NearbyFragment fragment = new NearbyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragment.setLocationProviderActivity(locationProviderActivity);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nearby, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.nearby_swipe_layout);
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });

        doRefresh();
    }

    private void doRefresh() {
        final View view = getView();

        NearbyPOITask nTask = new NearbyPOITask() {
            @Override
            protected void onPostExecute(List<PlaceOfInterest> placeOfInterests) {
                swipeRefreshLayout.setRefreshing(false);
                if(view != null){
                    ArrayAdapter<PlaceOfInterest> adapter = new CustomPOIAdapter(getActivity(), R.layout.poiadapter_row, placeOfInterests);
                    ListView lvNearby = (ListView) view.findViewById(R.id.lvNearby);
                    lvNearby.setAdapter(adapter);
                    lvNearby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            PlaceOfInterest poiAtPos = (PlaceOfInterest) adapterView.getItemAtPosition(i);
                            Uri uri = Uri.parse("geo:0,0?q=" + poiAtPos.getAddress().getLatitude() + "," + poiAtPos.getAddress().getLongitude() + "(" + poiAtPos.getName() + ")");
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        };

        swipeRefreshLayout.setRefreshing(true);
        Location location = getLocationProviderActivity().getLocationClient().getLastLocation();
        nTask.execute(location.getLatitude(), location.getLongitude());
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
