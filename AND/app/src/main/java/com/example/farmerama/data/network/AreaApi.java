package com.example.farmerama.data.network;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.response.AreaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface AreaApi {

    @POST(".")
    Call<AreaResponse> createArea(@Body Area area);

    @GET(".")
    Call<List<AreaResponse>> getAreas();

    @GET("{id}")
    Call<AreaResponse> getSpecificArea(@Path("id") int areaId);

    @PUT("{id}")
    Call<AreaResponse> editArea(@Path("id") int areaId, @Body Area area);

    @DELETE("{id}")
    Call<AreaResponse> removeArea(@Path("id") int areaId);
}
