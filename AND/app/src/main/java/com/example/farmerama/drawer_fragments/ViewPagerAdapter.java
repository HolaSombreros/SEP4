package com.example.farmerama.drawer_fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.farmerama.drawer_fragments.LatestMeasurementFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new LatestMeasurementFragment(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
