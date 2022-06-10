package com.macas.nmsc.sict.PagerAnims;

import android.os.*;
import android.util.Log;
import android.view.View.*;
import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

public class IntroPagersAdapter extends FragmentStateAdapter {
    Context context;
    int tabCount;
    
    public IntroPagersAdapter(Context context,FragmentManager fm, Lifecycle lifecycle, int tabCount) {
        super(fm, lifecycle);
        this.tabCount = tabCount;
        this.context = context;
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                //return new FirstAnim();
            case 1:
                //return new SecondAnim();
            case 2:
                //return new ThirdAnim();
            default:
                return null;
        }
    }
   
    @Override
    public int getItemCount() {
        return tabCount;
    }
    
}









