package fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.reuben.donatebloodkenya.R;

import java.util.Objects;

import activities.BookAppointment;
import activities.UpdateProfile;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import models.Donor;
import storage.SharedPrefManager;


public class MeFragment extends Fragment implements View.OnClickListener{

    private TextView name, county_name, age;


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_me, container, false);



    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.about_toolbar);
        Objects.requireNonNull(getActivity()).setActionBar(toolbar);
        getActivity().getActionBar();
        getActivity().setTitle("About Me");


        Donor donor = SharedPrefManager.getInstance(getActivity()).getDonor();
        String image_url = "http://10.0.2.2:8000" + donor.getImage();

        name = view.findViewById(R.id.name);
         county_name = view.findViewById(R.id.county_name);
         age = view.findViewById(R.id.age);
        final ImageView imageView = view.findViewById(R.id.user_profile_photo);

        Glide
                .with(view.getContext())
                .load(image_url)
                .into(imageView)
                ;





        name.setText(donor.getFirst_name() + "  " + donor.getLast_name());
        county_name.setText(" " +donor.getCounty_name());
        age.setText("Age: " + donor.getAge() + " years.");

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.donor_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Donor donor = SharedPrefManager.getInstance(getActivity()).getDonor();
                name.setText(donor.getFirst_name() + "  " + donor.getLast_name());
                county_name.setText(" " +donor.getCounty_name());
                age.setText("Age: " + donor.getAge() + " years.");
                swipeRefreshLayout.setRefreshing(false);



            }
        });







        view.findViewById(R.id.fab_book).setOnClickListener(this);
        view.findViewById(R.id.edit_profile).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.edit_profile:
                Intent intent = new Intent(getContext(), UpdateProfile.class);
                startActivity(intent);
                break;

            case R.id.fab_book:
                intent = new Intent(getContext(), BookAppointment.class);
                startActivity(intent);
                break;




        }



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh){
            SharedPrefManager.getInstance(getActivity()).getDonor();
            Toast.makeText(getActivity(), "Refreshing details", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}



