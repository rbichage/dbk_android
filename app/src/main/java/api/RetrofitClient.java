package api;


import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*
 * A singleton class that defines a  RetrofitClient
 */

public class RetrofitClient {

    private static final String AUTH_HEADER = "Basic " + Base64.encodeToString(("reuben:admin").getBytes(), Base64.NO_WRAP);
//    private static final String BASE_URL ="https://dbke.herokuapp.com/api/v1/";
    private  static  final String BASE_URL = "http://10.0.2.2:8000/api/v1/";
//      private static final String BASE_URL = "http://192.168.43.65:8000/api/v1/";

    private static RetrofitClient retrofitInstance;
    private static Retrofit retrofit;




    //Initializing retrofit object

    private  RetrofitClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


         OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request auth = chain.request();
                                Request.Builder requestBuilder = auth.newBuilder()
                                        .addHeader("Authorization", AUTH_HEADER)
                                        .method(auth.method(), auth.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                )
                 .addInterceptor(httpLoggingInterceptor)
                 .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
