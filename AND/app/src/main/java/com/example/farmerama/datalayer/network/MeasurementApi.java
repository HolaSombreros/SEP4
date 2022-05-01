package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.response.MeasurementResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeasurementApi {

    @GET("{areaId}/temperatures")
    Call<MeasurementResponse> getLatestTemperature(@Path("areaId") int areaId, @Query("latest") boolean latest);

    @GET("{areaId}/humidities")
    Call<MeasurementResponse> getLatestHumidity(@Path("areaId") int areaId, @Query("latest") boolean latest);
}
