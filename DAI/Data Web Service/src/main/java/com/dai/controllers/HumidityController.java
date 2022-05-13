package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.humidity.HumidityModel;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class HumidityController {
    private HumidityModel humidityModel;

    @Autowired
    public HumidityController(HumidityModel humidityModel) {
        this.humidityModel = humidityModel;
    }

    @GetMapping(value ="/areas/{id}/humidities" )
    public List<SentMeasurement> getAllFromToday(@PathVariable("id") int areaId) {
        try {
            return humidityModel.getAllFromToday(areaId);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/humidities", params = "latest=true")
    public List<SentMeasurement> getLatest(@PathVariable("id") int areaId, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            return humidityModel.readLatest(areaId);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/humidities", params = "date")
    public List<SentMeasurement> getAllByDate(@PathVariable("id") int areaId, @Valid @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
        try{
            return humidityModel.getAllByDate(areaId, localDate.get());
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

}
