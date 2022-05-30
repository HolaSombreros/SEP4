package com.example.farmerama.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmerama.R;
import com.example.farmerama.data.recycler.EmployeeAdapter;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.viewmodel.EmployeeViewModel;
import com.example.farmerama.viewmodel.RegisterViewModel;

public class EmployeesFragment extends Fragment {
    private EmployeeViewModel employeeViewModel;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employees, container, false);
    }

    private void initViews(View view){
        employeeViewModel = new ViewModelProvider(getActivity()).get(EmployeeViewModel.class);
        recyclerView = view.findViewById(R.id.rev);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        initViews(view);

        EmployeeAdapter adapter = new EmployeeAdapter();

        employeeViewModel.getAllEmployees().observe(getViewLifecycleOwner(), employees -> {
            adapter.setUserList(employees);
        });
        recyclerView.setAdapter(adapter);
        employeeViewModel.retrieveAllEmployees();

        /*
        Deleting the user from the API, as well as the user from the Roomdatabase
        No possibility of deleting yourself
         */

        adapter.setOnDeleteListener(user -> {
            employeeViewModel.deleteEmployeeById(user);
            employeeViewModel.getUserById(user.getUserId());
            employeeViewModel.retrieveAllEmployees();
        });

    }


}