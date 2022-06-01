package com.example.farmerama.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.BarnRepository;
import com.example.farmerama.data.repository.ExceededLogsRepository;
import com.example.farmerama.data.repository.MeasurementRepository;
import com.example.farmerama.data.repository.ThresholdModificationRepository;
import com.example.farmerama.data.repository.ThresholdRepository;
import com.example.farmerama.data.repository.UserRepository;

public class FactoryViewModel extends AndroidViewModel {

    private AreaRepository areaRepository;
    private UserRepository userRepository;
    private MeasurementRepository measurementRepository;
    private ThresholdRepository thresholdRepository;
    private BarnRepository barnRepository;
    private ExceededLogsRepository exceededLogsRepository;
    private ThresholdModificationRepository thresholdModificationRepository;


    public FactoryViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        areaRepository = AreaRepository.getInstance(application);
        barnRepository = BarnRepository.getInstance(application);
        measurementRepository = MeasurementRepository.getInstance(application);
        exceededLogsRepository = ExceededLogsRepository.getInstance(application);
        thresholdModificationRepository =  ThresholdModificationRepository.getInstance(application);
        thresholdRepository = ThresholdRepository.getInstance(application);
    }

    public ThresholdRepository getThresholdRepository() {
        return thresholdRepository;
    }

    public ThresholdModificationRepository getThresholdModificationRepository() {
        return thresholdModificationRepository;
    }

    public ExceededLogsRepository getExceededLogsRepository() {
        return exceededLogsRepository;
    }

    public BarnRepository getBarnRepository() {
        return barnRepository;
    }

    public AreaRepository getAreaRepository() {
        return areaRepository;
    }

    public MeasurementRepository getMeasurementRepository() {
        return measurementRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
