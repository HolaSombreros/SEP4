package com.dai.repository;

import com.dai.model.Barn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarnRepository extends JpaRepository<Barn, Integer> {
    Barn deleteByBarnId(int id);
}
