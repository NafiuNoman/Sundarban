package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.adapters.BookingListAdapter;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.models.BookingPackageListModel;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.models.ResponseModel;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.LocationManager;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.UserPermission;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Interface.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mDrawerToggol;
    private AppDatabase db;
    private LocationManager locationManager;
    private MyPreference preferences;
    private String lang = "bn";
    private Context context;
    private Activity activity;
    private ProgressDialog prgDialog;
    private ApiInterface apiInterface;
    private AppService networkCall;
    Toolbar toolbar;
    TextView txtLoginUserName,txtLoginUserDesignation,txtLoginUserMobile,
            txtLoginUserEmail,totalTrips,totalReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        context = getApplicationContext();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setSubtitle(getResources().getText(R.string.over_night_form));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Toolbar :: Transparent
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);

        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, MyProfileActivity.this, R.id.drawer_layout,R.id.myProfile);
//        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        lang = CommonUtils.getCurrentLanguage( context );
        UserPermission.requestAppPermission(MyProfileActivity.this);

        //share preferences
        preferences = MyPreference.getPreferences(this);
        // Initialize service model
        networkCall = new AppService(this);
        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        // Instantiating progress bar
        prgDialog = new ProgressDialog(this);

        initializationALlField();

    }


    private void initializationALlField() {
        txtLoginUserName = findViewById(R.id.txtLoginUserName);
        txtLoginUserDesignation = findViewById(R.id.txtLoginUserDesignation);
        txtLoginUserMobile = findViewById(R.id.txtLoginUserMobile);
        txtLoginUserEmail = findViewById(R.id.txtLoginUserEmail);
        totalTrips = findViewById(R.id.totalTrips);
        totalReviews = findViewById(R.id.totalReviews);

        txtLoginUserName.setText(  (lang.equalsIgnoreCase("bn")) ? preferences.getuserNameBn() : preferences.getuserNameEn() );
        txtLoginUserDesignation.setText( (lang.equalsIgnoreCase("bn")) ? preferences.getUserDesignationBn() : preferences.getUserDesignationEn() );
        txtLoginUserMobile.setText( (lang.equalsIgnoreCase("bn")) ? CommonUtils.translateNumber(preferences.getMobileNumber(),"bn") : preferences.getuserNameEn() );
        txtLoginUserEmail.setText( preferences.getuserEmail() );
        totalTrips.setText( (lang.equalsIgnoreCase("bn")) ? CommonUtils.translateNumber(preferences.getTotalTrips(),"bn") : preferences.getTotalTrips() );
        totalReviews.setText( (lang.equalsIgnoreCase("bn")) ? CommonUtils.translateNumber(preferences.getTotalReview(),"bn") : preferences.getTotalReview() );

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Hide navigation drawer when clicked on Left arrow in action bar
        if(mDrawerToggol.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}