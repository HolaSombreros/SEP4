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
        double temperatureToCompare = -30;

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
        int soundToCompare = 200;

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
        int co2Value = 0;

        //Act
        boolean co2ValueValid = validator.isCo2ValueValid(co2Value);

        //Assert
        assertFalse(co2ValueValid);
    }

    @Test
    void isHumidityValueValid() {
        //Arrange
        double humidity = 50;

        //Act
        boolean value = validator.isHumidityValueValid(humidity);

        //Assert
        assertTrue(value);
    }

    @Test
    void isHumidityValueValidUpperBoundaryPlusOne() {
        //Arrange
        double humidity = 101;

        //Act
        boolean value = validator.isHumidityValueValid(humidity);

        //Assert
        assertFalse(value);
    }

    @Test
    void isHumidityValueValidUpperBoundary() {
        //Arrange
        double humidity = 100;

        //Act
        boolean value = validator.isHumidityValueValid(humidity);

        //Assert
        assertTrue(value);
    }

    @Test
    void isHumidityValueValidUpperBoundaryMinusOne() {
        //Arrange
        double humidity = 99;

        //Act
        boolean value = validator.isHumidityValueValid(humidity);

        //Assert
        assertTrue(value);
    }

    @Test
    void isHumidityValueValidLowerBoundaryPlusOne() {
        //Arrange
        double humidity = 1;

        //Act
        boolean value = validator.isHumidityValueValid(humidity);

        //Assert
        assertTrue(value);
    }

    @Test
    void isHumidityValueValidLowerBoundary() {
        //Arrange
        double humidity = 0;

        //Act
        boolean value = validator.isHumidityValueValid(humidity);

        //Assert
        assertTrue(value);
    }

    @Test
    void isHumidityValueValidLowerBoundaryMinusOne() {
        //Arrange
        double humidity = -1;

        //Act
        boolean value = validator.isHumidityValueValid(humidity);

        //Assert
        assertFalse(value);
    }

}