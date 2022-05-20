package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.ThresholdModifications;

import java.util.List;

public class ThresholdModificationsViewModel extends AndroidViewModel {

    private ThresholdRepository thresholdRepository;

    public ThresholdModificationsViewModel(@NonNull Application application) {
        super(application);
        thresholdRepository = ThresholdRepository.getInstance();
    }

    public void retrieveThresholdsModifications() {
        thresholdRepository.retrieveThresholdModifications();
    }

    public LiveData<List<ThresholdModifications>> getThresholdsModifications() {
        return thresholdRepository.getThresholdModifications();
    }
}
