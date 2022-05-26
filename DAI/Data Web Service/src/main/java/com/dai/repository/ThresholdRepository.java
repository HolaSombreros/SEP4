package com.dai.repository;

import com.dai.model.Threshold;
import com.dai.model.ThresholdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Integer>{
    Threshold findFirstByAreaAreaIdEqualsAndTypeEquals(int area_id, ThresholdType type);
    List<Threshold> getAllByAreaAreaId(int areaId);
}
