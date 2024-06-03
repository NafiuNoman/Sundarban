package com.ets.bfd.visitor.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.adapters.BookingListAdapter;
import com.ets.bfd.visitor.adapters.PackageListAdapter;
import com.ets.bfd.visitor.datasource.AppDatabase;
import com.ets.bfd.visitor.models.BookingPackageListModel;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.models.ResponseModel;
import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.services.AppService;
import com.ets.bfd.visitor.utilities.CommonUtils;
import com.ets.bfd.visitor.utilities.LocationManager;
import com.ets.bfd.visitor.utilities.NavigationDrawer;
import com.ets.bfd.visitor.utilities.UserPermission;

import java.util.ArrayList;
import java.util.List;

import Interface.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingListActivity extends AppCompatActivity {

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
    RecyclerView packageRecyclerView;
    private BookingListAdapter bookingListAdapter;
    ArrayList<BookingPackageListModel> bookingModelArrayList;
    BookingPackageListModel bookingModelObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, BookingListActivity.this, R.id.drawer_layout,R.id.idBookingList);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        lang = CommonUtils.getCurrentLanguage( context );
        UserPermission.requestAppPermission(BookingListActivity.this);

        //share preferences
        preferences = MyPreference.getPreferences(this);
        // Initialize service model
        networkCall = new AppService(this);
        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        // Instantiating progress bar
        prgDialog = new ProgressDialog(this);


        fieldInitialization();

    }

    public void fieldInitialization(){
        packageRecyclerView =   findViewById(R.id.idPackageRecyclerView);
//        packageRecyclerView.setAdapter(new PackageListAdapter());
        loadBookingListFromServer();

    }

    public void loadBookingListFromServer(){
        prgDialog.show();
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);

        Call<ResponseModel> call = apiInterface.getBookingPackagesListForView(preferences.getUserId(),preferences.getOperatorId());
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModels = response.body();
                if(response.code()== 200 && responseModels.getCode().equalsIgnoreCase("200")){
                    loadBookingAdaperList(responseModels.getBookingList());
                    prgDialog.hide();
                } else {
                    CommonUtils.showToastError(getApplicationContext(),"No data has been found!!");
                    prgDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                CommonUtils.showToastError(getApplicationContext(),"Server response not found.");
                prgDialog.hide();
            }
        });
    }


    public void loadBookingAdaperList(List<BookingPackageListModel> packagesArrayListModel){

        if(packagesArrayListModel != null && packagesArrayListModel.size() > 0){

            bookingModelArrayList = new ArrayList<>();

            if( packagesArrayListModel !=null ){
                bookingModelArrayList = (ArrayList<BookingPackageListModel>) packagesArrayListModel;
                bookingModelObject =  packagesArrayListModel.get(0);
//                packagesModelArrayList.remove(0);
            }

            bookingListAdapter = new BookingListAdapter(bookingModelArrayList,bookingModelObject,BookingListActivity.this);
            packageRecyclerView.setHasFixedSize(true);
            LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
            MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            packageRecyclerView.setAdapter(bookingListAdapter);
            packageRecyclerView.setLayoutManager(MyLayoutManager);

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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}