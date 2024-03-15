package com.example.earthquakes.earthquakes;

import com.example.earthquakes.api.ApiClient;
import com.example.earthquakes.api.EarthquakeJSONResponse;
import com.example.earthquakes.api.Feature;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Con el repositorio podemos hacer que toda la recuperación este aqui, y no en el viewmodel
//En el view model solo debe estar lo suyo, esto no
public class MainRepository {
    public interface DownloadEqsListener{
        void onEqsDownloaded(List<Earthquake> eqList);
    }

    private List<Earthquake> getEarthquakesWithMoshi(EarthquakeJSONResponse body) {
        ArrayList<Earthquake> eqList = new ArrayList<>();
        List<Feature> features = body.getFeatures();

        for (Feature feature : features){
            String id = feature.getId();
            double magnitude = feature.getProperties().getMagnitude();
            String place = feature.getProperties().getPlace();
            long time = feature.getProperties().getTime();

            double longitude = feature.getGeometry().getLongitude();
            double latitude = feature.getGeometry().getLatitude();

            Earthquake earthquake = new Earthquake(id, place, magnitude, time,
                    latitude, longitude);
            eqList.add(earthquake);

        }
        return eqList;
    }

    public void getEarthquakes(DownloadEqsListener downloadEqsListener) {
        ApiClient.Service service = ApiClient.getApiClient().getService();
        service.getEartquakes().enqueue(new Callback<EarthquakeJSONResponse>() {
            @Override
            public void onResponse(Call<EarthquakeJSONResponse> call, Response<EarthquakeJSONResponse> response) {
                List<Earthquake> earthquakeList = getEarthquakesWithMoshi(response.body());
                downloadEqsListener.onEqsDownloaded(earthquakeList);
            }

            @Override
            public void onFailure(Call<EarthquakeJSONResponse> call, Throwable t) {

            }
        });
    }
}
