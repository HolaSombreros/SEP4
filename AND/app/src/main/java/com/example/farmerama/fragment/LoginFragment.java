package com.example.farmerama.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.farmerama.R;
import com.example.farmerama.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    private LoginViewModel viewModel;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button continueAsGuest;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        setupViews(view);
    }

    private void setupViews(View view) {
        navController = Navigation.findNavController(view);
        email = view.findViewById(R.id.LoginEmailAddress);
        password = view.findViewById(R.id.LoginPassword);
        loginButton = view.findViewById(R.id.loginButton);
        continueAsGuest = view.findViewById(R.id.continueAsGuest);

        loginButton.setOnClickListener(l -> {
            if (viewModel.validate(email.getText().toString(), password.getText().toString())) {
                viewModel.loginUser(email.getText().toString(), password.getText().toString());
            }
        });

        continueAsGuest.setOnClickListener(v -> {
            navController.navigate(R.id.latestDataFragment);
        });
    }
}
