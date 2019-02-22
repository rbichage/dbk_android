package com.example.reuben.donatebloodkenya.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.fragments.HospitalsListFragment;

import java.util.Objects;

public class BookAppointment extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_appointment);

        if (savedInstanceState != null){
            displayFragment(new HospitalsListFragment());
        }


        toolbar = findViewById(R.id.book_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.hospitals_container, fragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayFragment(new HospitalsListFragment());
    }
}
