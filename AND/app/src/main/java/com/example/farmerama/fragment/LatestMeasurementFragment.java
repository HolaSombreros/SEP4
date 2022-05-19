package com.example.farmerama.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.farmerama.R;
import com.example.farmerama.viewmodel.MeasurementsViewModel;


public class LatestMeasurementFragment extends Fragment {
    private EditText timeText;
    private TextView measurementTextView;
    private TextView typeTextView;
    private MeasurementsViewModel viewModel;
    private SharedPreferences sharedPreferences;

    public LatestMeasurementFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest_measurement, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(MeasurementsViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        measurementTextView = view.findViewById(R.id.latestTemperature_value);
        timeText = view.findViewById(R.id.latestTemperature_time);
        typeTextView = view.findViewById(R.id.latestTemperature_measurementType);
    }

    private void setUpViews() {
        sharedPreferences = getActivity().getSharedPreferences("AreaLog", Context.MODE_PRIVATE);
        viewModel.getMeasurements().observe(getViewLifecycleOwner(), measurements -> {
            measurementTextView.setText(String.valueOf(measurements.get(0).getValue()));
            timeText.setText(measurements.get(0).getDateTime());
            if (measurements.get(0).getMeasurementType()!=null)
                typeTextView.setText(measurements.get(0).getMeasurementType().toUnit());
        });
    }
}
