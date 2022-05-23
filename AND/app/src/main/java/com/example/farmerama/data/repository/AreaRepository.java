package com.example.farmerama.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.response.AreaResponse;
import com.example.farmerama.data.network.AreaApi;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.data.util.ErrorReader;

import java.util.ArrayList;
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
        Call<List<AreaResponse>> call = areaApi.getAreas();
        call.enqueue(new Callback<List<AreaResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<AreaResponse>> call, Response<List<AreaResponse>> response) {
                if (response.isSuccessful()) {
                    List<Area> list = new ArrayList<>();
                    for(AreaResponse areaResponse : response.body()) {
                        list.add(areaResponse.getArea());
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
            }
        });
    }

    public void getSpecificAreaById(int areaId) {
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