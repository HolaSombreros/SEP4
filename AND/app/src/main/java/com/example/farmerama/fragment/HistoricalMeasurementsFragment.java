package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.data.util.DateFormatter;
import com.example.farmerama.fragment.view.TimeMarkerView;
import com.example.farmerama.viewmodel.MeasurementsViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class HistoricalMeasurementsFragment extends Fragment {
    private MeasurementsViewModel viewModel;
    private LineChart lineChart;
    private ProgressBar pbHistorical;

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
        lineChart = view.findViewById(R.id.chart1);
        pbHistorical = view.findViewById(R.id.pbHistorical);
    }

    private void setupViews() {
        pbHistorical.setVisibility(View.VISIBLE);
        lineChart.setVisibility(View.INVISIBLE);

        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setEnabled(false);

        lineChart.getLegend().setTextColor(ContextCompat.getColor(getContext(), R.color.blue_200));
        lineChart.getAxisRight().setTextColor(ContextCompat.getColor(getContext(), R.color.blue_200));
        lineChart.getAxisLeft().setTextColor(ContextCompat.getColor(getContext(), R.color.blue_200));
        lineChart.getDescription().setTextColor(ContextCompat.getColor(getContext(), R.color.blue_200));
        lineChart.getXAxis().setTextColor(ContextCompat.getColor(getContext(), R.color.blue_200));


        viewModel.getMeasurements().observe(getViewLifecycleOwner(), measurements -> {
            pbHistorical.setVisibility(View.INVISIBLE);
            lineChart.setVisibility(View.VISIBLE);

            List<Entry> entries = new ArrayList<>();
            for (int i = 0; i < measurements.size(); i++) {
                entries.add(new Entry(i, (float) measurements.get(i).getValue()));
            }

            ILineDataSet lineDataSet = new LineDataSet(entries, "Value");
            lineDataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.blue_200));
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
            if (!measurements.isEmpty()) {
                lineChart.getAxisLeft().setAxisMaximum(measurements.get(0).getMeasurementType().getMaximum());
                lineChart.getAxisLeft().setAxisMinimum(0);
            }
            lineChart.animateX(3000);

            lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    TimeMarkerView tmv = new TimeMarkerView(getContext(), R.layout.marker_date);
                    tmv.refreshContent(DateFormatter.formatDate(measurements.get((int) e.getX()).getDateTime().toString()));
                    lineChart.setMarker(tmv);
                }

                @Override
                public void onNothingSelected() {
                }
            });

        });

    }
}
