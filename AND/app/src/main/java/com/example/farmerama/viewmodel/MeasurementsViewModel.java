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

import java.util.List;

public class MeasurementsViewModel extends AndroidViewModel {
    private MeasurementRepository measurementRepository;
    //private MutableLiveData<Measurement> measurement;
    private AreaRepository areaRepository;
    private SharedPreferences sharedPreferences;
    private int areaId;
    private MeasurementType measurementType;

    public MeasurementsViewModel(Application application) {
        super(application);
        //measurement = new MutableLiveData<>();
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

    public LiveData<Measurement> getLatestMeasurement() {
        return measurementRepository.getLatestMeasurement();
    }

    public void retrieveLatestMeasurement(MeasurementType measurementType, int areaId) {
        measurementRepository.retrieveLatestMeasurement(areaId, measurementType, true);
    }

    public void saveLatestInformation(MeasurementType measurementType, int areaId){
        this.areaId = areaId;
        this.measurementType = measurementType;
    }
    // for historical
    public void retrieveMeasurements(MeasurementType type, String date) {
        measurementRepository.retrieveMeasurements(areaId, type, date);
    }

    public void getAllAreas() {
        areaRepository.retrieveAreas();
    }

    public LiveData<List<Area>> getAreas() {
       return areaRepository.getAreas();
    }

    public void setAreaId(int id) {
        areaId = id;
    }
//
//    public void setMeasurementType(MeasurementType measurementType) {
//        measurementRepository.setMeasurementType(measurementType);
//    }
}
