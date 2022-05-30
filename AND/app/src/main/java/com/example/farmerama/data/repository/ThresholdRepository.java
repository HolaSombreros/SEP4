package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdModification;
import com.example.farmerama.data.model.response.LogResponse;
import com.example.farmerama.data.model.response.ThresholdModificationsResponse;
import com.example.farmerama.data.model.response.ThresholdResponse;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
import com.example.farmerama.data.persistence.IThresholdDAO;
import com.example.farmerama.data.util.ConnectivityChecker;
import com.example.farmerama.data.util.ErrorReader;
import com.example.farmerama.data.util.ToastMessage;
import com.google.common.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
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
    private FarmeramaDatabase database;
    private final ExecutorService executorService;
    private ConnectivityChecker checker;


    private ThresholdRepository(Application application) {
        checker = new ConnectivityChecker(application);
        threshold = new MutableLiveData<>();
        thresholdModifications = new MutableLiveData<>();
        database = FarmeramaDatabase.getInstance(application);
        executorService = Executors.newFixedThreadPool(5);
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

    public LiveData<List<ThresholdModification>> getThresholdModifications() {
        return thresholdModifications;
    }

    public void retrieveThreshold(MeasurementType type, int areaId) {
        if(checker.isOnlineMode()){
            Call<ThresholdResponse> call = ServiceGenerator.getThresholdsApi().getLatestThresholds(areaId, type.toString());
            call.enqueue(new Callback<ThresholdResponse>() {
                @Override
                public void onResponse(Call<ThresholdResponse> call, Response<ThresholdResponse> response) {
                    if (response.isSuccessful()) {
                        executorService.execute(() -> database.thresholdDAO().createThreshold(response.body().getThreshold()));
                        threshold.postValue(response.body().getThreshold());
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
        else {
            ListenableFuture<Threshold> future = database.thresholdDAO().getThreshold(areaId, type.getType());
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
    }

    public void editThreshold(int areaId, MeasurementType type, Threshold threshold, int userId) {
        if(checker.isOnlineMode()){
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
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }

    public void createThreshold(int areaId, MeasurementType type, Threshold threshold) {
        if(checker.isOnlineMode()){
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
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }


    public void retrieveThresholdModifications(String date) {
        if(checker.isOnlineMode()) {
            Call<List<ThresholdModificationsResponse>> call = ServiceGenerator.getThresholdsApi().getThresholdModifications(date);
            call.enqueue(new Callback<List<ThresholdModificationsResponse>>() {
                @Override
                public void onResponse(Call<List<ThresholdModificationsResponse>> call, Response<List<ThresholdModificationsResponse>> response) {
                    if (response.isSuccessful()) {
                        List<ThresholdModification> list = new ArrayList<>();
                        executorService.execute( () -> {
                            for (ThresholdModificationsResponse modification : response.body()) {
                                list.add(modification.getModification());
                                database.thresholdDAO().createThresholdModification(modification.getModification());
                            }
                            thresholdModifications.postValue(list);
                        });
                        if (list.size() == 0) {
                            ToastMessage.setToastMessage("No data available");
                        }
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
        else {
            executorService.execute( () -> {
                thresholdModifications.postValue(database.thresholdDAO().getThresholdModifications(date));
            });
        }

    }
}
