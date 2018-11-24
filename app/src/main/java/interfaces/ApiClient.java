package interfaces;

import java.sql.Struct;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import services.LoginResponse;

public interface ApiClient {

    @FormUrlEncoded
    @POST("signup/")
    Call<ResponseBody> createUser(@Field("username") String username,
                                  @Field("first_name") String first_name,
                                  @Field("last_name") String last_name,
                                  @Field("email") String email,
                                  @Field("birthdate") String birthdate,
                                  @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login/")
    Call <ResponseBody> userLogin(
            @Field("username") String username,
            @Field("password") String password
            );

}
