package com.dai.model.sound;

import com.dai.dao.sound.SoundDao;
import com.dai.helpers.Helper;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SoundModelImpl implements SoundModel
{
  private SoundDao soundDao;

  @Autowired
  public SoundModelImpl(SoundDao soundDao) {
    this.soundDao = soundDao;
  }


  @Override
  public List<SentMeasurement> readLastAreaSound(int areaId) throws Exception {
    return new ArrayList<>(List.of(Helper.await(soundDao.readLastAreaSound(areaId))));
  }

  @Override public List<SentMeasurement> readAllAreaSounds(int areaId)
      throws Exception
  {
    return Helper.await(soundDao.readAllAreaSounds(areaId));
  }

  @Override
  public List<SentMeasurement> readAreaSoundsByDate(int areaId, LocalDate localDate) throws Exception {
    return Helper.await(soundDao.readAreaSoundsByDate(areaId, localDate));
  }
}
