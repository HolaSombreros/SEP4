package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.Threshold;

import java.util.List;

@Dao
public interface IThresholdDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createThreshold(Threshold threshold);

    @Query("SELECT * FROM threshold_table WHERE areaareaId = (:areaId) AND type = (:type)")
    LiveData<Threshold> getThreshold(int areaId, String type);


}
