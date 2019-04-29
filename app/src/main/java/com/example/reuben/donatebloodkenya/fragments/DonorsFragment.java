package com.example.reuben.donatebloodkenya.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.adapters.DonorsListAdapter;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.Donor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonorsFragment extends Fragment {
    RecyclerView recyclerView;
    DonorsListAdapter donorsListAdapter;
    private List<Donor> donors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_donors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        donors = new ArrayList<>();

        recyclerView = view.findViewById(R.id.donors_recycler);

        Call<List<Donor>> call = RetrofitClient.getInstance().getApi().getDonors();
        call.enqueue(new Callback<List<Donor>>() {
            @Override
            public void onResponse(Call<List<Donor>> call, Response<List<Donor>> response) {
                if (response.isSuccessful()){
                    donors = response.body();
                    donorsListAdapter = new DonorsListAdapter(getContext(), donors);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(donorsListAdapter);
                    recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<List<Donor>> call, Throwable t) {

            }
        });
    }
}
