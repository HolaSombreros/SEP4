package com.example.farmerama.datalayer.adapter;

import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.datalayer.model.response.MeasurementResponse;
import com.example.farmerama.datalayer.network.MeasurementApi;
import com.example.farmerama.datalayer.network.ServiceGenerator;

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
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}
