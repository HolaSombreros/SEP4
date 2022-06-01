package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.response.LogResponse;
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
import retrofit2.internal.EverythingIsNonNull;

public class ExceededLogsRepository {
    private MutableLiveData<List<ExceededLog>> logs;
    private FarmeramaDatabase database;
    private final ExecutorService executorService;
    private ConnectivityChecker checker;
    private static ExceededLogsRepository instance;

    private ExceededLogsRepository(Application application) {
        logs = new MutableLiveData<>();
        database = FarmeramaDatabase.getInstance(application);
        executorService = Executors.newFixedThreadPool(5);
        checker = new ConnectivityChecker(application);
    }

    public static ExceededLogsRepository getInstance(Application application){
        if(instance == null) {
            instance = new ExceededLogsRepository(application);
        }
        return instance;
    }

    public LiveData<List<ExceededLog>> getLogs() {
        return logs;
    }

    /**
     * Method that checks if the user is online
     * If the user is online, the data is retrieved from the webservice,
     * loaded in the database and posted to the user
     * In case of offline mode, the data will be retrieved from the local databse
     */
    public void retrieveLogs(int areaId, MeasurementType type, String date) {
        if(checker.isOnlineMode()) {
            Call<List<LogResponse>> call = ServiceGenerator.getThresholdsApi().getLogs(areaId,type.toString(),date);
            call.enqueue(new Callback<List<LogResponse>>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<List<LogResponse>> call, Response<List<LogResponse>> response) {
                    if (response.isSuccessful()) {
                        List<ExceededLog> list = new ArrayList<>();
                        executorService.execute( () -> {
                            for (LogResponse logResponse : response.body()) {
                                list.add(logResponse.getLog(type));
                                database.exceededLogDAO().createExceededLog(logResponse.getLog(type));
                            }
                            logs.postValue(list);
                        });
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
        else {
            ListenableFuture<List<ExceededLog>> result = database.exceededLogDAO().getExceededLogs();
            result.addListener(() -> {
                try {
                    logs.postValue(result.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newFixedThreadPool(5));
        }
    }
}
