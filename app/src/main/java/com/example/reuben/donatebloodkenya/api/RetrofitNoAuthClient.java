package com.example.reuben.donatebloodkenya.api;

import com.example.reuben.donatebloodkenya.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitNoAuthClient {

    //    private static final String BASE_URL ="https://dbke.herokuapp.com/api/v1/";
//    private  static  final String BASE_URL = "http://10.0.2.2:8000/v1/";
//    private static final String BASE_URL = "http://192.168.43.65:8000/api/v1/";
      private static final String BASE_URL = Constants.BASE_URL;


    private static RetrofitNoAuthClient retrofitInstance;
    private static Retrofit retrofit;


    private  RetrofitNoAuthClient() {


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
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

    public static synchronized RetrofitNoAuthClient getInstance(){
        if (retrofitInstance == null){                      // if instance is not yet created
            retrofitInstance = new RetrofitNoAuthClient();

        }
        return retrofitInstance;
    }
    // A method to get ApiClient interface.


    public ApiClient getApi(){
        return retrofit.create(ApiClient.class);
    }
}


