package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.areas.AreasModel;
import com.dai.shared.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/areas")
@RestController
public class AreasController {
    private AreasModel areasModel;

    @Autowired
    public AreasController(AreasModel areasModel) {
        try {
            this.areasModel = areasModel;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping
    public Area create(@RequestBody Area area) {
        try {
            return areasModel.create(area);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public Area read(@PathVariable int id) {
        try {
            return areasModel.read(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping
    public List<Area> getAll() {
        try {
            return areasModel.getAll();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
