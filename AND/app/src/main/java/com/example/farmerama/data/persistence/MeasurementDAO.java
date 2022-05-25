package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.farmerama.data.model.Measurement;

import java.util.List;


@Dao
public interface MeasurementDAO {
    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'TEMPERATURE'")
    List<Measurement> getLatestTemperature(int areaId, boolean latest);

    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'HUMIDIDTY'")
    List<Measurement> getLatestHumididty(int areaId, boolean latest);

    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'CO2'")
    List<Measurement> getLatestCO2(int areaId, boolean latest);

    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'SOUND PRESSURE LEVEL'")
    List<Measurement> getLatestSound(int areaId, boolean latest);

    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'TEMPERATURE'")
    List<Measurement> getTemperatures(int areaId, String date);

    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'HUMIDIDTY'")
    List<Measurement> getHumididties(int areaId, String date);

    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'SOUND PRESSURE LEVEL'")
    List<Measurement> getSounds(int areaId, String date);

    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'CO2'")
    List<Measurement> getCO2(int areaId, String date);

}
