package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.data.model.User;
import com.example.farmerama.viewmodel.EditAccountViewModel;

public class EditAccountFragment extends Fragment {

    private EditAccountViewModel viewModel;
    private TextView email;
    private TextView firstName;
    private TextView lastName;
    private TextView password;
    private Spinner role;
    private Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(EditAccountViewModel.class);

        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        email = view.findViewById(R.id.editAccount_email);
        firstName = view.findViewById(R.id.editAccount_firstName);
        lastName = view.findViewById(R.id.editAccount_LastName);
        password = view.findViewById(R.id.editAccount_password);
        role = view.findViewById(R.id.editAccount_role);
        save = view.findViewById(R.id.editAccount_saveButton);
    }

    private void setupViews() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);

        viewModel.getLoggedInUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                viewModel.setUserId(user.getId());

                email.setText(user.getEmail());
                String[] tokens = user.getName().split(" ");
                firstName.setText("");
                for (int i = 0; i < tokens.length - 1; i++)
                    firstName.setText(firstName.getText().toString() + tokens[i] + " ");
                firstName.setText(firstName.getText().toString().trim());
                lastName.setText(tokens[tokens.length - 1]);
                password.setText(user.getPassword());


                if (user.getRole().equals("ADMINISTRATOR"))
                    role.setSelection(1);
                else
                    role.setSelection(0);
                role.setEnabled(false);
            }
        });

        save.setOnClickListener(v -> {
            if (viewModel.validate(firstName.getText().toString(), lastName.getText().toString(),
                    email.getText().toString(), password.getText().toString(),
                    role.getSelectedItem().toString())) {
                User user = new User(firstName.getText().toString(), lastName.getText().toString(),
                        email.getText().toString(), password.getText().toString(),
                        role.getSelectedItem().toString());
                user.setId(viewModel.getUserId());
                viewModel.saveAccount(user);
            }
        });
    }
}