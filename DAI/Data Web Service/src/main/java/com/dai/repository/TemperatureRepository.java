package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import com.dai.shared.SentThresholdLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Measurement, Integer> {

    @Query( value = "SELECT TOP 1 measured_date as measuredDate, temperature as value FROM measurement WHERE measurement.area_id = :area_id Order By measurement_id DESC",nativeQuery = true)
    List<SentMeasurement> getLatestByArea(@Param("area_id") int area_id);

    @Query( value = "SELECT measured_date as measuredDate, temperature as value FROM measurement WHERE measurement.area_id = :area_id AND convert(date ,measurement.measured_date) = :date ORDER BY measurement_id",nativeQuery = true)
    List<SentMeasurement> getAllByAreaAndDate(@Param("area_id") int area_id, @Param("date") Date date);
}
