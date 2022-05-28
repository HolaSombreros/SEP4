package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.LogObj;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdModification;
import com.example.farmerama.data.model.response.LogResponse;
import com.example.farmerama.data.model.response.ThresholdModificationsResponse;
import com.example.farmerama.data.model.response.ThresholdResponse;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
import com.example.farmerama.data.persistence.IThresholdDAO;
import com.example.farmerama.data.util.ErrorReader;
import com.example.farmerama.data.util.ToastMessage;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ThresholdRepository {
    private static ThresholdRepository instance;
    private MutableLiveData<Threshold> threshold;
    private MutableLiveData<List<ThresholdModification>> thresholdModifications;
    private MutableLiveData<List<LogObj>> logs;
    private MutableLiveData<List<LogObj>> latestLogs;
    private IThresholdDAO thresholdDAO;
    private FarmeramaDatabase database;
    private final ExecutorService executorService;


    private ThresholdRepository(Application application) {
        threshold = new MutableLiveData<>();
        thresholdModifications = new MutableLiveData<>();
        logs = new MutableLiveData<>();
        latestLogs = new MutableLiveData<>();
        database = FarmeramaDatabase.getInstance(application);
        executorService = Executors.newFixedThreadPool(5);
        thresholdDAO = database.thresholdDAO();
    }

    public static ThresholdRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ThresholdRepository(application);
        }
        return instance;
    }

    public LiveData<Threshold> getThreshold() {
        return threshold;
    }

    public LiveData<List<LogObj>> getLogs() {
        return logs;
    }

    public LiveData<List<LogObj>> getLatestLogs() {
        return latestLogs;
    }

    public LiveData<List<ThresholdModification>> getThresholdModifications() {
        return thresholdModifications;
    }

    public void retrieveThresholds(MeasurementType type, int areaId) {
        Call<ThresholdResponse> call = ServiceGenerator.getThresholdsApi().getLatestThresholds(areaId, type.toString());
        call.enqueue(new Callback<ThresholdResponse>() {
            @Override
            public void onResponse(Call<ThresholdResponse> call, Response<ThresholdResponse> response) {
                if (response.isSuccessful()) {
                    executorService.execute(() -> thresholdDAO.createThreshold(response.body().getThreshold()));
                } else {
                    ErrorReader<ThresholdResponse> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }

            @Override
            public void onFailure(Call<ThresholdResponse> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
        ListenableFuture<Threshold> future = thresholdDAO.getThreshold(areaId, type.getType());
        future.addListener(new Runnable() {
            @Override
            public void run() {
                try{
                    threshold.postValue(future.get());
                }
                catch (Exception e) {

                }
            }
        }, Executors.newSingleThreadExecutor());

    }

    public void editThreshold(int areaId, MeasurementType type, Threshold threshold, int userId) {
        Call<ThresholdResponse> call = ServiceGenerator.getThresholdsApi().editThreshold(areaId, type.toString(), userId, threshold);
        call.enqueue(new Callback<ThresholdResponse>() {
            @Override
            public void onResponse(Call<ThresholdResponse> call, Response<ThresholdResponse> response) {
                if (response.isSuccessful()) {
                    ThresholdRepository.this.threshold.setValue(response.body().getThreshold());
                    ToastMessage.setToastMessage("Threshold edited!");
                } else {
                    ErrorReader<ThresholdResponse> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }

            @Override
            public void onFailure(Call<ThresholdResponse> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void createThreshold(int areaId, MeasurementType type, Threshold threshold) {
        Call<ThresholdResponse> call = ServiceGenerator.getThresholdsApi().createThreshold(areaId, type.toString(), threshold);
        call.enqueue(new Callback<ThresholdResponse>() {
            @Override
            public void onResponse(Call<ThresholdResponse> call, Response<ThresholdResponse> response) {
                if(response.isSuccessful()) {
                    ThresholdRepository.this.threshold.setValue(response.body().getThreshold());
                    ToastMessage.setToastMessage("Threshold created!");
                }
                else {
                    ErrorReader<ThresholdResponse> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }

            @Override
            public void onFailure(Call<ThresholdResponse> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void retrieveLogs(int areaId, MeasurementType type, String date ){
        Call<List<LogResponse>> call = ServiceGenerator.getThresholdsApi().getLogs(areaId,type.toString(),date);
        call.enqueue(new Callback<List<LogResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<LogResponse>> call, Response<List<LogResponse>> response) {
                if (response.isSuccessful()) {
                    List<LogObj> list = new ArrayList<>();
                    for (LogResponse logResponse : response.body()) {
                        list.add(logResponse.getLog(type));
                    }
                    logs.setValue(list);
                } else {
                    ErrorReader<List<LogResponse>> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<LogResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void retrieveThresholdModifications(String date) {
        Call<List<ThresholdModificationsResponse>> call = ServiceGenerator.getThresholdsApi().getThresholdModifications(date);
        call.enqueue(new Callback<List<ThresholdModificationsResponse>>() {
            @Override
            public void onResponse(Call<List<ThresholdModificationsResponse>> call, Response<List<ThresholdModificationsResponse>> response) {
                if (response.isSuccessful()) {
                    List<ThresholdModification> list = new ArrayList<>();

                    for (ThresholdModificationsResponse modification : response.body()) {
                        list.add(modification.getModification());
                    }
                    thresholdModifications.setValue(list);
                } else {
                    ErrorReader<List<ThresholdModificationsResponse>> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }

            @Override
            public void onFailure(Call<List<ThresholdModificationsResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void retrieveTodayLogs() {
        Call<List<LogResponse>> call = ServiceGenerator.getThresholdsApi().getLatestLogs();
        call.enqueue(new Callback<List<LogResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<LogResponse>> call, Response<List<LogResponse>> response) {
                if (response.isSuccessful()) {
                    List<LogObj> list = new ArrayList<>();
                    for (LogResponse logResponse : response.body()) {
                        list.add(logResponse.getLog());
                    }
                    latestLogs.setValue(list);
                } else {
                    ErrorReader<List<LogResponse>> responseErrorReader = new ErrorReader<>();
                    ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<LogResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
