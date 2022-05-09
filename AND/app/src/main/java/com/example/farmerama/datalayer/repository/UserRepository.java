package com.example.farmerama.datalayer.repository;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.model.response.UserResponse;
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
    private MutableLiveData<Boolean> loggedUser;
    private SharedPreferences sharedPreferences;


    private UserRepository(Application application) {
        sharedPreferences = application.getSharedPreferences("isLoggedUser",0);
        users = new MutableLiveData<>();
        user = new MutableLiveData<>();
        loggedUser = new MutableLiveData<>();

    }

    public static synchronized UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }
    public LiveData<Boolean> isLoggedIn(){
        return loggedUser;
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

    public void getUserById(int id) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.getEmployeeById(id);
        call.enqueue(new Callback<UserResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    user.setValue( response.body().getUser());
                    Log.w("test2", user.getValue().toString());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Retrofit", "could not retrieve shit");
            }
        });
    }

    public void register(User employee) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.register(employee);
        call.enqueue(new Callback<UserResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    user.setValue(response.body().getUser());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void getUserByEmail(String email, String password) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.getEmployeeByEmail(email, password);
        call.enqueue(new Callback<UserResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    user.setValue(response.body().getUser());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
