package com.example.farmerama.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.data.model.Barn;
import com.example.farmerama.data.model.response.BarnResponse;
import com.example.farmerama.data.network.BarnApi;
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

public class BarnRepository {

    private MutableLiveData<List<Barn>> barns;
    private static BarnRepository instance;
    private final ExecutorService executorService;
    private final FarmeramaDatabase database;
    private ConnectivityChecker checker;

    private BarnRepository(Application application) {
        barns = new MutableLiveData<>();
        executorService = Executors.newFixedThreadPool(5);
        database = FarmeramaDatabase.getInstance(application);
        checker = new ConnectivityChecker(application);
    }

    public static BarnRepository getInstance(Application application) {
        if (instance == null) {
            return new BarnRepository(application);
        }

        return instance;
    }

    public LiveData<List<Barn>> getBarns() {
        return barns;
    }

    /**
     * Method that checks if the user is online
     * If the user is online, the data is retrieved from the webservice,
     * loaded in the database and posted to the user
     * In case of offline mode, the data will be retrieved from the local databse
     */
    public void retrieveBarns() {
        if(checker.isOnlineMode()) {
            BarnApi barnApi = ServiceGenerator.getBarnApi();
            Call<List<BarnResponse>> call = barnApi.getBarns();
            call.enqueue(new Callback<List<BarnResponse>>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<List<BarnResponse>> call, Response<List<BarnResponse>> response) {
                    if (response.isSuccessful()) {
                        List<Barn> list = new ArrayList<>();
                        executorService.execute(() -> {
                            for(BarnResponse barnResponse : response.body()) {
                                database.barnDAO().createBarn(barnResponse.getBarn());
                                list.add(barnResponse.getBarn());
                            }
                            barns.postValue(list);
                        });
                    }
                    else {
                        ErrorReader<List<BarnResponse>> responseErrorReader = new ErrorReader<>();
                        ToastMessage.setToastMessage(responseErrorReader.errorReader(response));
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<List<BarnResponse>> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve data");
                }
            });
        }
        else {
            ListenableFuture<List<Barn>> result = database.barnDAO().getBarns();
            result.addListener(() -> {
                try {
                    barns.postValue(result.get());
                }
                catch (Exception e) {
                    Log.i("Room", "Could not retrieve data");
                }
            }, Executors.newFixedThreadPool(5));
        }
    }
}
