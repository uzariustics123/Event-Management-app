package com.macas.nmsc.sict;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.example.moeidbannerlibrary.banner.BannerLayout;
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.macas.nmsc.sict.AddNewEventDialog;
import com.macas.nmsc.sict.MainActivity;
import com.macas.nmsc.sict.databinding.ActivityMainBinding;
import androidx.appcompat.app.ActionBarDrawerToggle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DrawerLayout mdrawer;
    FragmentManager fragmentManager;
    AddNewEventDialog addNewEventDialog;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate and get instance of binding
        fragmentManager = getSupportFragmentManager();
        addNewEventDialog = new AddNewEventDialog();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mdrawer = binding.getRoot();
        setContentView(binding.getRoot());
        /*setSupportActionBar(binding.toolbar);
            ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(
                this, binding.getRoot(), binding.toolbar, R.string.app_name, R.string.app_name);
        binding.getRoot().setDrawerListener(toggle);
        toggle.syncState();*/
        // mdrawer.openDrawer(GravityCompat.START);
        binding.menuBtn.setOnClickListener((view) ->{
                        binding.getRoot().openDrawer(GravityCompat.START);
                    }
                );
        // Glide.with(this).load("https://www.nmsc.edu.ph/application/files/9716/4550/5545/darilag_2.jpg").into(binding.sample);
        binding.fab.setOnClickListener((view) -> {
            createEvent();
            }
                );
                

        changeBarColors();
        setUpTabs();
        getBanners();

        // Use lambdas
        // binding.fab.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Replace with your
        // action", Toast.LENGTH_SHORT).show());
    }

    private void changeBarColors() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow();
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            w.setStatusBarColor(getColor(R.color.backgroundColor));
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            WindowInsetsControllerCompat wic = new WindowInsetsControllerCompat(window, decorView);

            wic.setAppearanceLightStatusBars(true); // true or false as desired.
            // And then you can set any background color to the status bar.
            window.setStatusBarColor(getColor(R.color.backgroundColor));
        } else {

        }

        window.setNavigationBarColor(getColor(R.color.backgroundColor));
    }

    public void setUpTabs() {
        TabLayout tbl = binding.tablayout;
        tbl.addTab(tbl.newTab().setText("All Events"));
        tbl.addTab(tbl.newTab().setText("Participated"));
        tbl.addTab(tbl.newTab().setText("Organized"));
        tbl.addTab(tbl.newTab().setText("Evaluation"));
        // tbl.addTab(tbl.newTab().setText("Evaluated"));

    }

    public void getBanners() {
        binding.banner.setVisibility(View.VISIBLE);
        List<String> urls = new ArrayList<>();
        urls.add(
                "https://www.nmsc.edu.ph/application/files/7316/4610/1470/2022_NWMC_Streamer_11x4ft.jpg");
        urls.add(
                "https://www.nmsc.edu.ph/application/files/5716/4748/1815/Blue_Generals_Welcome.jpg");
        urls.add("https://www.nmsc.edu.ph/application/files/9716/4550/5545/darilag_2.jpg");
        urls.add("https://www.nmsc.edu.ph/application/files/4016/1743/4976/level3.jpg");

        BannerAdapter webBannerAdapter = new BannerAdapter(this, urls);
        webBannerAdapter.setOnBannerItemClickListener(
                new BannerLayout.OnBannerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // binding.banner.scrollTo(position);
                    }
                });
        binding.banner.setAdapter(webBannerAdapter);
    }

    public void toastit(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void createEvent() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
       transaction.add(android.R.id.content, addNewEventDialog).addToBackStack(null).commit();
    }
}
