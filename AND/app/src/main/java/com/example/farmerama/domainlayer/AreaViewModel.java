package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.farmerama.datalayer.model.Area;
import com.example.farmerama.datalayer.repository.AreaRepository;

import java.util.ArrayList;
import java.util.List;


public class AreaViewModel extends ViewModel {

    private final AreaRepository repository;

    public AreaViewModel() {
        repository = AreaRepository.getInstance() ;
    }

    public LiveData<Area> getSpecificArea(){

        return repository.getSpecificArea();

    }
    public LiveData<List<Area>> getAreas(){
        return repository.getAreas();
    }

    public void getAllAreas() {
        repository.getAllAreas();
    }

    public LiveData<List<String>> getAreasName() {
        List<String> list = new ArrayList<>();
        getAllAreas();
        for(Area area : getAreas().getValue()) {
            list.add(area.getName());
        }
        return new MutableLiveData<>(list);
    }

}
