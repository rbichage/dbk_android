package com.example.reuben.donatebloodkenya.auth;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
;
public class NoAuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request auth = chain.request();

        Request authRequest = auth.newBuilder()
                .addHeader("Content-Type", "application/json")
                .method(auth.method(), auth.body())
                .build();

        return chain.proceed(authRequest);
    }
}
