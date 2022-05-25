package com.dai.dao.sound;

import com.dai.model.SentMeasurement;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface SoundDao
{
  Future<SentMeasurement> readLatestByAreaId(int areaId);
  Future<List<SentMeasurement>> readAllByAreaId(int areaId);
  Future<List<SentMeasurement>> readAllByAreaIdAndDate(int areaId, LocalDate date);
}
