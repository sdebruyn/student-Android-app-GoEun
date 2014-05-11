package be.muel.sa.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

import be.muel.sa.R;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationProviderActivity {

    private static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private static final String DEBUG_TAG = "BBAPP_Main";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    public LocationClient getLocationClient() {
        return locationClient;
    }

    public void setLocationClient(LocationClient locationClient) {
        this.locationClient = locationClient;
    }

    private LocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        setLocationClient(new LocationClient(this, this, this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocationClient().connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLocationClient().disconnect();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment newContent = null;
        int fragmentNo = position + 1;
        switch (fragmentNo) {
            default:
            case 1:
                newContent = WelcomeFragment.newInstance(fragmentNo);
                break;
            case 2:
                newContent = PromotionsFragment.newInstance(fragmentNo);
                break;
            case 3:
                newContent = RoomsFragment.newInstance(fragmentNo);
                break;
            case 4:
                newContent = InformationFragment.newInstance(fragmentNo);
                break;
            case 5:
                newContent = NearbyFragment.newInstance(fragmentNo, this);
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, newContent)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_welcome);
                break;
            case 2:
                mTitle = getString(R.string.title_promo);
                break;
            case 3:
                mTitle = getString(R.string.title_rooms);
                break;
            case 4:
                mTitle = getString(R.string.title_information);
                break;
            case 5:
                mTitle = getString(R.string.title_nearby);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForGooglePlayServices();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(DEBUG_TAG, "Location Services connected.");
    }

    @Override
    public void onDisconnected() {
        Log.d(DEBUG_TAG, "Location Services disconnected.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(DEBUG_TAG, "Location Services connection failed.");

        if(connectionResult.hasResolution()){
            try{
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            }catch(IntentSender.SendIntentException e){
                Log.e(DEBUG_TAG, "Google Play Services resolution intent failed.", e);
            }
        }else{
            showGooglePlayServicesErrorDialog(connectionResult.getErrorCode());
        }
    }

    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    public boolean checkForGooglePlayServices() {
        int available = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (available == ConnectionResult.SUCCESS) {
            Log.d(DEBUG_TAG, "Google Play Services is available.");
            return true;
        } else {
            showGooglePlayServicesErrorDialog(available);
        }
        return false;
    }

    public void showGooglePlayServicesErrorDialog(int errorCode){
        Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                errorCode,
                this,
                CONNECTION_FAILURE_RESOLUTION_REQUEST
        );
        if(errorDialog != null){
            ErrorDialogFragment errorFragment = new ErrorDialogFragment();
            errorFragment.setDialog(errorDialog);
            errorFragment.show(getFragmentManager(), DEBUG_TAG);
        }
    }


}
