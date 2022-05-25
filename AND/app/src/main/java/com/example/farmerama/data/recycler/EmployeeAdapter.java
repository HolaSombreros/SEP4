package com.example.farmerama.data.recycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerama.R;
import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private List<User> userList;
    private UserRepository userRepository;

    public EmployeeAdapter(){
         this.userList = new ArrayList<>();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate (R.layout.item_employee, parent, false);
        userRepository = UserRepository.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        holder.name.setText(userList.get(position).getName());
        //TODO: need to get lifeCycleOwner such that it verifies the user loggedin and it doesn't allow to delete that one
        //TODO: if delete user with you are logged in = throws null exception error and app stops working.
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*      userRepository.deleteEmployeeById(userList.get(holder.getPosition()).getId());
                    userList.remove(holder.getPosition());*/
            }
        });
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
        }
    }
}
