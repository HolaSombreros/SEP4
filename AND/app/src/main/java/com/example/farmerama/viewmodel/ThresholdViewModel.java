package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;

import java.util.List;

public class ThresholdViewModel extends FactoryViewModel {
    private int areaId;
    private MeasurementType measurementType;

    public ThresholdViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Threshold> getThreshold() {
        return getThresholdRepository().getThresholdData();
    }

    public void getLatestThresholds(MeasurementType type) {
        getThresholdRepository().retrieveThreshold(type, areaId);
    }

    public LiveData<List<Area>> getAreas() {
        return getAreaRepository().getAreas();
    }

    public void getAllAreas() {
        getAreaRepository().retrieveAreas();
    }



    public void editThreshold(Threshold threshold) {
        if(getUserRepository().getLoggedInUser().getValue().getUserId() != 0)
            getThresholdRepository().editThreshold(areaId, measurementType, threshold, getUserRepository().getLoggedInUser().getValue().getUserId());
    }

    public void createThreshold(Threshold threshold) {
        if(getUserRepository().getLoggedInUser().getValue().getUserId() != 0) {
            getThresholdRepository().createThreshold(areaId, measurementType, threshold);
        }
    }

    public void setAreaId(int id) {
        areaId = id;
    }
    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

}
