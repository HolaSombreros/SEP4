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


public class LatestTemperatureFragment extends Fragment {
    private EditText timeText;
    private TextView textView;
    private LatestMeasurementViewModel latestMeasurement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.fragment_latest_temperature, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        latestMeasurement = new ViewModelProvider(getActivity()).get(LatestMeasurementViewModel.class);
        initializeViews(view);
        latestMeasurement.getLatestMeasurement(0).observe(getViewLifecycleOwner(), measurement ->{
            timeText.setText(measurement.getDateTime().toString());
            //TODO: check to display temperature not random value from measurement
            textView.setText(String.valueOf(measurement.getValue()));
        });
    }

    private void initializeViews(View view) {
        textView = view.findViewById(R.id.latestTemperatureValue);
        timeText = view.findViewById(R.id.latestTemperatureTime);
    }


}