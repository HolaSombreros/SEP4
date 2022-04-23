package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Measurement, Integer> {
    @Query(nativeQuery = true, value = "SELECT measured_date, temperature as value FROM measurement WHere area_id = :area_id Order By measurement_id DESC")
    SentMeasurement findFirstTemperatureAndMeasuredDateOrderByIdDesc(@Param("area_id")int area_id);
}
