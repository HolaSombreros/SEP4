package com.dai.dao.sound;

import com.dai.model.SentThresholdLog;
import com.dai.model.ThresholdType;
import com.dai.repository.SoundRepository;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

@Repository
@EnableAsync
public class SoundDaoImpl implements SoundDao
{
  private SoundRepository repository;
  @Autowired
  public SoundDaoImpl(SoundRepository repository) {
    this.repository = repository;
  }

  @Override
  @Async
  public  Future<SentMeasurement> readLatestByAreaId(int areaId) {
    return new AsyncResult<>(repository.findFirstSoundMeasuredDateOrderByIdDesc(areaId));
  }

  @Override public Future<List<SentMeasurement>> readAllByAreaId(int areaId)
  {
    return readAllByAreaIdAndDate(areaId, LocalDate.now());
  }

  @Override
  public Future<List<SentMeasurement>> readAllByAreaIdAndDate(int areaId, LocalDate date) {
    return new AsyncResult<>(repository. getAllMeasurementsByDate(areaId, Date.valueOf(date)));
  }

  @Override
  public Future<List<SentThresholdLog>> getAllExceedingThresholdChanges(int areaId, ThresholdType type, LocalDate date) {
    List<SentThresholdLog> max = repository.getAllExceedingMax(areaId, type.getType(), Date.valueOf(date));
    max.addAll(repository.getAllExceedingMin(areaId, type.getType(), Date.valueOf(date)));
    return new AsyncResult<>(max);
  }
}
