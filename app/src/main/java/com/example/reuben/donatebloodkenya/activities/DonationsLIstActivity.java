package com.example.reuben.donatebloodkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.adapters.DonationsListAdapter;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.Donation;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationsLIstActivity extends AppCompatActivity {
    DonationsListAdapter donationsListAdapter;
    List<Donation> donationList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_list);

        recyclerView = findViewById(R.id.donations_recycler);
        donationList = new ArrayList<>();

        String username = SharedPrefManager.getInstance(this).getDonor().getUsername();
        Call<List<Donation>> call = RetrofitClient.getInstance().getApi().getDonations(username);

        call.enqueue(new Callback<List<Donation>>() {
            @Override
            public void onResponse(Call<List<Donation>> call, Response<List<Donation>> response) {
                if (response.isSuccessful()){
                    donationList = response.body();
                    donationsListAdapter = new DonationsListAdapter(getApplicationContext(), donationList);
                    recyclerView.setAdapter(donationsListAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<List<Donation>> call, Throwable t) {

            }
        });



    }
}
