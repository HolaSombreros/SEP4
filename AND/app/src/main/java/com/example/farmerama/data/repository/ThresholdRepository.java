package com.example.farmerama.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdModifications;
import com.example.farmerama.data.model.response.MeasurementResponse;
import com.example.farmerama.data.model.response.ThresholdModificationsResponse;
import com.example.farmerama.data.model.response.ThresholdResponse;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.util.ErrorReader;
import com.example.farmerama.data.util.ToastMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThresholdRepository {
    private static ThresholdRepository instance;
    private MutableLiveData<Threshold> thresholds;
    private MutableLiveData<List<ThresholdModifications>> thresholdModifications;

    public ThresholdRepository() {
        thresholds = new MutableLiveData<>();
        thresholdModifications = new MutableLiveData<>();
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

    public LiveData<List<ThresholdModifications>> getThresholdModifications() {
        return thresholdModifications;
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


    public void retrieveThresholdModifications(String date) {
        Call<List<ThresholdModificationsResponse>> call = ServiceGenerator.getThresholdsApi().getThresholdModifications(date);
        call.enqueue(new Callback<List<ThresholdModificationsResponse>>() {
            @Override
            public void onResponse(Call<List<ThresholdModificationsResponse>> call, Response<List<ThresholdModificationsResponse>> response) {
                List<ThresholdModifications> list = new ArrayList<>();
                if (response.isSuccessful()) {
                    for (ThresholdModificationsResponse modifications : response.body()) {
                        list.add(modifications.getModification());
                    }
                    thresholdModifications.setValue(list);
                }
                else {
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
}
