package com.ets.bfd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.ets.bfd.activity.DashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ets.bfd.datasource.AppDatabase;
import com.ets.bfd.entity.User;
import com.ets.bfd.preference.MyPreference;
import com.ets.bfd.services.AppService;
import com.ets.bfd.utilities.App_Config;
import com.ets.bfd.utilities.CommonUtils;
import com.ets.bfd.utilities.MyProgressDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import Interface.ResponseCallback;

public class LoginActivity extends AppCompatActivity {

    private MyProgressDialog progressDialog;
    private MaterialButton btnLogin;
    private TextInputEditText txtUserName, txtPassword;
    private CheckBox chkRememberMe;
    private MyPreference preferences;
    private AppService networkCall;
    private AppDatabase db;

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
            startActivity(new Intent(this, DashboardActivity.class));
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
                    preferences.setuserNameEn(userdata.getUser_name_en());
                    preferences.setuserNameBn(userdata.getUser_name_bn());
                    preferences.setuserEmail(userdata.getUser_email());
                    preferences.setUserImage(userdata.getUser_image());
                    preferences.setMobileNumber(userdata.getMobileNumber());
                    preferences.setOfficeId(userdata.getOffice_id());
                    preferences.setOfficeNameEn(userdata.getOffice_name_en());
                    preferences.setOfficeNameBn(userdata.getOffice_name_bn());
                    preferences.setSpotId(userdata.getSpot_id());
                    preferences.setSpotNameEn(userdata.getSpot_name_en());
                    preferences.setSpotNameBn(userdata.getSpot_name_bn());

                    preferences.setToken(userdata.getApi_token());

                    startActivity(new Intent( getApplicationContext(), DashboardActivity.class ));
                    finish();

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
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

}