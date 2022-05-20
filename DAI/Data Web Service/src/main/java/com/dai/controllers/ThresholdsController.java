package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.threshold.ThresholdModel;
import com.dai.shared.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/thresholds")
public class ThresholdsController {

    private ThresholdModel model;

    @Autowired
    public ThresholdsController(ThresholdModel model) {
        this.model = model;
    }

    @GetMapping(value = "/{areaId}", params = "type")
    public ThresholdValues find(@PathVariable int areaId, @RequestParam("type") ThresholdType type) {
        try {
            return model.find(areaId, type).toThresholdValues();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping(value = "/{areaId}", params = "type")
    public Threshold create(@PathVariable("areaId") int areaId, @RequestParam("type") ThresholdType type, @RequestBody ThresholdValues threshold) {
        try {
            Area area = new Area();
            area.setId(areaId);
            Threshold t = new Threshold(-1, threshold.getMinimum(), threshold.getMaximum(), type, area);
            return model.create(t);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/{areaId}/logs", params = {"type", "date"})
    public List<SentThresholdLog> getLogs(@PathVariable("areaId") int areaId, @RequestParam("type") ThresholdType type, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try{
            return model.getAllExceeding(areaId, type,date);
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
