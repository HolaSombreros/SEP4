package com.example.farmerama.fragment.pageadapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.farmerama.fragment.LatestMeasurementFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new LatestMeasurementFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
