package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.model.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    @GET(".")
    Call<List<UserResponse>> getAllEmployees();

    @GET
    Call<UserResponse> getEmployeeByEmail(@Query("email") String email, @Query("password") String password);

    @GET("{id}")
    Call<UserResponse> getEmployeeById(@Path("id") int id);

    @POST(".")
    Call<UserResponse> register(@Body User user);

    @POST
    Call<UserResponse> login(User user);

}
