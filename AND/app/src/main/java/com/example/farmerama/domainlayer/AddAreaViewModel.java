package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.Area;
import com.example.farmerama.datalayer.model.Barn;
import com.example.farmerama.datalayer.repository.AreaRepository;
import com.example.farmerama.datalayer.repository.BarnRepository;

import java.util.List;

public class AddAreaViewModel extends ViewModel {

    private AreaRepository areaRepository;
    private BarnRepository barnRepository;
    private MutableLiveData<String> errorMessage;
    private Barn barn;

    public AddAreaViewModel() {
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
        if (name == null || name.isEmpty()) {
            errorMessage.setValue("Please specify the name of the area");
            return false;
        }

        if (description == null || description.isEmpty()) {
            errorMessage.setValue("Please specify the description of the area");
            return false;
        }

        if (noOfPigs == null || noOfPigs.isEmpty()) {
            errorMessage.setValue("Please specify the number of the pigs");
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

        Area area = new Area(barn, name, description, Integer.parseInt(noOfPigs), hardwareId);
        areaRepository.createArea(area);
        return true;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
