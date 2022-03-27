package com.example.farmerama.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.dao.MeasurementDAO;
import com.example.farmerama.datalayer.model.Measurement;

import java.util.List;

public class MeasurementRepository {

    private MeasurementDAO measurementDAO;
    private static MeasurementRepository instance;
    private static Object lock = new Object();

    public MeasurementRepository() {
        measurementDAO = MeasurementDAO.getInstance();
    }

    public static MeasurementRepository getInstance() {
        if(instance == null){
            synchronized (lock) {
                instance = new MeasurementRepository();
            }
        }
        return instance;
    }
    /**
     * List of latest measurement of temperature, humidity and so on
     * @param areaId
     * @return
     */
    public MutableLiveData<List<Measurement>> getLatestMeasurement(int areaId) {
        return measurementDAO.getLatestMeasurements(areaId);
    }
}
