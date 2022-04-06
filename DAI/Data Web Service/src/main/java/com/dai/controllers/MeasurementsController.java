package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.measurement.MeasurementModel;
import com.dai.shared.Measurement;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;

@RequestMapping("/measurements")
@RestController
public class MeasurementsController {

    private MeasurementModel measurementModel;

    @Autowired

    public MeasurementsController(MeasurementModel measurementModel) {
        this.measurementModel = measurementModel;
    }

    @GetMapping
    public Future<Measurement> readLastMeasurement()
    {
        try {
            return measurementModel.readLastMeasurement();
        } catch (Exception e) {
            throw new BadRequestException();
        }

    }
}
