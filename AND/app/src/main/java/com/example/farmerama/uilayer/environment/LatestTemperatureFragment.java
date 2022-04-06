package com.example.farmerama.uilayer.environment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.farmerama.R;


public class LatestTemperatureFragment extends Fragment {
    private EditText timeText;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(R.layout.fragment_latest_temperature, container, false);
    }
/*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initializeViews(view);
    }

    private void initializeViews(View view) {
        textView = view.findViewById(R.id.measurement);
        timeText = view.findViewById(R.id.timeText);
    }*/


}