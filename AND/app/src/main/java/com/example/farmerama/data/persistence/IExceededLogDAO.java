package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.farmerama.data.model.ExceededLog;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface IExceededLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createExceededLog(ExceededLog log);

    @Query("SELECT * FROM exceeded_log_table")
    ListenableFuture<List<ExceededLog>> getExceededLogs();

    @Query("DELETE FROM exceeded_log_table")
    void removeExceededLogs();
}
