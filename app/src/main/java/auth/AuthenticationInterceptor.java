package auth;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import storage.SharedPrefManager;

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
