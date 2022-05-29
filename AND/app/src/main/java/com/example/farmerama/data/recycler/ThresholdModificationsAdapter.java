package com.example.farmerama.data.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.ThresholdModification;

import java.util.ArrayList;
import java.util.List;

public class ThresholdModificationsAdapter extends RecyclerView.Adapter<ThresholdModificationsAdapter.ViewHolder>{

    private List<ThresholdModification> list;

    public ThresholdModificationsAdapter() {
        list = new ArrayList<>();
    }

    public void setModifications(List<ThresholdModification> measurements) {
        this.list = measurements;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_threshold_modification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.employee.setText(list.get(position).getUser().getUserName());
        holder.area.setText(list.get(position).getThreshold().getArea().getAreaName());
        holder.measurementType.setText(list.get(position).getThreshold().getType());
        holder.minMax.setText(list.get(position).getLogType().getLogType());
        holder.oldNewValue.setText(String.format("%.2f -> %.2f", list.get(position).getOldValue(), list.get(position).getNewValue()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView employee;
        private TextView area;
        private TextView measurementType;
        private TextView minMax;
        private TextView oldNewValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            employee = itemView.findViewById(R.id.thresholdModification_employee);
            area = itemView.findViewById(R.id.thresholdModification_area);
            measurementType = itemView.findViewById(R.id.thresholdModification_measurementType);
            minMax = itemView.findViewById(R.id.thresholdModification_minMax);
            oldNewValue = itemView.findViewById(R.id.thresholdModification_oldNew);
        }
    }
}
