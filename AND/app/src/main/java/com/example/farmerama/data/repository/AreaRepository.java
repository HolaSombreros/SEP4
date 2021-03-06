package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.response.AreaResponse;
import com.example.farmerama.data.network.AreaApi;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.persistence.FarmeramaDatabase;
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

public class AreaRepository {
    private  MutableLiveData<List<Area>> areas;
    private final MutableLiveData<Area> specificArea;
    private static AreaRepository instance;
    private final ExecutorService executorService;
    private final FarmeramaDatabase database;
    private ConnectivityChecker checker;

    private AreaRepository(Application application) {
        checker = new ConnectivityChecker(application);
        areas = new MutableLiveData<>();
        specificArea = new MutableLiveData<>();
        executorService = Executors.newFixedThreadPool(5);
        database = FarmeramaDatabase.getInstance(application);
    }

    public static AreaRepository getInstance(Application application) {
        if(instance == null) {
            return new AreaRepository(application);
        }
        return instance;
    }

    public LiveData<Area> getSpecificArea() {
        return specificArea;
    }

    public LiveData<List<Area>> getAreas(){
        return areas;
    }

    /**
     * Method that checks if the user is online
     * If the user is online, the data is retrieved from the webservice,
     * loaded in the database and posted to the user
     * In case of offline mode, the data will be retrieved from the local databse
     */
    public void retrieveAreas() {
        if(checker.isOnlineMode()) {
            AreaApi areaApi = ServiceGenerator.getAreaApi();
            Call<List<AreaResponse>> call = areaApi.getAreas();
            call.enqueue(new Callback<List<AreaResponse>>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<List<AreaResponse>> call, Response<List<AreaResponse>> response) {
                    if (response.isSuccessful()) {
                        List<Area> areaList = new ArrayList<>();
                        executorService.execute(() -> {
                            for(AreaResponse areaResponse : response.body()) {
                                database.areaDAO().createArea(areaResponse.getArea());
                                areaList.add(areaResponse.getArea());
                            }
                            areas.postValue(areaList);
                        });
                    }
                    else {
                        ErrorReader<List<AreaResponse>> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<List<AreaResponse>> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ListenableFuture<List<Area>> result = database.areaDAO().getAreas();
            result.addListener(() -> {
                try {
                    areas.postValue(result.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newFixedThreadPool(5));
        }
    }

    public void retrieveAreaById(int areaId) {
        if(checker.isOnlineMode()) {
            AreaApi areaApi = ServiceGenerator.getAreaApi();
            Call<AreaResponse> call = areaApi.getSpecificArea(areaId);
            call.enqueue(new Callback<AreaResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                    if (response.isSuccessful()) {
                        specificArea.setValue(response.body().getArea());
                    }
                    else {
                        ErrorReader<AreaResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<AreaResponse> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ListenableFuture<Area> result = database.areaDAO().getAreaById(areaId);
            result.addListener(() -> {
                try {
                    specificArea.postValue(result.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newFixedThreadPool(5));
        }
    }

    public void createArea(Area area) {
        if(checker.isOnlineMode()){
            AreaApi areaApi = ServiceGenerator.getAreaApi();
            Call<AreaResponse> call = areaApi.createArea(area);
            call.enqueue(new Callback<AreaResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                    if (response.isSuccessful()) {
                        executorService.execute(() -> database.areaDAO().createArea(response.body().getArea()));
                        specificArea.setValue(response.body().getArea());
                        ToastMessage.setToastMessage("Area successfully added");
                    }
                    else {
                        ErrorReader<AreaResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<AreaResponse> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }

    public void editArea(Area area) {
        if(checker.isOnlineMode()) {
            AreaApi areaApi = ServiceGenerator.getAreaApi();
            Call<AreaResponse> call = areaApi.editArea(area.getAreaId(), area);
            call.enqueue(new Callback<AreaResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                    if (response.isSuccessful()) {
                        specificArea.setValue(response.body().getArea());
                        executorService.execute(() -> database.areaDAO().updateArea(response.body().getArea()));
                        ToastMessage.setToastMessage("Area successfully updated");
                    }
                    else {
                        ErrorReader<AreaResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<AreaResponse> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }

    public void removeArea(int areaId) {
        if(checker.isOnlineMode()){
            AreaApi areaApi = ServiceGenerator.getAreaApi();
            Call<AreaResponse> call = areaApi.removeArea(areaId);
            call.enqueue(new Callback<AreaResponse>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                    if (response.isSuccessful()) {
                        executorService.execute(() -> database.areaDAO().removeAreaById(areaId));
                        ToastMessage.setToastMessage("The area has been successfully deleted");
                    }
                    else {
                        ErrorReader<AreaResponse> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<AreaResponse> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ToastMessage.setToastMessage("OFFLINE MODE");
        }
    }
}
