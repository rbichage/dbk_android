package services;


import interfaces.ApiClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*
 * This is a singleton class that defines a  RetrofitClient
 */

public class RetrofitClient {

    private static final String BASE_URL ="https://dbke.herokuapp.com/api/v1/";
    private static RetrofitClient retrofitInstance;
    private static Retrofit retrofit;


    //Initializing retrofit object

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
//    a sync method that gets Singleton instance of RetrofitClient
//    since only one instance is required

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
