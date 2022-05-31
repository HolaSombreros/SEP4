package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.Barn;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface IBarnDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createBarn(Barn barn);

    @Query("DELETE FROM barn_table")

    void removeAllBarns();

    @Update
    void editBarn(Barn barn);

    @Query("SELECT * FROM barn_table")
    ListenableFuture<List<Barn>> getBarns();

}
