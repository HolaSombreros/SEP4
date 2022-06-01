package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.util.ToastMessage;

import java.util.List;

public class ThresholdViewModel extends FactoryViewModel {
    private int areaId;
    private MeasurementType measurementType;
    private double max;
    private double min;

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

    public void editThreshold(String maximum, String minimum) {
        if(verifyThresholdsValues(maximum, minimum))
            if(getUserRepository().getLoggedInUser().getValue().getUserId() != 0)
                getThresholdRepository().editThreshold(areaId, measurementType, new Threshold(min, max), getUserRepository().getLoggedInUser().getValue().getUserId());
    }

    public boolean verifyThresholdsValues(String maximum, String minimum) {
        if(maximum.trim().equals("")|| minimum.trim().equals("")) {
            ToastMessage.setToastMessage("Cannot create threshold with no value");
            return false;
        }
        try{
            max = Double.parseDouble(maximum);
            min = Double.parseDouble(minimum);
            if(max < 0 || min <0)
                return false;
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public void createThreshold(String maximum, String minimum) {
        if(verifyThresholdsValues(maximum, minimum)) {
            if(getUserRepository().getLoggedInUser().getValue().getUserId() != 0) {
                getThresholdRepository().createThreshold(areaId, measurementType, new Threshold(min, max));
            }
        }
    }

    public void setAreaId(int id) {
        areaId = id;
    }
    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

}
