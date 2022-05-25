package com.example.farmerama.data.recycler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.LogObj;

import java.util.ArrayList;
import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {

    private List<LogObj> logs;

    public LogsAdapter() {
        logs = new ArrayList<>();
    }

    @NonNull
    @Override
    public LogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_log, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsAdapter.ViewHolder holder, int position) {
        holder.thresholdValue.setText(String.valueOf(logs.get(position).getThresholdValue()));
        holder.exceededValue.setText(String.valueOf(logs.get(position).getExceededValue()));
        holder.date.setText(String.valueOf(logs.get(position).getMeasuredDate()));
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView thresholdValue;
        private final TextView exceededValue;
        private final EditText date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thresholdValue = itemView.findViewById(R.id.threshold_value_item);
            exceededValue = itemView.findViewById(R.id.exceeded_item);
            date = itemView.findViewById(R.id.log_date_item);
        }
    }

    public void setLogs(List<LogObj> logs) {
        this.logs=logs;
        notifyDataSetChanged();
    }
}
