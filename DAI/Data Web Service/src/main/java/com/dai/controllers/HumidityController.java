package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.service.humidity.HumidityService;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class HumidityController {
    private HumidityService humidityService;

    @Autowired
    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @GetMapping(value ="/areas/{id}/humidities" )
    public List<SentMeasurement> readAllFromTodayByAreaId(@PathVariable("id") int areaId) {
        try {
            return humidityService.readAllFromTodayByAreaId(areaId);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/humidities", params = "latest=true")
    public List<SentMeasurement> readLatestByAreaId(@PathVariable("id") int areaId, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            return humidityService.readLatestByAreaId(areaId);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/humidities", params = "date")
    public List<SentMeasurement> readAllByDateAndAreaId(@PathVariable("id") int areaId, @Valid @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
        try{
            return humidityService.readAllByDateAndAreaId(areaId, localDate.get());
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

}
