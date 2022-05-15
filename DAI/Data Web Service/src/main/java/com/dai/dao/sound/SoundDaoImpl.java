package com.dai.dao.sound;

import com.dai.repository.SoundRepository;
import com.dai.shared.SentMeasurement;
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
  public  Future<SentMeasurement> getLatestSoundMeasurement(int areaId) {
    return new AsyncResult<>(repository.findFirstSoundMeasuredDateOrderByIdDesc(areaId));
  }

  @Override
  public Future<List<SentMeasurement>> getAreaSoundsInDate(int areaId, LocalDate date) {
    return new AsyncResult<>(repository. getAllMeasurementsByDate(areaId, Date.valueOf(date)));
  }
}
