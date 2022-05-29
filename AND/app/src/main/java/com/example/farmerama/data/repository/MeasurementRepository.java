package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.adapter.MeasurementApiAdapterInterface;
import com.example.farmerama.data.adapter.MeasurementApiAdapter;
import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.response.MeasurementResponse;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
import com.example.farmerama.data.persistence.IMeasurementDAO;
import com.example.farmerama.data.util.ConnectivityChecker;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.data.util.ErrorReader;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.firebase.firestore.local.QueryResult;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MeasurementRepository {

    private MutableLiveData<List<Measurement>> measurements;
    private MutableLiveData<Measurement> latestMeasurement;
    private static MeasurementRepository instance;
    private MeasurementApiAdapterInterface adapter;
    private final ExecutorService executorService;
    private final FarmeramaDatabase database;
    private IMeasurementDAO measurementDAO;
    private ConnectivityChecker onlineChecker;


    private MeasurementRepository(Application application) {
        measurements = new MutableLiveData<>();
        latestMeasurement = new MutableLiveData<>();
        adapter = new MeasurementApiAdapter();
        executorService = Executors.newFixedThreadPool(5);
        database = FarmeramaDatabase.getInstance(application);
        measurementDAO = database.measurementDAO();
        onlineChecker = new ConnectivityChecker(application);
    }

    public static MeasurementRepository getInstance(Application application) {
        if (instance == null) {
            instance = new MeasurementRepository(application);
        }
        return instance;
    }

    public LiveData<Measurement> getLatestMeasurement() {
        return latestMeasurement;
    }

    public LiveData<List<Measurement>> getMeasurements() {
        return measurements;
    }

    public void removeLocalData(){
        measurementDAO.removeMeasurements();
    }

    public void retrieveLatestMeasurement(int areaId, MeasurementType type, boolean latest) {
        if(onlineChecker.isOnlineMode()) {
            Call<List<MeasurementResponse>> call = adapter.retrieveLatestMeasurement(type, areaId, latest);
            call.enqueue(new Callback<List<MeasurementResponse>>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<List<MeasurementResponse>> call, Response<List<MeasurementResponse>> response) {
                    if (response.isSuccessful()) {
                        executorService.execute(() -> {
                            for (MeasurementResponse measurement : response.body()) {
                                measurementDAO.createMeasurement(measurement.getMeasurement(type));
                                latestMeasurement.postValue(measurement.getMeasurement(type));
                            }
                        });
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
        else {
            executorService.execute(() -> {
                latestMeasurement.postValue(measurementDAO.getLatestMeasurement(type, areaId));
            });
        }
    }

    public void retrieveMeasurements(int areaId, MeasurementType type, String date) {
        if(onlineChecker.isOnlineMode()) {
            Call<List<MeasurementResponse>> call = adapter.retrieveMeasurements(type, areaId, date);
            call.enqueue(new Callback<List<MeasurementResponse>>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<List<MeasurementResponse>> call, Response<List<MeasurementResponse>> response) {
                    if (response.isSuccessful()) {
                        List<Measurement> measurementsList = new ArrayList<>();
                        executorService.execute(() -> {
                            for (MeasurementResponse measurement : response.body()) {
                                measurementDAO.createMeasurement(measurement.getMeasurement(type));
                                measurementsList.add(measurement.getMeasurement(type));
                            }
                            measurements.postValue(measurementsList);
                        });
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
        else {
            executorService.execute(() -> {
                measurements.postValue(measurementDAO.getHistoricalMeasurements(type, areaId));
            });
        }
    }
}
