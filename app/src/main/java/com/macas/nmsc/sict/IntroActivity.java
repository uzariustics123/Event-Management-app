package com.macas.nmsc.sict;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.macas.nmsc.sict.databinding.IntroActivityBinding;
import com.airbnb.lottie.LottieAnimationView;

public class IntroActivity extends AppCompatActivity {
    IntroActivityBinding binding;
    View mContentView;
    private LottieAnimationView lottie1;
    private LottieAnimationView lottie2;
    private LottieAnimationView lottie3;
    private LottieAnimationView lottie4;
    private TextView introtxt;
    private ViewPager2 viewpager;
    private Handler handler;
    SharedPreferences sharedprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = IntroActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContentView = binding.getRoot();
        handler = new Handler(getMainLooper());
        sharedprefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        // binding.txtgreet.setText("greetings");
        introtxt = findViewById(R.id.actTitles);
        changeBarColors();
        // configBars();
        // revisited();
        setUpPagers();
        binding.fabnxtstrt.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        if (viewpager.getCurrentItem() < 3) {
                            viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
                        } else {
                            Intent i = new Intent(IntroActivity.this, LogInActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
        binding.fabprev.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        if (viewpager.getCurrentItem() > 0) {
                            viewpager.setCurrentItem(viewpager.getCurrentItem() - 1);
                        }
                    }
                });
    }

    public void setUpPagers() {
        String[] anims = {""};

        viewpager = findViewById(R.id.viewpager1);
        viewpager.setAdapter(new AnimPagers(anims));
        viewpager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageScrolled(
                            int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        String msg = "";
                        if (position == 0) {
                            msg =
                                    "Manage events for your organization or school easily, with your smartphone.";
                            lottie1.playAnimation();
                            binding.fabprev.hide();
                        } else if (position == 1) {
                            msg =
                                    "There's no need to stand in line for an extended period of time just to acquire your attendance.";
                            binding.fabprev.show();
                            handler.postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            lottie2.playAnimation();
                                        }
                                    },
                                    50);

                        } else if (position == 2) {
                            msg =
                                    "Get your attendance by scanning QR code. It is fast, simple, and no hassles.";
                            handler.postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            lottie3.playAnimation();
                                        }
                                    },
                                    50);
                            binding.fabnxtstrt.setText("NEXT");
                        } else if (position == 3) {
                            msg =
                                    "View each Event's statistics. Attendance, Event Attendees, and Evaluation are all in your hands.";
                            binding.fabnxtstrt.setText("START");
                            handler.postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            lottie4.playAnimation();
                                        }
                                    },
                                    50);
                        }
                        introtxt.setText(msg);
                    }
                });
    }

    public void revisited() {
        if (sharedprefs.getBoolean("revisited", false)) {
            Intent i = new Intent(this, LogInActivity.class);
            startActivity(i);
            finish();
        } 
    }

    public void configBars() {
        if (Build.VERSION.SDK_INT >= 30) {
            mContentView.getWindowInsetsController().hide(WindowInsets.Type.navigationBars());
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
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

    public class AnimPagers extends RecyclerView.Adapter<AnimPagers.ViewHolder> {
        String[] anims = {""};

        public AnimPagers(String[] anims) {
            this.anims = anims;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater =
                    (LayoutInflater)
                            getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.anim_pager1, null);
            RecyclerView.LayoutParams _lp =
                    new RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(_lp);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder _holder, final int position) {
            View v = _holder.itemView;
            LottieAnimationView lottie = v.findViewById(R.id.lottie1);
            lottie.setTag("tag" + String.valueOf(position));

            if (position == 0) {
                lottie.setAnimation("19169-user-testing.json");
                lottie.playAnimation();
                lottie.loop(true);
                lottie1 = lottie;

            } else if (position == 1) {
                lottie.setAnimation("84679.json");
                lottie.loop(true);
                lottie2 = lottie;
                Log.wtf("sss", "hsjx");
            } else if (position == 2) {
                lottie.setAnimation("lf20_q30c1wrm.json");
                lottie3 = lottie;
            } else if (position == 3) {
                lottie.setAnimation("lf20_kDfr8a.json");
                lottie4 = lottie;
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        revisited();
    }
}
