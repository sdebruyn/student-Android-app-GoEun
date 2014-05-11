package be.muel.sa.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.muel.sa.R;

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
        if(swipeRefreshLayout != null)
            swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
