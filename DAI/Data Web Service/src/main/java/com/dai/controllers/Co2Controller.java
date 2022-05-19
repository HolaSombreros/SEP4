package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.co2.Co2Model;
import com.dai.shared.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<SentMeasurement> getAllFromToday(@PathVariable int id){
        try{
            return co2Model.getAllFromToday(id);
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    @GetMapping(value = "/areas/{id}/co2s", params = "latest=true")
    public List<SentMeasurement> getLatestCo2(@PathVariable int id, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            return co2Model.getLatest(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/co2s", params = "date")
    public List<SentMeasurement> getAllCo2sInDate(@PathVariable int id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {
        try {
            return co2Model.getAllByDate(id, date.get());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
