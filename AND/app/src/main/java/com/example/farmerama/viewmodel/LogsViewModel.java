package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.LogObj;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.LogsRepository;

import java.util.ArrayList;
import java.util.List;

public class LogsViewModel extends AndroidViewModel {
    private LogsRepository logRepository;
    private AreaRepository areaRepository;
    private int areaId;

    public LogsViewModel(@NonNull Application application) {
        super(application);
        this.logRepository = LogsRepository.getInstance();
        this.areaRepository = AreaRepository.getInstance();
    }

    public LiveData<List<LogObj>> getLogs() {
        return logRepository.getLogs();
    }

    public void retrieveLogs(MeasurementType type, String date) {
//        logRepository.retrieveLogs(areaId, type, date);
    }

    public void getAllAreas() {
        areaRepository.getAllAreas();
    }

    public LiveData<List<Area>> getAreas() {
        return areaRepository.getAreas();
    }

    public LiveData<List<String>> getAreasName() {
        List<String> list = new ArrayList<>();
        if(areaRepository.getAreas().getValue() != null) {
            for(Area area : areaRepository.getAreas().getValue()) {
                list.add(area.getName());
            }
        }
        return new MutableLiveData<>(list);
    }

    public void setAreaId(int id) {
        areaId = id;
    }
}
