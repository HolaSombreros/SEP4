package com.example.farmerama.data.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void registerUser(User user);

    @Delete
    void removeUser(User user);

    @Update
    void editArea(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllEmployees();

    @Query("SELECT * FROM user_table WHERE userId = (:id)")
    User getEmployeeById(int id);

}
