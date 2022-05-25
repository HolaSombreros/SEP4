package com.dai.helpers;

import com.dai.model.Threshold;
import com.dai.model.ThresholdType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThresholdValidatorTest {

    private ThresholdValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ThresholdValidator();
    }

    @Test
    void validateThresholdTemperatureCorrect() {
        //Arrange
        Threshold threshold = createThreshold(15, 20, ThresholdType.TEMPERATURE);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNull(exception);
    }

    @Test
    void validateThresholdTemperatureMinBiggerThanMax() {
        //Arrange
        Threshold threshold = createThreshold(20, 10, ThresholdType.TEMPERATURE);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }

    @Test
    void validateThresholdTemperatureTooSmall() {
        //Arrange
        Threshold threshold = createThreshold(5, 20, ThresholdType.TEMPERATURE);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }

    @Test
    void validateThresholdTemperatureTooBig() {
        //Arrange
        Threshold threshold = createThreshold(15, 40, ThresholdType.TEMPERATURE);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }

    @Test
    void validateThresholdTemperatureBothOutOfBoundaries() {
        //Arrange
        Threshold threshold = createThreshold(10, 40, ThresholdType.TEMPERATURE);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }

    @Test
    void validateThresholdCo2Correct() {
        //Arrange
        Threshold threshold = createThreshold(200, 1100, ThresholdType.CO2);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNull(exception);
    }

    @Test
    void validateThresholdCo2MinBiggerThanMax() {
        //Arrange
        Threshold threshold = createThreshold(200, 170, ThresholdType.CO2);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }

    @Test
    void validateThresholdCo2TooSmall() {
        //Arrange
        Threshold threshold = createThreshold(149, 400, ThresholdType.CO2);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }

    @Test
    void validateThresholdCo2TooBig() {
        //Arrange
        Threshold threshold = createThreshold(400, 2600, ThresholdType.CO2);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }

    @Test
    void validateThresholdCo2BothOutOfBoundaries() {
        //Arrange
        Threshold threshold = createThreshold(140, 2600, ThresholdType.CO2);
        Exception exception = null;

        //Act
        try {
            validator.validateThreshold(threshold);
        } catch (Exception e) {
            exception = e;
        }

        //Assert
        assertNotNull(exception);
    }


    private Threshold createThreshold(double min, double max, ThresholdType type) {
        return new Threshold(0, min, max, type, null);
    }
}