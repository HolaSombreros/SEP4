package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.Threshold;

import java.util.List;

@Dao
public interface IThresholdDAO {

    @Insert
    void createThreshold(Threshold threshold);


    @Update
    void editThreshold(Threshold threshold);

    @Query("SELECT * FROM threshold_table WHERE type = (:type) AND areaareaid = (:areaId)")
    LiveData<List<Threshold>> getThreshold(int areaId, String type);

}
