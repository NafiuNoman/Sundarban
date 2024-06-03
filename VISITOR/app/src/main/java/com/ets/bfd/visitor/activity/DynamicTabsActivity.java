package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.adapters.MyTicketListAdapter;
import com.ets.bfd.visitor.adapters.ViewPagerAdapter;
import com.ets.bfd.visitor.entity.User;
import com.ets.bfd.visitor.models.DynamicOneDayTicketModel;
import com.ets.bfd.visitor.models.MyTicketListModel;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.models.ResponseModel;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.App_Config;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.MyProgressDialog;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Interface.ApiInterface;
import Interface.ResponseCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DynamicTabsActivity extends AppCompatActivity {

    private Context context;
    private ActionBarDrawerToggle mDrawerToggol;
    private ProgressDialog prgDialog;

    private MyPreference preferences;
    private AppService networkCall;
    private MyProgressDialog progressDialog;
    private ApiInterface apiInterface ;

    ArrayList<DynamicOneDayTicketModel> dynamicOneDayTicketModelsArrayList;
    private DynamicOneDayTicketModel dynamicOneDayTicketModel;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private MyTicketListModel ticketObjectModel;
    String oneDayTourId = "0";
    private int noOfTabs = 10;
    private String lang = "bn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_tabs);


        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getResources().getText(R.string.app_full_name));
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, DynamicTabsActivity.this, R.id.drawer_layout,R.id.myTicket);

        // Instantiating MyPreferences(Sharedpreference)
        preferences = MyPreference.getPreferences(this);
        networkCall = new AppService(this);
        progressDialog = new MyProgressDialog(this);

        // Set app default language
        App_Config.changeLanguage(App_Config.getCurrentLanguage(this), this);
        lang = CommonUtils.getCurrentLanguage( context );
        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);


        //for edit mode
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            oneDayTourId = extras.getString("one_day_tour_id");
            ticketObjectModel = (MyTicketListModel) getIntent().getSerializableExtra("oneDayTourInfo");
        }

        if(!CommonUtils.isNetworkOnline(getApplicationContext())){
            CommonUtils.showToastError( getApplicationContext(), getResources().getString(R.string.message_internet_con) );
        } else {
            if(oneDayTourId.equalsIgnoreCase("0")){
                CommonUtils.showToastError( getApplicationContext(), getString(R.string.message_enter_user_name) );
            }else {
                loadOneDayTicketById();
            }
        }

//        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), noOfTabs,ticketObjectModel);
//        viewPager = findViewById(R.id.viewpager);
//        viewPager.setAdapter(viewPagerAdapter);
//
//        tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);

    }

    public void loadOneDayTicketById(){
        progressDialog.setMessage("Please Wait Ticket Searching...");
        progressDialog.show();

        Call<ResponseModel> call = apiInterface.getOneDayTicketLisById(oneDayTourId);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if(response.code()== 200 && responseModel.getTicketListByOneDayId() !=null){
                    loadTicketAdapter(responseModel.getTicketListByOneDayId());
                    progressDialog.hide();
                } else {    // If response code 404
                    CommonUtils.showToastError(context,"No data found!");
                    progressDialog.hide();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.hide();
                CommonUtils.showToastError(context,"Server response not found for ticket data..");
            }
        });
    }

    public void loadTicketAdapter(List<DynamicOneDayTicketModel> ticketArrayList){

        if(ticketArrayList != null && ticketArrayList.size() > 0){
            dynamicOneDayTicketModelsArrayList = new ArrayList<>();
            if( ticketArrayList !=null ){
                dynamicOneDayTicketModelsArrayList = (ArrayList<DynamicOneDayTicketModel>) ticketArrayList;
            }

            noOfTabs = dynamicOneDayTicketModelsArrayList.size();

            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), noOfTabs,ticketObjectModel,dynamicOneDayTicketModelsArrayList,lang);
            viewPager = findViewById(R.id.viewpager);
            viewPager.setAdapter(viewPagerAdapter);

            tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

        }
    }

}