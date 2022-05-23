package com.example.farmerama.fragment.pageadapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.farmerama.fragment.LogsMeasurementsFragment;

public class LogsPagerAdapter extends FragmentStateAdapter {

    public LogsPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new LogsMeasurementsFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
