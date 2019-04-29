package com.example.reuben.donatebloodkenya.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.AuthHeader;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePassword extends AppCompatActivity implements View.OnClickListener {

    EditText etOldPassword, etNewPassword, etRepeatNewPassword;
    Button bUpdate;
    Toolbar toolbar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

            setupActionBar();

            etOldPassword = findViewById(R.id.et_old_password);
            etNewPassword = findViewById(R.id.new_password);
            etRepeatNewPassword = findViewById(R.id.repeat_new_password);
            bUpdate = findViewById(R.id.b_update);
            bUpdate.setOnClickListener(this);

    }

    public void setupActionBar(){
        toolbar = findViewById(R.id.update_password_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b_update){
            updatePassword();
        }


    }

    private void updatePassword() {
        String current_password = etOldPassword.getText().toString().trim();
        if (current_password.isEmpty()){
            etOldPassword.setError("This field is required");
            return;
        }

        if (current_password.length() <8){
            etOldPassword.setError("This password is too short");
            return;
        }

        String new_password_test = etNewPassword.getText().toString().trim();
        if (new_password_test.isEmpty()){
            etNewPassword.setError("This field is required");
            return;
        }

        if (new_password_test.length() <8){
            etNewPassword.setError("This password is too short");
            return;
        }

        final String new_password = etRepeatNewPassword.getText().toString().trim();
        if (!new_password.matches(new_password_test)){
            etRepeatNewPassword.setError("These passwords do not match");
            etRepeatNewPassword.requestFocus();
            return;

        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating password");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .updatePassword(new_password, current_password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UpdatePassword.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    String username = SharedPrefManager.getInstance(getApplicationContext()).getDonor().getUsername();
                    String auth = Base64.encodeToString((username +":"+ new_password).getBytes(), Base64.NO_WRAP);
                    AuthHeader authHeader = new AuthHeader(auth);
                    SharedPrefManager.getInstance(getApplicationContext()).saveAuthHeader(authHeader);
                    onBackPressed();


                }

                else {
                    try {
                        String s= response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(s);
                        Toast.makeText(UpdatePassword.this, jsonObject.getString("detail"), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    } catch (IOException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UpdatePassword.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }
}
