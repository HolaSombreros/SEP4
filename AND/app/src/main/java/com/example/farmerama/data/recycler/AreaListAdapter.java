package com.example.farmerama.data.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.farmerama.R;
import com.example.farmerama.data.model.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaListAdapter extends RecyclerView.Adapter<AreaListAdapter.ViewHolder>
{
    private List<Area> areas;
    private OnClickListener<Area> listener;

    public AreaListAdapter() {
        areas = new ArrayList<>();
    }

    @NonNull
    @Override
    public AreaListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_area, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaListAdapter.ViewHolder holder, int position) {
        holder.areaName.setText(areas.get(position).getAreaName());
        holder.barnName.setText(areas.get(position).getBarn().getName());
        holder.nrOfPigs.setText(String.valueOf(areas.get(position).getNoOfPigs()));
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView areaName;
        private TextView barnName;
        private TextView nrOfPigs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            areaName = itemView.findViewById(R.id.areaItem_name);
            barnName = itemView.findViewById(R.id.areaItem_barnName);
            nrOfPigs = itemView.findViewById(R.id.areaItem_noOfPigs);

            itemView.setOnClickListener(v-> {
                listener.onClick(areas.get(getBindingAdapterPosition()));
            });
        }
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
        notifyDataSetChanged();
    }
}
