package activities;

//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;

public class ResetPassword extends AppCompatActivity {
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        setTitle(R.string.action_reset);

        editText = findViewById(R.id.etemailsend);

        button = findViewById(R.id.b_link);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equalsIgnoreCase(""))
                    editText.setError("please enter email");
                else if (!editText.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {

                    editText.setError("enter a valid Email Address");

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

}
