package com.ets.bfd.services;

import android.content.Context;

import com.ets.bfd.entity.CheckInCheckOut;
import com.google.gson.Gson;
import com.ets.bfd.R;
import com.ets.bfd.datasource.AppDatabase;



import com.ets.bfd.entity.User;

import com.ets.bfd.preference.MyPreference;
import com.ets.bfd.retrofit.RetrofitApiClient;
import com.ets.bfd.utilities.CommonUtils;
import com.ets.bfd.utilities.MyProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interface.ApiInterface;
import Interface.ResponseCallback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * API endpoint for whole app.
 * All services will be placed here.
 *
 * @author Nazmul Hasan
 */
public class AppService {

    private Context context;
    private ApiInterface apiInterface;
    private MyPreference preferences;
    private AppDatabase db;
    private MyProgressDialog progressDialog;
    private int loopCounter;

    public AppService(Context context) {
        this.context = context;
        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        // Instantiating MyPreferences(Sharedpreference)
        preferences = MyPreference.getPreferences(context);
        db = AppDatabase.getAppDatabase(context);
        progressDialog = new MyProgressDialog(context);
    }


    /**
     * Check user authentication with remote server
     * @param user
     * @param responseCallback
     *
     * @author Nazmul Hasan
     */
    public void validateUser(final User user, final ResponseCallback<User> responseCallback){
        Call<User> call = apiInterface.getUserValidity(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                if(response.code()== 200 && userResponse!=null){
                    responseCallback.onSuccess(userResponse);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_userpass)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //responseCallback.onFail(new Exception("An error has been occurred"));
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }


    /**
     * Ticket Check In Check Out with remote server
     * @param checkInCheckOut
     * @param responseCallback
     *
     * @author Arif Hossain
     */
    public void getTicketCheckInCheckOutValidity(final CheckInCheckOut checkInCheckOut, final ResponseCallback<CheckInCheckOut> responseCallback){
        Call<CheckInCheckOut> call = apiInterface.getTicketCheckInCheckOutValidity(checkInCheckOut);
        call.enqueue(new Callback<CheckInCheckOut>() {
            @Override
            public void onResponse(Call<CheckInCheckOut> call, Response<CheckInCheckOut> response) {
                CheckInCheckOut userResponse = response.body();
                if(response.code()== 200 && userResponse!=null){
                    responseCallback.onSuccess(userResponse);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_userpass)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<CheckInCheckOut> call, Throwable t) {
                //responseCallback.onFail(new Exception("An error has been occurred"));
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }

    /**
     * Get Union Data from remote server and storing in app local database
     *
     * @param responseCallback
     *
     * @author Arif Hossain
     */
   /* public void getUnionWardData(final ResponseCallback<List<UnionWard>> responseCallback){

        Call<List<UnionWard>> call = apiInterface.getUnionWardData();
        call.enqueue(new Callback<List<UnionWard>>() {
            @Override
            public void onResponse(Call<List<UnionWard>> call, Response<List<UnionWard>> response) {
                List<UnionWard> unionWardList = response.body();
                if(response.code()== 200 && unionWardList!=null){
                    db.unionDao().insert(unionWardList);
                    responseCallback.onSuccess(unionWardList);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_union)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<List<UnionWard>> call, Throwable t) {
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }*/


    /**
     * Get Financial year Data from remote server and storing in app local database
     *
     * @param responseCallback
     *
     * @author Arif Hossain
     */
    /*public void getFinancialYearData(final ResponseCallback<List<FinancialYear>> responseCallback){

        Call<List<FinancialYear>> call = apiInterface.getFinancialYearData();
        call.enqueue(new Callback<List<FinancialYear>>() {
            @Override
            public void onResponse(Call<List<FinancialYear>> call, Response<List<FinancialYear>> response) {
                List<FinancialYear> financialYearList = response.body();
                if(response.code()== 200 && financialYearList!=null){
                    db.financialYearDao().insert(financialYearList);
                    responseCallback.onSuccess(financialYearList);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_financial_year)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<List<FinancialYear>> call, Throwable t) {
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }*/


    /**
     * Get Financial year Data from remote server and storing in app local database
     *
     * @param responseCallback
     *
     * @author Arif Hossain
     */
    /*public void getCommonSetupData(final ResponseCallback<List<CommonLabelsModel>> responseCallback){

        Call<List<CommonLabelsModel>> call = apiInterface.getCommonLabelsData();
        call.enqueue(new Callback<List<CommonLabelsModel>>() {
            @Override
            public void onResponse(Call<List<CommonLabelsModel>> call, Response<List<CommonLabelsModel>> response) {
                List<CommonLabelsModel> commonSetUpDataList = response.body();
                if(response.code()== 200 && commonSetUpDataList!=null){
                    db.commonLabelsDao().insert(commonSetUpDataList);
                    responseCallback.onSuccess(commonSetUpDataList);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_financial_year)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<List<CommonLabelsModel>> call, Throwable t) {
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }*/


    /**
     * Get Financial year Data from remote server and storing in app local database
     *
     * @param responseCallback
     *
     * @author Arif Hossain
     */

  /*  public void getCommonUtilitiesData(final ResponseCallback<UtilitesDataModel> responseCallback){
        Call<UtilitesDataModel> call = apiInterface.getCommonUtilitiesData();
        call.enqueue(new Callback<UtilitesDataModel>() {
            @Override
            public void onResponse(Call<UtilitesDataModel> call, Response<UtilitesDataModel> response) {
                UtilitesDataModel commonUtilitesDataList = response.body();
                if(response.code()== 200 && commonUtilitesDataList!=null){
                    responseCallback.onSuccess(commonUtilitesDataList);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_userpass)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<UtilitesDataModel> call, Throwable t) {
                //responseCallback.onFail(new Exception("An error has been occurred"));
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }*/



}
