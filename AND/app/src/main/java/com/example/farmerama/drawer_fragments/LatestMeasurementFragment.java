package com.example.farmerama.drawer_fragments;

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
import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.domainlayer.LatestMeasurementViewModel;


public class LatestMeasurementFragment extends Fragment {
    private MeasurementType measurementType;
    private EditText timeText;
    private TextView measurementTextView;
    private TextView typeTextView;
    private LatestMeasurementViewModel viewModel;
    private SharedPreferences sharedPreferences;

    public LatestMeasurementFragment(int position) {
        switch (position) {
            case 0:
                measurementType = MeasurementType.TEMPERATURE;
                break;
            case 1:
                measurementType = MeasurementType.HUMIDITY;
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest_measurement, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(LatestMeasurementViewModel.class);
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
        viewModel.retrieveLatestMeasurement(sharedPreferences.getInt("areaId", 1), measurementType, true);

        viewModel.getLatestMeasurement().observe(getViewLifecycleOwner(), measurement -> {
            measurementTextView.setText(String.valueOf(measurement.getValue()));
            if (measurement.getMeasurementType()!=null)
                typeTextView.setText(measurement.getMeasurementType().toUnit());
        });
    }
}
