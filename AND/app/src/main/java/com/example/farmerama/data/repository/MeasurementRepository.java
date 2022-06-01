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
import com.example.farmerama.data.util.ConnectivityChecker;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.data.util.ErrorReader;
import com.google.common.util.concurrent.ListenableFuture;

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
    private MutableLiveData<Measurement> latestMeasurement;
    private static MeasurementRepository instance;
    private MeasurementApiAdapterInterface adapter;
    private final ExecutorService executorService;
    private final FarmeramaDatabase database;
    private ConnectivityChecker onlineChecker;


    private MeasurementRepository(Application application) {
        measurements = new MutableLiveData<>();
        latestMeasurement = new MutableLiveData<>();
        adapter = new MeasurementApiAdapter();
        executorService = Executors.newFixedThreadPool(5);
        database = FarmeramaDatabase.getInstance(application);
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

    /**
     * Method that checks if the user is online
     * If the user is online, the data is retrieved from the webservice,
     * loaded in the database and posted to the user
     * In case of offline mode, the data will be retrieved from the local databse
     */

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
                                database.measurementDAO().createMeasurement(measurement.getMeasurement(type));
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
            ListenableFuture<Measurement> result = database.measurementDAO().getLatestMeasurement(type, areaId);
            result.addListener(() -> {
                try {
                    latestMeasurement.postValue(result.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newFixedThreadPool(5));
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
                                database.measurementDAO().createMeasurement(measurement.getMeasurement(type));
                                measurementsList.add(measurement.getMeasurement(type));
                            }
                            measurements.postValue(measurementsList);
                        });
                        if (measurementsList.size() == 0) {
                            ToastMessage.setToastMessage("No data available");
                        }
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
            ListenableFuture<List<Measurement>> result = database.measurementDAO().getHistoricalMeasurements(type, areaId);
            result.addListener(() -> {
                try {
                    measurements.postValue(result.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newFixedThreadPool(5));
        }
    }
}
