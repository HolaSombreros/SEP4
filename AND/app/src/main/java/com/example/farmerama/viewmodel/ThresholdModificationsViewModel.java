package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.ThresholdModification;

import java.util.List;

public class ThresholdModificationsViewModel extends FactoryViewModel {


    public ThresholdModificationsViewModel(@NonNull Application application) {
        super(application);
    }

    public void retrieveThresholdModifications(String date) {
        getThresholdModificationRepository().retrieveThresholdModifications(date);
    }

    public LiveData<List<ThresholdModification>> getThresholdModifications() {
        return getThresholdModificationRepository().getThresholdModifications();
    }
}
