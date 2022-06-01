package com.example.farmerama.data.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private List<User> userList;
    private onDeleteListener onDeleteListener;


    public EmployeeAdapter(){
         this.userList = new ArrayList<>();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public void setOnDeleteListener(onDeleteListener onDeleteListener) {
      this.onDeleteListener = onDeleteListener;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate (R.layout.item_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        holder.name.setText(userList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employeeName);
            delete = itemView.findViewById(R.id.deleteEmployee);

            delete.setOnClickListener(v -> onDeleteListener.onDelete(userList.get(getBindingAdapterPosition())));
        }
    }

    public interface onDeleteListener
    {
        void onDelete(User user);
    }

}
