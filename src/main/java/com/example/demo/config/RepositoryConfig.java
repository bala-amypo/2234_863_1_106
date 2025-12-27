
package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class RepositoryConfig {

    @Bean
    @Primary
    public LocationRepository locationRepository() {
        return new LocationRepository() {
            private final Map<Long, Location> data = new ConcurrentHashMap<>();
            private final AtomicLong idGen = new AtomicLong(1);

            public Location save(Location location) {
                if (location.getId() == null) location.setId(idGen.getAndIncrement());
                data.put(location.getId(), location);
                return location;
            }
            public Optional<Location> findById(Long id) { return Optional.ofNullable(data.get(id)); }
            public List<Location> findAll() { return new ArrayList<>(data.values()); }
            public Optional<Location> findByLocationName(String name) {
                return data.values().stream().filter(l -> Objects.equals(l.getLocationName(), name)).findFirst();
            }
            public List<Location> findByRegion(String region) {
                return data.values().stream().filter(l -> Objects.equals(l.getRegion(), region)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            }
        };
    }

    @Bean
    @Primary
    public SensorRepository sensorRepository() {
        return new SensorRepository() {
            private final Map<Long, Sensor> data = new ConcurrentHashMap<>();
            private final AtomicLong idGen = new AtomicLong(1);

            public Sensor save(Sensor sensor) {
                if (sensor.getId() == null) sensor.setId(idGen.getAndIncrement());
                data.put(sensor.getId(), sensor);
                return sensor;
            }
            public Optional<Sensor> findById(Long id) { return Optional.ofNullable(data.get(id)); }
            public List<Sensor> findAll() { return new ArrayList<>(data.values()); }
            public Optional<Sensor> findBySensorCode(String code) {
                return data.values().stream().filter(s -> Objects.equals(s.getSensorCode(), code)).findFirst();
            }
            public List<Sensor> findByLocation_Region(String region) {
                return data.values().stream().filter(s -> s.getLocation() != null && Objects.equals(s.getLocation().getRegion(), region)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            }
        };
    }

    @Bean
    @Primary
    public ComplianceThresholdRepository complianceThresholdRepository() {
        return new ComplianceThresholdRepository() {
            private final Map<Long, ComplianceThreshold> data = new ConcurrentHashMap<>();
            private final AtomicLong idGen = new AtomicLong(1);

            public ComplianceThreshold save(ComplianceThreshold threshold) {
                if (threshold.getId() == null) threshold.setId(idGen.getAndIncrement());
                data.put(threshold.getId(), threshold);
                return threshold;
            }
            public Optional<ComplianceThreshold> findById(Long id) { return Optional.ofNullable(data.get(id)); }
            public List<ComplianceThreshold> findAll() { return new ArrayList<>(data.values()); }
            public Optional<ComplianceThreshold> findBySensorType(String type) {
                return data.values().stream().filter(t -> Objects.equals(t.getSensorType(), type)).findFirst();
            }
        };
    }

    @Bean
    @Primary
    public SensorReadingRepository sensorReadingRepository() {
        return new SensorReadingRepository() {
            private final Map<Long, SensorReading> data = new ConcurrentHashMap<>();
            private final AtomicLong idGen = new AtomicLong(1);

            public SensorReading save(SensorReading reading) {
                if (reading.getId() == null) reading.setId(idGen.getAndIncrement());
                data.put(reading.getId(), reading);
                return reading;
            }
            public Optional<SensorReading> findById(Long id) { return Optional.ofNullable(data.get(id)); }
            public List<SensorReading> findAll() { return new ArrayList<>(data.values()); }
            public List<SensorReading> findBySensor_Id(Long sensorId) {
                return data.values().stream().filter(r -> r.getSensor() != null && Objects.equals(r.getSensor().getId(), sensorId)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            }
            public List<SensorReading> findBySensor_IdAndReadingTimeBetween(Long sensorId, LocalDateTime start, LocalDateTime end) {
                return data.values().stream().filter(r -> r.getSensor() != null && Objects.equals(r.getSensor().getId(), sensorId) && r.getReadingTime() != null && !r.getReadingTime().isBefore(start) && !r.getReadingTime().isAfter(end)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            }
        };
    }

    @Bean
    @Primary
    public ComplianceLogRepository complianceLogRepository() {
        return new ComplianceLogRepository() {
            private final Map<Long, ComplianceLog> data = new ConcurrentHashMap<>();
            private final AtomicLong idGen = new AtomicLong(1);

            public ComplianceLog save(ComplianceLog log) {
                if (log.getId() == null) log.setId(idGen.getAndIncrement());
                data.put(log.getId(), log);
                return log;
            }
            public Optional<ComplianceLog> findById(Long id) { return Optional.ofNullable(data.get(id)); }
            public List<ComplianceLog> findAll() { return new ArrayList<>(data.values()); }
            public List<ComplianceLog> findBySensorReading_Id(Long readingId) {
                return data.values().stream().filter(l -> l.getSensorReading() != null && Objects.equals(l.getSensorReading().getId(), readingId)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            }
        };
    }

    @Bean
    @Primary
    public UserRepository userRepository() {
        return new UserRepository() {
            private final Map<Long, User> data = new ConcurrentHashMap<>();
            private final AtomicLong idGen = new AtomicLong(1);

            public User save(User user) {
                if (user.getId() == null) user.setId(idGen.getAndIncrement());
                data.put(user.getId(), user);
                return user;
            }
            public Optional<User> findById(Long id) { return Optional.ofNullable(data.get(id)); }
            public List<User> findAll() { return new ArrayList<>(data.values()); }
            public Optional<User> findByEmail(String email) {
                return data.values().stream().filter(u -> Objects.equals(u.getEmail(), email)).findFirst();
            }
        };
    }
}
