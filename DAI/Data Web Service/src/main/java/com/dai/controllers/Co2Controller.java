package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.co2.Co2Model;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Co2Controller {

    private Co2Model co2Model;

    @Autowired
    public Co2Controller(Co2Model co2Model) {
        this.co2Model = co2Model;
    }

    @GetMapping(value = "/areas/{id}/co2s")
    public List<SentMeasurement> readLastCo2(@PathVariable int id, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            if (isLatest.isPresent() && isLatest.get()) {
                return co2Model.readLatestCo2(id);
            } else {
                //TODO return all temperatures for the given area
                return null;
            }
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
