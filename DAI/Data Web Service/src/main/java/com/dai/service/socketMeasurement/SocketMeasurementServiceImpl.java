package com.dai.service.socketMeasurement;

import com.dai.dao.threshold.ThresholdDao;
import com.dai.helpers.Helper;
import com.dai.dao.area.AreaDao;
import com.dai.dao.measurement.MeasurementDao;
import com.dai.helpers.MeasurementValidator;
import com.dai.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class SocketMeasurementServiceImpl implements SocketMeasurementService {

    private MeasurementDao measurementDao;
    private AreaDao areaDao;
    private MeasurementValidator measurementValidator;
    private ThresholdDao thresholdDao;

    @Autowired
    public SocketMeasurementServiceImpl(MeasurementDao measurementDao, AreaDao areaDao, MeasurementValidator measurementValidator, ThresholdDao thresholdDao) {
        this.measurementDao = measurementDao;
        this.areaDao = areaDao;
        this.measurementValidator = measurementValidator;
        this.thresholdDao = thresholdDao;
    }

    @Override
    public Measurement saveSocketData(SocketData data) throws Exception {
        if (!data.getCmd().equals("rx")) {
            return null;
        }

        Area area = Helper.await(areaDao.readByHardwareId(data.getEUI()));
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(data.getTs())), ZoneId.of("Europe/Copenhagen"));
        Measurement measurement = new Measurement(dateTime, area);

        String payload = data.getData();
        int[] values = parseStringToValues(payload);

        String stringFlag = payload.substring(payload.length() - 1);
        String flags = Integer.toBinaryString(Integer.parseInt(stringFlag, 16));

        if (flags.charAt(0) == '1') {
            double humidity = (double) values[0] / 10;

            if (measurementValidator.isHumidityValueValid(humidity)) {
                measurement.setHumidity(humidity);
            }
        }

        if (flags.charAt(1) == '1') {
            double temperature = (double) values[1] / 10;

            if (measurementValidator.isTemperatureValueValid(temperature)) {
                measurement.setTemperature(temperature);
            }
        }

        if (flags.charAt(2) == '1') {
            int co2 = values[2];

            if (measurementValidator.isCo2ValueValid(co2)) {
                measurement.setCo2(co2);
            }
        }

        if (flags.charAt(3) == '1') {
            int sound = values[3];

            if (measurementValidator.isSoundValueValid(sound)) {
                measurement.setSound(sound);
            }
        }

        System.out.println("Parsed data: " + measurement.toString());

        return Helper.await(measurementDao.create(measurement));
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

}
