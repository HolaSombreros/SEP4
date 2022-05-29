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

public class MeasurementsViewModel extends FactoryViewModel {
    private SharedPreferences sharedPreferences;
    private int areaId;
    private MeasurementType measurementType;

    public MeasurementsViewModel(Application application) {
        super(application);
        this.sharedPreferences = application.getSharedPreferences("Latest", Context.MODE_PRIVATE);
    }

    public LiveData<List<Measurement>> getMeasurements() {
        return getMeasurementRepository().getMeasurements();
    }

    public void retrieveLatestMeasurement(MeasurementType type, boolean latest) {
        getMeasurementRepository().retrieveLatestMeasurement(areaId, type, latest);
    }

    public LiveData<Measurement> getLatestMeasurement() {
        return getMeasurementRepository().getLatestMeasurement();
    }

    public void retrieveLatestMeasurement(MeasurementType measurementType, int areaId) {
        getMeasurementRepository().retrieveLatestMeasurement(areaId, measurementType, true);
    }

    public void saveLatestInformation(MeasurementType measurementType, int areaId){
        this.areaId = areaId;
        this.measurementType = measurementType;
    }
    // for historical
    public void retrieveMeasurements(MeasurementType type, String date) {
        getMeasurementRepository().retrieveMeasurements(areaId, type, date);
    }

    public void getAllAreas() {
        getAreaRepository().retrieveAreas();
    }

    public LiveData<List<Area>> getAreas() {
       return getAreaRepository().getAreas();
    }

    public void setAreaId(int id) {
        areaId = id;
    }
}
