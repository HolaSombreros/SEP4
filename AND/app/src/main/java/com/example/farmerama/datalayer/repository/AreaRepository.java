package com.example.farmerama.datalayer.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.farmerama.datalayer.model.Area;
import com.example.farmerama.datalayer.network.AreaApi;
import com.example.farmerama.datalayer.network.ServiceGenerator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AreaRepository {
    private final MutableLiveData<List<Area>> areas;
    private final MutableLiveData<Area> specificArea;
    private static AreaRepository instance;

    private AreaRepository() {
        areas = new MutableLiveData<>();
        specificArea = new MutableLiveData<>();

    }
    public static AreaRepository getInstance() {
        if(instance == null) {
            return new AreaRepository();
        }
        return instance;
    }

    public LiveData<Area> getSpecificArea() {
        return specificArea;
    }
    public LiveData<List<Area>> getAreas(){
        return areas;
    }

    public void getAllAreas() {
        AreaApi areaApi = ServiceGenerator.getAreaApi();
        Call<List<Area>> call = areaApi.getAreas();
        call.enqueue(new Callback<List<Area>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Area>> call, Response<List<Area>> response) {
                if (response.isSuccessful()) {
                    areas.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Area>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void getSpecificArea(String name) {
        AreaApi areaApi = ServiceGenerator.getAreaApi();
        Call<Area> call = areaApi.getSpecificArea(name);
        call.enqueue(new Callback<Area>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {
                if (response.isSuccessful()) {
                    specificArea.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

    public void createArea(Area area) {
        AreaApi areaApi = ServiceGenerator.getAreaApi();
        Call<Area> call = areaApi.createArea(area);
        call.enqueue(new Callback<Area>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {
                if (response.isSuccessful()) {
                    specificArea.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }

}
