package com.macas.nmsc.sict;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.macas.nmsc.sict.Utils.LoaderView;
import com.macas.nmsc.sict.databinding.LoginScreenBinding;

import net.steamcrafted.loadtoast.LoadToast;

public class LogInActivity extends AppCompatActivity {

    LoginScreenBinding binding;
    LottieAnimationView loginanim;
    Handler mhandler;
    private FirebaseAuth myfirebaseauth;
    private OnCompleteListener<AuthResult> myfirebaseauth_sign_in_listener;
    SharedPreferences sharedprefs;
    LoaderView loader;
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        myfirebaseauth = FirebaseAuth.getInstance();
        binding = LoginScreenBinding.inflate(getLayoutInflater());
        mhandler = new Handler(Looper.getMainLooper());
        setContentView(binding.getRoot());
        loader = new LoaderView(this);
        loginanim = binding.lottie1;
        changeBarColors();
        setAnim();
        setUpMyFirebaseAuth();
        binding.loginfab.setOnClickListener(
                (view) -> {
                    signInUser(
                            binding.username.getText().toString(),
                            binding.password.getText().toString());
                    // lt.show();
                });
        binding.signupbtn.setOnClickListener(
                (view) -> {
                    Intent i = new Intent(LogInActivity.this, RegisterUserActivity.class);
                    startActivity(i);
                });
        sharedprefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        revisited();
    }

    public void revisited() {
        String greet = "Is'nt it a great day right?";
        if (sharedprefs.getBoolean("revisited", false)) {
            greet = "Welcome Back!";
        } else {
            sharedprefs.edit().putBoolean("revisited", true).commit();
        }
        binding.greetxt.setText(greet);
    }


    private void setUpMyFirebaseAuth() {
        // TODO: Implement this method
        myfirebaseauth_sign_in_listener =
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        loader.hide();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = myfirebaseauth.getCurrentUser();
                            // updateUI(user);
                            mhandler.postDelayed(
                                    new Runnable() {

                                        @Override
                                        public void run() {
                                            succesRedirect();
                                        }
                                    },
                                    500);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.getStackTraceString(task.getException());
                            
                            showErrmsg(task.getException().getMessage());
                                           errorToast("Authentication failed.");
                            // updateUI(null);
                        }
                    }
                };
    }

    public void signInUser(String username, String password) {

        if (isValidFields()) {
            loader.show();
            myfirebaseauth
                    .signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, myfirebaseauth_sign_in_listener);
        } else {

        }
    }

    public void showErrmsg(String msg) {
        binding.errormsg.setVisibility(View.VISIBLE);
        binding.errormsg.setText(msg);
    }

    public boolean isValidFields() {
        String username = binding.username.getText().toString();
        String pass = binding.password.getText().toString();
        if (username.startsWith(" ")) {
            showWarningMsg("Email should'nt starts with space");
            return false;
        } else if (username.isEmpty()) {
            showWarningMsg("Email cannot be empty");
            return false;
        } else if (pass.isEmpty()) {
            showWarningMsg("Password is empty");
            return false;
        }
        return true;
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
                                Toast.LENGTH_LONG);
                                motionToast.show();
                                //Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
    }
    public void showWarningMsg(String msg) {

        MotionToast motionToast =
                new MotionToast(
                                this,
                                1,
                                MotionStyle.LIGHT,
                                MotionStyle.WARNING,
                                MotionStyle.BOTTOM,
                                "WARNING",
                                msg,
                                Toast.LENGTH_LONG)
                        .show();
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
        } 
        // window.setNavigationBarColor(getColor(R.color.backgroundColor));
    }

    public void setAnim() {
        loginanim.setAnimation("72883-login-page.json");
        loginanim.playAnimation();
        loginanim.loop(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = myfirebaseauth.getCurrentUser();
        if (currentUser != null) {
            // reload();
            succesRedirect();
            finish();
        } else {

        }
    }

    public void succesRedirect() {
        Intent i = new Intent(this, YourOrgsActivity.class);
        startActivity(i);
        finish();
    }
}
