package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.Barn;
import com.example.farmerama.data.model.User;
import com.example.farmerama.data.util.ToastMessage;

import java.util.List;

public class AddEditAreaViewModel extends FactoryViewModel {

    private Barn barn;

    public AddEditAreaViewModel(Application application) {
        super(application);
    }

    public LiveData<List<Barn>> getBarns() {
        return getBarnRepository().getBarns();
    }

    public void retrieveAllBarns() {
        getBarnRepository().retrieveBarns();
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
    }

    public boolean createNewArea(String name, String description, String noOfPigs, String hardwareId) {
        if(areaValidation(name, noOfPigs, hardwareId)) {
            Area area = new Area(barn, name, description, Integer.parseInt(noOfPigs), hardwareId);
            getAreaRepository().createArea(area);
            return true;
        }
        return false;
    }
    public boolean editArea(int id, String name, String description, String noOfPigs, String hardwareId) {
        if(areaValidation(name, noOfPigs, hardwareId)) {
            Area area = new Area(id, barn, name, description, Integer.parseInt(noOfPigs), hardwareId);
            getAreaRepository().editArea(area);
            return true;
        }
        return false;
    }

    public LiveData<User> getLoggedInUser() {
        return getUserRepository().getLoggedInUser();
    }

    public boolean areaValidation(String name, String noOfPigs, String hardwareId) {
        if (name == null || name.isEmpty()) {
            ToastMessage.setToastMessage("Please specify the name of the area");
            return false;
        }

        if (noOfPigs == null || noOfPigs.isEmpty()) {
            ToastMessage.setToastMessage("Please specify the number of the pigs");
            return false;
        }
        if(hardwareId == null || hardwareId.isEmpty()) {
            ToastMessage.setToastMessage("Please specify the hardware id");
            return false;
        }

        try {
            int numberOfPigs = Integer.parseInt(noOfPigs);

            if (numberOfPigs < 1) {
                ToastMessage.setToastMessage("The number of pigs has to be higher than 1");
                return false;
            }
        } catch (Exception e) {
            ToastMessage.setToastMessage("The number of pigs must be numeric");
            return false;
        }
        return true;
    }

    public void removeArea(int areaId) {
        getAreaRepository().removeArea(areaId);
    }

    public LiveData<Area> getSpecificArea(int areaId){
        getAreaRepository().retrieveAreaById(areaId);
        return getAreaRepository().getSpecificArea();
    }
}
