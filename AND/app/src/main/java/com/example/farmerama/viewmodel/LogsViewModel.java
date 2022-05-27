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
import com.example.farmerama.data.repository.ThresholdRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogsViewModel extends AndroidViewModel {
    private ThresholdRepository repository;
    private AreaRepository areaRepository;
    private int areaId;
    private MeasurementType type;
    private String date;

    public LogsViewModel(@NonNull Application application) {
        super(application);
        this.repository = ThresholdRepository.getInstance(application);
        this.areaRepository = AreaRepository.getInstance(application);
        date = LocalDate.now().toString();
        type = MeasurementType.TEMPERATURE;
    }

    public LiveData<List<LogObj>> getLogs() {
        return repository.getLogs();
    }

    public void retrieveLogs() {
        repository.retrieveLogs(areaId, type, date);
    }

    public void getAllAreas() {
        areaRepository.retrieveAreas();
    }

    public LiveData<List<Area>> getAreas() {
        return areaRepository.getAreas();
    }

    public LiveData<List<String>> getAreasName() {
        List<String> list = new ArrayList<>();
        if(areaRepository.getAreas().getValue() != null) {
            for(Area area : areaRepository.getAreas().getValue()) {
                list.add(area.getAreaName());
            }
        }
        return new MutableLiveData<>(list);
    }

    public void setAreaId(int id) {
        areaId = id;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
