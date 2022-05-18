package com.example.farmerama.drawer_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.datalayer.recycler.MeasurementsAdapter;
import com.example.farmerama.domainlayer.MeasurementsViewModel;

public class HistoricalMeasurementsFragment extends Fragment {
    private RecyclerView measurementsRecycler;
    private MeasurementsAdapter measurementsAdapter;
    private MeasurementsViewModel viewModel;
    private SharedPreferences sharedPreferences;

    public HistoricalMeasurementsFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historical_measurements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MeasurementsViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        //navController = Navigation.findNavController(view);
        measurementsRecycler = view.findViewById(R.id.measurements_recycler);

    }

    private void setupViews() {
        sharedPreferences = getActivity().getSharedPreferences("AreaLogHistorical", Context.MODE_PRIVATE);

        measurementsRecycler.hasFixedSize();
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            measurementsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        else {
            measurementsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }

        measurementsAdapter = new MeasurementsAdapter();
        viewModel.getMeasurements().observe(getViewLifecycleOwner(), measurements -> {
            measurementsAdapter.setMeasurements(measurements);
        });
        measurementsRecycler.setAdapter(measurementsAdapter);

    }

}