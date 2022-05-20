package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.Barn;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.BarnRepository;

import java.util.ArrayList;
import java.util.List;

public class AddEditAreaViewModel extends AndroidViewModel {

    private AreaRepository areaRepository;
    private BarnRepository barnRepository;
    private MutableLiveData<String> errorMessage;
    private Barn barn;

    public AddEditAreaViewModel(Application application) {
        super(application);
        areaRepository = AreaRepository.getInstance();
        barnRepository = BarnRepository.getInstance();
        errorMessage = new MutableLiveData<>();
    }

    public LiveData<List<Barn>> getBarns() {
        return barnRepository.getBarns();
    }

    public void retrieveAllBarns() {
        barnRepository.retrieveBarns();
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
    }

    public boolean createNewArea(String name, String description, String noOfPigs, String hardwareId) {
        if(areaValidation(name, noOfPigs, hardwareId)) {
            Area area = new Area(barn, name, description, Integer.parseInt(noOfPigs), hardwareId);
            areaRepository.createArea(area);
            return true;
        }
        return false;
    }
    public boolean editArea(int id, String name, String description, String noOfPigs, String hardwareId) {
        if(areaValidation(name, noOfPigs, hardwareId)) {
            Area area = new Area(id, barn, name, description, Integer.parseInt(noOfPigs), hardwareId);
            areaRepository.editArea(area);
            return true;
        }
        return false;
    }

    public boolean areaValidation(String name, String noOfPigs, String hardwareId) {
        if (name == null || name.isEmpty()) {
            errorMessage.setValue("Please specify the name of the area");
            return false;
        }

        if (noOfPigs == null || noOfPigs.isEmpty()) {
            errorMessage.setValue("Please specify the number of the pigs");
            return false;
        }
        if(hardwareId == null || hardwareId.isEmpty()) {
            errorMessage.setValue("Please specify the hardware id");
            return false;
        }

        try {
            int numberOfPigs = Integer.parseInt(noOfPigs);

            if (numberOfPigs < 1) {
                errorMessage.setValue("The number of pigs has to be higher than 1");
                return false;
            }
        } catch (Exception e) {
            errorMessage.setValue("The number of pigs must be numeric");
            return false;
        }
        return true;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}