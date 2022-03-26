package com.example.farmerama.datalayer.dao;

import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.model.Measurement;

import java.util.List;

public class MeasurementDAO {
    private static MeasurementDAO measurementDAO;
    private static Object lock = new Object();

    public static MeasurementDAO getInstance() {
        if(measurementDAO == null) {
            synchronized (lock) {
                if(measurementDAO == null)
                    measurementDAO = new MeasurementDAO();
            }
        }
        return measurementDAO;
    }
    public MeasurementDAO(){

    }

    public MutableLiveData<List<Measurement>> getLatestMeasurements(int areaId) {
        return null;
    }
}
