package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.adapters.TicketItemRecyclerAdapter;
import com.ets.bfd.visitor.entity.User;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.MainSendModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.ParentSendOneDayTourModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayGuardModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.SendOneDayTouristModel;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.MyProgressDialog;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Interface.ResponseCallback;

public class ConfirmTicket extends AppCompatActivity {

    //    public static double allTicketFee = 0;
    public static double allTicketFee;

    double totalRevenue = 0;
    double totalGuardRevenue = 0;
    double totalTouristRevenue = 0;

    TextInputLayout nameLayout, phoneLayout;
    private Context context;

    RecyclerView recyclerView;
    public static TextView allTicketGrandTotal;
    TextInputEditText phoneNumber, userName;

    List<ParentSendOneDayTourModel> parentSendOneDayTourModelList;
    private ParentSendOneDayTourModel parentSendOneDayTourModel;
    MainSendModel mainSendModel;

    private MyProgressDialog progressDialog;
    private MyPreference preferences;
    private AppService networkCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        setContentView(R.layout.activity_confirm_ticket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getResources().getText(R.string.label_one_day_tour));


        // Instantiating MyPreferences(Sharedpreference)
        preferences = MyPreference.getPreferences(this);
        networkCall = new AppService(this);

        progressDialog = new MyProgressDialog(this);
        progressDialog.setMessage("Authenticating...");

        initializationALlField();


    }

    private void initializationALlField() {

        allTicketFee = 0;

        phoneLayout = findViewById(R.id.idConfirmTicketPhonelayout);
        nameLayout = findViewById(R.id.idConfirmTicketUsernameLayout);
        phoneNumber = findViewById(R.id.idConfirmTicketPhoneNumber);
        userName = findViewById(R.id.idConfirmTicketUsername);


        recyclerView = findViewById(R.id.idConfirmTicketRecyclerView);
        allTicketGrandTotal = findViewById(R.id.idGrandTotalPrice);

        parentSendOneDayTourModel = new ParentSendOneDayTourModel();
        parentSendOneDayTourModelList = new ArrayList<>();
        mainSendModel = new MainSendModel();
        mainSendModel = (MainSendModel) getIntent().getSerializableExtra("sendObj");

        List<ParentSendOneDayTourModel> list = mainSendModel.getParentSendOneDayTourModelList();

        for (ParentSendOneDayTourModel model : list) {
            allTicketFee = allTicketFee + Double.parseDouble(model.getTotalSingleTicketFee());

            totalGuardRevenue = totalGuardRevenue + Double.parseDouble(model.getSendOneDayGuardModel().getNetFee());

            doCalculationOfAllTouristTotalRevenue(model);


        }


        allTicketGrandTotal.setText(String.valueOf(allTicketFee));


        TicketItemRecyclerAdapter ticketItemRecyclerAdapter = new TicketItemRecyclerAdapter(ConfirmTicket.this, mainSendModel.getParentSendOneDayTourModelList(), mainSendModel);
        recyclerView.setAdapter(ticketItemRecyclerAdapter);

        setTextChangedListener(userName, nameLayout);
        setTextChangedListener(phoneNumber, phoneLayout);
    }

    private void doCalculationOfAllTouristTotalRevenue(ParentSendOneDayTourModel model) {

        List<SendOneDayTouristModel> list = model.getSendTouristList();

        for (SendOneDayTouristModel touristModel : list) {

            totalTouristRevenue = totalTouristRevenue + Double.parseDouble(touristModel.getNetPrice());

        }
    }

    @Override
    public void onBackPressed() {

        allTicketFee = 0;
        startActivity(new Intent(ConfirmTicket.this, OneDayTourTicket.class));
        super.onBackPressed();


    }

    public void addAnotherSpotBtnClicked(View view) {

        startActivity(new Intent(ConfirmTicket.this, OneDayTourTicket.class));


        Intent newIntent = new Intent(context, OneDayTourTicket.class);

        newIntent.putExtra("addMore", mainSendModel);
        newIntent.putExtra("modeFor", "anotherTicket");

        startActivity(newIntent);

    }

    public void confirmedAndPayBtnClicked(View view) {

        if (validationForConfirmAndPayButton()) {

            totalRevenue = totalTouristRevenue + totalGuardRevenue;

            mainSendModel.setName(userName.getText().toString().trim());
            mainSendModel.setPhoneNumber(phoneNumber.getText().toString().trim());
            allTicketGrandTotal.setText(parentSendOneDayTourModel.getTotalSingleTicketFee());
            mainSendModel.setTotal_amount(String.valueOf(allTicketFee));



            Log.d("seeing", "" + mainSendModel);
            mainSendModel.setTotal_revenue(String.valueOf(totalRevenue));
            mainSendModel.setTotal_vat("200");

            if (preferences.getExternalUserType().equalsIgnoreCase("")) {
                mainSendModel.setExternal_user_type("visitor");
            } else {
                mainSendModel.setExternal_user_type(preferences.getExternalUserType());
            }

            if (preferences.getUserId().equalsIgnoreCase("")) {
                mainSendModel.setExternal_user_id(null);
            } else {
                mainSendModel.setExternal_user_id(preferences.getUserId());
            }


            submitOneDayTicketDataToRemoteServer();

        } else {

        }


    }


    private boolean validationForConfirmAndPayButton() {

        boolean isValidate = true;
        final String name, mobile;

        name = userName.getText().toString().trim();
        mobile = phoneNumber.getText().toString().trim();

        if (name.isEmpty()) {


            CommonUtils.setError(nameLayout, getString(R.string.message_field_mandatory));
            isValidate = false;


        } else {
            CommonUtils.setError(nameLayout, "");

        }

        if (mobile.isEmpty()) {
            CommonUtils.setError(phoneLayout, getString(R.string.message_field_mandatory));

            isValidate = false;
        } else if (mobile.length() != 11) {
            CommonUtils.setError(phoneLayout, getString(R.string.warning_mobile_length));
            isValidate = false;

        } else {
            CommonUtils.setError(phoneLayout, "");
        }
        return isValidate;
    }


    public void submitOneDayTicketDataToRemoteServer() {
        //Toast.makeText(this,DebugDB.getAddressLog(), Toast.LENGTH_LONG).show();
        progressDialog.show();
//        MainSendModel mainModel = new MainSendModel();
//        mainModel = mainSendModel;
        networkCall.submitOneDayTicketDataToRemoteServer(mainSendModel, new ResponseCallback<MainSendModel>() {
            @Override
            public void onSuccess(MainSendModel submitdata) {

                if (submitdata.getCode().equalsIgnoreCase("200")) {

                    if (submitdata.getEkpay_secure_token() != null) {
                        Intent newIntent = new Intent(context, WebViewActivity.class);
                        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        newIntent.putExtra("mode_for", "ekpay_one_day_ticket");
                        newIntent.putExtra("ekpay_secure_token", submitdata.getEkpay_secure_token());
                        newIntent.putExtra("scope_id", submitdata.getReturnId());
                        context.startActivity(newIntent);

                    }
//                    CommonUtils.showToastSuccess( getApplicationContext(), submitdata.getMessage() );
                    progressDialog.hide();

                } else if (submitdata.getCode().equalsIgnoreCase("401")) {
                    CommonUtils.showToastError(getApplicationContext(), submitdata.getMessage());
                } else {
                    CommonUtils.showToastError(getApplicationContext(), "Sorry Something worng!");
                }

                progressDialog.hide();

            }

            @Override
            public void onFail(Throwable th) {
                CommonUtils.showToastError(getApplicationContext(), th.getMessage());
                progressDialog.hide();
            }
        });
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
}