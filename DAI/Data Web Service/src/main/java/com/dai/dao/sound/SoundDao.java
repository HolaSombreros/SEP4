package com.dai.dao.sound;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface SoundDao
{
  Future<SentMeasurement> getLatestSoundMeasurement(int areaId);
  Future<List<SentMeasurement>> getAreaSoundsInDate(int areaId, LocalDate date);
}
