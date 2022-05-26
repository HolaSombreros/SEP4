package com.dai.repository;

import com.dai.model.SentThresholdLog;
import com.dai.model.Threshold;
import com.dai.model.ThresholdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Integer>{
    Threshold findFirstByAreaIdEqualsAndTypeEquals(int area_id, ThresholdType type);
    List<Threshold> getAllByAreaId(int areaId);
}
