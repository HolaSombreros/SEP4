package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.humidity.HumidityModel;
import com.dai.shared.SentMeasurement;
import com.dai.shared.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class HumidityController {
    private HumidityModel humidityModel;

    @Autowired
    public HumidityController(HumidityModel humidityModel) {
        this.humidityModel = humidityModel;
    }

    @GetMapping(value = "/areas/{id}/humidities", params = "latest")
    public List<SentMeasurement> readLastHumidity(@PathVariable("id") int areaId, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            if (isLatest.isPresent() && isLatest.get()) {
                return humidityModel.readLatestHumidity(areaId);
            } else {
                return humidityModel.getHumidityMeasurementsByDate(areaId, LocalDate.now());
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/humidities", params = "date")
    public List<SentMeasurement> getAllMeasurementsByDate(@PathVariable("id") int areaId, @Valid @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
        try{
            return humidityModel.getHumidityMeasurementsByDate(areaId, localDate.get());
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

}
