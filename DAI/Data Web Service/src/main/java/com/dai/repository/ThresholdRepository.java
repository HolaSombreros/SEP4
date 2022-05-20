package com.dai.repository;

import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThresholdRepository extends JpaRepository<Threshold, Integer> {
    Threshold findFirstByAreaIdEqualsAndTypeEquals(int area_id, ThresholdType type);
}
