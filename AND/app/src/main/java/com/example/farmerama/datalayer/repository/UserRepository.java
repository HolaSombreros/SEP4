package com.example.farmerama.datalayer.repository;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.model.response.ErrorResponse;
import com.example.farmerama.datalayer.model.response.UserResponse;
import com.example.farmerama.datalayer.network.ServiceGenerator;
import com.example.farmerama.datalayer.network.UserApi;
import com.example.farmerama.util.ErrorReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class UserRepository extends ErrorRepository {

    private static UserRepository instance;
    private final MutableLiveData<List<User>> users;
    private final MutableLiveData<User> user;
    private final MutableLiveData<String> error;
    private MutableLiveData<Boolean> loggedUser;
    private SuccessResponse successResponse;
    private SharedPreferences sharedPreferences;


    private UserRepository() {
        super();
        //sharedPreferences = application.getSharedPreferences("isLoggedUser",0);
        users = new MutableLiveData<>();
        user = new MutableLiveData<>();
        error = new MutableLiveData<>();
        loggedUser = new MutableLiveData<>();
        successResponse = new SuccessResponse(false);
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
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

    public LiveData<String> getError() {
        return error;
    }

    public SuccessResponse getSuccessResponse() {
        return successResponse;
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
                    successResponse.setSuccess(true);
                }
                else {
                    ErrorReader<List<UserResponse>> responseErrorReader = new ErrorReader<>();
                    error.setValue(responseErrorReader.errorReader(response));
                    error.setValue(null);
                    successResponse.setSuccess(false);
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
                    successResponse.setSuccess(true);
                }
                else {
                    ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                    error.setValue(responseErrorReader.errorReader(response));
                    error.setValue(null);
                    successResponse.setSuccess(false);
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
                    successResponse.setSuccess(true);
                }
                else {
                    ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                    error.setValue(responseErrorReader.errorReader(response));
                    error.setValue(null);
                    successResponse.setSuccess(false);
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

    public void loginUser(User employee) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.login(employee);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    user.setValue(response.body().getUser());
                    loggedUser.setValue(true);
                    successResponse.setSuccess(true);
                }
                else {
                    ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                    error.setValue(responseErrorReader.errorReader(response));
                    error.setValue(null);
                    successResponse.setSuccess(false);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
