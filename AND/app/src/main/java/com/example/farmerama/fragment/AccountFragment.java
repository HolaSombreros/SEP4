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

    private NavController navController;
    private TextView email;
    private TextView name;
    private TextView role;
    private AccountViewModel viewModel;
    private FloatingActionButton edit;

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
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.name);
        role = view.findViewById(R.id.role);
        edit = view.findViewById(R.id.edit);
    }

    private void setUpViews() {
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if(user != null){
                email.setText(user.getEmail());
                email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_alternate_email_24, 0, 0, 0);
                name.setText(user.getName());
                name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_account_circle_24, 0, 0, 0);
                role.setText(user.getRole());
                role.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_work_24, 0, 0, 0);

            }
        });

        edit.setOnClickListener(v -> {
            navController.navigate(R.id.editAccountFragment);
        });
    }
}