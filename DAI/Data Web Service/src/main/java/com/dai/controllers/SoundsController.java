package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.sound.SoundModel;
import com.dai.shared.SentMeasurement;
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
  private SoundModel soundModel;

  @Autowired
  public SoundsController(SoundModel soundModel) {
    this.soundModel = soundModel;
  }

  @GetMapping(value = "/areas/{id}/sounds", params = "latest")
  public List<SentMeasurement> readLastSound(@PathVariable("id") int areaId, @RequestParam("latest") Optional<Boolean> isLatest) {
    try {
      if (isLatest.isPresent() && isLatest.get()) {
        return soundModel.readLatestSound(areaId);
      } else {
        return soundModel.getSoundMeasurementsByDate(areaId, LocalDate.now());
      }
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @GetMapping(value = "/areas/{id}/sounds", params = "date")
  public List<SentMeasurement> getAllSoundsByDate(@PathVariable("id") int areaId, @Valid @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
    try{
      return soundModel.getSoundMeasurementsByDate(areaId, localDate.get());
    }catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }

  }
}
