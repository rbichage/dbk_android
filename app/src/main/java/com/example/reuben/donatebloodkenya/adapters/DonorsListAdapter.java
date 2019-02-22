package com.example.reuben.donatebloodkenya.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reuben.donatebloodkenya.R;

public class DonorsListAdapter extends RecyclerView.Adapter<DonorsListAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView allNames;
        TextView countyName;
        TextView bloodGroup;
        TextView phoneNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           allNames = itemView.findViewById(R.id.tv_first_name);
           countyName = itemView.findViewById(R.id.tv_donor_county_name);
           bloodGroup = itemView.findViewById(R.id.tv_blood_group);
        }
    }
}
