package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.MeasurementType;

import java.util.List;


@Dao
public interface IMeasurementDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createMeasurement(Measurement measurement);

    @Query("SELECT * FROM measurement_table")
    LiveData<List<Measurement>> getMeasurements();

    @Query("DELETE FROM measurement_table")
    void removeMeasurements();

    @Query("SELECT * FROM measurement_table WHERE measurementType = (:measurementType) AND areaId = (:areaId) ORDER BY measurementId DESC")
    LiveData<Measurement> getLatestMeasurement(MeasurementType measurementType, int areaId);

//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'TEMPERATURE' ORDER BY measurementId DESC")
//    LiveData<List<Measurement>> getLatestTemperature(int areaId, boolean latest);
//
//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'HUMIDIDTY' ORDER BY measurementId DESC")
//    LiveData<List<Measurement>> getLatestHumididty(int areaId, boolean latest);
//
//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'CO2' ORDER BY measurementId DESC")
//    LiveData<List<Measurement>> getLatestCO2(int areaId, boolean latest);
//
//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND latest = (:latest) AND measurementType = 'SOUND PRESSURE LEVEL' ORDER BY measurementId DESC")
//    LiveData<List<Measurement>> getLatestSound(int areaId, boolean latest);

//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'TEMPERATURE'")
//    LiveData<List<Measurement>> getTemperatures(int areaId, String date);
//
//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'HUMIDIDTY'")
//    LiveData<List<Measurement>> getHumididties(int areaId, String date);
//
//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'SOUND PRESSURE LEVEL'")
//    LiveData<List<Measurement>> getSounds(int areaId, String date);
//
//    @Query("SELECT * FROM measurement_table WHERE areaId = (:areaId) AND measuredDate = (:date) AND measurementType = 'CO2'")
//    LiveData<List<Measurement>> getCO2(int areaId, String date);

}
