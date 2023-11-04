package com.example.iotdemo.service;

import com.example.iotdemo.entity.TreeStatus;
import com.example.iotdemo.exception.BusinessException;
import com.example.iotdemo.repository.TreeStatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreeStatusService {
    private final TreeStatusRepository treeStatusRepository;

    public TreeStatusService(TreeStatusRepository treeStatusRepository) {
        this.treeStatusRepository = treeStatusRepository;
    }

    public TreeStatus getById(int id) {
        return treeStatusRepository.findById(id).orElseThrow(() -> new BusinessException("404", "error", "Status not found"));
    }

    public List<TreeStatus> getAll(){
        return treeStatusRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    public int create(TreeStatus treeStatus) {
        treeStatusRepository.save(treeStatus);
        return treeStatus.getId();
    }

    public Boolean delete(int id) {
        treeStatusRepository.findById(id)
                .orElseThrow(()-> new BusinessException("404", "error", "Status not found"));
        treeStatusRepository.deleteById(id);
        return true;
    }
}
