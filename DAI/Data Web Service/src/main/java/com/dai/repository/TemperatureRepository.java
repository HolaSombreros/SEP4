package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Measurement, Integer> {

    @Query( value = "SELECT TOP 1 measured_date as measuredDate, temperature as value FROM measurement WHERE measurement.area_id = :area_id Order By measurement_id DESC",nativeQuery = true)
    SentMeasurement findFirstTemperatureAndMeasuredDateByAreaIdOrderByIdDesc(@Param("area_id") int area_id);
}
