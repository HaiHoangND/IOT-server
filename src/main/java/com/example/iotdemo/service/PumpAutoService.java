package com.example.iotdemo.service;

import com.example.iotdemo.entity.PumpAuto;
import com.example.iotdemo.entity.TreeStatus;
import com.example.iotdemo.exception.BusinessException;
import com.example.iotdemo.repository.PumpAutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PumpAutoService {
    private final PumpAutoRepository pumpAutoRepository;

    public PumpAutoService(PumpAutoRepository pumpAutoRepository) {
        this.pumpAutoRepository = pumpAutoRepository;
    }

    public PumpAuto getById(int id) {
        return pumpAutoRepository.findById(id).orElseThrow(() -> new BusinessException("404", "error", "Pump not found"));
    }

    public List<PumpAuto> getAll(){
        return pumpAutoRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    public int create(PumpAuto pumpAuto) {
        pumpAutoRepository.save(pumpAuto);
        return pumpAuto.getId();
    }

    public Boolean delete(int id) {
        pumpAutoRepository.findById(id)
                .orElseThrow(()-> new BusinessException("404", "error", "Pump not found"));
        pumpAutoRepository.deleteById(id);
        return true;
    }
}
