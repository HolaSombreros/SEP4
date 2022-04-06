package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.Measurement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MeasurementApi {

    @GET("{areaId}/temperature/latest")
    Call<Measurement> getLatestTemperature(@Path("areaId") int areaId);
}