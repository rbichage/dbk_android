package com.example.reuben.donatebloodkenya.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodTypeActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_type);

        Toolbar toolbar = findViewById(R.id.update_blood_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        spinner = findViewById(R.id.blood_type);

        button = findViewById(R.id.b_type);
        button.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.b_type){
            updateType();
        }
    }

    private void updateType() {
        String bloodGroup = spinner.getSelectedItem().toString().trim();
        if (bloodGroup.equals("Not Sure")){
            Toast.makeText(this, "choose type", Toast.LENGTH_SHORT).show();
            return;
        }

        Integer id = SharedPrefManager.getInstance(getApplicationContext()).getDonor().getId();
        Call<Donor> call = RetrofitClient.getInstance().getApi().updateBloodType(id, bloodGroup);
        call.enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {
                if (response.isSuccessful()){
                    onBackPressed();
                    Toast.makeText(BloodTypeActivity.this, "updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {

            }
        });
    }
}
