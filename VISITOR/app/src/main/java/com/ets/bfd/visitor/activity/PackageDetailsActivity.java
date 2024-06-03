package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ets.bfd.visitor.LoginActivity;
import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.LocationManager;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.UserPermission;
import com.squareup.picasso.Picasso;

import Interface.ApiInterface;

public class PackageDetailsActivity extends AppCompatActivity {
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

    private PackagesModel pacakgesObjectModel;
    private String formMode = null;
    TextView oneratorName,packageTitle,packageDescription,dayNight,amountRangeFromTo;
    ImageView packageImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);

        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, PackageDetailsActivity.this, R.id.drawer_layout,R.id.idOverNight);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        lang = CommonUtils.getCurrentLanguage( context );
        UserPermission.requestAppPermission(PackageDetailsActivity.this);

        //share preferences
        preferences = MyPreference.getPreferences(this);
        // Initialize service model
        networkCall = new AppService(this);
        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        // Instantiating progress bar
        prgDialog = new ProgressDialog(this);

        //initialize field
        oneratorName = (TextView) findViewById(R.id.oneratorName);
        packageTitle = (TextView) findViewById(R.id.packageTitle);
        packageDescription = (TextView) findViewById(R.id.packageDescription);
        packageImage = (ImageView) findViewById(R.id.packageImage);
        dayNight = (TextView) findViewById(R.id.dayNight);
        amountRangeFromTo = (TextView) findViewById(R.id.amountRangeFromTo);

        //for edit mode
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            formMode = extras.getString("mode");
            pacakgesObjectModel = (PackagesModel) getIntent().getSerializableExtra("pacakgesObject");

            String getTitle = pacakgesObjectModel.getTitle_en();
            if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
                if(pacakgesObjectModel.getTitle_bn() != null){
                    getTitle = pacakgesObjectModel.getTitle_bn();
                }
            }

            String getOrganizationName = pacakgesObjectModel.getOrganization_name_en();
            if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
                if(!pacakgesObjectModel.getOrganization_name_bn().equalsIgnoreCase("")){
                    getOrganizationName = pacakgesObjectModel.getOrganization_name_bn();
                }
            }

            String getDescription = pacakgesObjectModel.getDescription_en();
            if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
                if(pacakgesObjectModel.getDescription_bn() != null){
                    getDescription = pacakgesObjectModel.getDescription_bn();
                }
            }



            String viewDayNight = pacakgesObjectModel.getDuration_day() +" "+context.getString(R.string.day) +" - "+pacakgesObjectModel.getDuration_night() +" "+ context.getString(R.string.night);
            String viewAmountFromTo = pacakgesObjectModel.getPrice_start_from() +" - "+pacakgesObjectModel.getPrice_up_to();

            if(CommonUtils.getCurrentLanguage( context ).equalsIgnoreCase("bn")){
                viewDayNight = CommonUtils.translateNumber(pacakgesObjectModel.getDuration_day(),"bn") +" "+context.getString(R.string.day) +" - "+pacakgesObjectModel.getDuration_night() +" "+ context.getString(R.string.night);
                viewAmountFromTo = CommonUtils.translateNumber(pacakgesObjectModel.getPrice_start_from(),"bn") +" - "+ CommonUtils.translateNumber(pacakgesObjectModel.getPrice_up_to(),"bn") ;
            }

            packageTitle.setText( getTitle );
            oneratorName.setText( getOrganizationName );
            packageDescription.setText(getDescription);
            dayNight.setText("⌚ "+viewDayNight);
            amountRangeFromTo.setText("৳ "+viewAmountFromTo);

            if(pacakgesObjectModel.getPackage_image() != null ){
                Uri imgUri = null;
                if(pacakgesObjectModel.getPackage_image() !=null) {
                    imgUri = Uri.parse(App_Config.BASE_URL_FOR_ONLY_IMAGE + pacakgesObjectModel.getPackage_image());
                }
                Picasso.with(context)
//                .load("https://i.picsum.photos/id/1/5616/3744.jpg?hmac=kKHwwU8s46oNettHKwJ24qOlIAsWN9d2TtsXDoCWWsQ")
                        .load(imgUri)
                        .placeholder(R.drawable.cover_for_form)
                        .error(R.drawable.cover_for_form)
                        .into(packageImage);
            }else{
                Uri imgUri = Uri.parse("android.resource://" + context.getPackageName() + "/drawable/" + "cover_for_form");
                packageImage.setImageURI(imgUri);
            }

        }
    }

    public void openOverNightTourForm(View view) {
        // If rememberMeFlag found 1 in preference the redirect to dashboard
        String rememberMeFlag = preferences.getRememberMeFlag();
        if(rememberMeFlag.equalsIgnoreCase("1")){
            Intent newIntent = new Intent(context, OverNightTour.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.putExtra("mode","view");
            newIntent.putExtra("pacakgesObject",pacakgesObjectModel);
            context.startActivity(newIntent);
            finish();
        }else{
            CommonUtils.showToastError(context,getString(R.string.have_to_login_as_a_visitor));

            Intent newIntent = new Intent(context, LoginActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.putExtra("mode","overnight");
            newIntent.putExtra("pacakgesObject",pacakgesObjectModel);
            context.startActivity(newIntent);
            finish();
        }


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
        Intent intent = new Intent(getApplicationContext(), AllPackageList.class);
        startActivity(intent);
        super.onBackPressed();
    }

}