package com.example.farmerama.fragment.pageadapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.farmerama.fragment.IntroFragment;

public class IntroPagerAdapter extends FragmentPagerAdapter {

    IntroFragment page1, page2, page3;

    public IntroPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        page1 = new IntroFragment() ;
        page1.setCurPos ( 1 ) ;
        page2 = new IntroFragment() ;
        page2.setCurPos ( 2 ) ;
        page3 = new IntroFragment() ;
        page3.setCurPos ( 3 ) ;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return page1;
            case 1: return page2;
            case 2: return page3;
            default: return null;
        }    }

    @Override
    public int getCount() {
        return 3;
    }
}
