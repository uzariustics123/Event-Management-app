package com.macas.nmsc.sict.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macas.nmsc.sict.R;
import com.macas.nmsc.sict.databinding.FragmentOnboardSummaryBinding;

public class OnboardSummaryFrag extends Fragment {
 FragmentOnboardSummaryBinding binding;

    public OnboardSummaryFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOnboardSummaryBinding.inflate(inflater, container, false);
        initActivity();
        return binding.getRoot();
    }
    private void initActivity(){

    }
}