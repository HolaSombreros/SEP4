package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Co2Repository extends JpaRepository<Measurement, Integer> {
    @Query(nativeQuery = true, value = "SELECT measured_date as measuredDate, co2 as value FROM measurement WHERE measurement.area_id = :area_id ORDER BY measurement_id DESC LIMIT 1")
    SentMeasurement findFirstCo2MeasurementByAreaId(@Param("area_id") int area_id);
}
