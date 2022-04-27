package com.example.farmerama.uilayer.environment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.farmerama.R;
import com.example.farmerama.domainlayer.LatestMeasurementViewModel;


public class LatestMeasurementFragment extends Fragment {
    private EditText timeText;
    private TextView measurementTextView;
    private TextView typeTextView;
    private LatestMeasurementViewModel latestMeasurement;

    public LatestMeasurementFragment(int position) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.fragment_latest_measurement, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        latestMeasurement = new ViewModelProvider(getActivity()).get(LatestMeasurementViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        measurementTextView = view.findViewById(R.id.latestTemperature_value);
        timeText = view.findViewById(R.id.latestTemperature_time);
        typeTextView = view.findViewById(R.id.latestTemperature_measurementType);
    }

    private void setUpViews() {
        latestMeasurement.getLatestMeasurement(0).observe(getViewLifecycleOwner(), measurement ->{
            timeText.setText(measurement.getDateTime().toString());
            //TODO: check to display temperature not random value from measurement
            measurementTextView.setText(String.valueOf(measurement.getValue()));
            typeTextView.setText(measurement.getMeasurementType().toString());
        });
    }
}
