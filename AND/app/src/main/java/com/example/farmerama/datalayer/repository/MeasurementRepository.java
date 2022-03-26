package com.example.farmerama.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.dao.MeasurementDAO;
import com.example.farmerama.datalayer.model.Measurement;

import java.util.List;

public class MeasurementRepository {

    private MeasurementDAO measurementDAO;

    public MutableLiveData<List<Measurement>> getLatestMeasurement(int areaId) {
        return measurementDAO.getLatestMeasurements(areaId);
    }
}
