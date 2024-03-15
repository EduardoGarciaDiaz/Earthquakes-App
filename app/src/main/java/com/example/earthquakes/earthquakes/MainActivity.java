package com.example.earthquakes.earthquakes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.earthquakes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.earthquakeRecycler.setLayoutManager(new LinearLayoutManager(this));

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getEarthquakeList().observe(this, eqList->{
            for (Earthquake eq : eqList) {
                Log.d("eq", eq.getMagnitude() + " " + eq.getPlace());
            }
        });
        viewModel.getEarthquakes();



        //MADE BY ME
        EarthquakeAdapter adapter = new EarthquakeAdapter();
        binding.earthquakeRecycler.setAdapter(adapter);

        viewModel.getEarthquakeList().observe(this, eqList->{
            adapter.submitList(eqList);
        });

    }
}