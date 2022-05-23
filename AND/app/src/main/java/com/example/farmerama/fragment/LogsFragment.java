package com.example.farmerama.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.example.farmerama.R;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.fragment.pageadapter.LogsPagerAdapter;
import com.example.farmerama.viewmodel.LogsViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.LocalDate;


public class LogsFragment extends Fragment {
    private Spinner areaSpinner;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private LogsViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(LogsViewModel.class);
        initializeViews(view);
        setUpViews();
    }


    private void initializeViews(View view) {
        tabLayout = view.findViewById(R.id.logTabLayout);
        viewPager2 = view.findViewById(R.id.viewPager2);
        areaSpinner = view.findViewById(R.id.area_spinner);
    }

    private void setUpViews() {
        LogsPagerAdapter adapter = new LogsPagerAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
               viewModel.retrieveLogs(MeasurementType.values()[position],LocalDate.now().toString());
            }
        });

        String[] tabTitles = {"Temperature", "Humidity", "CO₂", "SPL"};
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(tabTitles[position]);
        }).attach();

        viewModel.getAreas().observe(getViewLifecycleOwner(), areas -> {
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, viewModel.getAreasName().getValue());
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            areaSpinner.setAdapter(adapter2);
        });
        viewModel.getAllAreas();

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tabLayout.selectTab(tabLayout.getTabAt(0));
                viewModel.setAreaId(viewModel.getAreas().getValue().get(i).getId());
               viewModel.retrieveLogs(MeasurementType.TEMPERATURE, LocalDate.now().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

