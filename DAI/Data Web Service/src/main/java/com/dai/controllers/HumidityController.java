package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.humidity.HumidityModel;
import com.dai.shared.SentMeasurement;
import com.dai.shared.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/humidity")
@RestController
public class HumidityController {
    private HumidityModel humidityModel;

    @Autowired
    public HumidityController(HumidityModel humidityModel) {
        this.humidityModel = humidityModel;
    }
    @GetMapping(value = "latestMeasurement")
    public SentMeasurement readLastHumidity(@RequestParam("areaId") Optional<Integer> areaId) {
        try {
            return humidityModel.readLatestHumidity(areaId.get());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }


}
