package com.example.demo.entity;

public class Location {
    private Long id;
    private String locationName;
    private String region;
    
    public Location() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}