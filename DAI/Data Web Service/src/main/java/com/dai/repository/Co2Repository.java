package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;


public interface Co2Repository extends JpaRepository<Measurement, Integer> {
    @Query(nativeQuery = true, value = "SELECT TOP 1 measured_date as measuredDate, co2 as value FROM measurement WHERE measurement.area_id = :area_id ORDER BY measurement_id DESC")
    SentMeasurement getLatestCo2(@Param("area_id") int area_id);


    @Query(nativeQuery = true, value = "SELECT measured_date as measuredDate, co2 as value FROM measurement WHERE measurement.area_id = :area_id AND convert(date, measurement.measured_date) = :date ORDER BY measurement_id DESC")
    List<SentMeasurement> getAllCo2sInDate(@Param("area_id")int areaId, @Param("date") Date date);
}
