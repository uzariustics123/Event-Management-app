package com.macas.nmsc.sict.useractivities;

import android.graphics.PorterDuff;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.lazyprogrammer.motiontoast.MotionStyle;
import com.lazyprogrammer.motiontoast.MotionToast;
import com.macas.nmsc.sict.databinding.UserProfileBinding;
import com.macas.nmsc.sict.R;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileAct extends AppCompatActivity {

    UserProfileBinding binding;
    FirebaseUser currentUser;
    String name;
    String useremail;
    String photourl;
    TextView nametxt;
    TextView emailtxt;
    ImageView profpic;
    NavigationView nav;

    String defAvatarUrl =
            "https://pbs.twimg.com/profile_images/504511158984785921/ZtRUqzu-_400x400.jpeg";
    private FirebaseAuth myfirebaseauth;
    private OnCompleteListener<AuthResult> myfirebaseauth_sign_in_listener;
    BottomSheetBehavior btmsBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserProfileBinding.inflate(getLayoutInflater());
        myfirebaseauth = FirebaseAuth.getInstance();
        currentUser = myfirebaseauth.getCurrentUser();
        nav = binding.navigationView;
        setContentView(binding.getRoot());
        setUpToolbar();
        initBottomNav();
        loadUserInfos();
    }

    public void setUpToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        binding.toolbar.getNavigationIcon().setColorFilter(-1, PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        binding.toolbar.setNavigationOnClickListener(
                tlb -> {
                    onBackPressed();
                });
    }

    public void loadUserInfos() {
        name =
                currentUser.getDisplayName() == null | currentUser.getDisplayName() == ""
                        ? "User"
                        : currentUser.getDisplayName();
        useremail = currentUser.getEmail().toString();
        photourl =
                currentUser.getPhotoUrl() != null
                        ? currentUser.getPhotoUrl().toString()
                        : defAvatarUrl;
        Glide.with(this).load(photourl).into(binding.profpic);
        binding.profname.setText(wordCaps(name));
        binding.profEmail.setText(useremail);
    }

    public boolean isAdmin() {
        return false;
    }

    public String wordCaps(String tagName) {
        String[] splits = tagName.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String eachWord = splits[i];
            if (i > 0 && eachWord.length() > 0) {
                sb.append(" ");
            }
            String cap = eachWord.substring(0, 1).toUpperCase() + eachWord.substring(1);
            sb.append(cap);
        }
        return sb.toString();
    }

    public void initBottomNav() {
        btmsBehavior = BottomSheetBehavior.from(binding.navigationView);
        
        btmsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setAdminNavOptions();
        binding.editbtn.setOnClickListener(
                v -> {
                    btmsBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                });
    }

    public void setAdminNavOptions() {
        nav.inflateMenu(R.menu.profile_options);
        binding.navigationView.setNavigationItemSelectedListener(
                (item) -> {
                    switch(item.getItemId()){
                        case R.id.deleteaccount:
                        	delUser();
                        	break;
                        default:
                        break;
                        }
                    btmsBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                    return true;
                });
    }

    @Override
    public void onBackPressed() {
        if(btmsBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN){
            super.onBackPressed();
            return;
            }
        btmsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
    
    public void delUser(){
        
        FirebaseAuth.getInstance()
                .getCurrentUser()
                .delete()
                .addOnCompleteListener(
                        (task) -> {
                            if (task.isSuccessful()) {
                                // clear fields
                                        showSuccmsg("user account deleted succesfully");
                                        finish();
                            } else {
                                
                                        errorToast(task.getException().getMessage());
                                        
                            }
                        });
    }
    public void confirmDeleteAcc(){
        
        }
    public void showSuccmsg(String msg) {
        MotionToast motionToast =
                new MotionToast(
                                this,
                                1,
                                MotionStyle.LIGHT,
                                MotionStyle.SUCCESS,
                                MotionStyle.BOTTOM,
                                "SUCCESS",
                                msg,
                                Toast.LENGTH_LONG)
                        .show();
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
}
