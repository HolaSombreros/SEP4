package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Area;

import java.util.List;

@Dao
public interface IAreaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createArea(Area area);

    @Query("DELETE FROM area_table")
    void removeAreas();

    @Update
    void editArea(Area area);

    @Query("SELECT * FROM area_table")
    LiveData<List<Area>> getAreas();

    @Query("SELECT * FROM area_table WHERE areaId = (:id)")
    LiveData<Area> getAreaById(int id);
}
