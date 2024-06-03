package Interface;

import com.ets.bfd.visitor.entity.User;
import com.ets.bfd.visitor.models.BookingPackageListModel;
import com.ets.bfd.visitor.models.One_day_tour_save_data.MasterModel;
import com.ets.bfd.visitor.models.PackagesModel;
import com.ets.bfd.visitor.models.PostBookingData;
import com.ets.bfd.visitor.models.ResponseModel;
import com.ets.bfd.visitor.models.one_day_tour_all_data.OneDayTourParentModel;
import com.ets.bfd.visitor.models.send_one_day_tour_all_data.MainSendModel;
import com.ets.bfd.visitor.models.spots_and_routes.OldSpotModel;
import com.ets.bfd.visitor.models.touristfee.TouristModel;
import com.ets.bfd.visitor.models.vessels_data.RootVesselsData;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * APIInterface for service calling using Retrofit Network library
 * Declare an unique method in this interface for every service calling.
 * Every method must have an HTTP annotation that provides the request method and relative URL.
 * @author Nazmul Hasan
 * @url https://square.github.io/retrofit/
 *
 */
public interface ApiInterface {
    @POST("visitor-sign-in")
    Call<User> getUserValidity(@Body User user);


    @GET("get-starting-point")
    Call<OneDayTourParentModel> getAllOneDayTourData();

    @GET("get-vessel-list-for-one-day-ticket")
    Call<RootVesselsData> getAllVesselData(@Query("userType") String userType, @Query("userId") String userId);

    @GET("get-spots")
    Call<List<OldSpotModel>> getAllSpotData();

    @GET("get-tourist-fee-by-spot-id/{id}")
    Call<TouristModel> getAllTouristFees(@Path("id") int spotId );

//    @GET("get-operator-registered-vessels/{id}")
//    Call<RootVesselsData> getAllVesselData(@Path("id") int operatorId);

    @POST("one-day-tour/store")
    Call<ResponseModel> uploadOneDayTourTicketData(@Body MasterModel oneDayTourMasterModel);

    @GET("get-packages")
    Call<ArrayList<PackagesModel>> getPackagesListForView();

    @POST("visitor-post-package-booking-data")
    Call<PostBookingData> postBookingData(@Body PostBookingData bookingData);

    @GET("visitor-package-booking-list")
    Call<ResponseModel> getBookingPackagesListForView(@Query("userId") String userId, @Query("operatorId") String operatorId);

    @GET("search-one-day-ticket")
    Call<ResponseModel> getSearchOneDayTicketListForView(@Query("tracking_number") String tracking_number, @Query("mobile_number") String mobile_number,@Query("tour_date") String tour_date);

    @GET("get-one-day-ticket-data")
    Call<ResponseModel> getOneDayTicketLisById(@Query("one_day_tour_id") String one_day_tour_id);

    @POST("submit-one-day-ticket-data")
    Call<MainSendModel> submitOneDayticketData(@Body MainSendModel user);

    @GET("get-one-day-ticket-success-data")
    Call<ResponseModel> getOneDayTicketSuccessDataByOneDayId(@Query("one_day_tour_id") String one_day_tour_id);

}
