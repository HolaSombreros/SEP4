package com.example.farmerama.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.UserRole;
import com.example.farmerama.viewmodel.AccountViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AccountFragment extends Fragment {

    private NavController navController;
    private ImageView profilePicture;
    private TextView email;
    private TextView name;
    private Spinner role;
    private AccountViewModel viewModel;
    private FloatingActionButton edit;
    private StorageReference storageRef;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AccountViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        profilePicture = view.findViewById(R.id.userProfilePicture);
        progressBar = view.findViewById(R.id.progressbar);
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.name);
        role = view.findViewById(R.id.myRole);
        edit = view.findViewById(R.id.edit);
    }

    private void setUpViews() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);

        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if(user != null){
                email.setText(user.getEmail());
//                email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_alternate_email_24, 0, 0, 0);
                name.setText(user.getUserName());
//                name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_account_circle_24, 0, 0, 0);

                if (user.getRole().equals(UserRole.ADMINISTRATOR))
                    role.setSelection(1);
                else
                    role.setSelection(0);
                role.setEnabled(false);

//                role.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_work_24, 0, 0, 0);
                progressBar.setVisibility(View.VISIBLE);
                storageRef = FirebaseStorage.getInstance().getReference().child("users/"+user.getUserId()+"/profile.jpg");
                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilePicture);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });

        edit.setOnClickListener(v -> {
            navController.navigate(R.id.editAccountFragment);
        });
    }
}