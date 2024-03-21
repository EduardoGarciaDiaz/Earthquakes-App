package com.example.earthquakes.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.earthquakes.Earthquake;
import com.example.earthquakes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.earthquakeRecycler.setLayoutManager(new LinearLayoutManager(this));

        MainViewModel viewModel = new ViewModelProvider(this,
                new MainViewModelFactory(getApplication()))
                .get(MainViewModel.class);

        binding.earthquakeRecycler.setLayoutManager(new LinearLayoutManager(this));

        EarthquakeAdapter adapter = new EarthquakeAdapter();
        adapter.setOnItemClickListener(earthquake ->
                Toast.makeText(MainActivity.this,
                        earthquake.getPlace(),
                        Toast.LENGTH_SHORT).show());

        binding.earthquakeRecycler.setAdapter(adapter);
        viewModel.downloadEartquakes();

        viewModel.getEarthquakesList().observe(this,eqList ->{
            adapter.submitList(eqList);
        });


    }
}