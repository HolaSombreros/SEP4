package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.farmerama.data.model.Barn;

import java.util.List;

@Dao
public interface BarnDAO {

    @Query("SELECT * FROM barn_table")
    public List<Barn> getBarns();
}
