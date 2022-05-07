package com.example.farmerama.drawer_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.farmerama.R;
import com.example.farmerama.domainlayer.LoginViewModel;

public class LoginFragment extends Fragment
{
    private LoginViewModel viewModel;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button continueAsGuest;
    private NavController navController;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        setupViews(view);
    }

    private void setupViews(View view){
        navController = Navigation.findNavController(view);
        email= view.findViewById(R.id.LoginEmailAddress);
        password = view.findViewById(R.id.LoginPassword);
        loginButton = view.findViewById(R.id.loginButton);
        continueAsGuest = view.findViewById(R.id.continueAsGuest);
        sharedPreferences = getActivity().getSharedPreferences("GuestVisit", Context.MODE_PRIVATE);

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error,Toast.LENGTH_SHORT).show();
        });

        loginButton.setOnClickListener(this::login);
        continueAsGuest.setOnClickListener(v -> {
            sharedPreferences.edit().putBoolean("GuestVisit", true);
            navController.navigate(R.id.latestMeasurementFragment);
        });
    }

    public void login(View v){
        try{
            if(viewModel.validate(email.getText().toString(), password.getText().toString())) {
                navController.navigate(R.id.latestMeasurementFragment);
            }

            //TODO
//            if(user.getRole().equals(Role.OWNER)){
//
//            }
//            else if(user.getRole().equals(Role.EMPLOYEE)){
//
//            }
        }
        catch (Exception e)
        {

        }

    }





}
