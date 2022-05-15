package com.dai.repository;

import com.dai.shared.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
    Area getFirstByHardwareIdEquals(String hardwareId);
}
