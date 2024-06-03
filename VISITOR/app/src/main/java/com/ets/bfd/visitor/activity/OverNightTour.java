package com.ets.bfd.visitor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.entity.User;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.models.PostBookingData;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.LocationManager;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.UserPermission;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import Interface.ApiInterface;
import Interface.ResponseCallback;

public class OverNightTour extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
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

    TextInputLayout idPackageFormAdultsCountLayout, childLayout, calendarLayout, phoneLayout,idPackageFormNameLayout;
    TextInputEditText idPackageFormAdultsCount, childCount, phoneNumber, tourDate,idPackageFormVisitorName;

    TextView oneratorName,packageTitle,dayNight,amountRangeFromTo;
    ImageView packageImage;

    private String isOverNight = "false";
    private PackagesModel pacakgesObjectModel;
    private String packageId = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_night_tour);

        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getResources().getText(R.string.over_night_form));
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, OverNightTour.this, R.id.drawer_layout,R.id.idOverNight);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        lang = CommonUtils.getCurrentLanguage( context );
        UserPermission.requestAppPermission(OverNightTour.this);

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

        idPackageFormAdultsCountLayout = findViewById(R.id.idPackageFormAdultsCountLayout);
        childLayout = findViewById(R.id.idPackageFormChildCountLayout);
        calendarLayout = findViewById(R.id.idPackageFormCalendarLayout);
        phoneLayout = findViewById(R.id.idPackageFormPhoneLayout);
        idPackageFormNameLayout = findViewById(R.id.idPackageFormNameLayout);

        idPackageFormAdultsCount = findViewById(R.id.idPackageFormAdultsCount);
        childCount = findViewById(R.id.idPackageFormChildCount);
        tourDate = findViewById(R.id.idPackageFormDate);
        phoneNumber = findViewById(R.id.idPackageFormPhoneNumber);
        idPackageFormVisitorName = findViewById(R.id.idPackageFormVisitorName);


        oneratorName = (TextView) findViewById(R.id.oneratorName);
        packageTitle = (TextView) findViewById(R.id.packageTitle);
        packageImage = (ImageView) findViewById(R.id.packageImage);
        dayNight = (TextView) findViewById(R.id.dayNight);
        amountRangeFromTo = (TextView) findViewById(R.id.amountRangeFromTo);


        setTextChangedListener(idPackageFormAdultsCount, idPackageFormAdultsCountLayout);
        setTextChangedListener(childCount, childLayout);
        setTextChangedListener(tourDate, calendarLayout);
        setTextChangedListener(phoneNumber, phoneLayout);

        // Adding date picker listener
        CommonUtils.addDatePickerListener(calendarLayout, OverNightTour.this, tourDate);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            isOverNight = extras.getString("mode");
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

            packageId = pacakgesObjectModel.getId();
            packageTitle.setText( getTitle );
            oneratorName.setText( getOrganizationName );
            dayNight.setText("⌚ "+viewDayNight);
            amountRangeFromTo.setText("৳ "+viewAmountFromTo);
            phoneNumber.setText(preferences.getMobileNumber());
            idPackageFormVisitorName.setText(preferences.getuserNameEn());

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


    private boolean validatePackageForm() {

        boolean isValidate = true;
        String adult, child, date, phone,name;
        adult = idPackageFormAdultsCount.getText().toString().trim();
        child = childCount.getText().toString().trim();
        date = tourDate.getText().toString().trim();
        phone = phoneNumber.getText().toString().trim();
        name = idPackageFormVisitorName.getText().toString().trim();


        if (adult.isEmpty())
        {
            CommonUtils.setError(idPackageFormAdultsCountLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        }else if(Integer.parseInt(adult) <= 0){
            CommonUtils.setError(idPackageFormAdultsCountLayout, getString(R.string.message_field_mandatory));
            isValidate = false;
        }else {
            CommonUtils.setError(idPackageFormAdultsCountLayout, "");

        }

        if (name.isEmpty())
        {
            CommonUtils.setError(idPackageFormNameLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        }else {
            CommonUtils.setError(idPackageFormNameLayout, "");

        }

        if (child.isEmpty())
        {
            CommonUtils.setError(childLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        }
        else if(Integer.parseInt(childCount.getText().toString().trim()) <= 0) {
            CommonUtils.setError(childLayout, getString(R.string.message_field_mandatory));
            isValidate = false;
        }
        else {
            CommonUtils.setError(childLayout, "");
        }


        if (date.isEmpty())
        {
            CommonUtils.setError(calendarLayout, getString(R.string.message_field_mandatory));
            isValidate = false;

        }else {
            CommonUtils.setError(calendarLayout, "");

        } if (phone.isEmpty()) {
            CommonUtils.setError(phoneLayout, getString(R.string.message_field_mandatory));

            isValidate = false;
        } else if (phone.length() != 11) {
            CommonUtils.setError(phoneLayout, getString(R.string.warning_mobile_length));

        } else {
            CommonUtils.setError(phoneLayout, "");
        }


        return isValidate;
    }


    public void bookNowButtonClicked(View view) {

        if (validatePackageForm())
        {
            prgDialog.setMessage("Please wait data submitting...");
            prgDialog.show();
            PostBookingData postBookingData = new PostBookingData();
            postBookingData.setVisitor_id( preferences.getUserId() );
            postBookingData.setVisitor_name( idPackageFormVisitorName.getText().toString().trim() );
            postBookingData.setVisitor_mobile( phoneNumber.getText().toString().trim() );
            postBookingData.setVisitor_date( tourDate.getText().toString().trim() );
            postBookingData.setNumber_of_adult( idPackageFormAdultsCount.getText().toString().trim() );
            postBookingData.setNumber_of_child( childCount.getText().toString().trim() );
            postBookingData.setPackage_id( packageId );

            networkCall.postBookingData(postBookingData, new ResponseCallback<PostBookingData>() {
                @Override
                public void onSuccess( PostBookingData bookingData ) {
                    if( bookingData.getCode().equalsIgnoreCase("200") ){

                        CommonUtils.showToastSuccess( getApplicationContext(), bookingData.getMessage() );

                        startActivity(new Intent( getApplicationContext(), DashboardActivity.class ));
                        finish();

                        prgDialog.hide();

                    } else if( bookingData.getCode().equalsIgnoreCase("400") ){
                        CommonUtils.showToastError( getApplicationContext(), bookingData.getMessage() );
                    }else {
                        CommonUtils.showToastError( getApplicationContext(), "Sorry Something worng!" );
                    }

                    prgDialog.hide();

                }

                @Override
                public void onFail(Throwable th) {
                    CommonUtils.showToastError( getApplicationContext(), th.getMessage() );
                    prgDialog.hide();
                }
            });
        }


    }

    public static void setTextChangedListener(EditText editText, final TextInputLayout layout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    layout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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