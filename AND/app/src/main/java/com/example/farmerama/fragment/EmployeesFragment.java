package com.example.farmerama.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.User;
import com.example.farmerama.data.recycler.EmployeeAdapter;
import com.example.farmerama.viewmodel.RegisterViewModel;

public class EmployeesFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private RecyclerView recyclerView;
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
        recyclerView = view.findViewById(R.id.rev);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();

        EmployeeAdapter adapter = new EmployeeAdapter();

        registerViewModel.getAllEmployees().observe(getViewLifecycleOwner(), employees -> {
            adapter.setUserList(employees);
            recyclerView.setAdapter(adapter);

        });
        registerViewModel.retrieveAllEmployees();

//        registerViewModel.getEmployee().observe(getViewLifecycleOwner(), employee -> {

//        });

    }

}