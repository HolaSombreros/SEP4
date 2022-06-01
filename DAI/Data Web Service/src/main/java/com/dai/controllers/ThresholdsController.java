package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.service.socket.SocketService;
import com.dai.service.threshold.ThresholdService;
import com.dai.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/thresholds")
public class ThresholdsController {

    private ThresholdService model;
    private SocketService socketService;
    @Autowired
    public ThresholdsController(ThresholdService model, SocketService socketService) {
        this.model = model;
        this.socketService = socketService;
    }

    @GetMapping(value = "/{areaId}", params = "type")
    public Threshold readByAreaIdAndType(@PathVariable int areaId, @RequestParam("type") ThresholdType type) {
        try {
            return model.readByAreaIdAndType(areaId, type);
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
    public List<SentThresholdLog> readAllExceedingLogsByTypeAndAreaIdAndDate(@PathVariable("areaId") int areaId, @RequestParam("type") ThresholdType type, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try{
            return model.readAllExceedingLogsByTypeAndAreaIdAndDate(areaId, type,date);
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/modifications", params = "date")
    public List<ThresholdLogs> readAllLogsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try{
            return model.readAllLogsByDate(date);
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/logs/latest")
    public List<NotificationLogs> readAllLatestLogs(){
        try{
            return model.readAllLogsFromLast5Min();
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    public Threshold requestToThreshold( int areaId, ThresholdValues thresholdValues, ThresholdType type) {
        Area area = new Area();
        area.setAreaId(areaId);
        return new Threshold(-1, thresholdValues.getMinimum(), thresholdValues.getMaximum(), type, area);
    }
}
