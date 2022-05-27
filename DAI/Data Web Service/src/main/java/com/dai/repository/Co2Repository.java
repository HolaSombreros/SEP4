package com.dai.repository;

import com.dai.model.Measurement;
import com.dai.model.NotificationLogs;
import com.dai.model.SentMeasurement;
import com.dai.model.SentThresholdLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public interface Co2Repository extends JpaRepository<Measurement, Integer> {
    @Query(nativeQuery = true, value = "SELECT TOP 1 measured_date as measuredDate, co2 as value, measurement_id as measurementId FROM measurement WHERE measurement.area_id = :area_id ORDER BY measurement_id DESC")
    SentMeasurement getLatestCo2(@Param("area_id") int area_id);

    @Query(nativeQuery = true, value = "SELECT measured_date as measuredDate, co2 as value, measurement_id as measurementId FROM measurement WHERE measurement.area_id = :area_id AND convert(date, measurement.measured_date) = :date ORDER BY measurement_id")
    List<SentMeasurement> getAllCo2sInDate(@Param("area_id") int areaId, @Param("date") Date date);
    @Query(nativeQuery = true, value = "SELECT  measuredDate, value, threshold FROM (SELECT measured_date as measuredDate, co2 as value, maximum as threshold, measurement_id\n" +
            "                FROM measurement JOIN threshold t on measurement.area_id = t.area_id AND t.type like :type WHERE measurement.area_id = :area_id AND\n" +
            "                convert(date ,measurement.measured_date) = :date) as measurements\n" +
            "                WHERE value > threshold ORDER BY measurement_id DESC")
    List<SentThresholdLog> getAllExceedingMax(@Param("area_id") int area_id, @Param("type")String type, @Param("date")Date date);

    @Query(nativeQuery = true, value = "SELECT  measuredDate, value, threshold FROM (SELECT measured_date as measuredDate, co2 as value, minimum as threshold, measurement_id\n" +
            "                FROM measurement JOIN threshold t on measurement.area_id = t.area_id AND t.type like :type WHERE measurement.area_id = :area_id AND\n" +
            "                convert(date ,measurement.measured_date) = :date) as measurements\n" +
            "                WHERE value < threshold ORDER BY measurement_id DESC")
    List<SentThresholdLog> getAllExceedingMin(@Param("area_id") int area_id, @Param("type") String type, @Param("date")Date date);

    @Query(nativeQuery = true, value = "SELECT value, threshold, type, areaName FROM (SELECT co2 as value, minimum as threshold, type, name as areaName, measured_date from measurement join threshold t on measurement.area_id = t.area_id join area a on a.area_id = measurement.area_id) measurements\n" +
            "WHERE value < threshold AND measured_date >= DATEADD(MINUTE , -5, CURRENT_TIMESTAMP) AND type like :type")
    List<NotificationLogs> getAllMin(@Param("type")String type);

    @Query(nativeQuery = true, value = "SELECT value, threshold, type, areaName FROM (SELECT co2 as value, maximum as threshold, type, name as areaName, measured_date from measurement join threshold t on measurement.area_id = t.area_id join area a on a.area_id = measurement.area_id) measurements\n" +
            "WHERE value > threshold AND measured_date >= DATEADD(MINUTE , -5, CURRENT_TIMESTAMP) AND type like :type")
    List<NotificationLogs> getAllMax(@Param("type")String type);
}