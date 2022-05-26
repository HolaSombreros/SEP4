package com.dai.repository;

import com.dai.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
    Area getFirstByHardwareIdEquals(String hardwareId);
    Area getFirstByNameAndBarnBarnId(String name, int barnId);
    Area deleteByAreaId(int id);
}
