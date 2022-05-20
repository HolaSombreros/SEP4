package com.example.farmerama.fragment.pageadapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.farmerama.fragment.ThresholdMeasurementsFragment;

public class ThresholdAdapter extends FragmentStateAdapter {

    public ThresholdAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ThresholdMeasurementsFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
