package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Threshold;

import java.util.List;

@Dao
public interface ThresholdDAO {

    @Insert
    void createThreshold(Threshold threshold);


    @Update
    void editThreshold(Threshold threshold);

    @Query("SELECT * FROM threshold_table WHERE type = (:type) AND areaid = (:areaId)")
    List<Threshold> getThreshold(int areaId, String type);

}
