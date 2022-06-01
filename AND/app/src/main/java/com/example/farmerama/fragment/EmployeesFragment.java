package com.example.farmerama.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.farmerama.R;
import com.example.farmerama.data.recycler.EmployeeAdapter;
import com.example.farmerama.viewmodel.EmployeeViewModel;

public class EmployeesFragment extends Fragment {
    private EmployeeViewModel employeeViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employees, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employeeViewModel = new ViewModelProvider(getActivity()).get(EmployeeViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.rev);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        progressBar = view.findViewById(R.id.pbEmployees);
    }

    private void setUpViews() {
        progressBar.setVisibility(View.VISIBLE);
        EmployeeAdapter adapter = new EmployeeAdapter();

        employeeViewModel.getAllEmployees().observe(getViewLifecycleOwner(), employees -> {
            adapter.setUserList(employees);
            progressBar.setVisibility(View.INVISIBLE);
        });
        recyclerView.setAdapter(adapter);
        employeeViewModel.retrieveAllEmployees();

        adapter.setOnDeleteListener(user -> {
            employeeViewModel.deleteEmployeeById(user);
            employeeViewModel.retrieveAllEmployees();
        });
    }
}