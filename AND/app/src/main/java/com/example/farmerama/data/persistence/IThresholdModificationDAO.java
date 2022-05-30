package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.farmerama.data.model.ThresholdModification;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
@Dao
public interface IThresholdModificationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createThresholdModification(ThresholdModification thresholdModification);

    @Query("DELETE FROM threshold_modifications_table")
    void removeThresholdModification();

    @Query("SELECT * FROM threshold_modifications_table WHERE changedOn = (:date)")
    ListenableFuture<List<ThresholdModification>> getThresholdModifications(String date);
}
