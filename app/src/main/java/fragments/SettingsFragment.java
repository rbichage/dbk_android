package fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.reuben.donatebloodkenya.R;

import java.util.Objects;

import activities.UpdateProfile;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import models.Donor;
import storage.SharedPrefManager;

public class SettingsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_settings, container, false);



    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.about_toolbar);
        Objects.requireNonNull(getActivity()).setActionBar(toolbar);
        getActivity().getActionBar();
        getActivity().setTitle("Settings");

        TextView name;
        name= view.findViewById(R.id.profile_name);
        Donor donor = SharedPrefManager.getInstance(getContext()).getDonor();
        name.setText(donor.getFirst_name() + " " + donor.getLast_name());

        TextView textView = view.findViewById(R.id.tv_open_profile);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateProfile.class);

                startActivity(intent);
            }
        });

    }
}
