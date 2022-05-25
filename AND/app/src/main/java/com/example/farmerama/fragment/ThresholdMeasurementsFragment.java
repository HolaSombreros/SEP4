package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.viewmodel.ThresholdViewModel;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThresholdMeasurementsFragment extends Fragment {
    private TextView upperThreshold;
    private EditText upperThresholdValue;
    private TextView lowerThreshold;
    private EditText lowerThresholdValue;
    private ThresholdViewModel viewModel;
    private Button button;

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
       upperThreshold = view.findViewById(R.id.upperThresholdText);
       upperThresholdValue = view.findViewById(R.id.upperThreshold);
       lowerThreshold = view.findViewById(R.id.lowerThresholdText);
       lowerThresholdValue = view.findViewById(R.id.lowerThreshold);
       button = view.findViewById(R.id.saveThreshold);
    }

    private void setUpViews() {
       AtomicBoolean createThreshold = new AtomicBoolean(false);
        viewModel.getThresholds().observe(getViewLifecycleOwner(), thresholds -> {
            if(thresholds != null) {
                upperThresholdValue.setText(String.valueOf(thresholds.getMaximum()));
                upperThresholdValue.requestFocus();
                upperThresholdValue.setSelection(upperThresholdValue.getText().length());
                lowerThresholdValue.setText(String.valueOf(thresholds.getMinimum()));
                createThreshold.set(false);
            }
            else {
                createThreshold.set(true);
            }
        });

        button.setOnClickListener(l -> {
            viewModel.editThreshold(new Threshold(Double.parseDouble(lowerThresholdValue.getText().toString()),
                    Double.parseDouble(upperThresholdValue.getText().toString())));
            Toast.makeText(getActivity(), "Threshold edited!", Toast.LENGTH_SHORT).show();
//            if(createThreshold.get()) {
//                viewModel.createThreshold(new Threshold(Double.parseDouble(lowerThresholdValue.getText().toString()),
//                        Double.parseDouble(upperThresholdValue.getText().toString())));
//            }
//            else {
//
//            }

        });
    }

}