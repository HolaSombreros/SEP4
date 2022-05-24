package com.dai.helpers;

import com.dai.shared.Threshold;
import org.springframework.stereotype.Component;

@Component
public class ThresholdValidator {

    public void validateThreshold(Threshold threshold) throws Exception {
        double minimum = threshold.getMinimum();
        double maximum = threshold.getMaximum();

        if (maximum < minimum) {
            throw new Exception("Threshold maximum value must be bigger than minimum!");
        }

        switch (threshold.getType()) {
            case TEMPERATURE:
                if (!(isTemperatureValueValid(minimum) && isTemperatureValueValid(maximum)))
                    exception("Temperature");
                break;

            case HUMIDITY:
                if (!(isHumidityValueValid(minimum) && isHumidityValueValid(maximum)))
                    exception("Humidity");
                break;

            case SPL:
                if (!(isSoundValueValid((int) minimum) && isSoundValueValid((int) maximum)))
                    exception("Sound");
                break;

            case CO2:
                if (!(isCo2ValueValid((int) minimum) && isCo2ValueValid((int) maximum)))
                    exception("Co2");
                break;
        }
    }

    private void exception(String type) throws Exception {
        throw new Exception(type + " threshold is not valid!");
    }

    private boolean isTemperatureValueValid(double value) {
        return value >= 10 && value <= 35;
    }

    private boolean isHumidityValueValid(double value) {
        return value >= 0 && value <= 80;
    }

    private boolean isCo2ValueValid(int value) {
        return value >= 150 && value <= 2500;
    }

    private boolean isSoundValueValid(int value) {
        return value >= 0 && value <= 140;
    }
}
