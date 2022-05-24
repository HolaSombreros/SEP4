package com.dai.model.socket;

import com.dai.controllers.WebsocketClientController;
import com.dai.dao.area.AreaDao;
import com.dai.dao.threshold.ThresholdDao;
import com.dai.helpers.Helper;
import com.dai.model.socket.ISocketService;
import com.dai.shared.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.math.BigInteger;
@Component
public class SocketService implements ISocketService {
    private WebsocketClientController clientController;
    private AreaDao areaDao;
    private ThresholdDao thresholdDao;
    private Gson gson;

    @Autowired
    public SocketService(WebsocketClientController clientController, AreaDao areaDao, ThresholdDao thresholdDao) {
        this.clientController = clientController;
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
            clientController.sendDownLink(gson.toJson(downLink));
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    private String parseValuesToString(int areaId) throws Exception {
        String values = "";
        String bytes = "";
        Threshold temp = Helper.await(thresholdDao.find(areaId, ThresholdType.TEMPERATURE));
        Threshold humidity = Helper.await(thresholdDao.find(areaId, ThresholdType.HUMIDITY));
        Threshold co2 = Helper.await(thresholdDao.find(areaId, ThresholdType.CO2));
        Threshold spl = Helper.await(thresholdDao.find(areaId, ThresholdType.SPL));
        if(humidity != null){
            int max = (int)humidity.getMaximum();
            int min = (int) humidity.getMinimum();
            values +=Integer.toHexString(max) + Integer.toHexString(min);
            bytes += "11";
        }
        else{
            bytes += "00";
            values += "00000000";
        }
        if(temp != null){
            int max = (int)temp.getMaximum();
            int min = (int)temp.getMinimum();
            values +=Integer.toHexString(max) + Integer.toHexString(min);
            bytes += "11";
        }
        else{
            bytes += "00";
            values += "00000000";
        }
        if(co2 != null){
            int max = (int)co2.getMaximum();
            int min = (int)co2.getMinimum();
            values +=Integer.toHexString(max) + Integer.toHexString(min);
            bytes += "11";
        }
        else{
            bytes += "00";
            values += "00000000";
        }
        if(spl != null){
            int max = (int)spl.getMaximum();
            values += Integer.toHexString(max);
            bytes += "1";
        }
        else{
            bytes += "0";
            values += "0000";
        }

        String flag = new BigInteger(bytes,2).toString(16);
        values+=flag;
        return values;
    }
}
