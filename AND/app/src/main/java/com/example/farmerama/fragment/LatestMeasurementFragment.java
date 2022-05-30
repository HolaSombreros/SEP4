package com.example.farmerama.fragment;

import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;


public class LatestMeasurementFragment extends Fragment {
    private EditText timeText;
    private TextView measurementTextView;
    private TextView typeTextView;
    private MeasurementsViewModel viewModel;
    private DonutProgressView donut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest_measurement, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MeasurementsViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        measurementTextView = view.findViewById(R.id.latestTemperature_value);
        timeText = view.findViewById(R.id.latestTemperature_time);
        typeTextView = view.findViewById(R.id.latestTemperature_measurementType);
        donut = view.findViewById(R.id.latestMeasurements_donut);
    }

    private void setUpViews() {
        viewModel.getLatestMeasurement().observe(getViewLifecycleOwner(), measurement -> {
            if (measurement != null) {
                List<DonutSection> donutSection = new ArrayList<>();
                donut.clear();
                donut.setCap(measurement.getMeasurementType().getMaximum());
                donutSection.add(new DonutSection(measurement.getMeasurementType().toString(),
                        Color.parseColor("#2C4A78"), (float) measurement.getValue()));
                donut.submitData(donutSection);

                measurementTextView.setText(String.valueOf(measurement.getValue()));
                timeText.setText(measurement.getDateTime());
                if (measurement.getMeasurementType() != null)
                    typeTextView.setText(measurement.getMeasurementType().toUnit());
            }
        });
//        viewModel.getMeasurements().observe(getViewLifecycleOwner(), measurements -> {
//            if (measurements.size() != 0) {
//                List<DonutSection> donutSection = new ArrayList<>();
//                donut.clear();
//                donut.setCap(measurements.get(0).getMeasurementType().getMaximum());
//                donutSection.add(new DonutSection(measurements.get(0).getMeasurementType().toString(),
//                        Color.parseColor("#2C4A78"), (float) measurements.get(0).getValue()));
//                donut.submitData(donutSection);
//
//                measurementTextView.setText(String.valueOf(measurements.get(0).getValue()));
//                timeText.setText(measurements.get(0).getDateTime());
//                if (measurements.get(0).getMeasurementType() != null)
//                    typeTextView.setText(measurements.get(0).getMeasurementType().toUnit());
//            }
//        });
    }
}
