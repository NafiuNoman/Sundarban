package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;

public class TourPermissionApplicationForm extends AppCompatActivity {


    private ActionBarDrawerToggle mDrawerToggol;
    private MyPreference preferences;
    private String lang = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_permission_application_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getResources().getString(R.string.app_full_name));
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, TourPermissionApplicationForm.this, R.id.drawer_layout, R.id.dashboard);

        preferences = MyPreference.getPreferences(this);
        lang = CommonUtils.getCurrentLanguage(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Hide navigation drawer when clicked on Left arrow in action bar
        if (mDrawerToggol.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}