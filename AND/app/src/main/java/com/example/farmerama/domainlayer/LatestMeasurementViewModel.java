package com.example.farmerama.domainlayer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.repository.MeasurementRepository;

import java.util.List;

public class LatestMeasurementViewModel extends ViewModel {
    private MeasurementRepository measurementRepository;

    public LatestMeasurementViewModel(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    /*public MutableLiveData<List<Measurement>> getLatestMeasurement(int areaId) {
        return measurementRepository.getLatestMeasurement(areaId);
    }*/

}
