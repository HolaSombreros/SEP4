package com.dai.model.socketMeasurement;

import com.dai.Helper;
import com.dai.dao.area.AreaDao;
import com.dai.dao.measurement.MeasurementDao;
import com.dai.dao.measurement.MeasurementDaoImpl;
import com.dai.shared.Area;
import com.dai.shared.Measurement;
import com.dai.shared.SocketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.Future;

@Component
public class SocketMeasurementModelImpl implements SocketMeasurementModel {

    private MeasurementDao measurementDao;
    private AreaDao areaDao;

    @Autowired
    public SocketMeasurementModelImpl(MeasurementDao measurementDao, AreaDao areaDao) {
        this.measurementDao = measurementDao;
        this.areaDao = areaDao;
    }

    @Override
    public Future<Measurement> saveSocketData(SocketData data) throws Exception {

        if (!data.getCmd().equals("rx")) {
            return null;
        }

        Area area = Helper.await(areaDao.getAreaByHardwareId(data.getEUI()));
        LocalDateTime dateTime = getDateTimeFromEpochStringSeconds(data.getTs());
        Measurement measurement = new Measurement(dateTime, area);

        String payload = data.getData();
        int[] values = parseStringToValues(payload);

        String stringFlag = payload.substring(payload.length() - 1, payload.length());
        String flags = Integer.toBinaryString(Integer.parseInt(stringFlag, 16));

        if (flags.charAt(0) == '1') {
            double humidity = (double) values[0] / 10;
            measurement.setHumidity(humidity);
        }
        if (flags.charAt(1) == '1') {
            double temperature = (double) values[1] / 10;
            measurement.setTemperature(temperature);
        }
        if (flags.charAt(2) == '1') {
            measurement.setCo2(values[2]);
        }
        if (flags.charAt(3) == '1') {
            measurement.setSound(values[3]);
        }
        return measurementDao.saveMeasurement(measurement);
    }

    private static int[] parseStringToValues(String str) {
        int[] values = new int[4];
        int loopStep = 4;
        for (int i = 1; (i * loopStep) < str.length(); i++) {
            int current = i * loopStep;
            int startIndex = current - loopStep;
            String substring = str.substring(startIndex, current);
            values[i - 1] = Integer.parseInt(substring, 16);
        }
        return values;
    }

    private LocalDateTime getDateTimeFromEpochStringSeconds(String seconds) {
        long secondsFromEpoch = Long.parseLong(seconds);
        return LocalDateTime.ofEpochSecond(secondsFromEpoch, 0, ZoneId.of("Europe/Copenhagen").getRules().getOffset(LocalDateTime.now()));
    }
}
