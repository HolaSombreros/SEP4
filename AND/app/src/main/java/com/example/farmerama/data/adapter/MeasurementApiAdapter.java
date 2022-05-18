package com.example.farmerama.data.adapter;

import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.response.MeasurementResponse;

import java.util.List;

import retrofit2.Call;

public interface MeasurementApiAdapter {
    Call<List<MeasurementResponse>> retrieveLatestMeasurement(MeasurementType type, int areaId, boolean latest);
    Call<List<MeasurementResponse>> getMeasurements(MeasurementType type, int areaId, String date);
}
