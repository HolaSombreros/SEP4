package com.example.farmerama.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.farmerama.R;
import com.example.farmerama.datalayer.adapter.AreaListAdapter;
import com.example.farmerama.domainlayer.AreaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AreasFragment extends Fragment {

    private NavController navController;
    private FloatingActionButton fab;
    private AreaViewModel viewModel;
    private RecyclerView areasRecycler;
    private AreaListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_areas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AreaViewModel.class);
        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        fab = view.findViewById(R.id.areas_fab);
        areasRecycler = view.findViewById(R.id.areas_recycleView);
        areasRecycler.hasFixedSize();
        areasRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setupViews() {
        fab.setOnClickListener(v -> {
            navController.navigate(R.id.addAreaFragment);
        });

        viewModel.getAllAreas();
        adapter = new AreaListAdapter();
        viewModel.getAreas().observe(getViewLifecycleOwner(), areas -> {
            adapter.setAreas(areas);
        });
        areasRecycler.setAdapter(adapter);

        adapter.setOnClickListener(area -> {
            Bundle bundle = new Bundle();
            bundle.putInt("areaId", area.getId());
            navController.navigate(R.id.addAreaFragment, bundle);
        });

    }
}