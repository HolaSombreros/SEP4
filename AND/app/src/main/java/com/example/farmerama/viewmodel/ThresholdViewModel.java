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
        this.thresholdRepository = ThresholdRepository.getInstance();
        this.userRepository = UserRepository.getInstance();
        this.areaRepository = AreaRepository.getInstance();
    }

    public LiveData<Threshold> getThresholds() {
        return thresholdRepository.getThresholds();
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
        if(userRepository.getLoggedInUser().getValue().getId() != 0)
            thresholdRepository.editThreshold(areaId, measurementType, threshold, userRepository.getLoggedInUser().getValue().getId());
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
    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

}
