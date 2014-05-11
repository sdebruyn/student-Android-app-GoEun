package be.muel.sa.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import be.muel.sa.R;
import be.muel.sa.data.ApiRequestTask;
import be.muel.sa.data.RequestType;
import be.muel.sa.entities.Promotion;
import be.muel.sa.entities.Room;

/**
 * Created by Samuel on 29/04/2014.
 */
public class PromotionsFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private SwipeRefreshLayout swipeRefreshLayout;

    public PromotionsFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PromotionsFragment newInstance(int sectionNumber) {
        PromotionsFragment fragment = new PromotionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_promotions, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.promotion_swipe_layout);
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

        ApiRequestTask promotionsTask = new ApiRequestTask(){
            @Override
            protected void onPostExecute(Object o) {
                if(view != null){
                    List<Room> roomList =  (List<Room>) o;
                    Iterator<Room> itr = roomList.iterator();

                    List<Promotion> promotionsFromRoomsList = new LinkedList<Promotion>();
                    while(itr.hasNext()){
                        Room room = itr.next();
                        Iterator<Promotion> promItr = room.getPromotions().iterator();
                        while(promItr.hasNext()){
                            promotionsFromRoomsList.add(promItr.next());
                        }
                    }

                    ListView lvPromotions = (ListView) view.findViewById(R.id.lvPromotions);

                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };
        swipeRefreshLayout.setRefreshing(true);
        promotionsTask.execute(RequestType.ROOMS, null, null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
