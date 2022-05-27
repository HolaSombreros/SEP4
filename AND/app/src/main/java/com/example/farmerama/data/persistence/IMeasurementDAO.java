package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createMeasurement(Measurement measurement);

    @Query("SELECT * FROM measurement_table")
    LiveData<List<Measurement>> getMeasurements();

    @Query("DELETE FROM measurement_table")
    void removeMeasurements();

    @Query("SELECT * FROM measurement_table WHERE measurementType = (:measurementType) AND areaId = (:areaId) ORDER BY measurementId DESC")
    Measurement getLatestMeasurement(MeasurementType measurementType, int areaId);

    @Query("SELECT * FROM measurement_table WHERE measurementType = (:measurementType) AND areaId = (:areaId)")
    LiveData<List<Measurement>> getHistoricalMeasurements(MeasurementType measurementType, int areaId);

}
