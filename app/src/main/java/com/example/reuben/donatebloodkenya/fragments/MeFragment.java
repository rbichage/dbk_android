package com.example.reuben.donatebloodkenya.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.example.reuben.donatebloodkenya.R;
import com.example.reuben.donatebloodkenya.activities.AppointmentList;
import com.example.reuben.donatebloodkenya.activities.BookAppointment;
import com.example.reuben.donatebloodkenya.activities.DonationsLIstActivity;
import com.example.reuben.donatebloodkenya.activities.UpdateProfile;
import com.example.reuben.donatebloodkenya.api.RetrofitClient;
import com.example.reuben.donatebloodkenya.models.Donor;
import com.example.reuben.donatebloodkenya.storage.SharedPrefManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class MeFragment extends Fragment implements View.OnClickListener {

    private TextView name, county_name, age, tvAppoints, previousDonations;
    private static final int GET_FROM_GALLERY = 3;
    private String image_url;
    CircleImageView imageView;


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
        image_url = "http://192.168.43.65:8000" + donor.getImage();

        name = view.findViewById(R.id.name);
        county_name = view.findViewById(R.id.county_name);
        age = view.findViewById(R.id.age);
        imageView = view.findViewById(R.id.user_profile_photo);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));

        previousDonations = view.findViewById(R.id.previous_donations);
        previousDonations.setOnClickListener(this);

        if (donor.getImage().startsWith("/")){
            Glide.with(this)
                    .load(image_url)
                    .placeholder(getResources().getDrawable(R.drawable.ic_launcher))
                    .into(imageView);
        }
        else
            Glide.with(this)
                    .load(donor.getImage())
                    .placeholder(getResources().getDrawable(R.drawable.ic_launcher))
                    .into(imageView);



        imageView.setOnClickListener(this);

        name.setText(donor.getFirstName() + "  " + donor.getLastName());
        county_name.setText(" " + donor.getCountyName());
        age.setText("Age: " + donor.getAge() + " years.");

        view.findViewById(R.id.fab_book).setOnClickListener(this);
        view.findViewById(R.id.edit_profile).setOnClickListener(this);

        tvAppoints = view.findViewById(R.id.tv_appoints);
        tvAppoints.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edit_profile:
                Intent intent = new Intent(getContext(), UpdateProfile.class);
                startActivity(intent);
                break;

            case R.id.fab_book:
                boolean hasAppointment = SharedPrefManager.getInstance(getContext()).getAppointment().getHasAppointment();

                Toast.makeText(getContext(), String.valueOf(hasAppointment), Toast.LENGTH_SHORT).show();
                if (hasAppointment){
                    intent = new Intent(getContext(), BookAppointment.class);
                    startActivity(intent);
                }

                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("You already have a pending appointment");
                    builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                    builder.show();
                }

                break;
            case R.id.user_profile_photo:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Profile Photo");

                builder.setPositiveButton("View Photo", (dialog, which) -> dialog.cancel());

                builder.setNegativeButton("Update Photo", (dialog, which) -> updatePhoto());

                builder.show();
                break;

            case R.id.tv_appoints:
                startActivity(new Intent(getContext(), AppointmentList.class));
                break;

            case R.id.previous_donations:
                startActivity(new Intent(getContext(), DonationsLIstActivity.class));
                break;

        }


    }

    private void updatePhoto() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .setOutputCompressQuality(40)
                .start(getContext(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);


            if (resultCode == RESULT_OK){
                final Uri resultUri = result.getUri();

                File file = new File(resultUri.getPath());

                MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                int id = SharedPrefManager.getInstance(getContext()).getDonor().getId();

                Call<ResponseBody> call = RetrofitClient.getInstance()
                        .getApi().updatePhoto(id, part);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            imageView.setImageURI(resultUri);
                            getDonor();
                        }
                        else {
                            try {
                                Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });




            }

            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void getDonor() {
        int id = SharedPrefManager.getInstance(getContext()).getDonor().getId();
        Call<Donor> call = RetrofitClient.getInstance().getApi().getDonorDetails(id);
        call.enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {
                Donor donor = response.body();
                SharedPrefManager.getInstance(getContext()).saveDonor(donor);
                Glide.with(getContext()).load(donor.getImage()).into(imageView);

            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {

            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

