package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.User;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface IUserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void registerUser(User user);

    @Query("DELETE FROM user_table")
    void removeUsers();


    @Query("SELECT * FROM user_table")
    ListenableFuture<List<User>> getAllEmployees();

    @Query("SELECT * FROM user_table WHERE userId = (:id)")
    ListenableFuture<User> getEmployeeById(int id);

}
