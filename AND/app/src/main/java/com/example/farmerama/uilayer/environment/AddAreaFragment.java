package com.example.farmerama.uilayer.environment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.domainlayer.AddAreaViewModel;

public class AddAreaFragment extends Fragment {

    private AddAreaViewModel viewModel;
    private EditText areaName;
    private EditText areaDescription;
    private EditText noOfPigs;
    private Button create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_add_area, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(AddAreaViewModel.class);

        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        areaName = view.findViewById(R.id.addArea_areaName);
        areaDescription = view.findViewById(R.id.addArea_areaDescription);
        noOfPigs = view.findViewById(R.id.addArea_noPigs);
        create = view.findViewById(R.id.addArea_createButton);
    }

    private void setupViews() {
        create.setOnClickListener(v-> {
            if (viewModel.createNewArea(areaName.getText().toString(), areaDescription.getText().toString(),
                    noOfPigs.getText().toString()))
                Toast.makeText(getActivity(), "Area " + areaName.getText().toString() + " has been created!", Toast.LENGTH_SHORT).show();
            else Toast.makeText(getActivity(), viewModel.getErrorMessage().getValue(),Toast.LENGTH_SHORT).show();
        });
    }
}
