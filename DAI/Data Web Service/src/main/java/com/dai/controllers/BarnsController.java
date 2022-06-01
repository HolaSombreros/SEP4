package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.service.barns.BarnService;
import com.dai.model.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barns")
public class BarnsController {

    private BarnService barnService;

    @Autowired
    public BarnsController(BarnService barnService) {
        this.barnService = barnService;
    }

    @PostMapping
    public Barn create(@RequestBody Barn barn) {
        try {
            return barnService.create(barn);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping
    public List<Barn> readAll() {
        try {
            return barnService.readAll();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
