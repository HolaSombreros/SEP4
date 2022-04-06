package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.Area;
import com.example.farmerama.datalayer.repository.AreaRepository;

public class AddAreaViewModel extends ViewModel {

    private AreaRepository repository;
    private MutableLiveData<String> errorMessage;

    public AddAreaViewModel() {
        repository = AreaRepository.getInstance();
        errorMessage = new MutableLiveData<>();
    }

    public boolean createNewArea(String name, String description, String noOfPigs) {
        if (name == null || name.isEmpty()) {
            errorMessage.setValue("Please specify the name of the area");
            return false;
        }


       for (Area area : repository.getAreas().getValue()) {
            if (area.equals(name)) {
                errorMessage.setValue("There is already an area with this name");
                return false;
            }
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

        Area area = new Area(name, description, Integer.parseInt(noOfPigs));
        repository.createArea(area);
        return true;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
