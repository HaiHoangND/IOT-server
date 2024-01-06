package com.example.iotdemo.controller;

import com.example.iotdemo.entity.PumpAuto;
import com.example.iotdemo.entity.TreeStatus;
import com.example.iotdemo.response.GeneralResponse;
import com.example.iotdemo.service.PumpAutoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pump")
public class PumpAutoController {
    private final PumpAutoService pumpAutoService;

    public PumpAutoController(PumpAutoService pumpAutoService) {
        this.pumpAutoService = pumpAutoService;
    }

    @GetMapping("{id}")
    GeneralResponse<?> getPumpById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", pumpAutoService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping
    GeneralResponse<?> getAllPump() {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", pumpAutoService.getAll());
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createPump(@RequestBody PumpAuto pumpAuto) {
        try {
            return GeneralResponse.ok("success", "Successfully created", pumpAutoService.create(pumpAuto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("/updatePump")
    GeneralResponse<?> updatePump(@RequestParam boolean pump) {
        try {
            return GeneralResponse.ok("success", "Successfully update", pumpAutoService.updatePump(pump));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PutMapping("/updateAuto")
    GeneralResponse<?> updateAuto(@RequestParam boolean auto) {
        try {
            return GeneralResponse.ok("success", "Successfully update", pumpAutoService.updateAuto(auto));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deletePumpById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", pumpAutoService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
