package com.dai.service.sound;

import com.dai.dao.sound.SoundDao;
import com.dai.helpers.Helper;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SoundServiceImpl implements SoundService
{
  private SoundDao soundDao;

  @Autowired
  public SoundServiceImpl(SoundDao soundDao) {
    this.soundDao = soundDao;
  }


  @Override
  public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
    return new ArrayList<>(List.of(Helper.await(soundDao.readLatestByAreaId(areaId))));
  }

  @Override public List<SentMeasurement> readAllFromTodayByAreaId(int areaId)
      throws Exception
  {
    return Helper.await(soundDao.readAllByAreaId(areaId));
  }

  @Override
  public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate localDate) throws Exception {
    return Helper.await(soundDao.readAllByAreaIdAndDate(areaId, localDate));
  }
}
