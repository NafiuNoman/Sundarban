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
import com.ets.bfd.visitor.adapters.PackageListAdapter;
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

import java.util.ArrayList;
import java.util.List;

import Interface.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPackageList extends AppCompatActivity {
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
    private PackageListAdapter packageListAdapter;
    ArrayList<PackagesModel> packagesModelArrayList;
    PackagesModel packagesModelObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_package_list);

        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggol = NavigationDrawer.addNavigationDrawer(this, AllPackageList.this, R.id.drawer_layout,R.id.idOverNight);
        getSupportActionBar().setSubtitle( getResources().getString(R.string.app_full_name) );
        lang = CommonUtils.getCurrentLanguage( context );
        UserPermission.requestAppPermission(AllPackageList.this);

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
        loadPackagesListFromServer();

    }

    public void loadPackagesListFromServer(){
        prgDialog.show();
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);

        Call<ArrayList<PackagesModel>> call = apiInterface.getPackagesListForView();
        call.enqueue(new Callback<ArrayList<PackagesModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PackagesModel>> call, Response<ArrayList<PackagesModel>> response) {
                ArrayList<PackagesModel> packagesList = response.body();
                if(response.code()== 200 && packagesList != null){
                    loadPackagesAdaperList(packagesList);
                    prgDialog.hide();
                } else {
                    CommonUtils.showToastError(getApplicationContext(),"No data has been found!!");
                    prgDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PackagesModel>> call, Throwable t) {
                CommonUtils.showToastError(getApplicationContext(),"Server response not found.");
                prgDialog.hide();
            }
        });
    }


    public void loadPackagesAdaperList(List<PackagesModel> packagesArrayListModel){

        if(packagesArrayListModel != null && packagesArrayListModel.size() > 0){

            packagesModelArrayList = new ArrayList<>();

            if( packagesArrayListModel !=null ){
                packagesModelArrayList = (ArrayList<PackagesModel>) packagesArrayListModel;
                packagesModelObject =  packagesArrayListModel.get(0);
//                packagesModelArrayList.remove(0);
            }

            packageListAdapter = new PackageListAdapter(packagesModelArrayList,packagesModelObject,AllPackageList.this);
            packageRecyclerView.setHasFixedSize(true);
            LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
            MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            packageRecyclerView.setAdapter(packageListAdapter);
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