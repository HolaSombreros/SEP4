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
import com.example.farmerama.data.persistence.IAreaDAO;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.data.util.ErrorReader;

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
    private  IAreaDAO areaDAO;
    private MutableLiveData<List<Area>> areasRoom;

    private AreaRepository(Application application) {
        areas = new MutableLiveData<>();
        specificArea = new MutableLiveData<>();
        executorService = Executors.newFixedThreadPool(5);
        database = FarmeramaDatabase.getInstance(application);
        areaDAO = database.areaDAO();
        areasRoom = new MutableLiveData<>();
        areasRoom.setValue(areaDAO.getAreas().getValue());
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

    public void retrieveAreas() {
        AreaApi areaApi = ServiceGenerator.getAreaApi();
        Call<List<AreaResponse>> call = areaApi.getAreas();
        call.enqueue(new Callback<List<AreaResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<AreaResponse>> call, Response<List<AreaResponse>> response) {
                if (response.isSuccessful()) {
                    List<Area> list = new ArrayList<>();
                    executorService.execute(areaDAO::removeAreas);
                    for(AreaResponse areaResponse : response.body()) {
                        list.add(areaResponse.getArea());
                        executorService.execute(() -> areaDAO.createArea(areaResponse.getArea()));
                    }
                    areas.setValue(list);
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
                //areas.postValue(areaDAO.getAreas().getValue());
                areas = areasRoom;
            }
        });
    }

    public void retrieveAreaById(int areaId) {
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

    public void createArea(Area area) {
        AreaApi areaApi = ServiceGenerator.getAreaApi();
        Call<AreaResponse> call = areaApi.createArea(area);
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

    public void editArea(Area area) {
        AreaApi areaApi = ServiceGenerator.getAreaApi();
        Call<AreaResponse> call = areaApi.editArea(area.getId(), area);
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

    public void removeArea(int areaId) {
        AreaApi areaApi = ServiceGenerator.getAreaApi();
        Call<AreaResponse> call = areaApi.removeArea(areaId);
        call.enqueue(new Callback<AreaResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful()) {
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
}
