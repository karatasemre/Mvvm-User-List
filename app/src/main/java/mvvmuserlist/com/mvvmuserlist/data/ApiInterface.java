package mvvmuserlist.com.mvvmuserlist.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api")
    Call<UserResponseHandler> getUserList(@Query("results") int results);
}
