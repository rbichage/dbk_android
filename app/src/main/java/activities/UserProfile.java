package activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;

import java.util.Objects;

import fragments.EventsFragment;
import fragments.MeFragment;
import fragments.NewsFragment;

public class UserProfile extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton floatingActionButton;
    BottomNavigationView navigationView;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

    navigationView = findViewById(R.id.nav_view);

    navigationView.setOnNavigationItemSelectedListener(this);

    displayFragment(new  MeFragment());

    floatingActionButton = findViewById(R.id.fab_book);

    floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(UserProfile.this, "TODO: Implement fab", Snackbar.LENGTH_LONG).show();
        }
    });

    }

    public void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.user_container, fragment).
                commit();



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



        Fragment fragment = null;
        String title = "";

        switch (menuItem.getItemId()){
            case R.id.nav_news:
                fragment = new NewsFragment();
                title = "News";
                break;

            case R.id.nav_home:
                fragment = new MeFragment();
                title = "About Me";
                break;

            case R.id.nav_events:
                fragment = new EventsFragment();
                title = "Events";
                break;
        }

        if (fragment !=null){
            displayFragment(fragment);
            Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        }


        return true;
    }



}
