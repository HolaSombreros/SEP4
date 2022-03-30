package com.example.farmerama.uilayer.environment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.farmerama.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LatestMeasurementFragment extends Fragment {
   // private LatestMeasurementViewModel viewModel;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private Spinner areaSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_latest_data, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initializeViews(view);
        adapter = new ViewPagerAdapter(getActivity());
        viewPager2.setAdapter(adapter);

        String[] tabTitles = {"Temperature", "Humidity"};
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(tabTitles[position]);
        }).attach();
    }

    private void initializeViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2= view.findViewById(R.id.viewPager);
        areaSpinner = view.findViewById(R.id.area_spinner);
    }

}
