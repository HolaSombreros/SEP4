package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.Area;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface AreaApi {

    @POST
    Call<Area> createArea(Area area);

    @GET
    Call<List<Area>> getAreas();

    @GET("{id}")
    Call<Area> getSpecificArea(@Path("id") int id);

}
