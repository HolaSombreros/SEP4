package com.example.farmerama.uilayer;

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
import com.example.farmerama.datalayer.model.Role;
import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.domainlayer.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private  Button registerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_layout, container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        email = view.findViewById(R.id.RegisterEmailAddress);
        firstName = view.findViewById(R.id.RegisterFirstName);
        lastName = view.findViewById(R.id.RegisterLastName);
        password = view.findViewById(R.id.RegisterPassword);
        registerButton = view.findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this::registerUser);
    }


    public void registerUser(View v){
        viewModel.registerUser(new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString(), Role.EMPLOYEE));
    }
}
