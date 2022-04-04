package com.example.farmerama.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.dao.MeasurementDAO;
import com.example.farmerama.datalayer.model.Measurement;

import java.util.List;

public class MeasurementRepository {

    private MeasurementDAO measurementDAO;
    private static MeasurementRepository instance;
    private static Object lock = new Object();
    private static MeasurementRepository instance;
    //private MutableLiveData<List<Measurement>> measurements;
    private MutableLiveData<Measurement> measurement;

    private MeasurementRepository() {
        //measurements = new MutableLiveData<>();
        measurement = new MutableLiveData<>();
    }

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
    public static synchronized MeasurementRepository getInstance() {
        if (instance == null) {
            instance = new MeasurementRepository();
        }
        return instance;
    }

    public LiveData<Measurement> getLatestTemperature() {
        return measurement;
    }

    public void retrieveLatestTemperature(int areaId) {
        MeasurementApi measurementApi = ServiceGenerator.getMeasurementApi();
        Call<Measurement> call = measurementApi.getLatestTemperature(areaId);
        call.enqueue(new Callback<Measurement>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Measurement> call, Response<Measurement> response) {
                if (response.isSuccessful()) {
                    measurement.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Measurement> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
