package com.example.reuben.donatebloodkenya;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity {
    TextView tvReset, tvRegister;
    EditText etuname, etpass;
    Button blogin;
    ImageView facebook, instagram, twitter;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.action_sign_in);


        etuname = findViewById(R.id.etuname);
        etpass = findViewById(R.id.et_password);
        blogin = findViewById(R.id.blogin);
            blogin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //validating Edit text fields
                    if (etuname.getText().toString().trim().equalsIgnoreCase(""))
                        etuname.setError("username is required");
                    else if (etpass.getText().toString().trim().equalsIgnoreCase(""))
                        etpass.setError("please enter password");

                    else
                        Snackbar.make(v ,"Thank you, feature coming soon", Snackbar.LENGTH_SHORT ).show();
                    etuname.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    etpass.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            });



            tvReset = findViewById(R.id.tvReset);
            tvReset.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reset = new Intent(Login.this, ResetPassword.class);
                    startActivity(reset);
                }
            });

            tvRegister =findViewById(R.id.tvRegister);
                tvRegister.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v, "To be soon added", Snackbar.LENGTH_SHORT ).show();
                    }
                });
            twitter = findViewById(R.id.twitter);
                twitter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri webpage = Uri.parse("https://www.twitter.com/reuben_bichage");
                        Intent igintent =new  Intent(Intent.ACTION_VIEW, webpage);
                        PackageManager packageManager = getPackageManager();
                        List<ResolveInfo> activities = packageManager.queryIntentActivities(igintent, 0);
                        boolean isIntentSafe = activities.size() > 0;

                        if (isIntentSafe){
                            startActivity(igintent);
                        }
                        else
                            Snackbar.make(v, "No app can open this", Snackbar.LENGTH_SHORT).show();

                    }
                });
            instagram = findViewById(R.id.instagram);
                instagram.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri webpage = Uri.parse("https://www.instagram.com/reuben_bichage");
                        Intent twitterintent =new  Intent(Intent.ACTION_VIEW, webpage);
                        PackageManager packageManager = getPackageManager();
                        List<ResolveInfo> activities = packageManager.queryIntentActivities(twitterintent, 0);
                        boolean isIntentSafe = activities.size() > 0;

                        if (isIntentSafe){
                            startActivity(twitterintent);
                        }
                        else
                            Snackbar.make(v, "No app can open this", Snackbar.LENGTH_SHORT).show();

                    }
                });
            facebook = findViewById(R.id.facebook);
                facebook.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Uri webpage = Uri.parse("https://www.facebook.com/rube.bichage");
                       Intent fbintent =new  Intent(Intent.ACTION_VIEW, webpage);
                        PackageManager packageManager = getPackageManager();
                        List<ResolveInfo> activities = packageManager.queryIntentActivities(fbintent, 0);
                        boolean isIntentSafe = activities.size() > 0;

                        if (isIntentSafe){
                            startActivity(fbintent);
                        }
                        else
                            Snackbar.make(v, "No app can open this", Snackbar.LENGTH_SHORT).show();



                    }
                });



    }
    }








