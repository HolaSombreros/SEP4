package com.example.farmerama.data.network;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.model.response.UserResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    @GET(".")
    Call<List<UserResponse>> getAllEmployees();

    @GET("{id}")
    Call<UserResponse> getEmployeeById(@Path("id") int id);

    @POST(".")
    Call<UserResponse> register(@Body User user);

    @POST("login")
    Call<UserResponse> login(@Body User user);

    @PUT("{id}")
    Call<UserResponse> updateUser(@Path("id") int id, @Body User user);

    @DELETE("{id}")
    Call<UserResponse> deleteEmployeeById(@Path("id") int id);
}
