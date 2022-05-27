package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
    private SharedPreferences sharedPreferences;
    private int areaId;
    private MeasurementType measurementType;

    public MeasurementsViewModel(Application application) {
        super(application);
        this.measurementRepository = MeasurementRepository.getInstance(application);
        this.areaRepository = AreaRepository.getInstance(application);
        this.sharedPreferences = application.getSharedPreferences("Latest", Context.MODE_PRIVATE);
    }

    public LiveData<List<Measurement>> getMeasurements() {
        return measurementRepository.getMeasurements();
    }

    public void retrieveLatestMeasurement(MeasurementType type, boolean latest) {
        measurementRepository.retrieveLatestMeasurement(areaId, type, latest);
    }

    public LiveData<Measurement> getLatestMeasurements() {
        return measurementRepository.getLatestMeasurement(measurementType, areaId);
    }
    public void retrieveLatestMeasurement(MeasurementType measurementType, int areaId) {
        measurementRepository.retrieveLatestMeasurement(areaId, measurementType, true);
    }

    public void saveLatestInformation(MeasurementType measurementType, int areaId){
        this.areaId = areaId;
        this.measurementType = measurementType;

//        sharedPreferences.edit().putString("measurementType", measurementType.toString()).apply();
//        sharedPreferences.edit().putInt("areaId", areaId).apply();
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
