package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Measurement, Integer> {
    @Query(nativeQuery = true, value = "SELECT measured_date, temperature as value FROM measurement Order By measurement_id DESC")
    SentMeasurement findFirstTemperatureAndMeasuredDateOrderByIdDesc();
}
