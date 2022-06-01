package com.dai.service.socket;

import com.dai.controllers.DownLinkController;
import com.dai.dao.area.AreaDao;
import com.dai.dao.threshold.ThresholdDao;
import com.dai.helpers.Helper;
import com.dai.model.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.math.BigInteger;
@Component
public class SocketServiceImpl implements SocketService {
    private DownLinkController downlinkController;
    private AreaDao areaDao;
    private ThresholdDao thresholdDao;
    private Gson gson;

    @Autowired
    public SocketServiceImpl(DownLinkController downlinkController, AreaDao areaDao, ThresholdDao thresholdDao) {
        this.downlinkController = downlinkController;
        this.areaDao = areaDao;
        this.thresholdDao = thresholdDao;
        gson = new Gson();
    }

    @Override
    public void sendDownLinkData(int areaId) throws Exception {
        try {
            String data = parseValuesToString(areaId);
            Area area = Helper.await(areaDao.read(areaId));
            DownLink downLink = new DownLink("tx", area.getHardwareId(), 1, data);
            downlinkController.sendDownLink(gson.toJson(downLink));
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    private String parseValuesToString(int areaId) throws Exception {
        String values = "";
        String bytes = "";
        Threshold temp = Helper.await(thresholdDao.readByAreaIdAndType(areaId, ThresholdType.TEMPERATURE));
        Threshold humidity = Helper.await(thresholdDao.readByAreaIdAndType(areaId, ThresholdType.HUMIDITY));
        Threshold co2 = Helper.await(thresholdDao.readByAreaIdAndType(areaId, ThresholdType.CO2));
        Threshold spl = Helper.await(thresholdDao.readByAreaIdAndType(areaId, ThresholdType.SOUND));
        if(humidity != null){
            int max = (int)(humidity.getMaximum()*10);
            int min = (int)(humidity.getMinimum()*10);
            values += String.format("%04x", max) + String.format("%04x", min);
            bytes += "11";
        }
        else{
            bytes += "00";
            values += "00000000";
        }
        if(temp != null){
            int max = (int)(temp.getMaximum()*10);
            int min = (int)(temp.getMinimum()*10);
            values += String.format("%04x", max) + String.format("%04x", min);
            bytes += "11";
        }
        else{
            bytes += "00";
            values += "00000000";
        }
        if(co2 != null){
            int max = (int)co2.getMaximum();
            int min = (int)co2.getMinimum();
            values += String.format("%04x", max) + String.format("%04x", min);;
            bytes += "11";
        }
        else{
            bytes += "00";
            values += "00000000";
        }
        if(spl != null){
            int max = (int)spl.getMaximum();
            values += String.format("%04x", max);
            bytes += "1";
        }
        else{
            bytes += "0";
            values += "0000";
        }

        String flag = new BigInteger(bytes,2).toString(16);
        values+=flag;
        System.out.println(values);
        return values;
    }
}
