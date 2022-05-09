package com.example.farmerama.datalayer.adapter;

import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.datalayer.model.response.MeasurementResponse;

import java.util.List;

import retrofit2.Call;

public interface MeasurementApiAdapter {
    Call<List<MeasurementResponse>> retrieveLatestMeasurement(MeasurementType type, int areaId, boolean latest);
}
