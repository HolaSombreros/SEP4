package com.example.farmerama.datalayer.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.adapter.MeasurementApiAdapter;
import com.example.farmerama.datalayer.adapter.MeasurementApiAdapterClass;
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
    private MeasurementApiAdapter adapter;

    private MeasurementRepository() {
        measurement = new MutableLiveData<>();
        adapter = new MeasurementApiAdapterClass();
    }

    public static MeasurementRepository getInstance() {
        if(instance == null) {
            instance = new MeasurementRepository();
        }
        return instance;
    }

    public LiveData<Measurement> getLatestMeasurement() {
        return measurement;
    }

    public void retrieveLatestMeasurement(int areaId, MeasurementType type, boolean latest) {
        Call<MeasurementResponse> call = adapter.retrieveLatestMeasurement(type, areaId, latest);
        call.enqueue(new Callback<MeasurementResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MeasurementResponse> call, Response<MeasurementResponse> response) {
                if (response.isSuccessful()) {
                    measurement.setValue(response.body().getMeasurement());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MeasurementResponse> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
