package com.example.farmerama.data.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.farmerama.data.model.User;

import java.util.List;

@Dao
public interface IUserDAO {

    @Insert
    void registerUser(User user);

    @Delete
    void removeUser(User user);

    @Query("DELETE FROM user_table")
    void removeUsers();


    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllEmployees();

    @Query("SELECT * FROM user_table WHERE email = (:email) AND password = (:password)")
    LiveData<User> getLoggedUser(String email, String password);

    @Query("SELECT * FROM user_table WHERE userId = (:id)")
    LiveData<User> getEmployeeById(int id);

}
