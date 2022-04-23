package com.dai.repository;

import com.dai.shared.Measurement;
import com.dai.shared.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Measurement, Integer> {
    SentMeasurement findFirstTemperatureAndMeasuredDateOrderByIdDesc();
}
