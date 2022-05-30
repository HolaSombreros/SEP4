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
import com.example.farmerama.viewmodel.AccountViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private List<User> userList;
    private AccountViewModel viewModel;

    public EmployeeAdapter(){
         this.userList = new ArrayList<>();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate (R.layout.item_employee, parent, false);
        viewModel = new AccountViewModel();
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        holder.name.setText(userList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final Button delete;
        private EmployeeAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employeeName);
            delete = itemView.findViewById(R.id.deleteEmployee);

            if (viewModel.getUser().getValue().getRole().toUpperCase(Locale.ROOT).equals("ADMINISTRATOR"))
                    delete.setVisibility(View.VISIBLE);
            else delete.setVisibility(View.INVISIBLE);

            delete.setOnClickListener(v-> {
                if(userList.get(getAbsoluteAdapterPosition()).getUserId() != viewModel.getUser().getValue().getUserId()){
                    viewModel.deleteEmployeeById(userList.get(getAbsoluteAdapterPosition()).getUserId());
                    adapter.userList.remove(getAbsoluteAdapterPosition());
                    adapter.notifyItemRemoved(getAbsoluteAdapterPosition());
                }
            });
        }
        public ViewHolder linkAdapter(EmployeeAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }
}
