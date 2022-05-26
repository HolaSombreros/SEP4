package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaDAO implements IAreaDAO {
    private MutableLiveData<List<Area>> areas;
    private static AreaDAO areaDAO;
    private static Object lock = new Object();

    public static AreaDAO getInstance() {
        if(areaDAO == null) {
            synchronized (lock) {
                if(areaDAO == null) {
                    areaDAO = new AreaDAO();
                }
            }
        }
        return areaDAO;
    }

    private AreaDAO() {
        areas = new MutableLiveData<>();
        areas.setValue(new ArrayList<>());
    }

    @Override
    public void createArea(Area area) {
       List<Area> currentAreas = areas.getValue();
       currentAreas.add(area);
       areas.postValue(currentAreas);
    }

    @Override
    public void removeAreas(List<Area> area) {
        areas.setValue(new ArrayList<>());
    }

    @Override
    public void editArea(Area area) {

    }

    @Override
    public LiveData<List<Area>> getAreas() {
        return areas;
    }

//    @Override
//    public LiveData<Area> getAreaById(int id) {
//        List<Area> currentAreas = areas.getValue();
//        if(currentAreas != null) {
//            for (Area area : currentAreas) {
//                if(area.getId() == id) {
//                    return ;
//                }
//            }
//        }
//        return null;
//    }
}
