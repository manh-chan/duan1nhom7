package com.example.duan1nhom7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SlideFragment extends Fragment {
    private int imageResourceId;

    public SlideFragment() {
        // Required empty public constructor
    }

    public static SlideFragment newInstance(int imageResourceId) {
        SlideFragment fragment = new SlideFragment();
        Bundle args = new Bundle();
        args.putInt("imageResourceId", imageResourceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageResourceId = getArguments().getInt("imageResourceId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_slideshow, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(imageResourceId);
        return view;
    }
}