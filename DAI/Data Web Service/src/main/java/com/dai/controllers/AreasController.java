package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.service.areas.AreasService;
import com.dai.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/areas")
@RestController
public class  AreasController {
    private AreasService areasService;

    @Autowired
    public AreasController(AreasService areasService) {
        try {
            this.areasService = areasService;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping
    public Area create(@RequestBody Area area) {
        try {
            return areasService.create(area);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public Area read(@PathVariable int id) {
        try {
            return areasService.read(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    @GetMapping
    public List<Area> readAll() {
        try {
            return areasService.readAll();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public Area update(@PathVariable(name = "id") int id, @RequestBody Area area){
        try{
            area.setId(id);
            return areasService.update(area);
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public Area delete(@PathVariable int id) {
        try {
            return areasService.delete(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
