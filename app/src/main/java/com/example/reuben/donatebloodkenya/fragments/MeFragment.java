package com.example.reuben.donatebloodkenya.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.reuben.donatebloodkenya.R;
import com.yalantis.ucrop.UCrop;

import com.example.reuben.donatebloodkenya.activities.BookAppointment;
import com.example.reuben.donatebloodkenya.activities.UpdateProfile;
import de.hdodenhof.circleimageview.CircleImageView;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;

import static android.app.Activity.RESULT_OK;


public class MeFragment extends Fragment implements View.OnClickListener {

    private TextView name, county_name, age;
    public static final int GET_FROM_GALLERY = 3;


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



        Donor donor = SharedPrefManager.getInstance(getActivity()).getDonor();
        String image_url = "http://10.0.2.2:8000" + donor.getImage();

        name = view.findViewById(R.id.name);
        county_name = view.findViewById(R.id.county_name);
        age = view.findViewById(R.id.age);
        CircleImageView imageView = view.findViewById(R.id.user_profile_photo);


        Glide
                .with(view.getContext())
                .load(image_url)
                .into(imageView)
        ;



        imageView.setOnClickListener(this);

        name.setText(donor.getFirst_name() + "  " + donor.getLast_name());
        county_name.setText(" " + donor.getCounty_name());
        age.setText("Age: " + donor.getAge() + " years.");

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.donor_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Donor donor = SharedPrefManager.getInstance(getActivity()).getDonor();
                name.setText(donor.getFirst_name() + "  " + donor.getLast_name());
                county_name.setText(" " + donor.getCounty_name());
                age.setText("Age: " + donor.getAge() + " years.");
                swipeRefreshLayout.setRefreshing(false);


            }
        });


        view.findViewById(R.id.fab_book).setOnClickListener(this);
        view.findViewById(R.id.edit_profile).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edit_profile:
                Intent intent = new Intent(getContext(), UpdateProfile.class);
                startActivity(intent);
                break;

            case R.id.fab_book:
                intent = new Intent(getContext(), BookAppointment.class);
                startActivity(intent);
                break;
            case R.id.user_profile_photo:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Profile Photo");

                builder.setPositiveButton("View Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                    }
                });

                builder.setNegativeButton("Update Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new
                                Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                    }
                });

                builder.show();

        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP){
            final Uri resultUri = UCrop.getOutput(data);

        }
        else if (resultCode ==UCrop.RESULT_ERROR){
            final Throwable cropError = UCrop.getError(data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            SharedPrefManager.getInstance(getActivity()).getDonor();
            Toast.makeText(getActivity(), "Refreshing details", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}



