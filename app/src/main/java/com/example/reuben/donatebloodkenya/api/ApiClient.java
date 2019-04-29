package com.example.reuben.donatebloodkenya.api;

import com.example.reuben.donatebloodkenya.models.AppointmentResponse;
import com.example.reuben.donatebloodkenya.models.DefaultApiResponse;
import com.example.reuben.donatebloodkenya.models.Donation;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.models.Hospitals;
import com.example.reuben.donatebloodkenya.models.LoginResponse;
import com.example.reuben.donatebloodkenya.models.News;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {





    @GET("news/")
    Call<List<News>> getNews();

    @FormUrlEncoded
    @POST("signup/")
    Call<DefaultApiResponse> createUser(@Field("username") String username,
                                  @Field("first_name") String first_name,
                                  @Field("last_name") String last_name,
                                  @Field("email") String email,
                                  @Field("birthdate") String birthdate,
                                  @Field("county_name") String county_name,
                                  @Field("gender") String gender,
                                  @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login/")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
            );

    @FormUrlEncoded
    @PUT("donors/profile/{id}/")
    Call<Donor> updateProfile(
                                     @Path("id") int id,
                                     @Field("first_name") String first_name,
                                     @Field("last_name") String last_name,
                                     @Field("email") String email,
                                     @Field("birthdate") String birthdate,
                                     @Field("county_name") String county_name,
                                     @Field("gender") String gender,
                                     @Field("password") String phone_number


    );


    @Multipart
    @PUT("donors/profile/{id}/")
    Call <ResponseBody> updatePhoto(
            @Path("id") int id,
            @Part MultipartBody.Part file);

    @FormUrlEncoded
    @PUT("donors/profile/{id}/")
    Call <LoginResponse> updateBloodType(
            @Path("id") int id,
            @Field("blood_group")String blood_group
    );

    @FormUrlEncoded
    @POST("password/")
    Call<ResponseBody> updatePassword(
            @Field("new_password") String new_password,
            @Field("current_password") String current_password
    );

    @GET("donors/profile/{id}/")
    Call<Donor> getDonorDetails(@Path("id") int id);

    @FormUrlEncoded
    @POST("appointments/")
    Call<AppointmentResponse> bookAppointment(
            @Field("donor") int donor,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("county_name") String countyName,
            @Field("phone_number") String phoneNumber,
            @Field("schedule_date") String scheduleDate,
            @Field("hospital_name") String hospitalName
            );

    @GET("hospitals/")
    Call<List<Hospitals>> getHospitals(@Query("search") String county_name);

    @GET("donors/profile/")
    Call<List<Donor>> getDonors();

    @GET("donations/")
    Call<List<Donation>> getDonations(@Query("search") String username);








}
