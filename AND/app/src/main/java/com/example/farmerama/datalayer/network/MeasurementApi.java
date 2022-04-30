package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.response.MeasurementResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MeasurementApi {

    @GET("{areaId}/temperature/latest")
    Call<MeasurementResponse> getLatestTemperature(@Path("areaId") int areaId);

    @GET("{areaId}/humidity/latest")
    Call<MeasurementResponse> getLatestHumidity(@Path("areaId") int areaId);
}
