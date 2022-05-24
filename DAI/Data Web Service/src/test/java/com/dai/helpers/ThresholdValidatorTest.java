package com.dai.helpers;

import com.dai.shared.Threshold;
import com.dai.shared.ThresholdType;
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
        Threshold threshold = createThreshold(15, 20);
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
        Threshold threshold = createThreshold(20, 10);
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
        Threshold threshold = createThreshold(5, 20);
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
        Threshold threshold = createThreshold(15, 40);
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
        Threshold threshold = createThreshold(10, 40);
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


    private Threshold createThreshold(double min, double max) {
        return new Threshold(0, min, max, ThresholdType.TEMPERATURE, null);
    }
}