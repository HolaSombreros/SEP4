package com.example.farmerama.data.network;

import com.example.farmerama.data.util.EndpointsHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static String url = "http://sep4-data-service.eu-central-1.elasticbeanstalk.com/";
    private static UserApi userApi;
    private static MeasurementApi measurementApi;
    private static AreaApi areaApi;
    private static BarnApi barnApi;
    private static ThresholdApi thresholdApi;

    public static UserApi getUserApi() {
        if (userApi == null) {
            userApi = new Retrofit.Builder()
                    .baseUrl(url + EndpointsHelper.USERS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(UserApi.class);
        }
        return userApi;
    }

    public static MeasurementApi getMeasurementApi() {
        if (measurementApi == null) {
            measurementApi = new Retrofit.Builder()
                    .baseUrl(url + EndpointsHelper.AREAS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MeasurementApi.class);
        }
        return measurementApi;
    }

    public static ThresholdApi getThresholdsApi() {
        if(thresholdApi == null) {
            thresholdApi = new Retrofit.Builder()
                    .baseUrl(url + EndpointsHelper.THRESHOLDS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ThresholdApi.class);
        }
        return thresholdApi;
    }

    public static AreaApi getAreaApi() {
        if (areaApi == null) {
            areaApi = new Retrofit.Builder()
                    .baseUrl(url + EndpointsHelper.AREAS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AreaApi.class);
        }
        return areaApi;
    }

    public static BarnApi getBarnApi() {
        if (barnApi == null) {
            barnApi = new Retrofit.Builder()
                    .baseUrl(url + EndpointsHelper.BARNS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BarnApi.class);
        }
        return barnApi;
    }
}
