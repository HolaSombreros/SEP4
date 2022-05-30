package com.example.farmerama.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.recycler.ThresholdModificationsAdapter;
import com.example.farmerama.viewmodel.ThresholdModificationsViewModel;

import java.time.LocalDate;
import java.util.Calendar;

public class ThresholdModificationsFragment extends Fragment {

    private ThresholdModificationsViewModel viewModel;
    private TextView date;
    private RecyclerView recycler;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_threshold_modifications, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ThresholdModificationsViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        date = view.findViewById(R.id.thresholdModification_date);
        recycler = view.findViewById(R.id.thresholdModification_recycler);
        progressBar = view.findViewById(R.id.pbThresholdsModifications);
    }

    private void setupViews() {


        date.setText(LocalDate.now().toString());
        progressBar.setVisibility(View.VISIBLE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year, monthOfYear, dayOfMonth)
                        -> date.setText(String.format("%d-%02d-%02d", year, monthOfYear+1, dayOfMonth)),
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        date.setOnClickListener(view -> datePickerDialog.show());

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.retrieveThresholdsModifications(date.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        viewModel.retrieveThresholdsModifications(date.getText().toString());

        recycler.hasFixedSize();
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ThresholdModificationsAdapter adapter = new ThresholdModificationsAdapter();
        viewModel.getThresholdsModifications().observe(getViewLifecycleOwner(), thresholdModifications -> {
            adapter.setModifications(thresholdModifications);
            progressBar.setVisibility(View.INVISIBLE);
        });
        recycler.setAdapter(adapter);
    }
}
