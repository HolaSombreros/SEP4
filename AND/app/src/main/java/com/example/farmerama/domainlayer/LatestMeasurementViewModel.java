package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.dao.MeasurementDAO;
import com.example.farmerama.datalayer.model.Area;
import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.repository.AreaRepository;
import com.example.farmerama.datalayer.repository.MeasurementRepository;
import com.example.farmerama.datalayer.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class LatestMeasurementViewModel extends ViewModel {
    private MeasurementRepository measurementRepository;
    private AreaRepository areaRepository;

    public LatestMeasurementViewModel() {
        this.measurementRepository = MeasurementRepository.getInstance();
        this.areaRepository = AreaRepository.getInstance();
    }

    public LiveData<Measurement> getLatestMeasurement() {
        return measurementRepository.getLatestTemperature();
    }
    public void retrieveLatestMeasurement(int areaId) {
        measurementRepository.retrieveLatestTemperature(areaId);
    }

    public LiveData<List<Area>> getAreas() {
       return areaRepository.getAreas();
    }

    public LiveData<List<String>> getAreasName() {
        List<String> list = new ArrayList<>();
        /*for(Area area : areaRepository.getAreas().getValue()) {
            list.add(area.getName());
        }*/
        list.add("Area 1");
        list.add("Area 2");
        return new MutableLiveData<>(list);
    }

}
