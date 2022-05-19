package com.example.farmerama.data.adapter;

import com.example.farmerama.data.model.MeasurementType;
import com.example.farmerama.data.model.response.MeasurementResponse;
import com.example.farmerama.data.network.MeasurementApi;
import com.example.farmerama.data.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;

public class MeasurementApiAdapterClass implements MeasurementApiAdapter{

    @Override
    public Call<List<MeasurementResponse>> retrieveLatestMeasurement(MeasurementType type, int areaId, boolean latest) {
        MeasurementApi measurementApi = ServiceGenerator.getMeasurementApi();
        switch (type) {
            case TEMPERATURE:
                return measurementApi.getLatestTemperature(areaId, latest);
            case HUMIDITY:
                return measurementApi.getLatestHumidity(areaId, latest);
            case SPL:
                return measurementApi.getLatestSpl(areaId, latest);
            case CO2:
                return measurementApi.getLatestCo2(areaId, latest);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Override
    public Call<List<MeasurementResponse>> getMeasurements(MeasurementType type, int areaId, String date) {
        MeasurementApi measurementApi = ServiceGenerator.getMeasurementApi();
        switch (type) {
            case TEMPERATURE:
                return measurementApi.getTemperatures(areaId,date);
            case HUMIDITY:
                return measurementApi.getHumidities(areaId, date);
            case SPL:
                return measurementApi.getSpls(areaId, date);
            case CO2:
                return measurementApi.getCo2s(areaId, date);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
