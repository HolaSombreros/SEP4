package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    @Query("SELECT * FROM measurement ORDER BY measurement_id DESC LIMIT 1")
    Measurement getLatestMeasurement();
}
