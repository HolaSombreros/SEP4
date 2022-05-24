package com.dai.helpers;

import org.springframework.stereotype.Component;

@Component
public class MeasurementValidator {

    public boolean isTemperatureValueValid(double value) {
        return value >= 0 && value <= 60;
    }

    public boolean isHumidityValueValid(double value) {
        return value >= 0 && value <= 100;
    }

    public boolean isCo2ValueValid(int value) {
        return value >= 150 && value <= 3000;
    }

    public boolean isSoundValueValid(int value) {
        return value >= 0 && value <= 120;
    }
}
