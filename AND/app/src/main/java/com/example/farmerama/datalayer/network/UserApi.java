package com.example.farmerama.datalayer.network;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @GET("users")
    Call<List<UserResponse>> getAllEmployees();

    @GET("users/{email}")
    Call<User> getEmployeeByEmail(@Path("email") String email);

    @POST("users")
    Call<User> register(User employee);
}
