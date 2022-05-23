package com.example.farmerama.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farmerama.R;
import com.example.farmerama.viewmodel.AccountViewModel;
import com.example.farmerama.viewmodel.AddEditAreaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountFragment extends Fragment {

    private TextView email;
    private TextView name;
    private TextView role;
    private AccountViewModel viewModel;
    private FloatingActionButton edit;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AccountViewModel.class);
        initializeViews(view);

        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if(user != null){
                email.setText(user.getEmail());
                name.setText(user.getName());
                role.setText(user.getRole());
            }
        });

        edit.setOnClickListener(v -> {
            //todo navigate to edit
//            Navigation.findNavController(view).navigate(R.id.EditProfileOrIDK);
        });
    }

    private void initializeViews(View view) {
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.name);
        role = view.findViewById(R.id.role);
        edit = view.findViewById(R.id.edit);
    }
}