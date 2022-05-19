package com.example.farmerama.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Barn;
import com.example.farmerama.data.model.response.BarnResponse;
import com.example.farmerama.data.network.BarnApi;
import com.example.farmerama.data.network.ServiceGenerator;
import com.example.farmerama.data.util.ErrorReader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BarnRepository {

    private MutableLiveData<List<Barn>> barns;
    private MutableLiveData<String> error;
    private static BarnRepository instance;

    private BarnRepository() {
        barns = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public static BarnRepository getInstance() {
        if (instance == null) {
            return new BarnRepository();
        }

        return instance;
    }

    public LiveData<String> getErrorMessage() {
        return error;
    }

    public LiveData<List<Barn>> getBarns() {
        return barns;
    }

    public void retrieveBarns() {
        BarnApi barnApi = ServiceGenerator.getBarnApi();
        Call<List<BarnResponse>> call = barnApi.getBarns();
        call.enqueue(new Callback<List<BarnResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<BarnResponse>> call, Response<List<BarnResponse>> response) {
                if (response.isSuccessful()) {
                    List<Barn> list = new ArrayList<>();
                    for(BarnResponse barnResponse : response.body()) {
                        list.add(barnResponse.getBarn());
                    }
                    barns.setValue(list);
                }
                else {
                    ErrorReader<List<BarnResponse>> responseErrorReader = new ErrorReader<>();
                    error.setValue(responseErrorReader.errorReader(response));
                    error.setValue(null);
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<BarnResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve data");
            }
        });
    }
}
