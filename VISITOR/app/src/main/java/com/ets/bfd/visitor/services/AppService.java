package com.ets.bfd.visitor.services;

import android.content.Context;


import com.ets.bfd.visitor.models.One_day_tour_save_data.MasterModel;
import com.ets.bfd.visitor.models.PostBookingData;
import com.ets.bfd.visitor.models.ResponseModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.OneDayTourParentModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.MainSendModel;
import com.ets.bfd.visitor.models.spots_and_routes.OldSpotModel;
import com.ets.bfd.visitor.models.touristfee.TouristModel;
import com.ets.bfd.visitor.models.vessels_data.RootVesselsData;
import com.ets.bfd.visitor.R;
import com.ets.bfd.visitor.datasource.AppDatabase;



import com.ets.bfd.visitor.entity.User;

import com.ets.bfd.visitor.preference.MyPreference;
import com.ets.bfd.visitor.retrofit.RetrofitApiClient;
import com.ets.bfd.visitor.utilities.MyProgressDialog;

import java.util.List;

import Interface.ApiInterface;
import Interface.ResponseCallback;
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
     * Submit One day Ticket Data To remote server
     * @param mainModel
     * @param responseCallback
     *
     * @author Arif Hossain
     */
    public void submitOneDayTicketDataToRemoteServer(final MainSendModel mainModel, final ResponseCallback<MainSendModel> responseCallback){
        Call<MainSendModel> call = apiInterface.submitOneDayticketData(mainModel);
        call.enqueue(new Callback<MainSendModel>() {
            @Override
            public void onResponse(Call<MainSendModel> call, Response<MainSendModel> response) {
                MainSendModel submitResponse = response.body();
                if(response.code()== 200 && submitResponse!=null){
                    responseCallback.onSuccess(submitResponse);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_server_invalid)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<MainSendModel> call, Throwable t) {
                //responseCallback.onFail(new Exception("An error has been occurred"));
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }

    /**
     * Get District from remote server and storing in app local database
     *
     * @param responseCallback
     *
     * @author Nazmul Hasan
     */
//    public void getDistrictData(final ResponseCallback<ArrayList<District>> responseCallback){
//
//        Call<ArrayList<District>> call = apiInterface.getDistrictData();
//        call.enqueue(new Callback<ArrayList<District>>() {
//            @Override
//            public void onResponse(Call<ArrayList<District>> call, Response<ArrayList<District>> response) {
//                final ArrayList<District> districtList = response.body();
//                if(response.code()== 200 && districtList!=null){
//                    responseCallback.onSuccess(districtList);
//
//                } else {
//                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_district)));// If response code 404
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<District>> call, Throwable t) {
//                responseCallback.onFail(new Exception(t.getMessage()));
//            }
//        });
//    }
    public void getAllSpotData(final ResponseCallback<List<OldSpotModel>> responseCallback){

        Call<List<OldSpotModel>> call  = apiInterface.getAllSpotData();
        call.enqueue(new Callback<List<OldSpotModel>>() {
            @Override
            public void onResponse(Call<List<OldSpotModel>> call, Response<List<OldSpotModel>> response) {
                final List<OldSpotModel> spotList = response.body();
                if(response.code()== 200 && spotList!=null){
                    responseCallback.onSuccess(spotList);

                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_district)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<List<OldSpotModel>> call, Throwable t) {
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }


    /**
     * Post Booking Data to remote server
     * @param bookingData
     * @param responseCallback
     *
     * @author Arif Hossain
     */
    public void postBookingData(final PostBookingData bookingData, final ResponseCallback<PostBookingData> responseCallback){
        Call<PostBookingData> call = apiInterface.postBookingData(bookingData);
        call.enqueue(new Callback<PostBookingData>() {
            @Override
            public void onResponse(Call<PostBookingData> call, Response<PostBookingData> response) {
                PostBookingData userResponse = response.body();
                if(response.code() == 200 && userResponse!=null){
                    responseCallback.onSuccess(userResponse);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_userpass)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<PostBookingData> call, Throwable t) {
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
//    public void getAllRouteAndSpotData(String districtId,final ResponseCallback<AllRouteAndSpot> responseCallback){
//
//        Call<AllRouteAndSpot> call = apiInterface.getAllRouteAndSpotData(districtId);
//        call.enqueue(new Callback<AllRouteAndSpot>() {
//            @Override
//            public void onResponse(Call<AllRouteAndSpot> call, Response<AllRouteAndSpot> response) {
//                AllRouteAndSpot routeAndSpotList = response.body();
//                if(response.code()== 200 && routeAndSpotList!=null){
//
//                    responseCallback.onSuccess(routeAndSpotList);
//                } else {
//                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_spot_route)));// If response code 404
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllRouteAndSpot> call, Throwable t) {
//                responseCallback.onFail(new Exception(t.getMessage()));
//            }
//        });
//    }


    public void getAllVesselData(String userType,String userID, final ResponseCallback<RootVesselsData> responseCallback){

        Call<RootVesselsData> call = apiInterface.getAllVesselData(userType,userID);
        call.enqueue(new Callback<RootVesselsData>() {
            @Override
            public void onResponse(Call<RootVesselsData> call, Response<RootVesselsData> response) {
                RootVesselsData vesselsData = response.body();
                if(response.code()== 200 && vesselsData!=null){

                    responseCallback.onSuccess(vesselsData);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_spot_route)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<RootVesselsData> call, Throwable t) {
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }



    public void getOneDayTourData( final ResponseCallback<OneDayTourParentModel> responseCallback){

        Call<OneDayTourParentModel> call = apiInterface.getAllOneDayTourData();
        call.enqueue(new Callback<OneDayTourParentModel>() {
            @Override
            public void onResponse(Call<OneDayTourParentModel> call, Response<OneDayTourParentModel> response) {
                 OneDayTourParentModel superModelData = response.body();

                if(response.code()== 200 && superModelData!=null){

                    responseCallback.onSuccess(superModelData);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_spot_route)));// If response code 404
                }

            }

            @Override
            public void onFailure(Call<OneDayTourParentModel> call, Throwable t) {

                responseCallback.onFail(new Exception(t.getMessage()));

            }
        });
    }


    public void getAllTouristTypeAndGuardFeeData(String spotId,final ResponseCallback<TouristModel> responseCallback){

        Call<TouristModel> call = apiInterface.getAllTouristFees(Integer.parseInt(spotId));
        call.enqueue(new Callback<TouristModel>() {
            @Override
            public void onResponse(Call<TouristModel> call, Response<TouristModel> response) {
                TouristModel touristModel = response.body();
                if(response.code()== 200 && touristModel!=null){

//                    List<TouristDetails> touristDetailsList  = touristModel.getTouristFees();

                    responseCallback.onSuccess(touristModel);
                } else {
                    responseCallback.onFail(new Exception(context.getResources().getString(R.string.message_error_no_spot_route)));// If response code 404
                }
            }

            @Override
            public void onFailure(Call<TouristModel> call, Throwable t) {
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });
    }

    /**
     *  One Day Tour
     * @param responseCallback
     * @param model
     */

    public void sendDataForOneDayTour(MasterModel model, final ResponseCallback<ResponseModel> responseCallback){
        Call<ResponseModel> call = apiInterface.uploadOneDayTourTicketData(model);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseServer = response.body();
                if( responseServer != null && response.code()== 200){
                        responseCallback.onSuccess(responseServer);
                }else {
                    responseCallback.onFail(new Exception("Failed"));
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                responseCallback.onFail(new Exception(t.getMessage()));
            }
        });


    }





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
