package com.macas.nmsc.sict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
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
    LayoutInflater inflater;
    LottieAnimationView lottie;
    SpeedDialView speedDialView;
    RequestNetwork requestNetwork;
    RequestNetwork.RequestListener requestListener;
    ArrayList<HashMap<String, Object>> orgs = new ArrayList<>();
    String defServerUrl = "https://schooleventmanager.000webhostapp.com";
    FirebaseAuth mfirebaseauth;

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

        FirebaseApp.initializeApp(this);
        initNetworkListeners();
        mfirebaseauth = FirebaseAuth.getInstance();
        requestNetwork = new RequestNetwork(this);
        speedDialView = binding.speedDial;
        inflater = getLayoutInflater();
        getMyOrgslist();
        initFabMenus();
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

    public void createOrgDialog() {
        BottomSheetDialog bottmdialog = new BottomSheetDialog(this);
        View mview = getLayoutInflater().inflate(R.layout.create_org_dialog, null);
        bottmdialog.setContentView(mview);
        ((View) mview.getParent()).setBackgroundColor(0xFF000000);
        bottmdialog.getWindow().setDimAmount(0.2f);

        bottmdialog.show();
        bottmdialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(0x00000000);


    }

    private void createSchool() {
        MaterialAlertDialogBuilder addnew = new MaterialAlertDialogBuilder(this);
        View view = getLayoutInflater().inflate(R.layout.create_org_dialog, null);
        addnew.setView(view).
                setTitle("Add new");

        //addnew.setBackground(new ColorDrawable(0xFFEEEEEE));
        addnew.show();
    }

    private void initFabMenus() {
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_org_addschool, R.drawable.ic_baseline_school_24)
                .setLabel("Add a School")
                .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.success_color, getTheme()))
                .setFabImageTintColor(ResourcesCompat.getColor(getResources(), android.R.color.white, getTheme()))
                .create());
        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()) {
                    case R.id.fab_org_addschool:
                        createSchool();
                        break;

                }
                return false;
            }
        });


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

    public void initNetworkListeners() {
        requestListener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                String status = "";
                String msg = "";
                HashMap responsedata = new HashMap<String, Object>();
                try {
                    responsedata =
                            new Gson()
                                    .fromJson(
                                            response, new TypeToken<HashMap<String, Object>>() {
                                            }.getType());
                    status = responsedata.get("status").toString();
                    msg = responsedata.get("message").toString();
                } catch (Exception e) {
                    errorToast(e + "/nResponse: " + response);
                }
                if (status.equals("success")) {
                        if (tag.equals("createorg")) {

                        } else if (tag.equals("getorgs")) {

                        }
                } else if(status.equals("error")){

                }
                else{
                errorToast(msg);
                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {

            }
        }

        ;
    }

    public void errorToast(String msg) {

        MotionToast motionToast =
                new MotionToast(
                        this,
                        1,
                        MotionStyle.LIGHT,
                        MotionStyle.ERROR,
                        MotionStyle.BOTTOM,
                        "ERROR",
                        msg,
                        Toast.LENGTH_LONG)
                        .show();
    }

    public void showErrorAnim() {
        binding.lottie1.playAnimation();
    }

    public void getMyOrgslist() {
        HashMap netparams = new HashMap<String, Object>();
        String fbuid = mfirebaseauth.getCurrentUser().getUid();
        HashMap netheaders = new HashMap<String, Object>();
        netheaders.put("Access-Control-Allow-Origin", "*");
        netparams.put("fbuid", fbuid);
        requestNetwork.setHeaders(netheaders);
        requestNetwork.setParams(netparams, RequestNetworkController.REQUEST_PARAM);
        requestNetwork.startRequestNetwork(
                RequestNetworkController.POST,
                defServerUrl + "/api/create_user.php",
                "getorgs",
                requestListener);

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
