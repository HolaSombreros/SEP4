package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET(".")
    Call<List<UserResponse>> getAllEmployees();

    @GET("{email}")
    Call<UserResponse> getEmployeeByEmail(@Path("email") String email);

    @GET("{id}")
    Call<UserResponse> getEmployeeById(@Path("id") int id);

    @POST
    Call<UserResponse> register(User user);
}
