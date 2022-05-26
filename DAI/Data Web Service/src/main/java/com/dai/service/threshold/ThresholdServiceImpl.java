package com.dai.service.threshold;

import com.dai.dao.MeasurementDaoFactory;
import com.dai.dao.area.AreaDao;
import com.dai.dao.threshold.ThresholdDao;
import com.dai.dao.thresholdLog.ThresholdLogDao;
import com.dai.dao.user.UserDao;
import com.dai.helpers.Helper;
import com.dai.helpers.ThresholdValidator;
import com.dai.model.*;
import com.dai.model.ThresholdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ThresholdServiceImpl implements ThresholdService {

    private ThresholdDao thresholdDao;
    private AreaDao areaDao;
    private ThresholdLogDao thresholdLogDao;
    private UserDao userDao;
    private ThresholdValidator thresholdValidator;
    private MeasurementDaoFactory factory;

    @Autowired
    public ThresholdServiceImpl(ThresholdDao thresholdDao, AreaDao areaDao, ThresholdLogDao thresholdLogDao, UserDao userDao, ThresholdValidator thresholdValidator, MeasurementDaoFactory factory) {
        this.thresholdDao = thresholdDao;
        this.areaDao = areaDao;
        this.thresholdLogDao = thresholdLogDao;
        this.userDao = userDao;
        this.thresholdValidator = thresholdValidator;
        this.factory = factory;
    }

    @Override
    public Threshold readByAreaIdAndType(int areaId, ThresholdType type) throws Exception {
        Threshold threshold = Helper.await(thresholdDao.readByAreaIdAndType(areaId, type));
        if(threshold!= null) return threshold;
        else throw new Exception("Threshold doesn't exist");
    }

    @Override
    public Threshold create(Threshold threshold) throws Exception {
        thresholdValidator.validateThreshold(threshold);
        Area area = Helper.await(areaDao.read(threshold.getArea().getAreaId()));
        threshold.setArea(area);
        return Helper.await(thresholdDao.create(threshold));
    }

    @Override
    public Threshold update(Threshold newThreshold, int userId) throws Exception {

        User user;
        try {
            user = Helper.await(userDao.read(userId));
        } catch (Exception e) {
            throw new Exception("User with the given ID does not exist.");
        }

        thresholdValidator.validateThreshold(newThreshold);

        Threshold currentThreshold = Helper.await(thresholdDao.readByAreaIdAndType(newThreshold.getArea().getAreaId(), newThreshold.getType()));

        double maxNew = newThreshold.getMaximum();
        double maxOld = currentThreshold.getMaximum();
        double minOld = currentThreshold.getMinimum();
        double minNew = newThreshold.getMinimum();

        if (maxOld != maxNew) {
            currentThreshold.setMaximum(maxNew);
            ThresholdLogs thresholdLog = new ThresholdLogs(0, currentThreshold, user, LocalDateTime.now(), maxOld, maxNew, ThresholdLogType.MAXIMUM);
            thresholdLogDao.create(thresholdLog);
        }

        if (minOld != minNew) {
            currentThreshold.setMinimum(minNew);
            ThresholdLogs thresholdLog = new ThresholdLogs(0, currentThreshold, user, LocalDateTime.now(), minOld, minNew, ThresholdLogType.MINIMUM);
            thresholdLogDao.create(thresholdLog);
        }

        return Helper.await(thresholdDao.update(currentThreshold));
    }

    @Override
    public List<SentThresholdLog> readAllExceedingLogsByTypeAndAreaIdAndDate(int areaId, ThresholdType type, LocalDate date) throws Exception {
       switch (type){
           case TEMPERATURE:
               return Helper.await(factory.getTemperatureDao().getAllExceedingThresholdChanges(areaId, type, date));
           case HUMIDITY:
               return Helper.await(factory.getHumidityDao().getAllExceedingThresholdChanges(areaId, type, date));
           case CO2:
               return Helper.await(factory.getCo2Dao().getAllExceedingThresholdChanges(areaId, type, date));
           case SOUND:
               return Helper.await(factory.getSoundDao().getAllExceedingThresholdChanges(areaId, type, date));
           default: throw new Exception("Oops, something went wrong");
       }
    }

    @Override
    public List<ThresholdLogs> readAllLogsByDate(LocalDate date) throws Exception {
        return Helper.await(thresholdLogDao.readAllByDate(date));
    }

    @Override
    public Threshold read(int id) throws Exception {
        return Helper.await(thresholdDao.read(id));
    }

    @Override
    public List<NotificationLogs> readAllLogsFromLast5Min() throws Exception {
        List<NotificationLogs> logs = Helper.await(factory.getTemperatureDao().getAllNotificationLogs());
        logs.addAll(Helper.await(factory.getSoundDao().getAllNotificationLogs()));
        logs.addAll(Helper.await(factory.getHumidityDao().getAllNotificationLogs()));
        logs.addAll(Helper.await(factory.getCo2Dao().getAllNotificationLogs()));
        return logs;
    }
}
