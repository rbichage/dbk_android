package com.example.reuben.donatebloodkenya.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

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
        editor.putString("first_name", donor.getFirst_name());
        editor.putString("token", donor.getToken());
        editor.putString("last_name", donor.getLast_name());
        editor.putString("birthdate", donor.getBirthdate());
        editor.putString("county_name", donor.getCounty_name());
        editor.putInt("age", donor.getAge());
        editor.putString("phone_number", donor.getPhone_number());
        editor.putString("blood_group", donor.getBlood_group());
        editor.putString("date_donated", donor.getDate_donated());
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
                sharedPreferences.getString("last_name", null),
                sharedPreferences.getString("birthdate", null),
                sharedPreferences.getInt("age", 0),
                sharedPreferences.getString("county_name", null),
                sharedPreferences.getString("image", null),
                sharedPreferences.getString("blood_group", null),
                sharedPreferences.getString("phone_number", null),
                sharedPreferences.getString("date_donated", null)


        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }

    public void saveAppointment(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.apply();
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
