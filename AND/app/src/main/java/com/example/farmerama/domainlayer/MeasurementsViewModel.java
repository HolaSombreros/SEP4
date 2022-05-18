package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.Area;
import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.datalayer.repository.AreaRepository;
import com.example.farmerama.datalayer.repository.MeasurementRepository;

import java.util.ArrayList;
import java.util.List;

public class MeasurementsViewModel extends ViewModel {
    private MeasurementRepository measurementRepository;
    private AreaRepository areaRepository;

    public MeasurementsViewModel() {
        this.measurementRepository = MeasurementRepository.getInstance();
        this.areaRepository = AreaRepository.getInstance();
    }

    public LiveData<Measurement> getLatestMeasurement() {
        return measurementRepository.getLatestMeasurement();
    }

    public LiveData<List<Measurement>> getMeasurements() {
        return measurementRepository.getMeasurements();
    }

    public void retrieveLatestMeasurement(int areaId, MeasurementType type, boolean latest) {
        measurementRepository.retrieveLatestMeasurement(areaId, type, latest);
    }
    public void getAllAreas() {
        areaRepository.getAllAreas();
    }

    public void retrieveMeasurements(int areaId, MeasurementType type, String date) {
        measurementRepository.retrieveMeasurements(areaId, type, date);
    }

    public void getAllAreas() {
        areaRepository.getAllAreas();
    }

    public LiveData<List<Area>> getAreas() {
       return areaRepository.getAreas();
    }

    public LiveData<List<String>> getAreasName() {
        List<String> list = new ArrayList<>();
        if(areaRepository.getAreas().getValue() != null) {
            for(Area area : areaRepository.getAreas().getValue()) {
                list.add(area.getName());
            }
        }
        return new MutableLiveData<>(list);
    }

}
