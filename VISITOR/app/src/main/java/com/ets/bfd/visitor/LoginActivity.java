package com.ets.bfd.visitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.ets.bfd.visitor.activity.DashboardActivity;
import com.ets.bfd.visitor.activity.MainActivity;
import com.ets.bfd.visitor.activity.MyProfileActivity;
import com.ets.bfd.visitor.activity.OverNightTour;
import com.ets.bfd.visitor.models.PackagesModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.entity.User;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.MyProgressDialog;

import Interface.ResponseCallback;

public class LoginActivity extends AppCompatActivity {

    private MyProgressDialog progressDialog;
    private MaterialButton btnLogin;
    private TextInputEditText txtUserName, txtPassword;
    private CheckBox chkRememberMe;
    private MyPreference preferences;
    private AppService networkCall;
    private AppDatabase db;
    private String isOverNight = "false";
    private PackagesModel pacakgesObjectModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);

        getSupportActionBar().hide();
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        chkRememberMe = findViewById(R.id.chkRememberMe);
        // Instantiating MyPreferences(Sharedpreference)
        preferences = MyPreference.getPreferences(this);
        networkCall = new AppService(this);

        progressDialog = new MyProgressDialog(this);
        progressDialog.setMessage("Authenticating...");

        db = AppDatabase.getAppDatabase(this);

        // Set app default language
        App_Config.changeLanguage(App_Config.getCurrentLanguage(this), this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            isOverNight = extras.getString("mode");
            if(isOverNight.equalsIgnoreCase("overnight")){
                pacakgesObjectModel = (PackagesModel) getIntent().getSerializableExtra("pacakgesObject");
            }

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnLogin.startAnimation();
                if(!CommonUtils.isNetworkOnline(getApplicationContext())){
                    CommonUtils.showToastError( getApplicationContext(), getResources().getString(R.string.message_internet_con) );
                } else {
                    if(txtUserName.getText().toString().equalsIgnoreCase("")){
                        CommonUtils.showToastError( getApplicationContext(), getString(R.string.message_enter_user_name) );
                    } else if(txtPassword.getText().toString().equalsIgnoreCase("")){
                        CommonUtils.showToastError( getApplicationContext(), getString(R.string.message_enter_password) );
                    } else {
                        checkAuthentication();
                    }
                }

            }
        });

        // If rememberMeFlag found 1 in preference the redirect to dashboard
        String rememberMeFlag = preferences.getRememberMeFlag();

        if(rememberMeFlag.equalsIgnoreCase("1")){
            // Redirect to Dashboard after successful login
            startActivity(new Intent(this, MyProfileActivity.class));
            finish();
        }


    }


    public void checkAuthentication(){
        //Toast.makeText(this,DebugDB.getAddressLog(), Toast.LENGTH_LONG).show();
        progressDialog.show();
        User user = new User();
        user.setUsername( txtUserName.getText().toString().trim() );
        user.setPassword( txtPassword.getText().toString().trim() );

        networkCall.validateUser(user, new ResponseCallback<User>() {
            @Override
            public void onSuccess( User userdata ) {
                if( userdata.getCode().equalsIgnoreCase("200") ){
                    preferences.clearPreference();
                    if (chkRememberMe.isChecked()){
                        preferences.setRememberMeFlag("1");
                    }

                    preferences.setUserId(userdata.getUser_id());
                    preferences.setOperatorId(userdata.getOperator_id());
                    preferences.setExternalUserType(userdata.getExternal_user_type());
                    preferences.setuserNameEn(userdata.getUser_name_en());
                    preferences.setuserNameBn(userdata.getUser_name_bn());
                    preferences.setuserEmail(userdata.getUser_email());

                    preferences.setTotalPackage(userdata.getTotal_package());
                    preferences.setTotalRating(userdata.getTotal_rating());
                    preferences.setTotalReview(userdata.getTotal_review());
                    preferences.setTotalTrips(userdata.getTotal_trips());
                    preferences.setTotalTourist(userdata.getTotal_tourist());
                    preferences.setUserDesignationEn(userdata.getUser_designation_en());
                    preferences.setUserDesignationBn(userdata.getUser_designation_bn());

                    preferences.setUserImage(userdata.getUser_image());
                    preferences.setMobileNumber(userdata.getMobileNumber());
                    preferences.setToken(userdata.getApi_token());


                    if(isOverNight.equalsIgnoreCase("overnight")){
                        Intent newIntent = new Intent(getApplicationContext(), OverNightTour.class);
                        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        newIntent.putExtra("mode","overnight");
                        newIntent.putExtra("pacakgesObject",pacakgesObjectModel);
                        getApplicationContext().startActivity(newIntent);
                    }else if(isOverNight.equalsIgnoreCase("myprofile")){
                        startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
                    }else{
                        startActivity(new Intent( getApplicationContext(), MyProfileActivity.class ));
                        finish();
                    }


                    progressDialog.hide();

                } else if( userdata.getCode().equalsIgnoreCase("400") ){
                    CommonUtils.showToastError( getApplicationContext(), userdata.getMessage() );
                }else {
                    CommonUtils.showToastError( getApplicationContext(), "Sorry Something worng!" );
                }

                progressDialog.hide();

            }

            @Override
            public void onFail(Throwable th) {
                CommonUtils.showToastError( getApplicationContext(), th.getMessage() );
                progressDialog.hide();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}