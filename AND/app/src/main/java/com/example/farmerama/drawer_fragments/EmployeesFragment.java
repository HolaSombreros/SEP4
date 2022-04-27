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
import com.example.farmerama.domainlayer.LatestMeasurementViewModel;
import com.example.farmerama.domainlayer.RegisterViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeesFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private TextView textView;

    public EmployeesFragment() {
        // Required empty public constructor
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        registerViewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        textView = view.findViewById(R.id.employees);
        textView.setText(registerViewModel.getAllEmployees().getValue().get(0).toString());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employees, container, false);
    }
}