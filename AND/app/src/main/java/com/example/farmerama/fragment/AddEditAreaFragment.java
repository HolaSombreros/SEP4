package com.example.farmerama.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.farmerama.R;
import com.example.farmerama.data.model.Barn;
import com.example.farmerama.viewmodel.AddEditAreaViewModel;

import java.util.ArrayList;

public class AddEditAreaFragment extends Fragment {

    private AddEditAreaViewModel viewModel;
    private NavController navController;
    private Spinner barnSpinner;
    private EditText areaName;
    private EditText areaDescription;
    private EditText noOfPigs;
    private EditText hardwareId;
    private TextView title;
    private Button save;
    private Button remove;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_edit_area, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AddEditAreaViewModel.class);

        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        navController = Navigation.findNavController(view);
        areaName = view.findViewById(R.id.addArea_areaName);
        barnSpinner = view.findViewById(R.id.addArea_barnSpinner);
        areaDescription = view.findViewById(R.id.addArea_areaDescription);
        noOfPigs = view.findViewById(R.id.addArea_noPigs);
        hardwareId = view.findViewById(R.id.addArea_hardwareId);
        save = view.findViewById(R.id.addArea_createButton);
        title = view.findViewById(R.id.addArea_textView);
        remove = view.findViewById(R.id.addArea_removeButton);
    }

    private void setupViews() {
        areaName.requestFocus();

        viewModel.retrieveAllBarns();

        ArrayAdapter<Barn> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        viewModel.getBarns().observe(getViewLifecycleOwner(), barns -> {
            adapter.clear();
            adapter.addAll(barns);
            barnSpinner.setAdapter(adapter);
        });

        barnSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.setBarn(adapter.getItem(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (getArguments() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Edit area");
            title.setText("EDIT AREA");
            viewModel.getSpecificArea(getArguments().getInt("areaId")).observe(getViewLifecycleOwner(), area -> {
                area.setId(getArguments().getInt("areaId", 1));
                areaName.setText(area.getName());
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).equals(area.getBarn())) {
                        barnSpinner.setSelection(i);
                        break;
                    }
                }

                areaDescription.setText(area.getDescription());
                noOfPigs.setText(String.valueOf(area.getNoOfPigs()));
                hardwareId.setText(area.getHardwareId());
            });
        }

        save.setOnClickListener(v -> {
            if (getArguments() != null) {
                if (viewModel.editArea(
                        getArguments().getInt("areaId", 1),
                        areaName.getText().toString(),
                        areaDescription.getText().toString(),
                        noOfPigs.getText().toString(),
                        hardwareId.getText().toString())) {
                    Toast.makeText(getActivity(), "Area " + areaName.getText().toString() + " has been edited!", Toast.LENGTH_SHORT).show();
                    navController.popBackStack();
                }
            } else if (viewModel.createNewArea(areaName.getText().toString(), areaDescription.getText().toString(),
                    noOfPigs.getText().toString(), hardwareId.getText().toString())) {
                Toast.makeText(getActivity(), "Area " + areaName.getText().toString() + " has been created!", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        });

        viewModel.getLoggedInUser().observe(getViewLifecycleOwner(), user -> {
            if (user.getRole().equals("ADMINISTRATOR") && getArguments() != null)
                remove.setVisibility(View.VISIBLE);
            else
                remove.setVisibility(View.INVISIBLE);
        });

        AlertDialog.Builder deleteDialogBuilder = new AlertDialog.Builder(getActivity());
        deleteDialogBuilder.setMessage("Are you sure you want to delete this area?");
        deleteDialogBuilder.setPositiveButton("Yes", (dialogInterface, i) -> {
            viewModel.removeArea(getArguments().getInt("areaId"));
            navController.popBackStack();
        });
        deleteDialogBuilder.setNegativeButton("No", ((dialogInterface, i) -> {
        }));
        AlertDialog deleteDialog = deleteDialogBuilder.create();

        remove.setOnClickListener(v -> {
            deleteDialog.show();
        });
    }
}
