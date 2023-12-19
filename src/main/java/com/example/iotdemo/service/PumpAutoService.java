package com.example.iotdemo.service;

import com.example.iotdemo.config.MqttClientWrapper;
import com.example.iotdemo.entity.PumpAuto;
import com.example.iotdemo.exception.BusinessException;
import com.example.iotdemo.repository.PumpAutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PumpAutoService {
    private final PumpAutoRepository pumpAutoRepository;

    private final MqttClientWrapper mqttClientWrapper;

    public PumpAutoService(PumpAutoRepository pumpAutoRepository, MqttClientWrapper mqttClientWrapper) {
        this.pumpAutoRepository = pumpAutoRepository;
        this.mqttClientWrapper = mqttClientWrapper;
    }

    public PumpAuto getById(int id) {
        return pumpAutoRepository.findById(id).orElseThrow(() -> new BusinessException("404", "error", "Pump not found"));
    }

    public List<PumpAuto> getAll() {
        return pumpAutoRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    public int create(PumpAuto pumpAuto) {
        pumpAutoRepository.save(pumpAuto);
        return pumpAuto.getId();
    }

    public int updatePump(boolean pump) {
        Optional<PumpAuto> optionalPumpAuto = pumpAutoRepository.findById(1);
        PumpAuto pumpAuto = optionalPumpAuto.orElseThrow();
        pumpAuto.setPump(pump);
        String pumpTopic = "pump";
        String pumpMessage = pump ? "1" : "0";
        if (!pumpAuto.isAuto()) {
            mqttClientWrapper.publish(pumpTopic, pumpMessage);
            pumpAutoRepository.save(pumpAuto);
        } else {
            throw new BusinessException("404", "error", "Tắt chế độ tự động để điều khiển trực tiếp");
        }
        return pumpAuto.getId();
    }

    public int updateAuto(boolean auto) {
        Optional<PumpAuto> optionalPumpAuto = pumpAutoRepository.findById(1);
        PumpAuto pumpAuto = optionalPumpAuto.orElseThrow();
        pumpAuto.setAuto(auto);
        pumpAutoRepository.save(pumpAuto);
        String autoTopic = "auto";
        String autoMessage = auto ? "1" : "0";
        mqttClientWrapper.publish(autoTopic, autoMessage);
        return pumpAuto.getId();
    }

    public Boolean delete(int id) {
        pumpAutoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("404", "error", "Pump not found"));
        pumpAutoRepository.deleteById(id);
        return true;
    }
}
