package com.example.iotdemo.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TreeStatusWithTime {
    private int humidity;
    private float temperature;
    private String createdAt;
}
