package activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.RetrofitClient;

public class Register extends AppCompatActivity implements View.OnClickListener{

   private EditText etUname, etFirstName, etLastname, etEmail, etPassword, birth_date;
   private ProgressBar progressBar;
   Calendar myCalendar;
   private int mYear, mMonth, mDay, mHour, mMinute;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);










        etUname = findViewById(R.id.etuname);
        etFirstName = findViewById(R.id.etfirstname);
        etLastname = findViewById(R.id.etlastname);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);
        birth_date = findViewById(R.id.birthdate);





        findViewById(R.id.bregister).setOnClickListener(this);

        findViewById(R.id.birthdate).setOnClickListener(this);


        findViewById(R.id.tvLogin).setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bregister:
                userSignUp();

                break;
            case R.id.tvLogin:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
            case R.id.birthdate:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                birth_date.setText(R.string.date);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;


        }



    }

    private void userSignUp() {




        String username = etUname.getText().toString().trim();
            if (username.isEmpty()){
                etUname.setError("enter a username");
                etUname.requestFocus();
                return;
            }



          String first_name = etFirstName.getText().toString().trim();
            if (first_name.isEmpty()){
                etFirstName.setError("this field is required");
                etFirstName.requestFocus();
                return;

            }

        String last_name = etLastname.getText().toString().trim();
            if (last_name.isEmpty()){
                etLastname.setError("this field is required");
                etLastname.requestFocus();
                return;


            }

        String email = etEmail.getText().toString().trim();
        if (email.isEmpty()){
            etEmail.setError("this field is required");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("enter a valid email address");
            etEmail.requestFocus();
            return;
        }

        String birthdate = birth_date.getText().toString().trim();
            if (birthdate.isEmpty()){
                birth_date.setError("This field is required");
                birth_date.requestFocus();
                return;
            }



        String password = etPassword.getText().toString().trim();
            if (password.isEmpty()){
                etPassword.setError("this field is required");
                etPassword.requestFocus();
                return;
            }

            if (password.length() <8 ){
                etPassword.setError("This password is too short");
                etPassword.requestFocus();
                return;

            }




        progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

        Call <ResponseBody> call = RetrofitClient.getInstance()
                .getApi()
                .createUser(username, first_name, last_name, email, birthdate, password);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String s = null;

                    if (response.code() == 200){
                        if (response.body() != null) {
                            s = response.body().toString();

                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Register.this, "account created successfully", Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        }
                        else {

                            try {
                                assert response.errorBody() != null;
                                s = response.errorBody().string();
                                progressBar.setVisibility(View.INVISIBLE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }



                    }

                    if (s !=null){
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Register.this, jsonObject.getString("error"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

    }

    @Override
    public void onBackPressed() {

        finish();

    }




}