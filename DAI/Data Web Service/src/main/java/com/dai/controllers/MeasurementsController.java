package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.measurement.MeasurementModel;
import com.dai.shared.Area;
import com.dai.shared.Barn;
import com.dai.shared.Measurement;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class MeasurementsController {

    private MeasurementModel measurementModel;

    @Autowired

    public MeasurementsController(MeasurementModel measurementModel) {
        this.measurementModel = measurementModel;
    }

    //TODO Change the return to the actual value after the database is correct (This is very stupid fix but the AND team can retrieve data)
    @GetMapping(path = "/areas/{id}/measurements")
    public Measurement readLastMeasurement(@PathVariable int id, @RequestParam("latest") Optional<Boolean> isLatest)
    {
        try {
            if (isLatest.isPresent() && isLatest.get()) {
                return new Measurement(LocalDateTime.now(), 25.0, 30.0, 392, 5.0, new Hardware(100), new Area(id, new Barn("Not real barn"), "area name", "description", 1, new Hardware(100)));
//                return measurementModel.readLastMeasurement(id);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
