package com.dai.repository;

import com.dai.shared.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT * FROM user WHERE email = ?1 AND password = ?2")
    User getUserByEmailAndPassword(String email, String password);
}
