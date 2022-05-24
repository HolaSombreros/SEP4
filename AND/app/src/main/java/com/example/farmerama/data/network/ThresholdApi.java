package com.example.farmerama.data.network;

import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.response.ThresholdModificationsResponse;
import com.example.farmerama.data.model.response.ThresholdResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ThresholdApi {

    @GET("{areaId}")
    Call<ThresholdResponse> getLatestThresholds(@Path("areaId") int areaId, @Query("type") String type);

    @POST("{areaId}")
    Call<ThresholdResponse> createThreshold(@Path("areaId") int areaId, @Query("type") String type, @Body Threshold threshold);

    @PUT("{areaId}")
    Call<ThresholdResponse> editThreshold(@Path("areaId") int areaId, @Query("type") String type, @Query("userId") int userId, @Body Threshold threshold);

    @GET("logs")
    Call<List<ThresholdModificationsResponse>> getThresholdModifications(@Query("date") String date);

    @GET("{areaId}/logs")
    Call<List<LogResponse>> getLogs(@Path("areaId") int areaId, @Query("type") String type, @Query("date") String date);
}
