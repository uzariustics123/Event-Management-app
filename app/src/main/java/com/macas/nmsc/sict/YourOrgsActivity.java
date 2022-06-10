package com.macas.nmsc.sict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.macas.nmsc.sict.HomeActivity;
import com.macas.nmsc.sict.YourOrgsActivity;
import com.macas.nmsc.sict.databinding.ListItemholderBinding;
import com.macas.nmsc.sict.databinding.YourOrgsBinding;
import com.macas.nmsc.sict.useractivities.OnboardActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class YourOrgsActivity extends AppCompatActivity {

    SharedPreferences sharedprefs;
    YourOrgsBinding binding;
    LottieAnimationView lottie;

    ArrayList<HashMap<String, Object>> orgs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedprefs = getSharedPreferences("sharedprefs", Activity.MODE_PRIVATE);
        if (sharedprefs.getBoolean("darkmode", false)) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            changeBarColors(0x00000000, false);
        } else {
            changeBarColors(0xFFFFFFFF, true);
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
        binding = YourOrgsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getOrgslist();
        initDarkMode();
        binding.menuBtn.setOnClickListener(
                (view) -> {
                    binding.getRoot().openDrawer(GravityCompat.START);
                });
    }

    private void changeBarColors(int color, boolean islight) {

        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            WindowInsetsControllerCompat wic = new WindowInsetsControllerCompat(window, decorView);

            wic.setAppearanceLightStatusBars(islight); // true or false as desired.

            // And then you can set any background color to the status bar.
            window.setStatusBarColor(color);
        } else {

        }

        // window.setNavigationBarColor(getColor(R.color.backgroundColor));
    }

    public void initDarkMode() {
        ImageButton darkbtn = findViewById(R.id.night);
        binding.notifs.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        BitmapHolder.draws(binding.coordinator);

                        Intent intent = new Intent(YourOrgsActivity.this, TransistActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("cx", (int) (view.getX() + view.getWidth() / 2));
                        intent.putExtra("cy", (int) (view.getY() + view.getHeight() / 2));

                        intent.putExtra("radius", (view.getWidth()));
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        if (sharedprefs.getBoolean("darkmode", false)) {
                            sharedprefs.edit().remove("darkmode").commit();
                        } else {
                            sharedprefs.edit().putBoolean("darkmode", true).commit();
                        }
                        new Handler(Looper.getMainLooper())
                                .postDelayed(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                recreate();
                                            }
                                        },
                                        100);
                    }
                });
    }

    public void showErrorAnim() {
        binding.lottie1.playAnimation();
    }

    public void getOrgslist() {
        {
            HashMap<String, Object> org = new HashMap<>();
            org.put("name", "Northwester Mindanao State College of Science and Technology");
            org.put("address", "Labuyo, Tangub City, Misamis Occidental");
            org.put(
                    "imgurl",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqrP1rOI-ersxT7ikaRIeOsd6mS_IA-giOnfiCdZ08nXiTbFEr7DXmERnZ&s=10");
            org.put("abrev", "NMSC");
            org.put("tags", "School");
            orgs.add(org);
        }
        {
            HashMap<String, Object> org = new HashMap<>();
            org.put("name", "Sinanduloy Cultural Troupe");
            org.put("address", "City Plaza of Tangub");
            org.put("imgurl",
                    "https://lh3.googleusercontent.com/p/AF1QipMJYk-GM0yUTbW9dyfcvcRDsAIdze0thY0Sz6VO=s1378-w786-h1378");
            org.put("abrev", "SCT");
            org.put("tags", "Organization");
            orgs.add(org);
        }
        {
            HashMap<String, Object> org = new HashMap<>();
            org.put("name", "Sinanduloy Cultural Troupe");
            org.put("address", "City Plaza of Tangub");
            org.put("imgurl",
                    "https://lh3.googleusercontent.com/p/AF1QipMJYk-GM0yUTbW9dyfcvcRDsAIdze0thY0Sz6VO=s1378-w786-h1378");
            org.put("abrev", "SCT");
            org.put("tags", "Organization");
            orgs.add(org);
        }
        {
            HashMap<String, Object> org = new HashMap<>();
            org.put("name", "Sinanduloy Cultural Troupe");
            org.put("address", "City Plaza of Tangub");
            org.put("imgurl",
                    "https://lh3.googleusercontent.com/p/AF1QipMJYk-GM0yUTbW9dyfcvcRDsAIdze0thY0Sz6VO=s1378-w786-h1378");
            org.put("abrev", "SCT");
            org.put("tags", "Organization");
            orgs.add(org);
        }
        {
            HashMap<String, Object> org = new HashMap<>();
            org.put("name", "Sinanduloy Cultural Troupe");
            org.put("address", "City Plaza of Tangub");
            org.put("imgurl",
                    "https://lh3.googleusercontent.com/p/AF1QipMJYk-GM0yUTbW9dyfcvcRDsAIdze0thY0Sz6VO=s1378-w786-h1378");
            org.put("abrev", "SCT");
            org.put("tags", "Organization");
            orgs.add(org);
        }
        OrgListAdapter adapter = new OrgListAdapter(orgs);
        binding.orgrecyc.setAdapter(adapter);
        binding.orgrecyc.setLayoutManager(new LinearLayoutManager(this));
    }

    public class OrgListAdapter extends RecyclerView.Adapter<OrgListAdapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        ListItemholderBinding binding;

        public OrgListAdapter(ArrayList<HashMap<String, Object>> data) {
            this.data = data;
            int[] attrsArray =
                    new int[]{android.R.attr.layout_width, android.R.attr.layout_height};
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            binding = ListItemholderBinding.inflate(LayoutInflater.from(parent.getContext()));
            RecyclerView.LayoutParams lp =
                    new RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.getRoot().setLayoutParams(lp);
            return new ViewHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(ViewHolder _holder, final int position) {
            View rootView = _holder.itemView;
            rootView.setTransitionName("transOrg");
            String name = data.get(position).get("name").toString();
            String imgurl = data.get(position).get("imgurl").toString();
            String addr = data.get(position).get("address").toString();
            rootView.setOnClickListener(
                    (view) -> {
                        Intent i = new Intent(getBaseContext(), OnboardActivity.class);
//                        ActivityOptionsCompat options =
//                                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                        YourOrgsActivity.this, rootView, "transOrg");

                        i.putExtra("Orgname", name);
                        startActivity(i);
                    });
            TextView title = rootView.findViewById(R.id.title);
            binding.title.setText(name);
            binding.descriptiontxt.setText(addr);
            // TypedArray st = getBaseContext().obtainStyledAttributes(,);
            Glide.with(getBaseContext()).load(imgurl).into(binding.image);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }
    }
}
