package com.example.farmerama.datalayer.adapter;

import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.model.MeasurementType;

import retrofit2.Call;

public interface MeasurementApiAdapter {
    Call<Measurement> retrieveLatestMeasurement(MeasurementType type, int areaId);
}
