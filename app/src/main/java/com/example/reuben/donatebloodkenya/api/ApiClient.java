package com.example.reuben.donatebloodkenya.api;

import com.example.reuben.donatebloodkenya.models.AppointmentResponse;
import com.example.reuben.donatebloodkenya.models.DefaultApiResponse;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.models.Hospitals;
import com.example.reuben.donatebloodkenya.models.LoginResponse;
import com.example.reuben.donatebloodkenya.models.NewsResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient {





    @GET("news/")
    Call<NewsResponse> getNews();

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
    Call<LoginResponse> updateProfile(
                                     @Path("id") int id,
                                     @Field("first_name") String first_name,
                                     @Field("last_name") String last_name,
                                     @Field("email") String email,
                                     @Field("birthdate") String birthdate,
                                     @Field("county_name") String county_name,
                                     @Field("gender") String gender,
                                     @Field("password") String phone_number


    );


    @FormUrlEncoded
    @PUT("donors/profile/{id}/")
    Call <DefaultApiResponse> updatePhoto(
            @Path("id") int id,
            @Field("current_password")Object image


    );

    @FormUrlEncoded
    @PUT("donors/profile/{id}/")
    Call <LoginResponse> updateBloodType(
            @Path("id") int id,
            @Field("blood_group")String blood_group
    );

    @FormUrlEncoded
    @PUT("update-password/")
    Call<ResponseBody> updatePassword(
            @Field("old_password") String old_password,
            @Field("new_password") String new_password
    );

    @GET("donors/profile/{id}/")
    Call<LoginResponse> getDonorDetails(@Path("id") int id);

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
    Call<List<Hospitals>> getHospitals();

    @GET("donors/profile/")
    Call<List<Donor>> getDonors();





}
