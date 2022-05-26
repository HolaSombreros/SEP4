package com.dai.repository;

import com.dai.model.Measurement;
import com.dai.model.NotificationLogs;
import com.dai.model.SentMeasurement;
import com.dai.model.SentThresholdLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface SoundRepository
        extends JpaRepository<Measurement, Integer> {
    @Query(nativeQuery = true, value = "SELECT TOP 1 measured_date as measuredDate, sound as value FROM measurement WHERE measurement.area_id = :area_id ORDER BY measurement_id DESC")
    SentMeasurement findFirstSoundMeasuredDateOrderByIdDesc(@Param("area_id") int area_id);

    @Query(nativeQuery = true, value = "SELECT measured_date as measuredDate, sound as value FROM measurement WHERE measurement.area_id = :area_id AND convert(date, measurement.measured_date) = :date ORDER BY measurement_id")
    List<SentMeasurement> getAllMeasurementsByDate(@Param("area_id") int area_id, @Param("date") Date date);

    @Query(nativeQuery = true, value = "SELECT  measuredDate, value, threshold FROM (SELECT measured_date as measuredDate, sound as value, maximum as threshold, measurement_id\n" +
            "                FROM measurement JOIN threshold t on measurement.area_id = t.area_id AND t.type like :type WHERE measurement.area_id = :area_id AND\n" +
            "                convert(date ,measurement.measured_date) = :date) as measurements\n" +
            "                WHERE value > threshold ORDER BY measurement_id DESC")
    List<SentThresholdLog> getAllExceedingMax(@Param("area_id") int area_id, @Param("type") String type, @Param("date") Date date);

    @Query(nativeQuery = true, value = "SELECT  measuredDate, value, threshold FROM (SELECT measured_date as measuredDate, sound as value, minimum as threshold, measurement_id\n" +
            "                FROM measurement JOIN threshold t on measurement.area_id = t.area_id AND t.type like :type WHERE measurement.area_id = :area_id AND\n" +
            "                convert(date ,measurement.measured_date) = :date) as measurements\n" +
            "                WHERE value < threshold ORDER BY measurement_id DESC")
    List<SentThresholdLog> getAllExceedingMin(@Param("area_id") int area_id, @Param("type") String type, @Param("date") Date date);

    @Query(nativeQuery = true, value = "SELECT value, threshold, type, areaName FROM (SELECT sound as value, minimum as threshold, type, name as areaName, measured_date from measurement join threshold t on measurement.area_id = t.area_id join area a on a.area_id = measurement.area_id) measurements\n" +
            "WHERE value < threshold AND measured_date >= DATEADD(MINUTE , -5, :date) AND type like :type")
    List<NotificationLogs> getAllMin(@Param("date") Timestamp timestamp, @Param("type")String type);

    @Query(nativeQuery = true, value = "SELECT value, threshold, type, areaName FROM (SELECT sound as value, maximum as threshold, type, name as areaName, measured_date from measurement join threshold t on measurement.area_id = t.area_id join area a on a.area_id = measurement.area_id) measurements\n" +
            "WHERE value > threshold AND measured_date >= DATEADD(MINUTE , -5, :date) AND type like :type")
    List<NotificationLogs> getAllMax(@Param("date") Timestamp timestamp, @Param("type")String type);

}
