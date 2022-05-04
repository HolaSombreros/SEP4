package com.example.farmerama.datalayer.adapter;

import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.datalayer.model.response.MeasurementResponse;

import retrofit2.Call;

public interface MeasurementApiAdapter {
    Call<MeasurementResponse> retrieveLatestMeasurement(MeasurementType type, int areaId, boolean latest);
}
