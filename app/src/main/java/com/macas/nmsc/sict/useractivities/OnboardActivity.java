package com.macas.nmsc.sict.useractivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.macas.nmsc.sict.Fragments.MainActivityFragment;
import com.macas.nmsc.sict.R;
import com.macas.nmsc.sict.databinding.ActivityOnboardBinding;

public class OnboardActivity extends AppCompatActivity {
    ActivityOnboardBinding binding;
    SearchEventFragmentActivity searchEvent;
    FragmentManager fragmentManager;
    MainActivityFragment mainActivityFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentManager = getSupportFragmentManager();
        mainActivityFragment = new MainActivityFragment();
        searchEvent = new SearchEventFragmentActivity();
        changeBarColors();
        initMainFragment();
        binding.searchedtxt.setOnClickListener(view -> {
            launchSeachEventScreen();
        });
    }
    public void launchSeachEventScreen(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(android.R.id.content, searchEvent).addToBackStack(null).commit();
    }
    public void initMainFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(binding.layoutSwap.getId(), mainActivityFragment).commitAllowingStateLoss();
    }
























    private void changeBarColors() {

        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            w.setStatusBarColor(getColor(R.color.backgroundColor));
        }
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            WindowInsetsControllerCompat wic = new WindowInsetsControllerCompat(window, decorView);

            wic.setAppearanceLightStatusBars(true); // true or false as desired.

            // And then you can set any background color to the status bar.
            window.setStatusBarColor(getColor(R.color.backgroundColor));
        } else {

        }

        // window.setNavigationBarColor(getColor(R.color.backgroundColor));
    }
}