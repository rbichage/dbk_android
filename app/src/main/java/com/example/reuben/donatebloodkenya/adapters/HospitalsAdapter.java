package com.example.reuben.donatebloodkenya.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.HospitalViewHolder> {

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HospitalViewHolder extends RecyclerView.ViewHolder{

    public HospitalViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
}
