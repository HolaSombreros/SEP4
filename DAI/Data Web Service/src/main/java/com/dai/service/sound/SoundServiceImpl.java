package com.dai.service.sound;

import com.dai.dao.area.AreaDao;
import com.dai.dao.sound.SoundDao;
import com.dai.exceptions.BadRequestException;
import com.dai.helpers.Helper;
import com.dai.model.Area;
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
  private AreaDao areaDao;

  @Autowired
  public SoundServiceImpl(SoundDao soundDao, AreaDao areaDao) {
    this.soundDao = soundDao;
    this.areaDao = areaDao;
  }


  @Override
  public List<SentMeasurement> readLatestByAreaId(int areaId) throws Exception {
    try {
      Area area = Helper.await(areaDao.read(areaId));
      return new ArrayList<>(List.of(Helper.await(soundDao.readLatestByAreaId(areaId))));
    }
    catch (Exception e){
      throw new BadRequestException("Area with the given id doesn't exist");
    }
  }

  @Override public List<SentMeasurement> readAllFromTodayByAreaId(int areaId)
      throws Exception
  {
    try {
      Area area = Helper.await(areaDao.read(areaId));
      return Helper.await(soundDao.readAllByAreaId(areaId));
    }catch (Exception e){
      throw new BadRequestException("Area with the given id doesn't exist");
    }

  }

  @Override
  public List<SentMeasurement> readAllByDateAndAreaId(int areaId, LocalDate localDate) throws Exception {
    try {
      Area area = Helper.await(areaDao.read(areaId));
      return Helper.await(soundDao.readAllByAreaIdAndDate(areaId, localDate));
    }catch (Exception e){
      throw new BadRequestException("Area with the given id doesn't exist");
    }

  }
}
