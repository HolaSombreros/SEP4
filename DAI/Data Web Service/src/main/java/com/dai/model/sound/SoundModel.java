package com.dai.model.sound;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface SoundModel
{
  List<SentMeasurement> readLastAreaSound(int areaId) throws Exception;
  List<SentMeasurement> readAllAreaSounds(int areaId) throws Exception;
  List<SentMeasurement> readAreaSoundsByDate(int areaId,
      LocalDate localDate) throws Exception;
}
