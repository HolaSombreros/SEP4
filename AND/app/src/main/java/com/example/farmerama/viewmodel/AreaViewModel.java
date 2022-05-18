package com.example.farmerama.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.repository.AreaRepository;

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
        if(repository.getAreas().getValue() != null) {
            for(Area area : repository.getAreas().getValue()) {
                list.add(area.getName());
            }
        }
        return new MutableLiveData<>(list);
    }

}
