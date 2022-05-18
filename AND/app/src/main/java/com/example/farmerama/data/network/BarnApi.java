package com.example.farmerama.data.network;

import com.example.farmerama.data.model.response.BarnResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BarnApi {

    @GET(".")
    Call<List<BarnResponse>> getBarns();
}
