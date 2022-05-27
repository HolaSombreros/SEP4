package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.ThresholdRepository;
import com.example.farmerama.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ThresholdViewModel extends AndroidViewModel {

    private ThresholdRepository thresholdRepository;
    private AreaRepository areaRepository;
    private UserRepository userRepository;
    private int areaId;
    private MeasurementType measurementType;

    public ThresholdViewModel(@NonNull Application application) {
        super(application);
        this.thresholdRepository = ThresholdRepository.getInstance(application);
        this.userRepository = UserRepository.getInstance(application);
        this.areaRepository = AreaRepository.getInstance(application);
    }

    public LiveData<Threshold> getThreshold() {
        return thresholdRepository.getThreshold();
    }

    public LiveData<List<Area>> getAreas() {
        return areaRepository.getAreas();
    }

    public void getAllAreas() {
        areaRepository.retrieveAreas();
    }

    public void getLatestThresholds(MeasurementType type) {
        thresholdRepository.retrieveThresholds(type, areaId);
    }

    public void editThreshold(Threshold threshold) {
        if(userRepository.getLoggedInUser().getValue().getUserId() != 0)
            thresholdRepository.editThreshold(areaId, measurementType, threshold, userRepository.getLoggedInUser().getValue().getUserId());
    }

    public void createThreshold(Threshold threshold) {
        if(userRepository.getLoggedInUser().getValue().getUserId() != 0) {
            thresholdRepository.createThreshold(areaId, measurementType, threshold);
        }
    }

    public void setAreaId(int id) {
        thresholdRepository.setAreaId(id);
        //areaId = id;
    }
    public void setMeasurementType(MeasurementType measurementType) {
        thresholdRepository.setMeasurementType(measurementType);
        //this.measurementType = measurementType;
    }

}
