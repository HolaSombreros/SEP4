package com.dai.model.threshold;

import com.dai.dao.area.AreaDao;
import com.dai.dao.threshold.ThresholdDao;
import com.dai.helpers.Helper;
import com.dai.shared.Area;
import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThresholdModelImpl implements ThresholdModel{

    private ThresholdDao thresholdDao;
    private AreaDao areaDao;

    @Autowired
    public ThresholdModelImpl(ThresholdDao thresholdDao, AreaDao areaDao) {
        this.thresholdDao = thresholdDao;
        this.areaDao = areaDao;
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
    public Threshold update(Threshold toThreshold, int userId) throws Exception {
        //TODO create entry for ThresholdLogs change

        return Helper.await(thresholdDao.update(toThreshold));
    }
}
