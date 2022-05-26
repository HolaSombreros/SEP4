package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.MeasurementRepository;

import java.util.ArrayList;
import java.util.List;

public class MeasurementsViewModel extends AndroidViewModel {
    private MeasurementRepository measurementRepository;
    private AreaRepository areaRepository;
    private int areaId;

    public MeasurementsViewModel(Application application) {
        super(application);
        this.measurementRepository = MeasurementRepository.getInstance();
        this.areaRepository = AreaRepository.getInstance(application);
    }

    public LiveData<List<Measurement>> getMeasurements() {
        return measurementRepository.getMeasurements();
    }

    public void retrieveLatestMeasurement(MeasurementType type, boolean latest) {
        measurementRepository.retrieveLatestMeasurement(areaId, type, latest);
    }


    public void retrieveMeasurements(MeasurementType type, String date) {
        measurementRepository.retrieveMeasurements(areaId, type, date);
    }

    public void getAllAreas() {
        areaRepository.retrieveAreas();
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

    public void setAreaId(int id) {
        areaId = id;
    }
}
