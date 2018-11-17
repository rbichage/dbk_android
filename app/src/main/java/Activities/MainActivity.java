package com.example.reuben.donatebloodkenya.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.reuben.donatebloodkenya.R;

public class MainActivity extends AppCompatActivity {
    public  static  int SPLASH_TIMEOUT =4000;
    ImageView iv_launcher;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO-App controller
        iv_launcher = findViewById(R.id.iv_launcher);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.tvwelcome);

        Animation launcher =  AnimationUtils.loadAnimation(this, R.anim.launcher_transition);
            iv_launcher.startAnimation(launcher);
            progressBar.startAnimation(launcher);
            textView.startAnimation(launcher);




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
