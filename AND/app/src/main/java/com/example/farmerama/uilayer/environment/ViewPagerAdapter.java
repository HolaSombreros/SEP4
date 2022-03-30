package com.example.farmerama.uilayer.environment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentActivity fa) {
        super(fa);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LatestTemperatureFragment();
            case 1:
                return new LatestHumidityFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
