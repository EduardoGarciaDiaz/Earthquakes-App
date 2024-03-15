package com.example.earthquakes.api;

public class Feature {
    private String id;
    private Property properties;
    private Geometry geometry;

    public String getId() {
        return id;
    }

    public Property getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
