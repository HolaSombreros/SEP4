package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.MeasurementType;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;


@Dao
public interface IMeasurementDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createMeasurement(Measurement measurement);

    @Query("DELETE FROM measurement_table")
    void removeMeasurements();

    @Query("SELECT * FROM measurement_table WHERE measurementType = (:measurementType) AND areaId = (:areaId) ORDER BY measurementId DESC")
    ListenableFuture<Measurement> getLatestMeasurement(MeasurementType measurementType, int areaId);

    @Query("SELECT * FROM measurement_table WHERE measurementType = (:measurementType) AND areaId = (:areaId)")
    ListenableFuture<List<Measurement>> getHistoricalMeasurements(MeasurementType measurementType, int areaId);

}
