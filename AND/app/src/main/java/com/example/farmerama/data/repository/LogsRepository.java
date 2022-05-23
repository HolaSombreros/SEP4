package com.example.farmerama.data.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.farmerama.data.model.LogObj;

import java.util.List;


public class LogsRepository {
    private MutableLiveData<List<LogObj>> logs;
    private static LogsRepository instance;

    public static LogsRepository getInstance() {
        if(instance == null) {
            instance = new LogsRepository();
        }
        return instance;
    }

    public LiveData<List<LogObj>> getLogs() {
        return logs;
    }


}
