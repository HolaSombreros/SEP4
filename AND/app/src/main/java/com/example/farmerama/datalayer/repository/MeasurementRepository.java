package com.example.farmerama.datalayer.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.datalayer.network.MeasurementApi;
import com.example.farmerama.datalayer.network.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MeasurementRepository {

    private MutableLiveData<Measurement> measurement;
    private static MeasurementRepository instance;

    private MeasurementRepository() {
        measurement = new MutableLiveData<>();
    }

    public static MeasurementRepository getInstance() {
        if(instance == null) {
            instance = new MeasurementRepository();
        }
        return instance;
    }

    public LiveData<Measurement> getLatestTemperature() {
        return measurement;
    }

    public LiveData<Measurement> getLatestHumidity() {
        return measurement;
    }

    public void retrieveLatestMeasurement(int areaId, MeasurementType type) {
        MeasurementApi measurementApi = ServiceGenerator.getMeasurementApi();
        Call<Measurement> call = getMeasurementCall(measurementApi, type, areaId);
        call.enqueue(new Callback<Measurement>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Measurement> call, Response<Measurement> response) {
                if (response.isSuccessful()) {
                    response.body().setMeasurementType(type);
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

    private Call<Measurement> getMeasurementCall(MeasurementApi measurementApi, MeasurementType type, int areaId) {
        switch (type) {
            case TEMPERATURE:
                return measurementApi.getLatestTemperature(areaId);
            case HUMIDITY:
                return measurementApi.getLatestHumidity(areaId);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
