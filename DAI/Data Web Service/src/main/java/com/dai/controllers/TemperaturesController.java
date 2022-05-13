package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.temperature.TemperatureModel;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class TemperaturesController {

    private TemperatureModel temperatureModel;

    @Autowired
    public TemperaturesController(TemperatureModel temperatureModel) {
        this.temperatureModel = temperatureModel;
    }

    @GetMapping(value = "/areas/{id}/temperatures")
    public List<SentMeasurement> readAllAreaTemperatures(@PathVariable int id) {
        try {
            return temperatureModel.readAllTemperatures(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/temperatures", params = "latest=true")
    public List<SentMeasurement> readLastTemperature(@PathVariable int id, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            return temperatureModel.readLatestTemperature(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/temperatures", params = "date")
    public List<SentMeasurement> readDateTemperatures(@PathVariable int id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {
        try {
            return temperatureModel.getAreaTemperaturesByDate(id, date.get());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
