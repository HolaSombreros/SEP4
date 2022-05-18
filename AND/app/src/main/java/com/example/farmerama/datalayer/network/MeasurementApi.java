package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.response.MeasurementResponse;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeasurementApi {

    @GET("{areaId}/temperatures")
    Call<List<MeasurementResponse>> getLatestTemperature(@Path("areaId") int areaId,
                                                         @Query("latest") boolean latest);
    @GET("{areaId}/temperatures")
    Call<List<MeasurementResponse>> getTemperatures(@Path("areaId") int areaId,
                                                         @Query("date") String date);
    Call<List<MeasurementResponse>> getLatestHumidity(@Path("areaId") int areaId,
                                                      @Query("latest") boolean latest);
    @GET("{areaId}/humidities")
    Call<List<MeasurementResponse>> getHumidities(@Path("areaId") int areaId,
                                                  @Query("date") String date);

    @GET("{areaId}/spl")
    Call<List<MeasurementResponse>> getLatestSpl(@Path("areaId") int areaId, @Query("latest") boolean latest);

    @GET("{areaId}/co2s")
    Call<List<MeasurementResponse>> getLatestCo2(@Path("areaId") int areaId, @Query("latest") boolean latest);
}
