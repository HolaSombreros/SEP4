package com.example.farmerama.datalayer.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.dao.MeasurementDAO;
import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.network.MeasurementApi;
import com.example.farmerama.datalayer.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MeasurementRepository {

    private MeasurementDAO measurementDAO;
    private static MeasurementRepository instance;
    private static Object lock = new Object();
    //private MutableLiveData<List<Measurement>> measurements;
    private MutableLiveData<Measurement> measurement;

    private MeasurementRepository() {
        //measurements = new MutableLiveData<>();
        measurement = new MutableLiveData<>();
    }

    public static MeasurementRepository getInstance() {
        if(instance == null){
            synchronized (lock) {
                instance = new MeasurementRepository();
            }
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
