package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.temperature.TemperatureModel;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TemperaturesController {

    private TemperatureModel temperatureModel;

    @Autowired
    public TemperaturesController(TemperatureModel temperatureModel) {
        this.temperatureModel = temperatureModel;
    }

    @GetMapping(value = "/areas/{id}/temperatures")
    public SentMeasurement readLastTemperature(@PathVariable int id, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            if (isLatest.isPresent() && isLatest.get()) {
                return temperatureModel.readLatestTemperature(id);
            } else {
                //TODO return all temperatures for the given area
                return null;
            }
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
