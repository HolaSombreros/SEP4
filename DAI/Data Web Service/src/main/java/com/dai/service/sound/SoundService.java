package com.dai.service.sound;

import com.dai.model.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface SoundService
{
  List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception;
  List<SentMeasurement> readAllFromTodayByAreaId(int areaId) throws Exception;
  List<SentMeasurement> readAllByDateAndAreaId(int areaId,
                                               LocalDate localDate) throws Exception;
}
