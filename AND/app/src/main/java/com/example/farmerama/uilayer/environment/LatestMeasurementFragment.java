package com.example.farmerama.uilayer.environment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.domainlayer.LatestMeasurementViewModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class LatestMeasurementFragment extends Fragment {
    private LatestMeasurementViewModel viewModel;
    private TabLayout tabLayout;
    private TabItem temperatureTab;
    private Spinner areaSpinner;
    private EditText timeText;
    private TextView textView;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(LatestMeasurementViewModel.class);
        initializeViews(view);

    }

    private void initializeViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        temperatureTab = view.findViewById(R.id.latestTemperature);
        textView = view.findViewById(R.id.measurement);
        areaSpinner = view.findViewById(R.id.area_spinner);
        timeText = view.findViewById(R.id.timeText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest_data, container, false);
    }
}
