package com.example.earthquakes.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.earthquakes.Earthquake;
import com.example.earthquakes.database.EarthquakeDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final MainRepository repository;// = new MainRepository();

    public MainViewModel(@NonNull Application application) {
        super(application);
        EarthquakeDatabase database = EarthquakeDatabase.getDatabase(application);
        repository = new MainRepository(database);
    }

    public LiveData<List<Earthquake>> getEarthquakesList(){
        return repository.getEarthquakeList();
    }

    public void downloadEartquakes() {
        repository.downloadAndSaveEarthquakes();
    }

}
