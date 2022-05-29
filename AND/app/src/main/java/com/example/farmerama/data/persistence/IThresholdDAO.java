package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdModification;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;


@Dao
public interface IThresholdDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createThreshold(Threshold threshold);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createThresholdModification(ThresholdModification thresholdModification);

    @Query("SELECT * FROM threshold_table WHERE areaareaId = (:areaId) AND type = (:type)")
    ListenableFuture<Threshold> getThreshold(int areaId, String type);

    @Query("SELECT * FROM threshold_modifications_table WHERE changedOn = (:date)")
    List<ThresholdModification> getThresholdModifications(String date);

}
