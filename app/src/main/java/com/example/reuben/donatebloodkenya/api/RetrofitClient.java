package com.example.reuben.donatebloodkenya.api;


import com.example.reuben.donatebloodkenya.auth.AuthenticationInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/*
 * A singleton class that defines a  RetrofitClient
 */

public class RetrofitClient {

//    private static final String BASE_URL ="https://dbke.herokuapp.com/api/v1/";
    private  static  final String BASE_URL = "http://10.0.2.2:8000/api/v1/";
//      private static final String BASE_URL = "http://192.168.100.18:8000/api/v1/";

    private String authHeader;

    private static RetrofitClient retrofitInstance;
    private static Retrofit retrofit;


    private  RetrofitClient() {


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(
                       new AuthenticationInterceptor(authHeader)
                )

                 .addInterceptor(httpLoggingInterceptor)
                 .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    /*A synchronous  method that gets Singleton instance of RetrofitClient
       since only one instance is required*/

    public static synchronized RetrofitClient getInstance(){
        if (retrofitInstance == null){                      // if instance is not yet created
            retrofitInstance = new RetrofitClient();

        }
        return retrofitInstance;
    }
    // A method to get ApiClient interface.


    public ApiClient getApi(){
        return retrofit.create(ApiClient.class);
    }
}
