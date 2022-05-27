package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Barn;

import java.util.List;

@Dao
public interface IBarnDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createBarn(Barn barn);

    @Query("DELETE FROM barn_table")

    void removeAllBarns();

    @Update
    void editArea(Barn barn);

    @Query("SELECT * FROM barn_table")
    LiveData<List<Barn>> getBarns();

}
