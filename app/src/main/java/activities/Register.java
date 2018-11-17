package com.example.reuben.donatebloodkenya;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import activities.Login;

public class Register extends AppCompatActivity implements View.OnClickListener{

   private EditText etUname, etFirstName, etLastname, etEmail, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etUname = findViewById(R.id.etuname);
        etFirstName = findViewById(R.id.etfirstname);
        etLastname = findViewById(R.id.etlastname);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);




        findViewById(R.id.bregister).setOnClickListener(this);

        findViewById(R.id.tvLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bregister:
                userSignUp();

                break;
            case R.id.tvLogin:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }



    }

    private void userSignUp() {
        String username = etEmail.getText().toString().trim();
            if (username.isEmpty()){
                etUname.setError("enter a username");
                etUname.requestFocus();
                return;
            }

         String email = etEmail.getText().toString().trim();
            if (email.isEmpty()){
                etEmail.setError("this field is required");
                etEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.setError("enter a valid email address");
                etEmail.requestFocus();
                return;
            }

          String first_name = etFirstName.getText().toString().trim();
            if (first_name.isEmpty()){
                etFirstName.setError("this field is required");
                etFirstName.requestFocus();
                return;

            }

        String last_name = etLastname.getText().toString().trim();
            if (last_name.isEmpty()){
                etLastname.setError("this field is required");
                etLastname.requestFocus();
                return;


            }

        String password = etPassword.getText().toString().trim();
            if (password.isEmpty()){
                etPassword.setError("this field is required");
                etPassword.requestFocus();
                return;
            }

            if (password.length() <8 ){
                etPassword.setError("This password is too short");
                etPassword.requestFocus();
                return;

            }


        else

            Toast.makeText(this, "thank you, to be implemented", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onBackPressed() {

        finish();

    }
}