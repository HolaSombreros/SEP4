package com.example.farmerama.data.network;

import com.example.farmerama.data.model.response.MeasurementResponse;

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

    @GET("{areaId}/humidities")
    Call<List<MeasurementResponse>> getLatestHumidity(@Path("areaId") int areaId,
                                                      @Query("latest") boolean latest);

    @GET("{areaId}/humidities")
    Call<List<MeasurementResponse>> getHumidities(@Path("areaId") int areaId,
                                                  @Query("date") String date);

    @GET("{areaId}/sounds")
    Call<List<MeasurementResponse>> getLatestSpl(@Path("areaId") int areaId,
                                                 @Query("latest") boolean latest);

    @GET("{areaId}/sounds")
    Call<List<MeasurementResponse>> getSpls(@Path("areaId") int areaId,
                                            @Query("date") String date);

    @GET("{areaId}/co2s")
    Call<List<MeasurementResponse>> getLatestCo2(@Path("areaId") int areaId,
                                                 @Query("latest") boolean latest);

    @GET("{areaId}/co2s")
    Call<List<MeasurementResponse>> getCo2s(@Path("areaId") int areaId,
                                            @Query("date") String date);
}
