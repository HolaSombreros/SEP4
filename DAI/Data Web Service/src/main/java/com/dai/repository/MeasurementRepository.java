package com.dai.repository;

import com.dai.shared.Area;
import com.dai.shared.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Measurement findTopByAreaIdOrderByMeasuredDateDesc(int area_id);
}
