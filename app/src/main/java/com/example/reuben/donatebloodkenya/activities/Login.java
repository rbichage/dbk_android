package com.example.reuben.donatebloodkenya.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.AuthHeader;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.models.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends Activity implements OnClickListener {
    TextView tvReset, tvRegister;
    EditText etuname, etpass;
    Button blogin;
    ImageView facebook, instagram, twitter;
    ProgressDialog progressDialog;
    ProgressBar progressBar;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        etuname = findViewById(R.id.username);
        etpass = findViewById(R.id.et_password);
        blogin = findViewById(R.id.blogin);



        findViewById(R.id.blogin).setOnClickListener(this);


        tvRegister =findViewById(R.id.tvRegister);
        findViewById(R.id.tvRegister).setOnClickListener(this);


        tvReset = findViewById(R.id.tvReset);
        findViewById(R.id.tvReset).setOnClickListener(this);



        twitter = findViewById(R.id.twitter);
        findViewById(R.id.twitter).setOnClickListener(this);

        instagram = findViewById(R.id.instagram);
        findViewById(R.id.instagram).setOnClickListener(this);

        facebook = findViewById(R.id.facebook);
        findViewById(R.id.facebook).setOnClickListener(this);





    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(Login.this, UserProfile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin() {


        String username = etuname.getText().toString().trim();
        if (username.isEmpty()){
            etuname.setError("username is required");
            etpass.requestFocus();
            return;
        }

        String password = etpass.getText().toString().trim();
        if (password.isEmpty()){
            etpass.setError("please enter password");
            etpass.requestFocus();
            return;
        }

        if (password.length() < 8){
            etpass.setError("password is too short");
            etpass.requestFocus();
            return;
        }


        progressDialog = new ProgressDialog(Login.this, R.style.CustomDialog);
        progressDialog.setMessage("Logging you in, Please wait ...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        String auth = Base64.encodeToString((username +":"+ password).getBytes(), Base64.NO_WRAP);
        AuthHeader authHeader = new AuthHeader(auth);
        SharedPrefManager.getInstance(getApplicationContext()).saveAuthHeader(authHeader);



        Call<LoginResponse> call = RetrofitClient.getInstance()
                .getApi()
                .userLogin(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                String s = null;
                LoginResponse loginResponse = response.body();

                if (response.isSuccessful()) {
                    assert loginResponse != null;
                   SharedPrefManager.getInstance(getApplicationContext())
                            .saveDonor(loginResponse.getDonor());

                    Donor donor = SharedPrefManager.getInstance(getApplicationContext()).getDonor();
                    Toast.makeText(Login.this, "Welcome " + donor.getFirstName() + "!", Toast.LENGTH_LONG).show();

                    if (response.body() != null) {
                        Log.d("Login: ", loginResponse.getMessage());
                    }

                    progressDialog.dismiss();

                    Intent intent = new Intent(Login.this, UserProfile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);



                }

                else  {

                    if (response.errorBody() != null) {
                        try {
                            s = response.errorBody().string();

                            JSONObject jsonObject = new JSONObject(s);

                            Toast.makeText(Login.this, jsonObject.getString("detail"), Toast.LENGTH_LONG).show();

                            Log.e("Login error", s);

                            progressDialog.dismiss();
                        } catch (IOException e) {

                            Toast.makeText(Login.this, "Something went wrong, please try again", Toast.LENGTH_LONG).show();

                            Log.e("IOException", e.toString());
                            progressDialog.dismiss();
                            e.printStackTrace();
                        } catch (JSONException e) {
                            Toast.makeText(Login.this, "Something went wrong, please try again", Toast.LENGTH_LONG).show();

                            Log.e("JSONException", e.toString());

                            progressDialog.dismiss();
                            e.printStackTrace();
                        }


                    }


                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    progressDialog.dismiss();
                Toast.makeText(Login.this, "failed to connect. \ncheck your internet connection or try again", Toast.LENGTH_LONG).show();

                Log.e("Login error: ", t.getMessage());
    }
});


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.tvRegister:
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                break;

            case R.id.tvReset:
                Intent reset = new Intent(Login.this, ResetPassword.class);
                startActivity(reset);
                break;

            case R.id.twitter:
                Uri webpage = Uri.parse("https://www.twitter.com/reuben_bichage");
                Intent twitter_intent =new  Intent(Intent.ACTION_VIEW, webpage);
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(twitter_intent, 0);
                boolean isIntentSafe = activities.size() > 0;

                if (isIntentSafe){
                    startActivity(twitter_intent);
                }
                else
                    Snackbar.make(v, "No app can open this", Snackbar.LENGTH_LONG).show();

                break;

            case R.id.instagram:
                webpage = Uri.parse(getString(R.string.instagram_url));
                intent =new  Intent(Intent.ACTION_VIEW, webpage);
                activities = getPackageManager().queryIntentActivities(intent, 0);
                isIntentSafe = activities.size() > 0;

                if (isIntentSafe){
                    startActivity(intent);
                }
                else
                    Snackbar.make(v, "No app can open this", Snackbar.LENGTH_SHORT).show();

                break;

            case R.id.facebook:
                webpage = Uri.parse("https://www.facebook.com/rube.bichage");
                intent = new Intent(Intent.ACTION_VIEW, webpage);
                activities = getPackageManager().queryIntentActivities(intent, 0);
                isIntentSafe = activities.size() > 0;

                if (isIntentSafe){
                    startActivity(intent);
                }
                else
                    Snackbar.make(v, "No app can open this", Snackbar.LENGTH_SHORT).show();

                break;


            case R.id.blogin:
                userLogin();
                break;
        }

    }


    @Override

    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }










}










