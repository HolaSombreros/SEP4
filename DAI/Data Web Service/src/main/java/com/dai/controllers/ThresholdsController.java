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
    public Threshold find(@PathVariable int areaId, @RequestParam("type") ThresholdType type) {
        try {
            return model.find(areaId, type);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping(value = "/thresholds/{areaId}", params = "type")
    public Threshold create(@PathVariable("areaId") int areaId, @RequestParam("type") ThresholdType type, @RequestBody ThresholdValues threshold) {
        try {
            Threshold toThreshold = requestToThreshold(areaId, threshold, type);
            return model.create(toThreshold);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping(value = "/thresholds/{areaId}", params = {"type", "userId"})
    public Threshold update(@PathVariable int areaId, @RequestParam("type") ThresholdType type, @RequestParam("userId") int userId, @RequestBody ThresholdValues threshold) {
        try {
            Threshold toThreshold = requestToThreshold(areaId, threshold, type);
            return model.update(toThreshold, userId);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Threshold requestToThreshold(int areaId, ThresholdValues thresholdValues, ThresholdType type) {
        Area area = new Area();
        area.setId(areaId);
        return new Threshold(-1, thresholdValues.getMinimum(), thresholdValues.getMaximum(), type, area);
    }
}
