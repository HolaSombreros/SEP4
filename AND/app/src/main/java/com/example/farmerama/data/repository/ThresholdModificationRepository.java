package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.ThresholdModification;
import com.example.farmerama.data.model.response.ThresholdModificationsResponse;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
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

public class ThresholdModificationRepository {
    private static ThresholdModificationRepository instance;
    private MutableLiveData<List<ThresholdModification>> thresholdModifications;
    private FarmeramaDatabase database;
    private final ExecutorService executorService;
    private ConnectivityChecker checker;

    public static ThresholdModificationRepository getInstance(Application application) {
        if(instance == null) {
            instance = new ThresholdModificationRepository(application);
        }
        return instance;
    }
    private ThresholdModificationRepository(Application application) {
        thresholdModifications = new MutableLiveData<>();
        database = FarmeramaDatabase.getInstance(application);
        executorService = Executors.newFixedThreadPool(5);
        checker = new ConnectivityChecker(application);
    }

    public LiveData<List<ThresholdModification>> getThresholdModifications() {
        return thresholdModifications;
    }

    /**
     * Method that checks if the user is online
     * If the user is online, the data is retrieved from the webservice,
     * loaded in the database and posted to the user
     * In case of offline mode, the data will be retrieved from the local databse
     */

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
                                database.thresholdModificationDAO().createThresholdModification(modification.getModification());
                            }
                            thresholdModifications.postValue(list);
                        });

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
            ListenableFuture<List<ThresholdModification>> result = database.thresholdModificationDAO().getThresholdModifications(date);
            result.addListener(() -> {
                try {
                    thresholdModifications.postValue(result.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newFixedThreadPool(5));
        }
    }
}
