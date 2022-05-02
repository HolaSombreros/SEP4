package com.example.farmerama.drawer_fragments;

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

import com.example.farmerama.R;
import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.domainlayer.LoginViewModel;

public class LoginFragment extends Fragment
{
    private LoginViewModel viewModel;
    private EditText email;
    private EditText password;
    private Button loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        email= view.findViewById(R.id.LoginEmailAddress);
        password = view.findViewById(R.id.LoginPassword);
        loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(this::login);
    }

    public void login(View v){
        try{

            User user= viewModel.login(email.toString(), password.toString());

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
