package com.example.iotdemo.repository;

import com.example.iotdemo.entity.TreeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeStatusRepository  extends JpaRepository<TreeStatus,Integer> {
}
