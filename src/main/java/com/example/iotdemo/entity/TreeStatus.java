package com.example.iotdemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table
public class TreeStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int humidity;
    private int light;
    private int temperature;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
