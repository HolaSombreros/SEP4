package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.service.temperature.TemperatureService;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class TemperaturesController {

    private TemperatureService temperatureService;

    @Autowired
    public TemperaturesController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping(value = "/areas/{id}/temperatures")
    public List<SentMeasurement> readAllFromTodayByAreaId(@PathVariable int id) {
        try {
            return temperatureService.readAllFromTodayByAreaId(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/temperatures", params = "latest=true")
    public List<SentMeasurement> readLatestByAreaId(@PathVariable int id, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            return temperatureService.readLatestByAreaId(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/temperatures", params = "date")
    public List<SentMeasurement> readAllByDateAndAreaId(@PathVariable int id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {
        try {
            return temperatureService.readAllByDateAndAreaId(id, date.get());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
