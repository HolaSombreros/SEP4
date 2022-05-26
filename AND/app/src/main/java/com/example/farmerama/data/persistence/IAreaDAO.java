package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Area;

import java.util.List;

@Dao
public interface IAreaDAO {

    @Insert
    void createArea(Area area);


    @Delete
    void removeAreas(List<Area> areas);

    @Update
    void editArea(Area area);

    @Query("SELECT * FROM area_table")
    LiveData<List<Area>> getAreas();

//    @Query("SELECT * FROM area_table WHERE id = (:id)")
//    LiveData<Area> getAreaById(int id);

}
