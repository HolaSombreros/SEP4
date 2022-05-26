package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.adapter.MeasurementApiAdapterInterface;
import com.example.farmerama.data.adapter.MeasurementApiAdapter;
import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.response.MeasurementResponse;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
import com.example.farmerama.data.persistence.IMeasurementDAO;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.data.util.ErrorReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MeasurementRepository {

    private MutableLiveData<List<Measurement>> measurements;
    private static MeasurementRepository instance;
    private MeasurementApiAdapterInterface adapter;
    private final ExecutorService executorService;
    private final FarmeramaDatabase database;
    private IMeasurementDAO measurementDAO;
    private LiveData<List<Measurement>> measurementsRoom;


    private MeasurementRepository(Application application) {
        measurements = new MutableLiveData<>();
        adapter = new MeasurementApiAdapter();
        executorService = Executors.newFixedThreadPool(5);
        database = FarmeramaDatabase.getInstance(application);
        measurementDAO = database.measurementDAO();
        measurementsRoom = measurementDAO.getMeasurements();
    }

    public static MeasurementRepository getInstance(Application application) {
        if (instance == null) {
            instance = new MeasurementRepository(application);
        }
        return instance;
    }

    public LiveData<List<Measurement>> getMeasurements() {
        return measurementDAO.getMeasurements();
    }

    public void retrieveLatestMeasurement(int areaId, MeasurementType type, boolean latest) {
        Call<List<MeasurementResponse>> call = adapter.retrieveLatestMeasurement(type, areaId, latest);
        call.enqueue(new Callback<List<MeasurementResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<MeasurementResponse>> call, Response<List<MeasurementResponse>> response) {
                //List<Measurement> list = new ArrayList<>();
                //executorService.execute(measurementDAO::removeMeasurements);
                if (response.isSuccessful()) {
                    executorService.execute(() -> {
                        for (MeasurementResponse measurement : response.body()) {
                            //list.add(measurement.getMeasurement(type));
                            measurementDAO.createMeasurement(measurement.getMeasurement(type));
                        }
                    });

//                    if (list.size() != 0) {
//                        measurements.setValue(list);
//                    }
                } else {
                    ErrorReader<List<MeasurementResponse>> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<MeasurementResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void retrieveMeasurements(int areaId, MeasurementType type, String date) {
        Call<List<MeasurementResponse>> call = adapter.retrieveMeasurements(type, areaId, date);
        call.enqueue(new Callback<List<MeasurementResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<MeasurementResponse>> call, Response<List<MeasurementResponse>> response) {
                List<Measurement> list = new ArrayList<>();
                if (response.isSuccessful()) {
                    for (MeasurementResponse measurement : response.body()) {
                        list.add(measurement.getMeasurement(type));
                    }
                    measurements.setValue(list);
                }
                else {
                    ErrorReader<List<MeasurementResponse>> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<MeasurementResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
