package com.example.farmerama.drawer_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.farmerama.R;
import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.domainlayer.MeasurementsViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.LocalDate;

public class HistoricalPagerFragment extends Fragment {
    private MeasurementsViewModel viewModel;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private Spinner areaSpinner;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historical_pager, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MeasurementsViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        sharedPreferences = getActivity().getSharedPreferences("AreaLogHistorical", Context.MODE_PRIVATE);
        tabLayout = view.findViewById(R.id.tabLayout_historical);
        viewPager2 = view.findViewById(R.id.viewPager_historical);
        areaSpinner = view.findViewById(R.id.area_spinner_historical);
    }

    private void setUpViews() {
        HistoricalViewPagerAdapter adapter = new HistoricalViewPagerAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewModel.retrieveMeasurements(sharedPreferences.getInt("areaIdHistorical", 1), MeasurementType.values()[position], LocalDate.now().toString());
                System.out.println(LocalDate.now().toString());
            }
        });

        String[] tabTitles = {"Temperature", "Humidity"};
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
                sharedPreferences.edit().putInt("areaIdHistorical", viewModel.getAreas().getValue().get(i).getId()).apply();
                viewModel.retrieveMeasurements(viewModel.getAreas().getValue().get(i).getId(), MeasurementType.TEMPERATURE, LocalDate.now().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
