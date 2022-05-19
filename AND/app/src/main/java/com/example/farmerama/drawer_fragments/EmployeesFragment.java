package com.example.farmerama.drawer_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farmerama.R;
import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.domainlayer.RegisterViewModel;

public class EmployeesFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private TextView textView;

    public EmployeesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employees, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        registerViewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        textView = view.findViewById(R.id.employees);
        registerViewModel.getEmployee().observe(getViewLifecycleOwner(), employee -> {
            textView.setText(employee.getName());
        });
        registerViewModel.getAllEmployees().observe(getViewLifecycleOwner(), employees -> {
            String users = "";
            for (User user : employees) {
                users +=user.getName() + " \n";
                textView.setText(users);
            }
        });
        registerViewModel.getUserById(2);
        registerViewModel.retrieveAllEmployees();
        /*
        registerViewModel.retrieveAllEmployees();
        registerViewModel.getAllEmployees().observe(getViewLifecycleOwner(),employee ->{
            textView.setText(employee.get(0).toString());
            Log.w("test", employee.get(0).toString());
        });*/
    }

}