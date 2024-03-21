package com.example.earthquakes.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakes.Earthquake;
import com.example.earthquakes.databinding.EarthquakeListItemBinding;

public class EarthquakeAdapter extends ListAdapter<Earthquake, EarthquakeAdapter.EarthquakeViewHolder> {

    public static final DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected EarthquakeAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnItemClickListener onItemClickListener;
    interface OnItemClickListener {
        void onItemClick(Earthquake earthquake);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener =  onItemClickListener;
    }



    @NonNull
    @Override
    public EarthquakeAdapter.EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EarthquakeListItemBinding binding = EarthquakeListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EarthquakeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeAdapter.EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bind(earthquake);
    }

    class EarthquakeViewHolder extends RecyclerView.ViewHolder {
        private final EarthquakeListItemBinding binding;

        public EarthquakeViewHolder(@NonNull EarthquakeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Earthquake earthquake) {
            binding.magnitudeText.setText(String.valueOf(earthquake.getMagnitude()));
            binding.placeText.setText(earthquake.getPlace());

            binding.getRoot().setOnClickListener( v -> {
                onItemClickListener.onItemClick(earthquake);
            });

            binding.executePendingBindings();
        }

    }
}
