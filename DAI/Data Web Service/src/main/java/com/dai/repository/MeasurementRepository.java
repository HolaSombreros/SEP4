package com.dai.repository;

import com.dai.model.Measurement;
import com.dai.model.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Measurement getLatestByType(MeasurementType type);
}
