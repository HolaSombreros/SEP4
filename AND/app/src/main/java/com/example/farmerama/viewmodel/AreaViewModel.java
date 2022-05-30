package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.repository.AreaRepository;

import java.util.ArrayList;
import java.util.List;


public class AreaViewModel extends FactoryViewModel {

    public AreaViewModel(Application application) {
        super(application);
    }

    public LiveData<Area> getSpecificArea(int areaId){
        getAreaRepository().retrieveAreaById(areaId);
        return getAreaRepository().getSpecificArea();
    }
    public LiveData<List<Area>> getAreas(){
        return getAreaRepository().getAreas();
    }

    public void getAllAreas() {
        getAreaRepository().retrieveAreas();
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

}
