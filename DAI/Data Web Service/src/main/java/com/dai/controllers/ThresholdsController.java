package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.threshold.ThresholdModel;
import com.dai.shared.Area;
import com.dai.shared.Threshold;
import com.dai.shared.ThresholdValues;
import com.dai.shared.ThresholdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThresholdsController {

    private ThresholdModel model;

    @Autowired
    public ThresholdsController(ThresholdModel model) {
        this.model = model;
    }

    @GetMapping(value = "/thresholds/{areaId}", params = "type")
    public ThresholdValues find(@PathVariable int areaId, @RequestParam("type") ThresholdType type) {
        try {
            return model.find(areaId, type).toThresholdValues();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping(value = "/thresholds/{areaId}", params = "type")
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
}
