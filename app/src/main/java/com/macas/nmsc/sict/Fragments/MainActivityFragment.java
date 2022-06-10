package com.macas.nmsc.sict.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.macas.nmsc.sict.R;
import com.macas.nmsc.sict.adapters.MainFragmentPager;
import com.macas.nmsc.sict.databinding.FragmentMainActivityBinding;

public class MainActivityFragment extends Fragment {
FragmentMainActivityBinding binding;
ViewPager2 vpager;
TabLayout tbl;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainActivityBinding.inflate(inflater, container, false);
        vpager = binding.viewpager;
        tbl = binding.tabLayout;
        return binding.getRoot();
    }
    public void initActivity(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        vpager.setAdapter(new MainFragmentPager(fragmentManager, getActivity(),getLifecycle(),4));
        new TabLayoutMediator(
                tbl,
                vpager,
                (tab, position) -> {

                    if (position == 0) {
                        tab.setText(R.string.tab_campus_title);
                    } else if (position == 1) {
                        tab.setText(R.string.my_department);
                    } else if (position == 2) {
                        tab.setText(R.string.tab_notif_title);
                    }
                })
                .attach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initActivity();
    }
}