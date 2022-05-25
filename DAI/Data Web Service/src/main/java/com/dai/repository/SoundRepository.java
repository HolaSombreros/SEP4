package com.dai.repository;

import com.dai.model.Measurement;
import com.dai.model.SentMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SoundRepository
    extends JpaRepository<Measurement, Integer>
{
  @Query(nativeQuery = true, value = "SELECT TOP 1 measured_date as measuredDate, sound as value FROM measurement WHERE measurement.area_id = :area_id ORDER BY measurement_id DESC")
  SentMeasurement findFirstSoundMeasuredDateOrderByIdDesc(@Param("area_id")int area_id);
  @Query(nativeQuery = true, value = "SELECT measured_date as measuredDate, sound as value FROM measurement WHERE measurement.area_id = :area_id AND convert(date, measurement.measured_date) = :date ORDER BY measurement_id")
  List<SentMeasurement> getAllMeasurementsByDate(@Param("area_id")int area_id, @Param("date")Date date);
}
