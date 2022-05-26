package com.example.farmerama.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmerama.R;
import com.example.farmerama.viewmodel.MeasurementsViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class HistoricalMeasurementsFragment extends Fragment {
    private MeasurementsViewModel viewModel;
    private LineChart lineChart;

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
    }

    private void setupViews() {

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
                lineChart.zoomIn();
            }
            lineChart.animateX(3000);

            lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    TimeMarkerView tmv = new TimeMarkerView(getContext(), R.layout.marker_date);
                    tmv.refreshContent(measurements.get((int) e.getX()).getDateTime());
                    lineChart.setMarker(tmv);
                }

                @Override
                public void onNothingSelected() {
                }
            });
        });
    }

    public class TimeMarkerView extends MarkerView {
        private TextView date;

        public TimeMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            date = findViewById(R.id.markerDate_text);
        }

        @Override
        public void setOffset(MPPointF offset) {
            super.setOffset(new MPPointF(offset.x / 2, -offset.y));
        }

        public void refreshContent(String date) {
            this.date.setText(date);
        }

        @Override
        public void draw(Canvas canvas, float posX, float posY) {
            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int w = getWidth();
            if ((width - posX - w) < w) {
                posX -= w;
            }
            canvas.translate(posX, posY);
            draw(canvas);
            canvas.translate(-posX, -posY);
        }
    }
}
