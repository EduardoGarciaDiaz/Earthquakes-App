package com.example.earthquakes.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

//Aqui va to do lo de Retrofit
public class ApiClient {

    private static final ApiClient apiClient = new ApiClient();
    //Singleton
    public static ApiClient getApiClient(){ //getInstance
        return apiClient;
    }

    private ApiClient(){
    }

    public interface Service {
        @GET("all_hour.geojson")
        Call<EarthquakeJSONResponse> getEartquakes();
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private Service service;

    //Singleton
    public Service getService() {
        if (service==null){
            service = retrofit.create(Service.class);
        }
        return service;
    }


}
