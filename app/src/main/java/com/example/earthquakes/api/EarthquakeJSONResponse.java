package com.example.earthquakes.api;

import java.util.List;

//  clase que permita obtener la respuesta del API,
public class EarthquakeJSONResponse {
    private List<Feature> features;
    public List<Feature> getFeatures() {
        return features;
    }

}
