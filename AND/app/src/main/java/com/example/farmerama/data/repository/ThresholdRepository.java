package com.example.farmerama.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.LogObj;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.response.AreaResponse;
import com.example.farmerama.data.model.response.LogResponse;
import com.example.farmerama.data.model.response.ThresholdResponse;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.util.ErrorReader;
import com.example.farmerama.data.util.ToastMessage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ThresholdRepository {
    private static ThresholdRepository instance;
    private MutableLiveData<Threshold> thresholds;
    private MutableLiveData<List<LogObj>> logs;

    public ThresholdRepository() {
        thresholds = new MutableLiveData<>();
        logs = new MutableLiveData<>();
    }

    public static ThresholdRepository getInstance() {
        if(instance == null) {
            instance = new ThresholdRepository();
        }
        return instance;
    }

    public LiveData<Threshold> getThresholds() {
        return thresholds;
    }
    public LiveData<List<LogObj>> getLogs() {
        return logs;
    }

    public void getLatestThresholds(MeasurementType type, int areaId) {
        Call<ThresholdResponse> call = ServiceGenerator.getThresholdsApi().getLatestThresholds(areaId, type.toString());
        call.enqueue(new Callback<ThresholdResponse>() {
            @Override
            public void onResponse(Call<ThresholdResponse> call, Response<ThresholdResponse> response) {
                if(response.isSuccessful()) {
                        thresholds.setValue(response.body().getThreshold());
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

    public void editThreshold(int areaId, MeasurementType type, Threshold threshold, int userId) {
        Call<ThresholdResponse> call = ServiceGenerator.getThresholdsApi().editThreshold(areaId, type.toString(), userId,threshold);
        call.enqueue(new Callback<ThresholdResponse>() {
            @Override
            public void onResponse(Call<ThresholdResponse> call, Response<ThresholdResponse> response) {
                if(response.isSuccessful()) {
                    thresholds.setValue(response.body().getThreshold());
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

    public void getAllLogs(int areaId, MeasurementType type, String date ){
        Call<List<LogResponse>> call = ServiceGenerator.getThresholdsApi().getLogs(areaId,type.toString(),date);
        call.enqueue(new Callback<List<LogResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<LogResponse>> call, Response<List<LogResponse>> response) {
                if (response.isSuccessful()) {
                    List<LogObj> list = new ArrayList<>();
                    for(LogResponse logResponse : response.body()) {
                        list.add(logResponse.getLog(type));
                    }
                    logs.setValue(list);
                }
                else {
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
