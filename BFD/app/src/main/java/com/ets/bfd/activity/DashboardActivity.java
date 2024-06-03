package com.ets.bfd.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import com.ets.bfd.R;
import com.ets.bfd.datasource.AppDatabase;

import com.ets.bfd.preference.MyPreference;
import com.ets.bfd.utilities.CommonUtils;
import com.ets.bfd.utilities.LocationManager;
import com.ets.bfd.utilities.NavigationDrawer;
import com.ets.bfd.utilities.UserPermission;


import Interface.CoordinateReceivedInterface;

public class DashboardActivity extends AppCompatActivity implements CoordinateReceivedInterface {
    private ActionBarDrawerToggle mDrawerToggol;
    private AppDatabase db;
    private LocationManager locationManager;
    private MyPreference preferences;
    private String lang = "en";


    TextView linkTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, DashboardActivity.this, R.id.drawer_layout,R.id.dashboard);
        // Taking user permission to take photo and user location
        UserPermission.requestAppPermission(DashboardActivity.this);
        //locationManager = new LocationManager(this, DashboardActivity.this);
        //locationManager.getLocation();
        preferences = MyPreference.getPreferences(this);
        lang = CommonUtils.getCurrentLanguage(this);


        linkTextView = findViewById(R.id.design_developed);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
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
}