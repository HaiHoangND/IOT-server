package com.example.iotdemo.controller;

import com.example.iotdemo.entity.TreeStatus;
import com.example.iotdemo.response.GeneralResponse;
import com.example.iotdemo.service.TreeStatusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/treeStatus")
public class TreeStatusController {
    private final TreeStatusService treeStatusService;

    public TreeStatusController(TreeStatusService treeStatusService) {
        this.treeStatusService = treeStatusService;
    }


    @GetMapping("{id}")
    GeneralResponse<?> getTreeStatusById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", treeStatusService.getById(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping
    GeneralResponse<?> getAllTreeStatus() {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", treeStatusService.getAll());
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("/top10")
    GeneralResponse<?> getTop10() {
        try {
            return GeneralResponse.ok("success", "Successfully fetched", treeStatusService.getTop10LatestTreeStatus());
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @GetMapping("/latest")
    GeneralResponse<?> getLatest() {
        try {
//            System.out.println(1);
            return GeneralResponse.ok("success", "Successfully fetched", treeStatusService.getLatestTreeStatus());
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @PostMapping
    GeneralResponse<?> createTreeStatus(@RequestBody TreeStatus treeStatus) {
        try {
            return GeneralResponse.ok("success", "Successfully created", treeStatusService.create(treeStatus));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteTreeStatusById(@PathVariable int id) {
        try {
            return GeneralResponse.ok("success", "Successfully deleted", treeStatusService.delete(id));
        } catch (Exception e) {
            return GeneralResponse.failed("failed", e.getMessage());
        }
    }
}
