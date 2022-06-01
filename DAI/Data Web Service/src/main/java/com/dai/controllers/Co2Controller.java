package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.service.co2.Co2Service;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class Co2Controller {

    private Co2Service co2Service;

    @Autowired
    public Co2Controller(Co2Service co2Service) {
        this.co2Service = co2Service;
    }

    @GetMapping(value = "/areas/{id}/co2s")
    public List<SentMeasurement> readAllFromTodayByAreaId(@PathVariable int id){
        try{
            return co2Service.readAllFromTodayByAreaId(id);
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    @GetMapping(value = "/areas/{id}/co2s", params = "latest=true")
    public List<SentMeasurement> readLatestByAreaId(@PathVariable int id, @RequestParam("latest") Optional<Boolean> isLatest) {
        try {
            return co2Service.readLatestByAreaId(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/areas/{id}/co2s", params = "date")
    public List<SentMeasurement> readAllByDateAndAreaId(@PathVariable int id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {
        try {
            return co2Service.readAllByDateAndAreaId(id, date.get());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
