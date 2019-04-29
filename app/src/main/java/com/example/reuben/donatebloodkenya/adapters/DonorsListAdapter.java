package com.example.reuben.donatebloodkenya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.models.Donor;

import java.util.List;

public class DonorsListAdapter extends RecyclerView.Adapter<DonorsListAdapter.ViewHolder> {
    private Context context;
    private List<Donor> donorList;

    public DonorsListAdapter(Context context, List<Donor> donorList) {
        this.context = context;
        this.donorList = donorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donor_card_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Donor donor = donorList.get(position);

        holder.allNames.setText(String.format("%s %s", donor.getFirstName(), donor.getLastName()));
        holder.countyName.setText(donor.getCountyName());
        holder.bloodGroup.setText(donor.getBloodGroup());
        holder.phoneNumber.setText(donor.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView allNames;
        TextView countyName;
        TextView bloodGroup;
        TextView phoneNumber;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
           allNames = itemView.findViewById(R.id.tv_first_name);
           countyName = itemView.findViewById(R.id.tv_donor_county_name);
           bloodGroup = itemView.findViewById(R.id.tv_blood_group);
           phoneNumber = itemView.findViewById(R.id.tv_donor_phone);
        }
    }
}
