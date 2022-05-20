package com.dai.controllers;

import com.dai.model.threshold.ThresholdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThresholdsController {

    private ThresholdModel model;

    @Autowired
    public ThresholdsController(ThresholdModel model) {
        this.model = model;
    }
}
