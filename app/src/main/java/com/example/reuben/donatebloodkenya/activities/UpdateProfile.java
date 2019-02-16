package com.example.reuben.donatebloodkenya.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.models.LoginResponse;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;

    androidx.appcompat.widget.Toolbar toolbar;




    private EditText etphone, etfirst_name, etlast_name, etemail, birth_date, etcurrent_password, etnew_password;
    private Spinner spinner_gender, spinner_county_name, spinner_blood_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        setupActionBar();


        findViewById(R.id.update_button).setOnClickListener(this);

        findViewById(R.id.update_password).setOnClickListener(this);

        findViewById(R.id.b_type).setOnClickListener(this);

        etphone = findViewById(R.id.profile_phone);
        etfirst_name = findViewById(R.id.profile_first);
        etlast_name = findViewById(R.id.profile_last);
        etemail = findViewById(R.id.profile_email);
        birth_date = findViewById(R.id.profile_birthdate);
        findViewById(R.id.profile_birthdate).setOnClickListener(this);
        spinner_gender = findViewById(R.id.profile_gender);
        spinner_county_name = findViewById(R.id.profile_county);
        spinner_blood_type = findViewById(R.id.blood_type);
        etcurrent_password = findViewById(R.id.current_password);
        etnew_password = findViewById(R.id.new_password);







    }

    private void updateProfile() {
        String phone_number = etphone.getText().toString().trim();
        if (phone_number.isEmpty()){
            etphone.setError("This field is required");
            etphone.requestFocus();

            return;

        }

        if (phone_number.length() != 10){
            etphone.setError("Phone number should be 10 characters");
            etphone.requestFocus();
            return;
        }

        String first_name = etfirst_name.getText().toString().trim();
        if (first_name.isEmpty()){
            etfirst_name.setError("this field is required");
            etfirst_name.requestFocus();
            return;

        }

        String last_name = etlast_name.getText().toString().trim();
        if (last_name.isEmpty()){
            etlast_name.setError("this field is required");
            etlast_name.requestFocus();
            return;


        }

        String email = etemail.getText().toString().trim();
        if (email.isEmpty()){
            etemail.setError("this field is required");
            etemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemail.setError("enter a valid email address");
            etemail.requestFocus();
            return;
        }

        String birthdate = birth_date.getText().toString().trim();
        if (birthdate.isEmpty()){
            birth_date.setError("This field is required");
            birth_date.requestFocus();
            return;
        }

        String county_name = spinner_county_name.getSelectedItem().toString();
        if (spinner_county_name.getSelectedItem().toString().equalsIgnoreCase("Choose County")){
            Toast.makeText(UpdateProfile.this, "please choose county", Toast.LENGTH_LONG).show();
            spinner_county_name.requestFocus();
            return;

        }

        String gender = spinner_gender.getSelectedItem().toString();
        if (spinner_gender.getSelectedItem().toString().equalsIgnoreCase("Choose gender")){
            Toast.makeText(UpdateProfile.this, "Please choose gender", Toast.LENGTH_LONG).show();
            spinner_gender.requestFocus();
            return;
        }

        progressDialog = new ProgressDialog(UpdateProfile.this, R.style.CustomDialog);
        progressDialog.setMessage("loading, please wait ...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        Donor donor = SharedPrefManager.getInstance(getApplicationContext()).getDonor();

        Call<LoginResponse> call = RetrofitClient.getInstance()
                .getApi().updateProfile(donor.getId(), first_name, last_name, email, birthdate, county_name, gender, phone_number);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                String s = null;

                if (response.isSuccessful()){
                    assert response.body() != null;
                    s = response.body().getMessage();
                    SharedPrefManager.getInstance(getApplicationContext()).saveDonor(response.body().getDonor());
                    Toast.makeText(UpdateProfile.this,s , Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                    onBackPressed();
                }

                if (s!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        Toast.makeText(UpdateProfile.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateProfile.this, "Unable to connect, check your settings", Toast.LENGTH_LONG).show();

            }
        });





    }

    private  void updatePassword(){
        String current_password = etcurrent_password.getText().toString().trim();
            if(current_password.isEmpty()){
                etcurrent_password.setError("This field is required");
                etcurrent_password.requestFocus();
                return;
            }

            if (current_password.length()<8){
                etcurrent_password.setError("This password is too short");
                etcurrent_password.requestFocus();
                return;

            }


        String new_password = etnew_password.getText().toString().trim();

        if(new_password.isEmpty()){
            etnew_password.setError("This field is required");
            etnew_password.requestFocus();
            return;
        }

        if (new_password.length()<8){
            etnew_password.setError("This password is too short");
            etnew_password.requestFocus();
            return;

        }

        Toast.makeText(UpdateProfile.this, "TODO//: implement call", Toast.LENGTH_LONG).show();
    }

    public void updateBloodType(){
        String blood_group = spinner_blood_type.getSelectedItem().toString().trim();
        if (spinner_blood_type.getSelectedItem().toString().trim().equalsIgnoreCase("Not Sure")){
            Toast.makeText(UpdateProfile.this, "Choose a valid blood group", Toast.LENGTH_LONG).show();

            return;
        }


        progressDialog = new ProgressDialog(UpdateProfile.this, R.style.CustomDialog);
        progressDialog.setMessage("loading, please wait ...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        final Donor donor = SharedPrefManager.getInstance(getApplicationContext()).getDonor();


        Call <LoginResponse> call = RetrofitClient.getInstance()
                .getApi()
                .updateBloodType(donor.getId(),blood_group);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                String s =null;

                LoginResponse loginResponse = response.body();

                if (response.isSuccessful()){
                    assert loginResponse != null;
                    s = loginResponse.getMessage();
                    if (response.body() != null) {
                        SharedPrefManager.getInstance(getApplicationContext()).saveDonor(response.body().getDonor());
                    }
                    Toast.makeText(UpdateProfile.this, s, Toast.LENGTH_LONG).show();

                    if (response.body() != null) {
                        Log.d("Update profile", response.body().toString());
                    }
                    progressDialog.dismiss();
                }

                else {
                    try {

                        if (response.errorBody() != null) {
                            s = response.errorBody().string();
                            JSONObject jsonObject = new JSONObject(s);
                            Toast.makeText(UpdateProfile.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            Log.e("Update profile", s);
                            progressDialog.dismiss();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }





            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(UpdateProfile.this, "Unable to connect, check your settings", Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void setupActionBar(){
        toolbar = findViewById(R.id.update_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        setTitle("Update Profile");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.update_button:
                updateProfile();
                break;

            case R.id.update_password:
                updatePassword();
                break;

            case R.id.profile_birthdate:
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
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

            case R.id.b_type:
                updateBloodType();
                break;


        }

    }


}
