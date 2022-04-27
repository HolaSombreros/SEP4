package com.example.farmerama.uilayer.environment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.farmerama.R;
import com.example.farmerama.domainlayer.LatestMeasurementViewModel;


public class LatestHumidityFragment extends Fragment {
    private EditText timeText;
    private TextView textView;
    private LatestMeasurementViewModel latestMeasurement;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest_humidity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        latestMeasurement = new ViewModelProvider(getActivity()).get(LatestMeasurementViewModel.class);
        initializeViews(view);
        sharedPreferences = getActivity().getSharedPreferences("AreaLog", Context.MODE_PRIVATE);
        latestMeasurement.retrieveLatestMeasurement(sharedPreferences.getInt("areaId", 0));
        latestMeasurement.getLatestMeasurement().observe(getViewLifecycleOwner(), measurement -> {
            textView.setText(String.valueOf(measurement.getValue()));
        });
    }

    private void initializeViews(View view) {
        textView = view.findViewById(R.id.latestHumidityValue);
        timeText = view.findViewById(R.id.latestHumidityTime);
    }

}