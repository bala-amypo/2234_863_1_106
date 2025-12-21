package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sensor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sensorCode;
    private String sensorType;
    private boolean isActive = true;
    private LocalDateTime installedAt;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}