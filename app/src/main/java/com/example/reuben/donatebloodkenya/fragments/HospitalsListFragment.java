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
import com.example.reuben.donatebloodkenya.adapters.BookAppointmentAdapter;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.models.Hospitals;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAppointmentAdapter bookAppointmentAdapter;
    private List<Hospitals> hospitals;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hospitals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        hospitals = new ArrayList<>();
        recyclerView = view.findViewById(R.id.hospitals_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAppointmentAdapter = new BookAppointmentAdapter(getContext(), hospitals);
        recyclerView.setAdapter(bookAppointmentAdapter);

        String county_name = SharedPrefManager.getInstance(getContext()).getDonor().getCountyName();
        Call<List<Hospitals>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getHospitals(county_name);

        call.enqueue(new Callback<List<Hospitals>>() {
            @Override
            public void onResponse(Call<List<Hospitals>> call, Response<List<Hospitals>> response) {
                hospitals = response.body();
                bookAppointmentAdapter.setHospitals_list(hospitals);
            }

            @Override
            public void onFailure(Call<List<Hospitals>> call, Throwable t) {

            }
        });
    }
}
