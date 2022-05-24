package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Area;

import java.util.List;

@Dao
public interface AreaDAO {

    @Insert
    void createArea(Area area);

    @Delete
    void removeArea(Area area);

    @Update
    void editArea(Area area);

    @Query("SELECT * FROM area_table")
    List<Area> getAreas();

    @Query("SELECT * FROM area_table WHERE id = (:id)")
    Area getAreaById(int id);

}
