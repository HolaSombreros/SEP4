package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.socket.ISocketService;
import com.dai.model.socket.SocketService;
import com.dai.model.threshold.ThresholdModel;
import com.dai.shared.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/thresholds")
public class ThresholdsController {

    private ThresholdModel model;
    private ISocketService socketService;
    @Autowired
    public ThresholdsController(ThresholdModel model, SocketService socketService) {
        this.model = model;
        this.socketService = socketService;
    }

    @GetMapping(value = "/{areaId}", params = "type")
    public Threshold find(@PathVariable int areaId, @RequestParam("type") ThresholdType type) {
        try {
            return model.find(areaId, type);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping(value = "/{areaId}", params = "type")
    public Threshold create(@PathVariable("areaId") int areaId, @RequestParam("type") ThresholdType type, @RequestBody ThresholdValues threshold) {
        try {
            Threshold toThreshold = requestToThreshold(areaId, threshold, type);
            Threshold newThreshold = model.create(toThreshold);
            socketService.sendDownLinkData(areaId);
            return newThreshold;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping(value = "/{areaId}", params = {"type", "userId"})
    public Threshold update(@PathVariable int areaId, @RequestParam("type") ThresholdType type, @RequestParam("userId") int userId, @RequestBody ThresholdValues threshold) {
        try {
            Threshold toThreshold = requestToThreshold(areaId, threshold, type);
            Threshold updated = model.update(toThreshold, userId);
            socketService.sendDownLinkData(areaId);
            return updated;
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

    @GetMapping(value = "/logs", params = "date")
    public List<ThresholdLogs> getLogs(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try{
            return model.getAllByDate(date);
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    public Threshold requestToThreshold( int areaId, ThresholdValues thresholdValues, ThresholdType type) {
        Area area = new Area();
        area.setId(areaId);
        return new Threshold(-1, thresholdValues.getMinimum(), thresholdValues.getMaximum(), type, area);
    }
}
