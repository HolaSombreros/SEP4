package com.dai.helpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementValidatorTest {

    MeasurementValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new MeasurementValidator();
    }

    @Test
    void isTemperatureValueValid() {
        //Arrange
        double temperatureToCompare = 20;

        //Act
        boolean temperatureValueValid = validator.isTemperatureValueValid(temperatureToCompare);

        //Assert
        assertTrue(temperatureValueValid);
    }

    @Test
    void isTemperatureValueValidValueTooBig() {
        //Arrange
        double temperatureToCompare = 125;

        //Act
        boolean temperatureValueValid = validator.isTemperatureValueValid(temperatureToCompare);

        //Assert
        assertFalse(temperatureValueValid);
    }

    @Test
    void isTemperatureValueValidValueTooSmall() {
        //Arrange
        double temperatureToCompare = -15;

        //Act
        boolean temperatureValueValid = validator.isTemperatureValueValid(temperatureToCompare);

        //Assert
        assertFalse(temperatureValueValid);
    }

    @Test
    void isSoundValueValid() {
        //Arrange
        int soundToCompare = 50;

        //Act
        boolean soundValueValid = validator.isSoundValueValid(soundToCompare);

        //Assert
        assertTrue(soundValueValid);
    }

    @Test
    void isSoundValueValidValueTooSmall() {
        //Arrange
        int soundToCompare = -10;

        //Act
        boolean soundValueValid = validator.isSoundValueValid(soundToCompare);

        //Assert
        assertFalse(soundValueValid);
    }


    @Test
    void isSoundValueValidValueTooBig() {
        //Arrange
        int soundToCompare = 140;

        //Act
        boolean soundValueValid = validator.isSoundValueValid(soundToCompare);

        //Assert
        assertFalse(soundValueValid);
    }

    @Test
    void isCo2ValueValid() {
        //Arrange
        int co2Value = 300;

        //Act
        boolean co2ValueValid = validator.isCo2ValueValid(co2Value);

        //Assert
        assertTrue(co2ValueValid);
    }

    @Test
    void isCo2ValueValidValueTooBig() {
        //Arrange
        int co2Value = 3001;

        //Act
        boolean co2ValueValid = validator.isCo2ValueValid(co2Value);

        //Assert
        assertFalse(co2ValueValid);
    }

    @Test
    void isCo2ValueValidValueTooSmall() {
        //Arrange
        int co2Value = 100;

        //Act
        boolean co2ValueValid = validator.isCo2ValueValid(co2Value);

        //Assert
        assertFalse(co2ValueValid);
    }

    @Test
    void isHumidityValueValid() {
        //Arrange
        double humidity = 56;

        //Act
        boolean humidityValueValid = validator.isHumidityValueValid(humidity);

        //Assert
        assertTrue(humidityValueValid);
    }

    @Test
    void isHumidityValueValidValueTooBig() {
        //Arrange
        double humidity = 100;

        //Act
        boolean humidityValueValid = validator.isHumidityValueValid(humidity);

        //Assert
        assertFalse(humidityValueValid);
    }

    @Test
    void isHumidityValueValidValueTooSmall() {
        //Arrange
        double humidity = -1;

        //Act
        boolean humidityValueValid = validator.isHumidityValueValid(humidity);

        //Assert
        assertFalse(humidityValueValid);
    }
}