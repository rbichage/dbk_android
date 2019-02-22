package com.example.reuben.donatebloodkenya.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reuben.donatebloodkenya.R;

import com.example.reuben.donatebloodkenya.activities.BloodTypeActivity;
import com.example.reuben.donatebloodkenya.activities.UpdatePassword;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_settings, container, false);



    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Toolbar toolbar = view.findViewById(R.id.about_toolbar);
//        Objects.requireNonNull(getActivity()).setActionBar(toolbar);
//        getActivity().getActionBar();
//        getActivity().setTitle("Settings");

        TextView name;
        name= view.findViewById(R.id.profile_name);
        Donor donor = SharedPrefManager.getInstance(getContext()).getDonor();
        name.setText(donor.getFirst_name() + " " + donor.getLast_name());

        TextView updateInfo, updateBlood, updatePassword;

        updatePassword = view.findViewById(R.id.tv_update_password);
        updatePassword.setOnClickListener(this);

        updateBlood = view.findViewById(R.id.update_blood_type);
        updateBlood.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_update_password:
                startActivity(new Intent(getContext(), UpdatePassword.class));
                break;

            case R.id.update_blood_type:
                startActivity(new Intent(getContext(), BloodTypeActivity.class));
                break;
        }
    }
}
