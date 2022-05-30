package com.example.farmerama.fragment.pageadapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.farmerama.fragment.IntroFragment;

public class IntroPagerAdapter extends FragmentStateAdapter {

    public IntroPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new IntroFragment(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
