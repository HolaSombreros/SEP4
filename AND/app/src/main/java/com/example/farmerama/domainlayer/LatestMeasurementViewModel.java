package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.repository.MeasurementRepository;

import java.util.List;

public class LatestMeasurementViewModel extends ViewModel {
    private MeasurementRepository measurementRepository;

    public LatestMeasurementViewModel() {
        this.measurementRepository = MeasurementRepository.getInstance();
    }

    public LiveData<Measurement> getLatestMeasurement(int areaId) {
        return measurementRepository.getLatestTemperature();
    }

}
