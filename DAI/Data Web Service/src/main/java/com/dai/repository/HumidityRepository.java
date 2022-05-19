package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HumidityRepository extends JpaRepository<Measurement, Integer> {
    @Query(nativeQuery = true, value = "SELECT TOP 1 measured_date as measuredDate, humidity as value FROM measurement WHERE measurement.area_id = :area_id ORDER BY measurement_id DESC")
    SentMeasurement findFirstHumidityMeasuredDateOrderByIdDesc(@Param("area_id")int area_id);

}
