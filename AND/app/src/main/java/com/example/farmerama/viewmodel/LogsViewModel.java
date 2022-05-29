package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.ExceededLogsRepository;
import com.example.farmerama.data.repository.ThresholdRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogsViewModel extends FactoryViewModel {
    private int areaId;
    private MeasurementType type;
    private String date;

    public LogsViewModel(@NonNull Application application) {
        super(application);
        date = LocalDate.now().toString();
        type = MeasurementType.TEMPERATURE;
    }

    public LiveData<List<ExceededLog>> getLogs() {
        return getExceededLogsRepository().getLogs();
    }

    public void retrieveLogs(String date) {
        getExceededLogsRepository().retrieveLogs(areaId, type, date);
    }

    public void getAllAreas() {
        getAreaRepository().retrieveAreas();
    }

    public LiveData<List<Area>> getAreas() {
        return getAreaRepository().getAreas();
    }

    public LiveData<List<String>> getAreasName() {
        List<String> list = new ArrayList<>();
        if(getAreaRepository().getAreas().getValue() != null) {
            for(Area area : getAreaRepository().getAreas().getValue()) {
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
