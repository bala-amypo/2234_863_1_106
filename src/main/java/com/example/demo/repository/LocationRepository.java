package com.example.demo.repository;

import com.example.demo.entity.Location;
import java.util.Optional;
import java.util.List;

public interface LocationRepository {
    Location save(Location location);
    Optional<Location> findById(Long id);
    List<Location> findAll();
    Optional<Location> findByLocationName(String locationName);
    List<Location> findByRegion(String region);
}