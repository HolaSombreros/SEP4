package com.dai.dao.sound;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

public interface SoundDao
{
  Future<SentMeasurement> readLastAreaSound(int areaId);
  Future<List<SentMeasurement>> readAllAreaSounds(int areaId);
  Future<List<SentMeasurement>> readAreaSoundsByDate(int areaId, LocalDate date);
}
