package com.example.reuben.donatebloodkenya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.models.Donation;

import java.util.List;

public class DonationsListAdapter extends RecyclerView.Adapter<DonationsListAdapter.ViewHolder> {
    private Context context;
    private List<Donation> donationsList;

    public DonationsListAdapter(Context context, List<Donation> donationsList) {
        this.context = context;
        this.donationsList = donationsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donation_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Donation donation = donationsList.get(position);

        holder.tvDonationDate.setText(String.format("Donation Date: %s", donation.getDateDonated()));
        holder.tvCountyName.setText(String.format("County Name: %s", donation.getCountyName()));
        holder.tvHospitalName.setText(String.format("Hospital Name: %s", donation.getHospitalName()));
        holder.tvAmount.setText(String.format("Amount Donated: %s mls", String.valueOf(donation.getAmountDonated())));


    }

    @Override
    public int getItemCount() {
        return donationsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView donationsCard;
        TextView tvDonationDate, tvCountyName, tvHospitalName, tvAmount;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            donationsCard = itemView.findViewById(R.id.donations_card);
            tvDonationDate = itemView.findViewById(R.id.tv_donaton_date);
            tvCountyName =  itemView.findViewById(R.id.tv_county_name);
            tvHospitalName = itemView.findViewById(R.id.tv_hospital_name);
            tvAmount = itemView.findViewById(R.id.tv_amount_donated);


        }
    }
}
