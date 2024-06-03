package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.adapters.BookingListAdapter;
import com.ets.bfd.visitor.adapters.MyTicketListAdapter;
import com.ets.bfd.visitor.models.BookingPackageListModel;
import com.ets.bfd.visitor.models.MyTicketListModel;
import com.ets.bfd.visitor.models.ResponseModel;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.MyProgressDialog;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.TextAwesome;
import com.github.aakira.expandablelayout.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import Interface.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTicketActivity extends AppCompatActivity {

    private Context context;
    private ActionBarDrawerToggle mDrawerToggol;
    private ProgressDialog prgDialog;

    LinearLayout lytMasterForFilter,lytHeaderForFilter,lytMasterForFilterContent,lytHideFilter;
    TextAwesome btnIconFilter,btnHideFilter;
    RelativeLayout btnExpandForFilter;
    RecyclerView recyclerListView;
    TextAwesome total_list_count;

    ArrayList<MyTicketListModel> ticketModelArrayList;
    private MyTicketListModel ticketModel;
    private MyTicketListAdapter ticketAdapter;

    private MyPreference preferences;
    private AppService networkCall;
    private MyProgressDialog progressDialog;
    private ApiInterface apiInterface ;
    MaterialButton btnFilter,btnResetFilter;

    TextInputLayout idTrackingNumberLayout,idPackageFormPhoneLayout,idPackageFormCalendarLayout;
    TextInputEditText idTrackingNumber,idPhoneNumber,idTourDate;

    private String lang = "bn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);

        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getResources().getText(R.string.app_full_name));
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, MyTicketActivity.this, R.id.drawer_layout,R.id.myTicket);

        // Instantiating MyPreferences(Sharedpreference)
        preferences = MyPreference.getPreferences(this);
        networkCall = new AppService(this);
        progressDialog = new MyProgressDialog(this);

        // Set app default language
        App_Config.changeLanguage(App_Config.getCurrentLanguage(this), this);
        lang = CommonUtils.getCurrentLanguage( context );
        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        lytMasterForFilter = (LinearLayout) findViewById(R.id.lytMasterForFilter);
        lytHeaderForFilter = (LinearLayout) findViewById(R.id.lytHeaderForFilter);
        lytMasterForFilterContent = (LinearLayout) findViewById(R.id.lytMasterForFilterContent);
        btnExpandForFilter = (RelativeLayout) findViewById(R.id.btnExpandForFilter);
        lytHideFilter = (LinearLayout) findViewById(R.id.lytHideFilter);
        btnIconFilter = (TextAwesome) findViewById(R.id.btnIconFilter);
        btnHideFilter = (TextAwesome) findViewById(R.id.btnHideFilter);

        recyclerListView = (RecyclerView) findViewById(R.id.recyclerListView);
        total_list_count = (TextAwesome) findViewById(R.id.total_list_count);

        btnFilter = (MaterialButton) findViewById(R.id.btnFilter);
        btnResetFilter = (MaterialButton) findViewById(R.id.btnResetFilter);

        idTrackingNumberLayout = (TextInputLayout) findViewById(R.id.idTrackingNumberLayout);
        idPackageFormPhoneLayout = (TextInputLayout) findViewById(R.id.idPackageFormPhoneLayout);
        idPackageFormCalendarLayout = (TextInputLayout) findViewById(R.id.idPackageFormCalendarLayout);

        idTrackingNumber = (TextInputEditText) findViewById(R.id.idTrackingNumber);
        idPhoneNumber = (TextInputEditText) findViewById(R.id.idPhoneNumber);
        idTourDate = (TextInputEditText) findViewById(R.id.idTourDate);

        // Adding date picker listener
        CommonUtils.addDatePickerListener(idPackageFormCalendarLayout, MyTicketActivity.this, idTourDate);


        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnLogin.startAnimation();
                if(!CommonUtils.isNetworkOnline(getApplicationContext())){
                    CommonUtils.showToastError( getApplicationContext(), getResources().getString(R.string.message_internet_con) );
                } else {
                    if (validateForm()) {
                        getMyTicketFromServer();
                    }
                }

            }
        });

        btnResetFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTrackingNumber.setText("");
                idPhoneNumber.setText("");
                idTourDate.setText("");
            }
        });


    }

    private boolean validateForm() {

        boolean isValidate = true;
        String date, phone;
        date = idTourDate.getText().toString().trim();
        phone = idPhoneNumber.getText().toString().trim();

        if (date.isEmpty())
        {
            CommonUtils.setError(idPackageFormCalendarLayout, getString(R.string.message_field_mandatory));
            isValidate = false;
        }else {
            CommonUtils.setError(idPackageFormCalendarLayout, "");
        } if (phone.isEmpty()) {
            CommonUtils.setError(idPackageFormPhoneLayout, getString(R.string.message_field_mandatory));
            isValidate = false;
        } else if (phone.length() != 11) {
            CommonUtils.setError(idPackageFormPhoneLayout, getString(R.string.warning_mobile_length));
        } else {
            CommonUtils.setError(idPackageFormPhoneLayout, "");
        }

        return isValidate;
    }

    public void getMyTicketFromServer(){
        progressDialog.setMessage("Please Wait Ticket Searching...");
        progressDialog.show();

        Call<ResponseModel> call = apiInterface.getSearchOneDayTicketListForView(idTrackingNumber.getText().toString().trim(), idPhoneNumber.getText().toString().trim(), idTourDate.getText().toString().trim() );
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel ticketList = response.body();
                if(response.code()== 200 && ticketList!=null){
                    loadMyTicketDataList(ticketList.getTicketLists());
                    progressDialog.hide();
                    showHideFilter();
                } else {    // If response code 404
                    CommonUtils.showToastError(context,"No data found!");
                    progressDialog.hide();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.hide();
                CommonUtils.showToastError(context,"Server response not found for UP Member Data..");
//              CustomToast.showToastError(context,t.getMessage());
            }
        });

    }


    public void loadMyTicketDataList(List<MyTicketListModel> ticketArrayListModel){

        if(ticketArrayListModel != null && ticketArrayListModel.size() > 0){

            ticketModelArrayList = new ArrayList<>();

            if( ticketArrayListModel !=null ){
                ticketModelArrayList = (ArrayList<MyTicketListModel>) ticketArrayListModel;
                ticketModel =  ticketModelArrayList.get(0);
            }
            total_list_count.setText(String.valueOf(ticketModelArrayList.size()));

            ticketAdapter = new MyTicketListAdapter(ticketModelArrayList,ticketModel,context,total_list_count);
            recyclerListView.setHasFixedSize(true);
            LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
            MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerListView.setAdapter(ticketAdapter);
            recyclerListView.setLayoutManager(MyLayoutManager);

        }


//        ticketAdapter = new MyTicketListAdapter(ticketArrayListModel,null, context,this,total_list_count);
//        recyclerListView.setHasFixedSize(true);
//        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
//        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerListView.setAdapter(ticketAdapter);
//        recyclerListView.setLayoutManager(MyLayoutManager);
    }



    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }


    public void  onClickedLayoutFilterForExpand(View v){
        final int id = v.getId();

        switch (v.getId()) {

            case R.id.lytHeaderForFilter:
                showHideFilter();
                break;

            case R.id.btnHideFilter:
                showHideFilter();
                break;
        }
    }


    public void showHideFilter(){
        if(lytMasterForFilterContent.isShown()){
            createRotateAnimator(btnExpandForFilter, 180f, 0f).start();

            //hide lytStep1Content1
            lytMasterForFilterContent.animate()
                    //.translationY(0)
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            lytMasterForFilterContent.setVisibility(View.GONE);
                        }
                    });
            //hide lytStep1Content1

        } else {
            createRotateAnimator(btnExpandForFilter, 0f, 180f).start();

            lytMasterForFilterContent.setVisibility(View.VISIBLE);
            lytMasterForFilterContent.setAlpha(0.0f);

            // Start the animation
            lytMasterForFilterContent.animate()
                    .setDuration(500)
                    //.translationY(divSummary.getHeight())
                    .alpha(1.0f)
                    .setListener(null);

        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Hide navigation drawer when clicked on Left arrow in action bar
        if(mDrawerToggol.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


}