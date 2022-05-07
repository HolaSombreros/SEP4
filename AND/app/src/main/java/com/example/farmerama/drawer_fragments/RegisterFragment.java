package com.example.farmerama.drawer_fragments;

import android.os.Bundle;
import android.util.Log;
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

import com.example.farmerama.R;
import com.example.farmerama.datalayer.model.Role;
import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.domainlayer.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText role;
    private Button registerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        email = view.findViewById(R.id.RegisterEmailAddress);
        firstName = view.findViewById(R.id.RegisterFirstName);
        lastName = view.findViewById(R.id.RegisterLastName);
        password = view.findViewById(R.id.RegisterPassword);
        registerButton = view.findViewById(R.id.registerButton);
        role=view.findViewById(R.id.Role);

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error,Toast.LENGTH_SHORT).show();
        });

        registerButton.setOnClickListener(this::registerUser);
    }


    public void registerUser(View v){
        String userFirstName=firstName.getText().toString();
        String userLastName=lastName.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();
        String userRole=role.getText().toString();



            if(viewModel.validate(userFirstName, userLastName, userEmail, userPassword, userRole)){
                Log.i("Test", "I am in second if");
                viewModel.registerUser(new User(userFirstName, userLastName, userEmail, userPassword, userRole));
            }
    }
}
