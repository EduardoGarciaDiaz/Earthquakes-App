package com.example.earthquakes.earthquakes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.earthquakes.api.ApiClient;
import com.example.earthquakes.api.EarthquakeJSONResponse;
import com.example.earthquakes.api.Feature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Earthquake>> earthquakeList = new MutableLiveData<>();

    public LiveData<List<Earthquake>> getEarthquakeList(){
        return earthquakeList;
    }

    private MainRepository repository = new MainRepository();

    public void getEarthquakes(){
        repository.getEarthquakes(eqList -> {
            earthquakeList.setValue(eqList);
        });
    }


/*
    private List<Earthquake> getEarthquakesWithMoshi(EarthquakeJSONResponse body) {

        ArrayList<Earthquake> eqList = new ArrayList<>();

        List<Feature> features = body.getFeatures();

        for (Feature feature : features) {
            String id = feature.getId();
            double magnitude = feature.getProperties().getMagnitude();
            String place = feature.getProperties().getPlace();
            long time = feature.getProperties().getTime();

            double longitude = feature.getGeometry().getLongitude();
            double latitude = feature.getGeometry().getLatitude();

            Earthquake earthquake = new Earthquake(id, place, magnitude, time, latitude, longitude);

            eqList.add(earthquake);
        }

        return eqList;
    }

    //Acceder a los Servicios
    public void getEarthquakes() {
        ApiClient.Service service = ApiClient.getApiClient().getService();//
                //Con enqueue lo llamamos de forma Asincrona
        service.getEartquakes().enqueue(new Callback<EarthquakeJSONResponse>() {
            @Override
            public void onResponse(Call<EarthquakeJSONResponse> call,
                                   Response<EarthquakeJSONResponse> response) {
                List<Earthquake> eqList = getEarthquakesWithMoshi(response.body());//el response body es el que nos trae los datos
                earthquakeList.setValue(eqList);
            }

            @Override
            public void onFailure(Call<EarthquakeJSONResponse> call, Throwable t) {

            }
        });
    }

    private List<Earthquake> parseEartquake(String body) {
        ArrayList<Earthquake> eql = new ArrayList<>();

        try {
            //Aqui obtenemos el Objeto JSON completo
            JSONObject jsonResponse = new JSONObject(body);

            JSONArray featuresEaq = jsonResponse.getJSONArray("features");

            for (int i = 0; i < featuresEaq.length(); i++) {
                JSONObject jsonFeature = featuresEaq.getJSONObject(i);
                String id = jsonFeature.getString("id");

                JSONObject jsonProperties = jsonFeature.getJSONObject("properties");
                double magnitude = jsonProperties.getDouble("mag");
                String place = jsonProperties.getString("place");
                long time = jsonProperties.getLong("time");

                JSONObject jsonGeometry = jsonFeature.getJSONObject("geometry");
                JSONArray coord = jsonGeometry.getJSONArray("coordinates");
                double longitude = coord.getDouble(0);
                double latitude = coord.getDouble(1);

                eql.add(new Earthquake(id, place, magnitude, time, latitude, longitude));

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return eql;
    }
*/
}
