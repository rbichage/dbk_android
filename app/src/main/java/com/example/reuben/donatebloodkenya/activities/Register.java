package com.example.reuben.donatebloodkenya.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.reuben.donatebloodkenya.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import com.example.reuben.donatebloodkenya.api.RetrofitNoAuthClient;
import com.example.reuben.donatebloodkenya.models.DefaultApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText etUname, etFirstName, etLastname, etEmail, etPassword, birth_date;
    Spinner county_spinner, gender_spinner;
    ProgressDialog progressDialog;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupActionBar();



        etUname = findViewById(R.id.etuname);
        etFirstName = findViewById(R.id.etfirstname);
        etLastname = findViewById(R.id.etlastname);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);
        birth_date = findViewById(R.id.birthdate);

        county_spinner = findViewById(R.id.county_spinner);
        gender_spinner = findViewById(R.id.gender_spinner);

        findViewById(R.id.bregister).setOnClickListener(this);

        findViewById(R.id.birthdate).setOnClickListener(this);




    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bregister:
                userSignUp();

                break;

            case R.id.birthdate:
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                birth_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

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


        String county_name = county_spinner.getSelectedItem().toString();
            if (county_spinner.getSelectedItem().toString().equalsIgnoreCase("Choose County")){
                Toast.makeText(Register.this, "please choose county", Toast.LENGTH_LONG).show();
                county_spinner.requestFocus();
                return;

            }

         String gender = gender_spinner.getSelectedItem().toString();
            if (gender_spinner.getSelectedItem().toString().equalsIgnoreCase("Choose gender")){
                Toast.makeText(Register.this, "Please choose gender", Toast.LENGTH_LONG).show();
                gender_spinner.requestFocus();
                return;
            }




        String password = etPassword.getText().toString().trim();
            if (password.isEmpty()){
                etPassword.setError("this field is required");
                etPassword.requestFocus();
                return;
            }

            if (password.length() <8 ){
                etPassword.setError("the password should at least be 8 characters long");
                etPassword.requestFocus();
                return;

            }





        progressDialog = new ProgressDialog(Register.this, R.style.CustomDialog);
        progressDialog.setMessage("loading, please wait ...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        Call <DefaultApiResponse> call = RetrofitNoAuthClient.getInstance()
                .getApi()
                .createUser(username, first_name, last_name, email, birthdate, county_name, gender, password);

            call.enqueue(new Callback<DefaultApiResponse>() {
                @Override
                public void onResponse(Call<DefaultApiResponse> call, Response<DefaultApiResponse> response) {
                    String s;

                    if (response.isSuccessful()){
                        if (response.body() != null) {
                            s = response.body().toString();

//                            progressBar.setVisibility(View.INVISIBLE);

                            progressDialog.dismiss();
                            Toast.makeText(Register.this, "account created successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        }

                    }

                    else{
                        try {
                            if (response.errorBody() != null) {
                                s = response.errorBody().string();

                                JSONObject jsonObject = new JSONObject(s);

                                Toast.makeText(Register.this, jsonObject.getString("username"), Toast.LENGTH_SHORT).show();
                                Toast.makeText(Register.this, jsonObject.getString("email"), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }


                    }


                }

                @Override
                public void onFailure(Call<DefaultApiResponse> call, Throwable t) {
//                    progressBar.setVisibility(View.INVISIBLE);
                        progressDialog.dismiss();
                    Toast.makeText(Register.this, "Unable to connect, please check your internet connection", Toast.LENGTH_LONG).show();



                }
            });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
    public void setupActionBar(){
        toolbar = findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        setTitle("Register");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }



}