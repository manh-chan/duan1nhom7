package com.example.duan1nhom7.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.duan1nhom7.R;
import com.example.duan1nhom7.SlideFragment;

public class ImageSliderAdapter extends FragmentPagerAdapter {

    private int[] images = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

    public ImageSliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SlideFragment.newInstance(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }
}