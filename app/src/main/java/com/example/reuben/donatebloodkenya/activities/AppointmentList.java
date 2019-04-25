package com.example.reuben.donatebloodkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.models.AppointmentResponse;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppointmentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        TextView tvHospitalName, tvCountyName, tvScheduleDate, tvIsActive;
        AppointmentResponse appointmentResponse = SharedPrefManager.getInstance(getApplicationContext())
                .getAppointment();



        Toolbar toolbar = findViewById(R.id.appointment_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        tvHospitalName = findViewById(R.id.hospital_name);
        tvHospitalName.setText(String.format("Hospital Name: %s", appointmentResponse.getHospitalName()));

        tvCountyName = findViewById(R.id.county_name);
        tvCountyName.setText(String.format("County Name: %s", appointmentResponse.getCountyName()));

        tvScheduleDate = findViewById(R.id.schedule_date);
        tvScheduleDate.setText(String.format("Schedule Date: %s", appointmentResponse.getScheduleDate()));
        tvIsActive = findViewById(R.id.is_active);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
