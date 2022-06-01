package com.dai.repository;

import com.dai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);
    User deleteByUserId(int id);
    List<User> findAll();
}
