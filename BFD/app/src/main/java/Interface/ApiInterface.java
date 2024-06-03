package Interface;
import com.ets.bfd.entity.CheckInCheckOut;
import com.ets.bfd.entity.User;


import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @POST("bfd-sign-in")
    Call<User> getUserValidity(@Body User user);

    @POST("check-in-check-out")
    Call<CheckInCheckOut> getTicketCheckInCheckOutValidity(@Body CheckInCheckOut checkInCheckOut);

}
