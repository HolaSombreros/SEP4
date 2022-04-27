package com.example.farmerama.uilayer.environment;


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
import com.example.farmerama.datalayer.model.Area;
import com.example.farmerama.domainlayer.LatestMeasurementViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LatestMeasurementFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private LatestMeasurementViewModel viewModel;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private Spinner areaSpinner;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_latest_data, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(LatestMeasurementViewModel.class);
        sharedPreferences = getActivity().getSharedPreferences("AreaLog", Context.MODE_PRIVATE);
        initializeViews(view);
        adapter = new ViewPagerAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),
               android.R.layout.simple_spinner_item, viewModel.getAreasName().getValue());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(adapter2);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sharedPreferences.edit().putInt("areaId", viewModel.getAreas().getValue().get(i).getId()).apply();
        viewModel.retrieveLatestMeasurement(viewModel.getAreas().getValue().get(i).getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
