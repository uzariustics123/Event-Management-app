package com.macas.nmsc.sict.adapters;

import android.os.*;
import android.util.Log;
import android.view.View.*;
import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.macas.nmsc.sict.Fragments.BlankFragment;
import com.macas.nmsc.sict.Fragments.OnboardSummaryFrag;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Lifecycle;

import java.util.ArrayList;

public class MainFragmentPager extends FragmentStateAdapter {
    Context context;
    int tabCount;

    public MainFragmentPager( FragmentManager fm, Context context, Lifecycle lifecycle, int tabCount) {
        super(fm, lifecycle);
        this.tabCount = tabCount;
        this.context = context;
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                new OnboardSummaryFrag();
            case 1:
                //return new SecondAnim();
            case 2:

            default:
                return new BlankFragment();
        }
    }

    @Override
    public int getItemCount() {
        return tabCount;
    }

}
