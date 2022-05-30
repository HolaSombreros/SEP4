package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.User;
import com.example.farmerama.data.model.UserRole;
import com.example.farmerama.data.model.response.UserResponse;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.network.UserApi;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
import com.example.farmerama.data.persistence.IUserDAO;
import com.example.farmerama.data.util.ConnectivityChecker;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.data.util.ErrorReader;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class UserRepository {

    private static UserRepository instance;
    private  MutableLiveData<List<User>> users;
    private final MutableLiveData<User> user;
    private final MutableLiveData<User> loggedInUser;
    private final ExecutorService executorService;
    private final FarmeramaDatabase database;
    private ConnectivityChecker checker;

    private UserRepository(Application application) {
        super();
        checker = new ConnectivityChecker(application);
        users = new MutableLiveData<>();
        user = new MutableLiveData<>();
        loggedInUser = new MutableLiveData<>();
        database = FarmeramaDatabase.getInstance(application);
        executorService = Executors.newFixedThreadPool(5);
    }

    public static synchronized UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }

    public void logOut() {
        loggedInUser.setValue(null);
    }

    public LiveData<User> getLoggedInUser() {
        return loggedInUser;
    }

    public LiveData<List<User>> getAllEmployees() {
        return users;
    }

    public LiveData<User> getEmployee() {
        return user;
    }

    public void removeLocalData() {
        executorService.execute(() -> {
            database.areaDAO().removeAreas();
            database.thresholdModificationDAO().removeThresholdModification();
            database.userDAO().removeUsers();
            database.exceededLogDAO().removeExceededLogs();
            database.barnDAO().removeAllBarns();
            database.measurementDAO().removeMeasurements();
            database.thresholdDAO().removeThresholds();
        });
    }

    public void retrieveAllEmployees() {
        if(checker.isOnlineMode()) {
            UserApi userApi = ServiceGenerator.getUserApi();
            Call<List<UserResponse>> call = userApi.getAllEmployees();
            call.enqueue(new Callback<List<UserResponse>>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                    if (response.isSuccessful()) {
                        List<User> usersList = new ArrayList<>();
                        executorService.execute(() -> {
                            for(UserResponse user : response.body()) {
                                database.userDAO().registerUser(user.getUser());
                                usersList.add(user.getUser());
                            }
                        });
                        users.postValue(usersList);
                    }
                    else {
                        ErrorReader<List<UserResponse>> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ListenableFuture<List<User>> future = database.userDAO().getAllEmployees();
            future.addListener(() -> {
                try{
                    users.postValue(future.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newSingleThreadExecutor());
        }
    }

    public void retrieveUserById(int id) {
        if(checker.isOnlineMode()) {
            UserApi userApi = ServiceGenerator.getUserApi();
            Call<UserResponse> call = userApi.getEmployeeById(id);
            call.enqueue(new Callback<UserResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        user.setValue(response.body().getUser());
                    } else {
                        ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }

                @EverythingIsNonNull
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.i("Retrofit", "could not retrieve");
                }
            });
        }
        else {
            ListenableFuture<User> future = database.userDAO().getEmployeeById(id);
            future.addListener(() -> {
                try{
                    user.postValue(future.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newSingleThreadExecutor());
        }
    }

    public void register(User employee) {
        if(checker.isOnlineMode()) {
            UserApi userApi = ServiceGenerator.getUserApi();
            Call<UserResponse> call = userApi.register(employee);
            call.enqueue(new Callback<UserResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        user.setValue(response.body().getUser());
                        ToastMessage.setToastMessage("Account successfully added");
                    }
                    else {
                        ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }

    public void loginUser(User employee) {
        if(checker.isOnlineMode()) {
            UserApi userApi = ServiceGenerator.getUserApi();
            Call<UserResponse> call = userApi.login(employee);
            call.enqueue(new Callback<UserResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()) {
                        loggedInUser.setValue(response.body().getUser());
                    }
                    else {
                        ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                    loggedInUser.setValue(new User(employee.getEmail(), employee.getPassword(), UserRole.OFFLINE));
                }
            });
        }
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }

    public void deleteEmployeeById(int id)
    {
        if(checker.isOnlineMode()) {
            UserApi userApi = ServiceGenerator.getUserApi();
            Call<UserResponse> call = userApi.deleteEmployeeById(id);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()) {
                        ToastMessage.setToastMessage("Employee Deleted");
                    }
                    else{
                        ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.i("Retrofit","Could not delete the employee.");
                }
            });
        }
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }

    public void updateUser(User user) {
        if(checker.isOnlineMode()) {
            UserApi userApi = ServiceGenerator.getUserApi();
            Call<UserResponse> call = userApi.updateUser(user.getUserId(), user);
            call.enqueue(new Callback<UserResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()) {
                        loggedInUser.setValue(response.body().getUser());
                        ToastMessage.setToastMessage("The account has been successfully updated");
                    }
                    else {
                        ErrorReader<UserResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }
}
