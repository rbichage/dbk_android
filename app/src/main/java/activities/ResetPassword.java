package activities;

//import android.support.design.widget.Snackbar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import storage.SharedPrefManager;

public class ResetPassword extends AppCompatActivity {
    EditText editText;
    Button button;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        setTitle(R.string.action_reset);

        setupActionBar();








        button = findViewById(R.id.b_link);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editText.getText().toString();
                if (email.isEmpty()){
                    editText.setError("please enter email");
                    editText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editText.setError("enter a valid Email Address");
                    editText.requestFocus();
                }
                else
                    Toast.makeText(getApplicationContext(), "please check you mail(to be implemented)", Toast.LENGTH_SHORT).show();

             editText.addTextChangedListener(new TextWatcher() {
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


    }

    @Override
    public void onBackPressed() {

        finish();

    }

    public void setupActionBar(){
        toolbar = findViewById(R.id.reset_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar();

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}
