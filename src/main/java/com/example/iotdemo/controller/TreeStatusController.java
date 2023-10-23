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
        return GeneralResponse.ok("success", "Successfully fetched", treeStatusService.getById(id));
    }

    @GetMapping
    GeneralResponse<?> getAllTreeStatus() {
        return GeneralResponse.ok("success", "Successfully fetched", treeStatusService.getAll());
    }

    @PostMapping
    GeneralResponse<?> createOrderStatus(@RequestBody TreeStatus treeStatus) {
        return GeneralResponse.ok("success", "Successfully created", treeStatusService.create(treeStatus));
    }

    @DeleteMapping("{id}")
    GeneralResponse<?> deleteOrderStatusById(@PathVariable int id){
        return GeneralResponse.ok("success", "Successfully deleted", treeStatusService.delete(id));
    }
}
