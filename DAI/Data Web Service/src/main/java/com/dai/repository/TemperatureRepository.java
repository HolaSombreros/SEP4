package com.dai.repository;

import com.dai.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Measurement, Integer> {

    @Query(value = "SELECT TOP 1 measured_date as measuredDate, temperature as value, measurement_id as measurementId, area_id as areaId FROM measurement WHERE measurement.area_id = :area_id Order By measurement_id DESC", nativeQuery = true)
    List<SentMeasurement> getLatestByArea(@Param("area_id") int area_id);

    @Query(value = "SELECT measured_date as measuredDate, temperature as value, measurement_id as measurementId, area_id as areaId FROM measurement WHERE measurement.area_id = :area_id AND convert(date ,measurement.measured_date) = :date ORDER BY measurement_id", nativeQuery = true)
    List<SentMeasurement> getAllByAreaAndDate(@Param("area_id") int area_id, @Param("date") Date date);

    @Query(nativeQuery = true, value = "SELECT  measuredDate, value, threshold, measurementId, type FROM (SELECT measured_date as measuredDate, temperature as value, maximum as threshold, measurement_id as measurementId, type\n" +
            "                FROM measurement JOIN threshold t on measurement.area_id = t.area_id AND t.type like :type WHERE measurement.area_id = :area_id AND\n" +
            "                convert(date ,measurement.measured_date) = :date) as measurements\n" +
            "                WHERE value > threshold ORDER BY measurementId DESC")
    List<SentThresholdLog> getAllExceedingMax(@Param("area_id") int area_id, @Param("type") String type, @Param("date") Date date);

    @Query(nativeQuery = true, value = "SELECT  measuredDate, value, threshold, measurementId, type FROM (SELECT measured_date as measuredDate, temperature as value, minimum as threshold, measurement_id as measurementId, type\n" +
            "                FROM measurement JOIN threshold t on measurement.area_id = t.area_id AND t.type like :type WHERE measurement.area_id = :area_id AND\n" +
            "                convert(date ,measurement.measured_date) = :date) as measurements\n" +
            "                WHERE value < threshold ORDER BY measurementId DESC")
    List<SentThresholdLog> getAllExceedingMin(@Param("area_id") int area_id, @Param("type") String type, @Param("date") Date date);


    @Query(nativeQuery = true, value = "SELECT value, threshold, type, areaName FROM (SELECT temperature as value, minimum as threshold, type, name as areaName, measured_date from measurement join threshold t on measurement.area_id = t.area_id join area a on a.area_id = measurement.area_id) measurements\n" +
            "WHERE value < threshold AND measured_date >= DATEADD(MINUTE , 115, CURRENT_TIMESTAMP) AND type like :type")
    List<NotificationLogs> getAllMin(@Param("type")String type);

    @Query(nativeQuery = true, value = "SELECT value, threshold, type, areaName FROM (SELECT temperature as value, maximum as threshold, type, name as areaName, measured_date from measurement join threshold t on measurement.area_id = t.area_id join area a on a.area_id = measurement.area_id) measurements\n" +
            "WHERE value > threshold AND measured_date >= DATEADD(MINUTE , 115, CURRENT_TIMESTAMP) AND type like :type")
    List<NotificationLogs> getAllMax(@Param("type")String type);
}
