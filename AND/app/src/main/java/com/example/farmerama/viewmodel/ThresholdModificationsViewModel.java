package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.ThresholdModification;
import com.example.farmerama.data.repository.ThresholdRepository;

import java.util.List;

public class ThresholdModificationsViewModel extends AndroidViewModel {

    private ThresholdRepository thresholdRepository;

    public ThresholdModificationsViewModel(@NonNull Application application) {
        super(application);
        thresholdRepository = ThresholdRepository.getInstance(application);
    }

    public void retrieveThresholdsModifications(String date) {
        thresholdRepository.retrieveThresholdModifications(date);
    }

    public LiveData<List<ThresholdModification>> getThresholdsModifications() {
        return thresholdRepository.getThresholdModifications();
    }
}
