package com.example.reuben.donatebloodkenya.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.reuben.donatebloodkenya.models.AppointmentResponse;
import com.example.reuben.donatebloodkenya.models.AuthHeader;
import com.example.reuben.donatebloodkenya.models.Donor;

public class SharedPrefManager {


    private static final String SHARED_PREF_NAME = "donor_pref";

    @SuppressLint("StaticFieldLeak")
    private static SharedPrefManager sharedPrefManager;
    private   Context context;

    private SharedPrefManager(Context context){

        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (sharedPrefManager == null){
            sharedPrefManager = new SharedPrefManager(context);
        }
        return sharedPrefManager;
    }

    public  void saveDonor(Donor donor){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", donor.getId());
        editor.putString("username", donor.getUsername());
        editor.putString("email", donor.getEmail());
        editor.putString("first_name", donor.getFirstName());
        editor.putString("token", donor.getToken());
        editor.putString("gender", donor.getGender());
        editor.putString("last_name", donor.getLastName());
        editor.putString("birthdate", donor.getBirthdate());
        editor.putString("county_name", donor.getCountyName());
        editor.putInt("age", donor.getAge());
        editor.putBoolean("has_appointment", donor.getHasAppointment());
        editor.putString("phone_number", donor.getPhoneNumber());
        editor.putString("blood_group", donor.getBloodGroup());
        editor.putString("date_donated", donor.getDateDonated());
        editor.putString("image", donor.getImage());

        editor.apply();

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;

    }

    public Donor getDonor(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new Donor(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("first_name", null),
                sharedPreferences.getString("token", null),
                sharedPreferences.getString("gender", null),
                sharedPreferences.getString("last_name", null),
                sharedPreferences.getString("birthdate", null),
                sharedPreferences.getString("county_name", null),
                sharedPreferences.getInt("age", 0),
                sharedPreferences.getBoolean("has_appointment", false),
                sharedPreferences.getString("phone_number", null),
                sharedPreferences.getString("blood_group", null),
                sharedPreferences.getString("date_donated", null),
                sharedPreferences.getString("image", null)


                );
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }

    public void saveAppointment(AppointmentResponse appointmentResponse){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("appointment_county", appointmentResponse.getCountyName());
        editor.putString("schedule_date", appointmentResponse.getScheduleDate());
        editor.putString("hospital_name", appointmentResponse.getHospitalName());
        editor.putBoolean("has_appointment", appointmentResponse.getHasAppointment());
        editor.apply();
    }


    public AppointmentResponse getAppointment(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new AppointmentResponse(
                sharedPreferences.getString("appointment_county", null),
                sharedPreferences.getString("schedule_date", null),
                sharedPreferences.getString("hospital_name", null),
                sharedPreferences.getBoolean("has_appointment", false)
        );
    }

    public void saveAuthHeader(AuthHeader authHeader){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("auth", authHeader.getAuthHeader());
        editor.apply();
    }

    public AuthHeader getAuthHeader(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new AuthHeader(
                sharedPreferences.getString("auth", null)
        );

    }
}
