package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.MeasurementType;

import java.util.List;

public class MeasurementsViewModel extends FactoryViewModel {
    private int areaId;

    public MeasurementsViewModel(Application application) {
        super(application);
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

    // for historical
    public void retrieveMeasurements(MeasurementType type, String date) {
        getMeasurementRepository().retrieveMeasurements(areaId, type, date);
    }

    public void retrieveAreas() {
        getAreaRepository().retrieveAreas();
    }

    public LiveData<List<Area>> getAreas() {
       return getAreaRepository().getAreas();
    }

    public void setAreaId(int id) {
        areaId = id;
    }
}
