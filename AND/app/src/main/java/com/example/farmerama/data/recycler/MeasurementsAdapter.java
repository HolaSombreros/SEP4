package com.example.farmerama.data.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.Measurement;

import java.util.ArrayList;
import java.util.List;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.ViewHolder>{

    private List<Measurement> list;

    public void setMeasurements(List<Measurement> measurements) {
        this.list = measurements;
        notifyDataSetChanged();
    }
    public MeasurementsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_measurement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.measurement.setText(String.valueOf(list.get(position).getValue()));
        holder.measurementType.setText(list.get(position).getMeasurementType().toUnit());
        holder.date.setText(String.valueOf(list.get(position).getDateTime()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView measurement;
        private final TextView measurementType;
        private final EditText date;

        public ViewHolder( View itemView) {
            super(itemView);
            measurement = itemView.findViewById(R.id.measurement_value);
            measurementType = itemView.findViewById(R.id.measurement_type);
            date = itemView.findViewById(R.id.measurement_date);
        }
    }
}
