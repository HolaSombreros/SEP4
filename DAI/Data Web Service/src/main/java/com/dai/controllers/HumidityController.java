package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.humidity.HumidityModel;
import com.dai.shared.SentMeasurement;
import com.dai.shared.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HumidityController {
    private HumidityModel humidityModel;

    @Autowired
    public HumidityController(HumidityModel humidityModel) {
        this.humidityModel = humidityModel;
    }

    @GetMapping("/areas/{id}/humidities")
    public List<SentMeasurement> readLastHumidity(@PathVariable("id") int areaId, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            if (isLatest.isPresent() && isLatest.get()) {
                return humidityModel.readLatestHumidity(areaId);
            } else {
                //TODO return all temperatures for the given area
                return null;
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
