package com.example.iotdemo.repository;

import com.example.iotdemo.entity.TreeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeStatusRepository  extends JpaRepository<TreeStatus,Integer> {
    List<TreeStatus> findTop10ByOrderByCreatedAtDesc(Pageable pageable);

    Optional<TreeStatus> findFirstByOrderByCreatedAtDesc();
}
