package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.service.sound.SoundService;
import com.dai.model.SentMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class SoundsController
{
  private SoundService soundService;

  @Autowired
  public SoundsController(SoundService soundService) {
    this.soundService = soundService;
  }

  @GetMapping(value = "/areas/{id}/sounds")
  public List<SentMeasurement> readAllFromTodayByAreaId(@PathVariable int id) {
    try {
      return soundService.readAllFromTodayByAreaId(id);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @GetMapping(value = "/areas/{id}/sounds", params = "latest=true")
  public List<SentMeasurement> readLatestByAreaId(@PathVariable("id") int areaId, @RequestParam("latest") Optional<Boolean> isLatest) {
    try {
        return soundService.readLatestByAreaId(areaId);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @GetMapping(value = "/areas/{id}/sounds", params = "date")
  public List<SentMeasurement> readAllByDateAndAreaId(@PathVariable("id") int areaId, @Valid @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
    try{
      return soundService.readAllByDateAndAreaId(areaId, localDate.get());
    }catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }
}
