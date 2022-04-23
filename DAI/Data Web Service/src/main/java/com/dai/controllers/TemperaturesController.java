package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.temperature.TemperatureModel;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.Future;

@RequestMapping("/temperatures")
@RestController
public class TemperaturesController {

    private TemperatureModel temperatureModel;
    @Autowired
    public TemperaturesController(TemperatureModel temperatureModel) {
        this.temperatureModel = temperatureModel;
    }

    @GetMapping(value = "/latestMeasurement")
    public SentMeasurement readLastTemperature(@RequestParam("areaId") Optional<Integer> areaId){
        try{
            return temperatureModel.readLatestTemperature(areaId.get());
        }
        catch (Exception e){
            throw new BadRequestException();
        }
    }
}
