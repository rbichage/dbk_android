package com.example.reuben.donatebloodkenya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.models.Hospitals;

import java.util.List;

public class BookAppointmentAdapter extends RecyclerView.Adapter<BookAppointmentAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    private List<Hospitals> hospitals_list;

    public BookAppointmentAdapter(Context context, List<Hospitals> hospitals_list) {
        this.context = context;
        this.hospitals_list = hospitals_list;
    }

    public void setHospitals_list(List<Hospitals> hospitals_list) {
        this.hospitals_list = hospitals_list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_card_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.hospital_name.setText(hospitals_list.get(position).getHospital_name());
        holder.county_name.setText(hospitals_list.get(position).getCounty_name());
        holder.hospital_phone.setText(hospitals_list.get(position).getPhone_number());


    }

    @Override
    public int getItemCount() {
        if (hospitals_list != null) {
            return hospitals_list.size();
        }
        Toast.makeText(context, "No hospitals yet", Toast.LENGTH_SHORT).show();
        return 0;
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView hospital_name;
        TextView county_name;
        TextView hospital_phone;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            hospital_name = itemView.findViewById(R.id.tv_hospital_name);
            county_name = itemView.findViewById(R.id.tv_county_name);
            hospital_phone = itemView.findViewById(R.id.tv_hospital_phone);

        }
    }
}
