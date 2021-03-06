package com.example.farmerama.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.farmerama.R;
import com.example.farmerama.data.recycler.LogsAdapter;
import com.example.farmerama.viewmodel.LogsViewModel;

public class LogsMeasurementsFragment extends Fragment {

    private RecyclerView logsRecycler;
    private LogsViewModel viewModel;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logs_measurements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(LogsViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        logsRecycler = view.findViewById(R.id.logs_recycler);
        progressBar = view.findViewById(R.id.pbLogs);
    }

    private void setupViews() {
        progressBar.setVisibility(View.VISIBLE);
        logsRecycler.hasFixedSize();
        logsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        LogsAdapter logsAdapter = new LogsAdapter();
        viewModel.getLogs().observe(getViewLifecycleOwner(), exceededLogs -> {
            logsAdapter.setLogs(exceededLogs);
            progressBar.setVisibility(View.INVISIBLE);

        });
        logsRecycler.setAdapter(logsAdapter);
    }

}