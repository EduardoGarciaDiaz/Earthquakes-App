package com.example.earthquakes.api;

import com.squareup.moshi.Json;

@Json(name="Properties")
public class Property {
    @Json(name="mag")
    private double magnitude;
    private String place;
    private long time;

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }
}
