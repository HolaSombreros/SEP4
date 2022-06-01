package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.response.BarnResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BarnApi {

    @GET(".")
    Call<List<BarnResponse>> getBarns();
}
