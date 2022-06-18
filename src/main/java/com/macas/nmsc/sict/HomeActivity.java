package com.macas.nmsc.sict;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.macas.nmsc.sict.Fragments.EventsFragment;
import com.macas.nmsc.sict.databinding.HomeLayoutBinding;
import com.macas.nmsc.sict.useractivities.UserProfileAct;

public class HomeActivity extends AppCompatActivity {
    HomeLayoutBinding binding;
    DrawerLayout drawer;
    NavigationView nav;
    View headerView;
    ImageButton logoutbtn;
    FirebaseUser currentUser;
    String namo;
    String useremail;
    String userphoto;
    TextView nametxt;
    TextView emailtxt;
    String defAvatarUrl = "https://pbs.twimg.com/profile_images/504511158984785921/ZtRUqzu-_400x400.jpeg";
    ImageView profpic;
    private FirebaseAuth myfirebaseauth;
    FragmentManager fragmentManager;
    EventsFragment eventfrag = new EventsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myfirebaseauth = FirebaseAuth.getInstance();
        fragmentManager = getSupportFragmentManager();
		currentUser = myfirebaseauth.getCurrentUser();
        FirebaseAuth.AuthStateListener authStateListener =
                new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser() == null) {
                            // Do anything here which needs to be done after signout is complete
                            login();
                        } 
                    }
                };
                
                namo = currentUser.getDisplayName() == null| currentUser.getDisplayName() == "" ? "User" : currentUser.getDisplayName();
                useremail = currentUser.getEmail();
                userphoto = currentUser.getPhotoUrl() != null? currentUser.getPhotoUrl().toString(): defAvatarUrl ;
                Log.e("name", namo);
                Log.e("poto", userphoto);
                Log.e("email", useremail);
		myfirebaseauth.addAuthStateListener(authStateListener);
        setUpDrawer();
        
        
    }	

    public void setUpDrawer() {
        drawer = binding.drawer;
        nav = binding.navigation;
        headerView = nav.getHeaderView(0);
        profpic = headerView.findViewById(R.id.profpic);
        nametxt = headerView.findViewById(R.id.nametxtv);
        emailtxt = headerView.findViewById(R.id.email);
        nametxt.setText(wordCaps(namo));
        emailtxt.setText(useremail);
        Glide.with(this).load(userphoto).into(profpic);
        logoutbtn = headerView.findViewById(R.id.btn_logout);
        logoutbtn.setOnClickListener(
                (view) -> {
                    logout();
                });
       /* ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this, drawer, binding.toolbar, R.string.app_name, R.string.app_name) {
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };*/
                View.OnClickListener openProfile = (v)->{
                    openUserProfile();
                    };
                    profpic.setOnClickListener(openProfile);
              LinearLayout ll = headerView.findViewById(R.id.profdetaillayout);
              ll.setOnClickListener(openProfile);
        //toggle.syncState();
        changeBarColors();
        setUpMenuClicks();
    }
    
    public void loadEvent(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, eventfrag).commitAllowingStateLoss();
        }

    private void logout() {
        AlertDialog.Builder alertdialog =
                new AlertDialog.Builder(this)
                        .setTitle("Sign out")
                        .setMessage("Are you sure you want sign out?")
                        .setPositiveButton(
                                "Sign out",
                                (dialog, id) -> {
                                    myfirebaseauth.signOut();
                                    login();
                                })
                        .setNegativeButton(
                                "Cancel",
                                (dialog, id) -> {
                                    dialog.dismiss();
                                });
        alertdialog.create().show();
    }

    public void setUpMenuClicks() {
        nav.setNavigationItemSelectedListener(
                (item) -> {
                    switch (item.getItemId()) {
                        case R.id.dashboard:
                            Toast.makeText(getBaseContext(), "dashboard click", Toast.LENGTH_LONG)
                                    .show();
                            break;
                        case R.id.events:
                        loadEvent();
                            break;
                        case R.id.profile:
                        openUserProfile();
                            break;
                    }
                    return true;
                });
    }
    
    public void openUserProfile(){
        Intent i = new Intent(this, UserProfileAct.class);
        Pair[] pair = new Pair[3];
        pair[0] = new Pair<View, String>(profpic, "transPic");
        pair[1] = new Pair<View, String>(nametxt, "transName");
        pair[2] = new Pair<View, String>(emailtxt, "transEmail");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,pair);
        startActivity(i, options.toBundle());
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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = myfirebaseauth.getCurrentUser();
        if (currentUser == null) {
            // reload();
            Toast.makeText(getBaseContext(), "Session unknown", Toast.LENGTH_LONG).show();
            login();
            finish();
        } else {

        }
    }

    public void login() {
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
        finish();
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
}
