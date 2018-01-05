package com.example.reuben.donatebloodkenya;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Delayed;

public class MainActivity extends AppCompatActivity {
    public  static  int SPLASH_TIMEOUT =4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login =  new Intent(MainActivity.this, Login.class);
                startActivity(login);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
