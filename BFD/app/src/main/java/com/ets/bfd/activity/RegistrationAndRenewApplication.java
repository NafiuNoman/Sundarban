package com.ets.bfd.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ets.bfd.R;
import com.ets.bfd.preference.MyPreference;
import com.ets.bfd.utilities.CommonUtils;
import com.ets.bfd.utilities.NavigationDrawer;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegistrationAndRenewApplication extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggol;
    private MyPreference preferences;
    private String lang = "en";

    Uri filePath;
    Bitmap bitmap;
    String key,picUrl;
//    TextView linkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_and_renew_application);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, RegistrationAndRenewApplication.this, R.id.drawer_layout,R.id.dashboard);

        preferences = MyPreference.getPreferences(this);
        lang = CommonUtils.getCurrentLanguage(this);


//        linkTextView = findViewById(R.id.design_developed);
//        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Hide navigation drawer when clicked on Left arrow in action bar
        if(mDrawerToggol.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void DocumentBrowseButtonClicked(View view) {

        Dexter.withContext(RegistrationAndRenewApplication.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");

                        startActivityForResult(Intent.createChooser(intent, "please select the app"), 1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();
    }

    public void vesselImageBrowseClicked(View view) {




        Dexter.withContext(RegistrationAndRenewApplication.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");

                        startActivityForResult(Intent.createChooser(intent, "please select the app"), 1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();
    }

    public void LicenseBrowseButtonClicked(View view) {


        Dexter.withContext(RegistrationAndRenewApplication.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");

                        startActivityForResult(Intent.createChooser(intent, "please select the app"), 1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);

//                profilePic.setImageBitmap(bitmap);
//                SendToStorage();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}