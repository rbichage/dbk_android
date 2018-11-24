package activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.RetrofitClient;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements OnClickListener {
    TextView tvReset, tvRegister;
    ProgressBar progressBar;
    EditText etuname, etpass;
    Button blogin;
    ImageView facebook, instagram, twitter;

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


    private void userLogin() {


        String username = etuname.getText().toString().trim();
        if (username.isEmpty()){
            etuname.setError("please enter username");
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


        progressBar = findViewById(R.id.progress_bar);

//        progressBar = new ProgressBar(Login.this);
        progressBar.setVisibility(View.VISIBLE);

        Call <ResponseBody> call = RetrofitClient.getInstance()
                .getApi()
                .userLogin(username, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String s;

                if (response.isSuccessful()){
//                    s = response.body().toString();
//                    Toast.makeText(Login.this, "login successful", Toast.LENGTH_LONG).show();
                    Toast.makeText(Login.this, "Welcome back!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(Login.this, UserProfile.class);
                    startActivity(intent);

                }

                else
//                    s =response.body().toString();
                    Toast.makeText(Login.this, "please check your password or username. \\nUSERNAME is case sensitive", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);

//                if(s != null)
//                   Toast.makeText(Login.this, "please check your password or username. \nUSERNAME is case sensitive", Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);


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
                packageManager = getPackageManager();
                activities = packageManager.queryIntentActivities(intent, 0);
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
                packageManager = getPackageManager();
                activities = packageManager.queryIntentActivities(intent, 0);
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
        finish();
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void checkConnection(){
        if(isOnline()){
            Toast.makeText(getApplicationContext(), "You are connected to Internet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }






}










