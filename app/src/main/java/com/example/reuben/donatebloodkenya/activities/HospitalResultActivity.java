package com.example.reuben.donatebloodkenya.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.AppointmentResponse;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.models.Hospitals;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalResultActivity extends AppCompatActivity implements View.OnClickListener {
    Hospitals hospitals;
    private Button bSumbit;
    private TextView fullName, hospitalName, countyName, scheduleDate;
    EditText etScheduleDate;
    Donor donor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_result);

        hospitals = (Hospitals) getIntent().getSerializableExtra("hospital");

        donor = SharedPrefManager
                .getInstance(getApplicationContext())
                .getDonor();
        fullName = findViewById(R.id.user_name);
        fullName.setText(String.format("%s %s %s","Name: ", donor.getFirstName(), donor.getLastName()));

        hospitalName = findViewById(R.id.hospital_name);
        hospitalName.setText(String.format("Hospital Name: %s", hospitals.getHospital_name()));

        countyName = findViewById(R.id.county_name);
        countyName.setText(String.format("County Name: %s", hospitals.getCounty_name()));

        scheduleDate = findViewById(R.id.schedule_date);
        scheduleDate.setText("Schedule Date: Not Yet Set.");
        etScheduleDate = findViewById(R.id.et_schedule_date);
        etScheduleDate.setOnClickListener(this);


        bSumbit = findViewById(R.id.b_sumbit);
        bSumbit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_schedule_date:
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                etScheduleDate.setText(String.format(Locale.getDefault(), "%d-%d-%d", year, month + 1, dayOfMonth));
                               String dateChosen = etScheduleDate.getText().toString().trim();
                                scheduleDate.setText(String.format("Schedule Date: %s", dateChosen));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;

            case R.id.b_sumbit:
                submitAppointment();
                break;
        }
    }

    private void submitAppointment() {
        int id = donor.getId();
        String firstName = donor.getFirstName();
        String lastName = donor.getLastName();
        String countyName = donor.getCountyName();
        String phoneNumber = donor.getPhoneNumber();
        String scheduleDate = etScheduleDate.getText().toString().trim();
        String hospitalName = hospitals.getHospital_name();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        Call<AppointmentResponse> call = RetrofitClient.getInstance()
                .getApi()
                .bookAppointment(id, firstName, lastName, countyName, phoneNumber,
                scheduleDate, hospitalName);

        call.enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                if (response.isSuccessful()){
                    AppointmentResponse appointmentResponse = response.body();

                    SharedPrefManager.getInstance(getApplicationContext())
                            .saveAppointment(appointmentResponse);
                    progressDialog.dismiss();
                    Toast.makeText(HospitalResultActivity.this, "Successfully made an appointment", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), UserProfile.class));
                }

                else {
                    progressDialog.dismiss();
                    Toast.makeText(HospitalResultActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AppointmentResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HospitalResultActivity.this, "check your connection", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
