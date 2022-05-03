package com.example.farmerama.datalayer.adapter;

import com.example.farmerama.datalayer.model.Measurement;
import com.example.farmerama.datalayer.model.MeasurementType;
import com.example.farmerama.datalayer.network.MeasurementApi;
import com.example.farmerama.datalayer.network.ServiceGenerator;

import retrofit2.Call;

public class MeasurementApiAdapterClass implements MeasurementApiAdapter{

    @Override
    public Call<Measurement> retrieveLatestMeasurement(MeasurementType type, int areaId) {
        MeasurementApi measurementApi = ServiceGenerator.getMeasurementApi();
        switch (type) {
            case TEMPERATURE:
                return measurementApi.getLatestTemperature(areaId);
            case HUMIDITY:
                return measurementApi.getLatestHumidity(areaId);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
