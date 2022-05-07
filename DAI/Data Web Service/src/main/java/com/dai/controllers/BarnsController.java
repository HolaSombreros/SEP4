package com.dai.controllers;

import com.dai.exceptions.BadRequestException;
import com.dai.model.barns.BarnModel;
import com.dai.shared.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barns")
public class BarnsController {

    private BarnModel barnModel;

    @Autowired
    public BarnsController(BarnModel barnModel) {
        this.barnModel = barnModel;
    }

    @PostMapping
    public Barn create(@RequestBody Barn barn) {
        try {
            return barnModel.create(barn);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping
    public List<Barn> getAll() {
        try {
            return barnModel.getAll();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
