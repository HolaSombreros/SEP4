package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.recycler.ThresholdModificationsAdapter;
import com.example.farmerama.viewmodel.ThresholdModificationsViewModel;

public class ThresholdModificationsFragment extends Fragment {

    private ThresholdModificationsViewModel viewModel;
    private EditText date;
    private RecyclerView recycler;

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
    }

    private void setupViews() {


        viewModel.retrieveThresholdsModifications("date");
        ThresholdModificationsAdapter adapter = new ThresholdModificationsAdapter();
        viewModel.getThresholdsModifications().observe(getViewLifecycleOwner(), modifications -> {
            adapter.setModifications(modifications);
        });
        recycler.setAdapter(adapter);
    }
}
