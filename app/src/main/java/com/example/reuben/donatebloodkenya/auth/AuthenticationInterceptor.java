package com.example.reuben.donatebloodkenya.auth;

import android.content.Context;

import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authHeader;
    private Context context;

    public AuthenticationInterceptor(String authHeader){
        this.authHeader = authHeader;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        authHeader = SharedPrefManager.getInstance(context).getAuthHeader().getAuthHeader();
        Request auth = chain.request();

        Request authRequest = auth.newBuilder()
                .header("Content-Type", "application/json")
                .addHeader("Authorization", "Basic "+ authHeader)
                .method(auth.method(), auth.body())
                .build();

            return chain.proceed(authRequest);
    }
}
