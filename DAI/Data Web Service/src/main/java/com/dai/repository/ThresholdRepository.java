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
public interface ThresholdRepository extends JpaRepository<Threshold, Integer> {
    Threshold findFirstByAreaIdEqualsAndTypeEquals(int area_id, ThresholdType type);
    @Query(nativeQuery = true, value = "SELECT measured_date as measuredDate, temperature as value, maximum as threshold FROM measurement join threshold t on measurement.area_id = t.area_id WHERE measurement.temperature < t.minimum AND measurement.area_id = :area_id AND t.type like :type AND convert(date ,measurement.measured_date) = :date ORDER BY measurement_id DESC")
    List<SentThresholdLog> getAllExceedingMax(@Param("area_id") int area_id, @Param("type")String type, @Param("date")Date date);

    @Query(nativeQuery = true, value = "SELECT measured_date as measuredDate, temperature as value, minimum as threshold FROM measurement join threshold t on measurement.area_id = t.area_id WHERE measurement.temperature < t.minimum AND measurement.area_id = :area_id AND t.type like :type AND convert(date ,measurement.measured_date) = :date ORDER BY measurement_id DESC")
    List<SentThresholdLog> getAllExceedingMin(@Param("area_id") int area_id, @Param("type") String type, @Param("date")Date date);
    List<Threshold> getAllByAreaId(int areaId);
}
