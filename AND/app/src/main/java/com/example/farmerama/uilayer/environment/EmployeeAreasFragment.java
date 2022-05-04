package com.example.farmerama.uilayer.environment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.domainlayer.AreaViewModel;

public class EmployeeAreasFragment extends Fragment {
    private Spinner areaSpinnerForEmployee;
    private AreaViewModel viewModel;
    private TextView textView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.fragment_employee_areas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setUpViews();
    }


    private void initializeViews(View view) {
        areaSpinnerForEmployee = view.findViewById(R.id.area_spinner);
        textView = view.findViewById(R.id.areasText);
    }

    private void setUpViews() {
        viewModel = new ViewModelProvider(getActivity()).get(AreaViewModel.class);
        viewModel.getAreas().observe(getViewLifecycleOwner(), areas -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, viewModel.getAreasName().getValue());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            areaSpinnerForEmployee.setAdapter(adapter);
            textView.setText(viewModel.getAreas().getValue().get(0).getName());
        });
        viewModel.getAllAreas();



    }
}

