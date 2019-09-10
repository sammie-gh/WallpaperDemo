package com.gh.sammie.livewallpaper.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gh.sammie.livewallpaper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {

    private static RecentFragment INSTANCE =null;

    public RecentFragment() {

    }


    public  static RecentFragment getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new RecentFragment();
        return INSTANCE;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
