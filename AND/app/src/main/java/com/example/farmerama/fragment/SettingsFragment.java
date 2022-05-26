package com.example.farmerama.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;

import com.example.farmerama.R;
import com.example.farmerama.viewmodel.SettingsViewModel;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SettingsViewModel viewModel;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(SettingsViewModel.class);

        Preference storagePreference = findPreference("removeStorage");

        storagePreference.setOnPreferenceClickListener(pref -> {
            AlertDialog.Builder deleteDialogBuilder = new AlertDialog.Builder(getActivity());
            deleteDialogBuilder.setMessage("Are you sure you want to delete this area?");
            deleteDialogBuilder.setPositiveButton("Yes", (dialogInterface, i) -> {
                viewModel.removeLocalData();
            });
            deleteDialogBuilder.setNegativeButton("No", ((dialogInterface, i) -> {
            }));
            AlertDialog deleteDialog = deleteDialogBuilder.create();
            deleteDialog.show();
            return true;
        });

        SwitchPreference notificationPreference = findPreference("notification");

        notificationPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            viewModel.setGettingNotifications((boolean) newValue);
            return true;
        });
    }
}
