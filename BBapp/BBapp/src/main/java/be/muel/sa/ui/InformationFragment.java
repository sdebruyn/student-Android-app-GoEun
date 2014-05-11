package be.muel.sa.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.muel.sa.R;
import be.muel.sa.data.ApiRequestTask;
import be.muel.sa.data.RequestType;
import be.muel.sa.entities.Information;

/**
 * Created by Samuel on 29/04/2014.
 */
public class InformationFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private SwipeRefreshLayout swipeRefreshLayout;

    public InformationFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static InformationFragment newInstance(int sectionNumber) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.information_swipe_layout);
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });

        doRefresh();
    }

    public void doRefresh() {
        final View view = getView();

        ApiRequestTask informationTask = new ApiRequestTask() {

            @Override
            protected void onPostExecute(Object o) {
                Information info = (Information) o;

                if (view != null) {
                    TextView vw = (TextView) view.findViewById(R.id.breakfastTextView);
                    vw.setText(info.getBreakfast());

                    vw = (TextView) view.findViewById(R.id.telephoneView);
                    vw.setText(info.getTelephone());

                    vw = (TextView) view.findViewById(R.id.cellPhoneView);
                    vw.setText(info.getCellPhone());

                    vw = (TextView) view.findViewById(R.id.emailView);
                    vw.setText(info.getEmail());

                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };
        swipeRefreshLayout.setRefreshing(true);
        informationTask.execute(RequestType.INFORMATION, null, null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
