package be.muel.sa.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
public class WelcomeFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public WelcomeFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static WelcomeFragment newInstance(int sectionNumber) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        ApiRequestTask informationTask = new ApiRequestTask(){

            @Override
            protected void onPostExecute(Object o) {
                Information info = (Information) o;
                TextView vw = (TextView) rootView.findViewById(R.id.descriptionView);
                vw.setText(info.getDescription());
            }

        };
        informationTask.execute(RequestType.INFORMATION, null, null);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
