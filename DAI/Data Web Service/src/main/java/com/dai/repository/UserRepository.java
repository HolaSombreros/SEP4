package com.dai.repository;

import com.dai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User getUserByEmailAndPassword(String email, String password);
}
