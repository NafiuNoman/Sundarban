package com.ets.bfd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.ets.bfd.LoginActivity;
import com.ets.bfd.R;
import com.ets.bfd.datasource.AppDatabase;
import com.ets.bfd.entity.CheckInCheckOut;
import com.ets.bfd.entity.User;
import com.ets.bfd.preference.MyPreference;
import com.ets.bfd.services.AppService;
import com.ets.bfd.utilities.CommonUtils;
import com.ets.bfd.utilities.LocationManager;
import com.ets.bfd.utilities.MyProgressDialog;
import com.ets.bfd.utilities.NavigationDrawer;
import com.ets.bfd.utilities.UserPermission;
import com.google.zxing.Result;

import Interface.ResponseCallback;

public class QRCodeScannerActivity extends AppCompatActivity {

    private MyProgressDialog progressDialog;
    private CodeScanner mCodeScanner;
    private AppService networkCall;

    private ActionBarDrawerToggle mDrawerToggol;
    private AppDatabase db;
    private LocationManager locationManager;
    private MyPreference preferences;
    private String lang = "en";
    private TextView scanner_qr_code_view;
    CodeScannerView scannerView;
    Button btnCheckInCheckOut,btnScanQRCode;
    LinearLayout layoutBtnForCheckAndScanQrCode;
    String oneDayTourId = "";
    String parameterId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, QRCodeScannerActivity.this, R.id.drawer_layout,R.id.dashboard);
        // Taking user permission to take photo and user location
//        UserPermission.requestAppPermission(QRCodeScannerActivity.this);
        //locationManager = new LocationManager(this, QRCodeScannerActivity.this);
        //locationManager.getLocation();
        preferences = MyPreference.getPreferences(this);
        lang = CommonUtils.getCurrentLanguage(this);
        networkCall = new AppService(this);
        progressDialog = new MyProgressDialog(this);

        scannerView = findViewById(R.id.scanner_view);
        scanner_qr_code_view = findViewById(R.id.scanner_qr_code_view);
        btnCheckInCheckOut = findViewById(R.id.btnCheckInCheckOut);
        btnScanQRCode = findViewById(R.id.btnScanQRCode);
        layoutBtnForCheckAndScanQrCode = findViewById(R.id.layoutBtnForCheckAndScanQrCode);
        layoutBtnForCheckAndScanQrCode.setVisibility(View.GONE);

        initializeQRCodeScanner();

        btnCheckInCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInCheckOut();
            }
        });

        btnScanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneDayTourId = "";
                parameterId = "";
                layoutBtnForCheckAndScanQrCode.setVisibility(View.GONE);
                scannerView.setVisibility(View.VISIBLE);
                scanner_qr_code_view.setText("Please Wait a few second...");
            }
        });

    }


    private void initializeQRCodeScanner(){
        if (ContextCompat.checkSelfPermission(QRCodeScannerActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(QRCodeScannerActivity.this, new String[] {Manifest.permission.CAMERA}, 123);
        } else {
            startScanning();
        }
    }

    private void startScanning() {
//        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(QRCodeScannerActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                        scanner_qr_code_view.setText(result.getText());
                        String currentString = result.getText();
                        String[] separated = currentString.split("/");
//                        separated.length == 7
                        if (separated != null && 6 <= separated.length ) {
                            oneDayTourId = separated[5];
                        }

                        if (separated != null && 7 <= separated.length ) {
                            parameterId = separated[6];
                        }

                        scannerView.setVisibility(View.GONE);
                        layoutBtnForCheckAndScanQrCode.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show();
                startScanning();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(mCodeScanner != null) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    protected void onPause() {
        if(mCodeScanner != null) {
            mCodeScanner.releaseResources();
        }
        super.onPause();
    }


    public void checkInCheckOut(){

        if(oneDayTourId.equalsIgnoreCase("") || parameterId.equalsIgnoreCase("")){
            CommonUtils.showToastError( getApplicationContext(), "Sorry Invalid Ticket" );
            scanner_qr_code_view.setText("Sorry Invalid Ticket");
            scanner_qr_code_view.setTextColor(getResources().getColor(R.color.colorRed));
            return;
        }

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        CheckInCheckOut checkInCheckOut = new CheckInCheckOut();
        checkInCheckOut.setUser_id( preferences.getUserId().trim() );
        checkInCheckOut.setOffice_id( preferences.getOfficeId().trim() );
        checkInCheckOut.setSpot_id( preferences.getSpotId().trim() );
        checkInCheckOut.setOne_day_tour_id( oneDayTourId.trim() );
        checkInCheckOut.setParameter_id( parameterId.trim() );

        networkCall.getTicketCheckInCheckOutValidity(checkInCheckOut, new ResponseCallback<CheckInCheckOut>() {
            @Override
            public void onSuccess( CheckInCheckOut checkInCheckOutdata ) {
                if( checkInCheckOutdata.getCode().equalsIgnoreCase("200") ){

                    CommonUtils.showToastSuccess( getApplicationContext(), checkInCheckOutdata.getMessage() );
                    scanner_qr_code_view.setText(checkInCheckOutdata.getMessage());
                    scanner_qr_code_view.setTextColor(getResources().getColor(R.color.colorGreen));

                    progressDialog.hide();

                }else {
                    CommonUtils.showToastError( getApplicationContext(), checkInCheckOutdata.getMessage() );
                    scanner_qr_code_view.setText(checkInCheckOutdata.getMessage());
                    scanner_qr_code_view.setTextColor(getResources().getColor(R.color.colorRed));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Hide navigation drawer when clicked on Left arrow in action bar
        if(mDrawerToggol.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
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