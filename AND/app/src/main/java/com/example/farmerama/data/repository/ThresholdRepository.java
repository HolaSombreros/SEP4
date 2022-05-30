package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdModification;
import com.example.farmerama.data.model.response.ThresholdModificationsResponse;
import com.example.farmerama.data.model.response.ThresholdResponse;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
import com.example.farmerama.data.persistence.IThresholdDAO;
import com.example.farmerama.data.util.ConnectivityChecker;
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

public class ThresholdRepository {
    private static ThresholdRepository instance;
    private MutableLiveData<Threshold> thresholdData;
    private FarmeramaDatabase database;
    private final ExecutorService executorService;
    private ConnectivityChecker checker;


    private ThresholdRepository(Application application) {
        checker = new ConnectivityChecker(application);
        thresholdData = new MutableLiveData<>();
        database = FarmeramaDatabase.getInstance(application);
        executorService = Executors.newFixedThreadPool(5);
    }

    public static ThresholdRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ThresholdRepository(application);
        }
        return instance;
    }

    public LiveData<Threshold> getThresholdData() {
        return thresholdData;
    }


    public void retrieveThreshold(MeasurementType type, int areaId) {
        if(checker.isOnlineMode()){
            Call<ThresholdResponse> call = ServiceGenerator.getThresholdsApi().getLatestThresholds(areaId, type.toString());
            call.enqueue(new Callback<ThresholdResponse>() {
                @Override
                public void onResponse(Call<ThresholdResponse> call, Response<ThresholdResponse> response) {
                    if (response.isSuccessful()) {
                        executorService.execute(() -> {
                            database.thresholdDAO().createThreshold(response.body().getThreshold());
                            thresholdData.postValue(response.body().getThreshold());
                        });
                    } else {
                        thresholdData.postValue(null);
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
            future.addListener(() -> {
                try{
                    thresholdData.postValue(future.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
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
                        thresholdData.setValue(response.body().getThreshold());
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
                        thresholdData.setValue(response.body().getThreshold());
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
}
