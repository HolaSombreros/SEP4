package com.dai.model.threshold;

import com.dai.dao.area.AreaDao;
import com.dai.dao.threshold.ThresholdDao;
import com.dai.dao.thresholdLog.ThresholdLogDao;
import com.dai.dao.user.UserDao;
import com.dai.helpers.Helper;
import com.dai.helpers.ThresholdValidator;
import com.dai.shared.*;
import com.dai.shared.ThresholdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ThresholdModelImpl implements ThresholdModel{

    private ThresholdDao thresholdDao;
    private AreaDao areaDao;
    private ThresholdLogDao thresholdLogDao;
    private UserDao userDao;
    private ThresholdValidator thresholdValidator;

    @Autowired
    public ThresholdModelImpl(ThresholdDao thresholdDao, AreaDao areaDao, ThresholdLogDao thresholdLogDao, UserDao userDao, ThresholdValidator thresholdValidator) {
        this.thresholdDao = thresholdDao;
        this.areaDao = areaDao;
        this.thresholdLogDao = thresholdLogDao;
        this.userDao = userDao;
        this.thresholdValidator = thresholdValidator;
    }

    @Override
    public Threshold find(int areaId, ThresholdType type) throws Exception {
        return Helper.await(thresholdDao.find(areaId, type));
    }

    @Override
    public Threshold create(Threshold threshold) throws Exception {
        Area area = Helper.await(areaDao.read(threshold.getArea().getId()));
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

        Threshold currentThreshold = Helper.await(thresholdDao.find(newThreshold.getArea().getId(), newThreshold.getType()));

        double maxNew = newThreshold.getMaximum();
        double maxOld = currentThreshold.getMaximum();
        double minOld = currentThreshold.getMinimum();
        double minNew = newThreshold.getMinimum();

        if (maxOld != maxNew) {
            currentThreshold.setMaximum(maxNew);
            ThresholdLogs thresholdLog = new ThresholdLogs(0, currentThreshold, user, LocalDateTime.now(), maxOld, maxNew, ThresholdLogType.MAXIMUM);
            thresholdLogDao.createLog(thresholdLog);
        }

        if (minOld != minNew) {
            currentThreshold.setMinimum(minNew);
            ThresholdLogs thresholdLog = new ThresholdLogs(0, currentThreshold, user, LocalDateTime.now(), minOld, minNew, ThresholdLogType.MINIMUM);
            thresholdLogDao.createLog(thresholdLog);
        }

        return Helper.await(thresholdDao.update(currentThreshold));
    }

    @Override
    public List<SentThresholdLog> getAllExceeding(int areaId, ThresholdType type, LocalDate date) throws Exception {
        List<SentThresholdLog> min = Helper.await(thresholdDao.getAllExceedingMin(areaId, type, date));
        List<SentThresholdLog> max = Helper.await(thresholdDao.getAllExceedingMax(areaId, type, date));
        max.addAll(min);
        return max;
    }

    @Override
    public List<ThresholdLogs> getAllByDate(LocalDate date) throws Exception {
        return Helper.await(thresholdLogDao.getAllByDate(date));
    }
}
