package com.example.farmerama.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.farmerama.R;
import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.fragment.pageadapter.HistoricalViewPagerAdapter;
import com.example.farmerama.viewmodel.MeasurementsViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoricalDataFragment extends Fragment {
    private MeasurementsViewModel viewModel;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private Spinner areaSpinner;
    private TextView date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historical_data, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MeasurementsViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout_historical);
        viewPager2 = view.findViewById(R.id.viewPager_historical);
        areaSpinner = view.findViewById(R.id.area_spinner_historical);
        date = view.findViewById(R.id.historical_date);
    }

    private void setUpViews() {
        date.setText(LocalDate.now().toString());

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
                viewModel.retrieveMeasurements(MeasurementType.TEMPERATURE, date.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        HistoricalViewPagerAdapter adapter = new HistoricalViewPagerAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                date.setText(LocalDate.now().toString());
                viewModel.retrieveMeasurements(MeasurementType.values()[position], LocalDate.now().toString());
                System.out.println(LocalDate.now().toString());
            }
        });

        String[] tabTitles = {"Temperature", "Humidity", "CO₂", "SPL"};
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(tabTitles[position]);
        }).attach();

        final List<Area>[] areasRetrieved = new List[]{new ArrayList<>()};

        viewModel.getAreas().observe(getViewLifecycleOwner(), areas -> {
            List<String> areasName = new ArrayList<>();
            areasRetrieved[0] = areas;
            for (Area area : areas) {
                areasName.add(area.getName());
            }
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, areasName);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            areaSpinner.setAdapter(adapter2);
        });
        viewModel.getAllAreas();

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tabLayout.selectTab(tabLayout.getTabAt(0));
                viewModel.setAreaId(areasRetrieved[0].get(i).getAreaId());
                date.setText(LocalDate.now().toString());
                viewModel.retrieveMeasurements(MeasurementType.TEMPERATURE, LocalDate.now().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
