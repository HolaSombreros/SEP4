package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdModification;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;


@Dao
public interface IThresholdDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createThreshold(Threshold threshold);

    @Query("DELETE FROM threshold_table")
    void removeThresholds();

    @Update
    void updateThreshold(Threshold threshold);

    @Query("SELECT * FROM threshold_table WHERE areaareaId = (:areaId) AND measurementType = (:type)")
    ListenableFuture<Threshold> getThreshold(int areaId, String type);


}
