package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Area;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface IAreaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createArea(Area area);

    @Query("DELETE FROM area_table")
    void removeAreas();

    @Update
    void updateArea(Area area);

    @Delete
    void removeArea(Area area);

    @Query("DELETE FROM area_table WHERE areaId = (:id)")
    void removeAreaById(int id);

    @Query("SELECT * FROM area_table")
    ListenableFuture<List<Area>> getAreas();

    @Query("SELECT * FROM area_table WHERE areaId =(:id)")
    ListenableFuture<Area> getAreaById(int id);
}
