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

  @GetMapping(value = "/areas/{id}/sounds")
  public List<SentMeasurement> readAllAreaSounds(@PathVariable int id) {
    try {
      return soundModel.readAllAreaSounds(id);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @GetMapping(value = "/areas/{id}/sounds", params = "latest=true")
  public List<SentMeasurement> readLastAreaSound(@PathVariable("id") int areaId, @RequestParam("latest") Optional<Boolean> isLatest) {
    try {
        return soundModel.readLastAreaSound(areaId);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @GetMapping(value = "/areas/{id}/sounds", params = "date")
  public List<SentMeasurement> readAreaSoundsByDate(@PathVariable("id") int areaId, @Valid @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
    try{
      return soundModel.readAreaSoundsByDate(areaId, localDate.get());
    }catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }
}
