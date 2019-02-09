package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.reuben.donatebloodkenya.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import fragments.DonorsFragment;
import fragments.EventsFragment;
import fragments.MeFragment;
import fragments.NewsFragment;
import fragments.SettingsFragment;
import storage.SharedPrefManager;

public class UserProfile extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

         toolbar = findViewById(R.id.profile_toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar();
            setTitle("About Me");


        new DrawerBuilder().withActivity(this).build();

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(1).withIcon(R.drawable.ic_home).withName(R.string.drawer_home);
        PrimaryDrawerItem profile = new PrimaryDrawerItem().withIdentifier(2).withIcon(R.drawable.ic_person_black).withName(R.string.drawer_profile);
        final PrimaryDrawerItem settings = new PrimaryDrawerItem().withIdentifier(3).withIcon(R.drawable.ic_settings).withName(R.string.drawer_settings);
        final PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(4).withIcon(R.drawable.logout).withName(R.string.drawer_logout);

        SecondaryDrawerItem contact = new SecondaryDrawerItem().withIdentifier(5).withIcon(R.drawable.ic_contact_us).withName(R.string.drawer_contact);
        final SecondaryDrawerItem about = new SecondaryDrawerItem().withIdentifier(6).withIcon(R.drawable.ic_info_outline_black_24dp).withName(R.string.drawer_about);


        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSliderBackgroundColorRes(R.color.material_drawer_background)
                .addDrawerItems(
                        home, profile, settings, logout,
                        new DividerDrawerItem(),
                        contact, about
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        if (drawerItem.equals(4)) {
                            SharedPrefManager.getInstance(getApplicationContext()).clear();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                        if (drawerItem.equals(6)) {
                            Intent intent = new Intent(getApplicationContext(), About.class);
                            startActivity(intent);
                        }
                        if (drawerItem.equals(4)) {
                            Fragment settings = new SettingsFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.user_container, settings, "Settings")
                                    .addToBackStack(null)
                                    .commit();

                        }


                        return false;
                    }
                })
                .build();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setOnNavigationItemSelectedListener(this);
            displayFragment(new MeFragment());



    }
    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(UserProfile.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }



    public void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.user_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

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
            case  R.id.nav_settings:
                fragment = new SettingsFragment();
                title = "Settings";
                break;
            case R.id.nav_donors:
                fragment = new DonorsFragment();
                title ="Donors";

        }

        if (fragment !=null){
            displayFragment(fragment);
            Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        }


        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
