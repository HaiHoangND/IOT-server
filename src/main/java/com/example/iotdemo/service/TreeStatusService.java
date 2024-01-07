package com.example.iotdemo.service;

import com.example.iotdemo.dto.TreeStatusWithTime;
import com.example.iotdemo.entity.TreeStatus;
import com.example.iotdemo.exception.BusinessException;
import com.example.iotdemo.repository.TreeStatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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

//    public List<TreeStatus> getTop10LatestTreeStatus() {
//        PageRequest pageRequest = PageRequest.of(0, 10); // Lấy 10 bản ghi đầu tiên
//        return treeStatusRepository.findTop10ByOrderByCreatedAtDesc(pageRequest);
//    }

    public List<TreeStatusWithTime> getTop10LatestTreeStatus() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<TreeStatus> top10LatestTreeStatus = treeStatusRepository.findTop10ByOrderByCreatedAtDesc(pageRequest);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Collections.reverse(top10LatestTreeStatus);
        List<TreeStatusWithTime> treeStatusWithTimeList = new ArrayList<>();;
        // Định dạng lại trường createdAt
        top10LatestTreeStatus.forEach(treeStatus -> {
            TreeStatusWithTime treeStatusWithTime = new TreeStatusWithTime();
            treeStatusWithTime.setHumidity(treeStatus.getHumidity());
            treeStatusWithTime.setTemperature(treeStatus.getTemperature());
            treeStatusWithTime.setCreatedAt(treeStatus.getCreatedAt().format(formatter));
            treeStatusWithTimeList.add(treeStatusWithTime);
        });

        return treeStatusWithTimeList;
    }

    public Optional<TreeStatus> getLatestTreeStatus() {
        return treeStatusRepository.findFirstByOrderByCreatedAtDesc();
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
