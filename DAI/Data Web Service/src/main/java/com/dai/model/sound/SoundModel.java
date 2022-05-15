package com.dai.model.sound;

import com.dai.shared.SentMeasurement;

import java.time.LocalDate;
import java.util.List;

public interface SoundModel
{
  List<SentMeasurement> readLatestSound(int areaId) throws Exception;
  List<SentMeasurement> getSoundMeasurementsByDate(int areaId,
      LocalDate localDate) throws Exception;
}
