package com.example.earthquakes.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// Fabrica de ViewModel que crea instancias de MainViewModel
public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public MainViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(application);
    }

}
