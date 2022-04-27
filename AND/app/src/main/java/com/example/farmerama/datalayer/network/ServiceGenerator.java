package com.example.farmerama.datalayer.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static String url = "https://localhost/api/";
    private static UserApi userApi;
    private static MeasurementApi measurementApi;
    private static AreaApi areaApi;

    public static UserApi getUserApi() {
        if (userApi == null) {
            userApi = new Retrofit.Builder()
                    .baseUrl(url + "users")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserApi.class);
        }
        return userApi;
    }

    public static MeasurementApi getMeasurementApi() {
        if (measurementApi == null) {
            measurementApi = new Retrofit.Builder()
                    .baseUrl(url + "measurements")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MeasurementApi.class);
        }
        return measurementApi;
    }

    public static AreaApi getAreaApi() {
        if (areaApi == null) {
            areaApi = new Retrofit.Builder()
                    .baseUrl(url + "areas")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AreaApi.class);
        }
        return areaApi;
    }
}
