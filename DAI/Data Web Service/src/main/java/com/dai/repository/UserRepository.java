package com.dai.repository;

import com.dai.shared.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);
    User deleteById(int id);
    List<User> findAll();
}