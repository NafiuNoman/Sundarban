package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ets.bfd.visitor.LoginActivity;
import com.ets.bfd.visitor.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.LocationManager;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.UserPermission;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import Interface.CoordinateReceivedInterface;

import static com.ets.bfd.visitor.utilities.NavigationDrawer.drawerLayout;

public class MainActivity extends AppCompatActivity implements CoordinateReceivedInterface {
    private ActionBarDrawerToggle mDrawerToggol;
    private AppDatabase db;
    private LocationManager locationManager;
    private MyPreference preference;
    private String lang = "bn";
    private Context context;

    private static final int REQ_CODE_VERSION_UPDATE = 530;
    private AppUpdateManager appUpdateManager;
    private InstallStateUpdatedListener installStateUpdatedListener;
    private ConstraintLayout layoutForMyProfile,layoutForMyTicket;

    TextView linkTextView;
    SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = MyPreference.getPreferences(this);

        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, MainActivity.this, R.id.drawer_layout,R.id.btnHome);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        lang = CommonUtils.getCurrentLanguage( context );

        UserPermission.requestAppPermission(MainActivity.this);

        linkTextView = findViewById(R.id.design_developed);
        layoutForMyProfile = findViewById(R.id.layoutForMyProfile);
        layoutForMyTicket = findViewById(R.id.layoutForMyTicket);

        checkForAppUpdate();

        layoutForMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rememberMeFlag = preference.getRememberMeFlag();
                if(rememberMeFlag.equalsIgnoreCase("1")){
                    startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
//                    finish();
                }else{
                    CommonUtils.showToastError(context,getString(R.string.have_to_login_as_a_visitor));
//                    Intent newIntent = new Intent(context, LoginActivity.class);
//                    newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    newIntent.putExtra("mode","myprofile");
//                    context.startActivity(newIntent);
//                    finish();
                }


            }
        });

        layoutForMyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyTicketActivity.class));
            }
        });

    }



    public void onClick(View view) {
        int id = view.getId();
        switch (id){
//            case R.id.btnPublicAfs:
//                startActivity(new Intent(getApplicationContext(), PublicSearchAfsActivity.class));
//                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkNewAppVersionState();
    }

    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {

            case REQ_CODE_VERSION_UPDATE:
                if (resultCode != RESULT_OK) { //RESULT_OK / RESULT_CANCELED / RESULT_IN_APP_UPDATE_FAILED
                   CommonUtils.showToastWarning(context,"Update flow failed! Result code: " + resultCode);

                    // If the update is cancelled or fails,
                    // you can request to start the update again.
                    unregisterInstallStateUpdListener();
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterInstallStateUpdListener();
        super.onDestroy();
    }


    private void checkForAppUpdate() {
        // Creates instance of the manager.
        appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        // Create a listener to track request state updates.
        installStateUpdatedListener = new InstallStateUpdatedListener() {
            @Override
            public void onStateUpdate(InstallState installState) {
                // Show module progress, log state, or install the update.
                if (installState.installStatus() == InstallStatus.DOWNLOADED)
                    // After the update is downloaded, show a notification
                    // and request user confirmation to restart the app.
                    popupSnackbarForCompleteUpdateAndUnregister();
            }
        };

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                // Request the update.
                if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {

                    // Before starting an update, register a listener for updates.
                    appUpdateManager.registerListener(installStateUpdatedListener);
                    // Start an update.
                    startAppUpdateFlexible(appUpdateInfo);
                } else if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE) ) {
                    // Start an update.
                    startAppUpdateImmediate(appUpdateInfo);
                }
            }
        });
    }

    private void startAppUpdateImmediate(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    // The current activity making the update request.
                    this,
                    // Include a request code to later monitor this update request.
                    MainActivity.REQ_CODE_VERSION_UPDATE);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    private void startAppUpdateFlexible(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.FLEXIBLE,
                    // The current activity making the update request.
                    this,
                    // Include a request code to later monitor this update request.
                    MainActivity.REQ_CODE_VERSION_UPDATE);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
            unregisterInstallStateUpdListener();
        }
    }

    /**
     * Displays the snackbar notification and call to action.
     * Needed only for Flexible app update
     */
    private void popupSnackbarForCompleteUpdateAndUnregister() {
        Snackbar snackbar =
                Snackbar.make(drawerLayout, getString(R.string.update_app_version), Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.action_update, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();

        unregisterInstallStateUpdListener();
    }

    /**
     * Checks that the update is not stalled during 'onResume()'.
     * However, you should execute this check at all app entry points.
     */
    private void checkNewAppVersionState() {
        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        appUpdateInfo -> {
                            //FLEXIBLE:
                            // If the update is downloaded but not installed,
                            // notify the user to complete the update.
                            if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                                popupSnackbarForCompleteUpdateAndUnregister();
                            }

                            //IMMEDIATE:
                            if (appUpdateInfo.updateAvailability()
                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                                // If an in-app update is already running, resume the update.
                                startAppUpdateImmediate(appUpdateInfo);
                            }
                        });

    }

    /**
     * Needed only for FLEXIBLE update
     */
    private void unregisterInstallStateUpdListener() {
        if (appUpdateManager != null && installStateUpdatedListener != null)
            appUpdateManager.unregisterListener(installStateUpdatedListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Hide navigation drawer when clicked on Left arrow in action bar
        if(mDrawerToggol.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onReceivedLatLong(String latitude, String longitude) {

    }

    public void tourTypeButtonClicked(View view) {

       switch (view.getId())
       {
           case R.id.idOneDayTourBtn:
               startActivity(new Intent(this,OneDayTourTicket.class));
               break;
           case R.id.idOverNightTourBtn:
               startActivity(new Intent(this,AllPackageList.class));
               break;
           default:
               Toast.makeText(MainActivity.this, "Select tour type", Toast.LENGTH_SHORT).show();
       }

    }







}