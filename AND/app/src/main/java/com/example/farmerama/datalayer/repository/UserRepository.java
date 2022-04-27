package com.example.farmerama.datalayer.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.model.UserResponse;
import com.example.farmerama.datalayer.network.ServiceGenerator;
import com.example.farmerama.datalayer.network.UserApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class UserRepository {

    private static UserRepository instance;
    private final MutableLiveData<List<User>> users;
    private final MutableLiveData<User> user;

    private UserRepository() {
        users = new MutableLiveData<>();
        user = new MutableLiveData<>();
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public LiveData<List<User>> getAllEmployees() {
        return users;
    }

    public LiveData<User> getEmployee() {
        return user;
    }


    public void retrieveAllEmployees() {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<List<UserResponse>> call = userApi.getAllEmployees();
        call.enqueue(new Callback<List<UserResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                List<User> list = new ArrayList<>();
                if (response.isSuccessful()) {
                    for(UserResponse user : response.body()) {
                        list.add(user.getUser());
                    }
                    users.setValue(list);
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void register(User employee) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<User> call = userApi.register(employee);
        call.enqueue(new Callback<User>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void getUserByEmail(String email) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<User> call = userApi.getEmployeeByEmail(email);
        call.enqueue(new Callback<User>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
