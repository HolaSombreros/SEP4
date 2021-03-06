package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.viewmodel.ThresholdViewModel;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThresholdMeasurementsFragment extends Fragment {
    private EditText upperThresholdValue;
    private EditText lowerThresholdValue;
    private ThresholdViewModel viewModel;
    private Button button;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_threshold_measurements, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ThresholdViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
       upperThresholdValue = view.findViewById(R.id.upperThreshold);
       lowerThresholdValue = view.findViewById(R.id.lowerThreshold);
       button = view.findViewById(R.id.saveThreshold);
       progressBar = view.findViewById(R.id.pbThresholds);
    }

    private void setUpViews() {
        progressBar.setVisibility(View.VISIBLE);
       AtomicBoolean createThreshold = new AtomicBoolean(false);
        viewModel.getThreshold().observe(getViewLifecycleOwner(), thresholds -> {
            if(thresholds != null) {
                progressBar.setVisibility(View.INVISIBLE);
                upperThresholdValue.setText(String.valueOf(thresholds.getMaximum()));
                upperThresholdValue.requestFocus();
                upperThresholdValue.setSelection(upperThresholdValue.getText().length());
                lowerThresholdValue.setText(String.valueOf(thresholds.getMinimum()));
                createThreshold.set(false);
            }
            else {
                createThreshold.set(true);
                progressBar.setVisibility(View.INVISIBLE);
                upperThresholdValue.setText("");
                lowerThresholdValue.setText("");
            }
        });

        button.setOnClickListener(l -> {
            if(createThreshold.get()) {
                viewModel.createThreshold(upperThresholdValue.getText().toString(),
                        lowerThresholdValue.getText().toString());
            }
            else {
                viewModel.editThreshold(upperThresholdValue.getText().toString(),
                        lowerThresholdValue.getText().toString());
            }
            createThreshold.set(false);
        });
    }

}
