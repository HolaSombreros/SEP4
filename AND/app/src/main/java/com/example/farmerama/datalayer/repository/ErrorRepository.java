package com.example.farmerama.datalayer.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ErrorRepository {

    private MutableLiveData<String> errorMessage;

    public ErrorRepository(){
        errorMessage = new MutableLiveData<>();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setValue(errorMessage);
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
