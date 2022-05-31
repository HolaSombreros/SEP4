package com.example.farmerama.data.recycler;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.util.DateFormatter;

import java.util.ArrayList;
import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {

    private List<ExceededLog> logs;

    public LogsAdapter() {
        logs = new ArrayList<>();
    }

    public void setLogs(List<ExceededLog> logs) {
        this.logs=logs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_log, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LogsAdapter.ViewHolder holder, int position) {
        holder.thresholdValue.setText("Threshold: " + logs.get(position).getThresholdValue());
        holder.exceededValue.setText("Exceeded: " + logs.get(position).getExceededValue());
        holder.date.setText(DateFormatter.formatDate(logs.get(position).getMeasuredDate().toString()));
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
}
