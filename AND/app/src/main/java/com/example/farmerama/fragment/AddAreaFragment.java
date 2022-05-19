package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.farmerama.R;
import com.example.farmerama.data.model.Barn;
import com.example.farmerama.viewmodel.AddAreaViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddAreaFragment extends Fragment {

    private AddAreaViewModel viewModel;
    private NavController navController;
    private Spinner barnSpinner;
    private EditText areaName;
    private EditText areaDescription;
    private EditText noOfPigs;
    private EditText hardwareId;
    private Button create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_area, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AddAreaViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        areaName = view.findViewById(R.id.addArea_areaName);
        barnSpinner = view.findViewById(R.id.addArea_barnSpinner);
        areaDescription = view.findViewById(R.id.addArea_areaDescription);
        noOfPigs = view.findViewById(R.id.addArea_noPigs);
        hardwareId = view.findViewById(R.id.addArea_hardwareId);
        create = view.findViewById(R.id.addArea_createButton);
    }

    private void setupViews() {

        viewModel.getBarns().observe(getViewLifecycleOwner(), barns -> {
            List<String> list = new ArrayList<>();
            for (Barn barn : barns)
                list.add(barn.getName());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            barnSpinner.setAdapter(adapter);
        });

        viewModel.retrieveAllBarns();

        barnSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.setBarn(viewModel.getBarns().getValue().get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        create.setOnClickListener(v -> {
            if (viewModel.createNewArea(areaName.getText().toString(), areaDescription.getText().toString(),
                    noOfPigs.getText().toString(), hardwareId.getText().toString())) {
                Toast.makeText(getActivity(), "Area " + areaName.getText().toString() + " has been created!", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
            else
                Toast.makeText(getActivity(), viewModel.getErrorMessage().getValue(), Toast.LENGTH_SHORT).show();
        });
    }
}
