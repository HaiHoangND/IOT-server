package com.example.iotdemo.repository;

import com.example.iotdemo.entity.PumpAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PumpAutoRepository extends JpaRepository<PumpAuto,Integer> {
}
