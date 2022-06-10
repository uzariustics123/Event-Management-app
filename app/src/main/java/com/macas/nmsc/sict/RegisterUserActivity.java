package com.macas.nmsc.sict;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.HtmlCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.macas.nmsc.sict.HomeActivity;
import com.macas.nmsc.sict.LogInActivity;
import java.util.HashMap;
import net.steamcrafted.loadtoast.LoadToast;

import androidx.appcompat.app.AppCompatActivity;

import com.macas.nmsc.sict.databinding.RegLayoutBinding;

public class RegisterUserActivity extends AppCompatActivity {
    RegLayoutBinding binding;
    private FirebaseAuth myfirebaseauth;
    LoadToast lt;
    RequestNetwork networkRequest;
    Gson gson;
    RequestNetwork.RequestListener registrationNetworkListener;
    HashMap<String, Object> netparams = new HashMap<>();
    HashMap<String, Object> netheaders = new HashMap<>();

    String defAvatarUrl =
            "https://pbs.twimg.com/profile_images/504511158984785921/ZtRUqzu-_400x400.jpeg";
    String defServerUrl = "https://schooleventmanager.000webhostapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = RegLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        anim();

        gson = new Gson();
        networkRequest = new RequestNetwork(this);
        lt = new LoadToast(this);
        myfirebaseauth = FirebaseAuth.getInstance();
        changeBarColors();
        prepareLoader();
        binding.signupbtn.setOnClickListener(
                (view) -> {
                    if (isValidFields()) {
                        policy();
                    }
                });

        registrationNetworkListener =
                new RequestNetwork.RequestListener() {

                    @Override
                    public void onResponse(
                            String tag, String response, HashMap<String, Object> responseHeaders) {

                        if (response != null) {
                            String status = "";
                            try {
                                HashMap responsedata =
                                        new Gson()
                                                .fromJson(
                                                        response,
                                                        new TypeToken<
                                                                HashMap<
                                                                        String,
                                                                        Object>>() {}.getType());
                                status = responsedata.get("status").toString();
                            } catch (Exception e) {
                                showErrmsg(e.toString());
                                abortRegisteredUser();
                            }
                            if (status.equals("success")) {
                                settingProfile(binding.name.getText().toString());
                                lt.success();
                            } else if (status.equals("error")) {
                                showErrmsg(response);
                                abortRegisteredUser();
                                lt.error();
                            } else {
                                showErrmsg(response);
                                abortRegisteredUser();
                                lt.error();
                            }

                        } else {
                            showErrmsg(response);
                            abortRegisteredUser();
                            lt.error();
                        }
                    }

                    @Override
                    public void onErrorResponse(String tag, String message) {
                        showErrmsg(message);
                        abortRegisteredUser();
                        lt.error();
                    }
                };
    }

    public void showErrmsg(String msg) {
        binding.errormsg.setVisibility(View.VISIBLE);
        binding.errormsg.setText(HtmlCompat.fromHtml(msg, HtmlCompat.FROM_HTML_MODE_COMPACT));
    }

    public void showSuccmmsg(String msg) {
        MotionToast motionToast =
                new MotionToast(
                        this,
                        1,
                        MotionStyle.LIGHT,
                        MotionStyle.SUCCESS,
                        MotionStyle.BOTTOM,
                        "SUCCESS",
                        msg,
                        Toast.LENGTH_LONG);
        motionToast.show();
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

    public void informUser(String msg) {

        MotionToast motionToast =
                new MotionToast(
                                this,
                                1,
                                MotionStyle.LIGHT,
                                MotionStyle.INFO,
                                MotionStyle.BOTTOM,
                                "INFO",
                                msg,
                                Toast.LENGTH_LONG)
                        .show();
    }

    public void abortRegisteredUser() {
        myfirebaseauth
                .getCurrentUser()
                .delete()
                .addOnCompleteListener(
                        (task) -> {
                            if (task.isSuccessful()) {
                                // clear fields
                                showWarningMsg("User registration aborted");

                            } else {
                                errorToast("Error aborting user registration");
                                Log.e("error", task.getException().getMessage());
                            }
                        });
    }

    public boolean isValidFields() {
        String email = binding.email.getText().toString();
        String pass = binding.password.getText().toString();
        String cfpass = binding.cnfrmpassword.getText().toString();
        String fname = binding.firstname.getText().toString();
        String lname = binding.lastname.getText().toString();
        if (email.startsWith(" ") || email.isEmpty()) {
            showWarningMsg("email cannot be empty and should'nt starts with space");
            return false;
        } else if (pass.isEmpty()) {
            showWarningMsg("Password is empty");
            return false;
        } else if (fname.startsWith(" ") || fname.isEmpty()) {
            showWarningMsg("First name is required");
            return false;
        } else if (lname.startsWith(" ") || lname.isEmpty()) {
            showWarningMsg("Last name is required");
            return false;
        } else if (!pass.equals(cfpass)) {
            showWarningMsg("Password and confirm password should match/n ");
            return false;
        }
        return true;
    }

    private void changeBarColors() {

        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0xFFEEEEEE);
        }
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            WindowInsetsControllerCompat wic = new WindowInsetsControllerCompat(window, decorView);

            wic.setAppearanceLightStatusBars(true); // true or false as desired.

            // And then you can set any background color to the status bar.
            window.setStatusBarColor(0xFFEEEEEE);
        } else {

        }

        // window.setNavigationBarColor(getColor(R.color.backgroundColor));
    }

    public void anim() {

        binding.mlottie.setAnimation("29564-working-man.json");
        binding.mlottie.loop(true);
        binding.mlottie.playAnimation();
    }

    public void policy() {
        WebView wb = new WebView(this);
        wb.loadUrl("file:///android_asset/privacypolicy.html");

        AlertDialog.Builder alertdialog =
                new AlertDialog.Builder(this)
                        .setView(wb)
                        .setPositiveButton(
                                "Agree",
                                (dialog, id) -> {
                                    registerClient();
                                    dialog.dismiss();
                                })
                        .setNegativeButton(
                                "Disagree",
                                (dialog, id) -> {
                                    dialog.dismiss();
                                });
        alertdialog.create().show();
    }

    public void registerClient() {
        lt.show();
        String username = binding.name.getText().toString();
        String firstname = binding.firstname.getText().toString();
        String email = binding.email.getText().toString();
        String lastname = binding.lastname.getText().toString();
        String password = binding.password.getText().toString();

        lt.show();
        myfirebaseauth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        (task) -> {
                            if (task.isSuccessful()) {
                                showSuccmmsg("Registered to firebase");
                                registerInDb(
                                        username,
                                        firstname,
                                        lastname,
                                        email,
                                        myfirebaseauth.getCurrentUser().getUid());
                            } else {
                                lt.error();
                                Log.getStackTraceString(task.getException());
                                showErrmsg(task.getException().getMessage());
                            }
                        });
    }

    public void verify() {
        AlertDialog.Builder alertdialog =
                new AlertDialog.Builder(this)
                        .setTitle("Verify Email")
                        .setMessage(
                                "We just sent you a verification email. Please check your email")
                        .setPositiveButton(
                                "OK",
                                (dialog, id) -> {
                                    dialog.dismiss();
                                })
                        .setNegativeButton(
                                "Cancel",
                                (dialog, id) -> {
                                    dialog.dismiss();
                                    finish();
                                });
        alertdialog.create().show();
    }

    public void prepareLoader() {
        // int i = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        // lt.setTranslationY(i - ((i/3)/2));
        lt.setText("Signing Up");
        lt.setTranslationY(150);
    }

    public void sentVerification() {
        if (myfirebaseauth.getCurrentUser() != null
                && !myfirebaseauth.getCurrentUser().isEmailVerified()) {
            myfirebaseauth
                    .getCurrentUser()
                    .sendEmailVerification()
                    .addOnCompleteListener(
                            (task) -> {
                                if (task.isSuccessful()) {
                                    verify();
                                } else {

                                    showErrmsg(task.getException().getMessage());
                                }
                            });
        }
    }


    public void registerInDb(
            String username, String fname, String lname, String email, String fbuid) {
        netparams.put("username", username);
        netparams.put("firstname", fname);
        netparams.put("lastname", lname);
        netparams.put("fbuid", fbuid);
        netparams.put("email", email);
        netheaders.put("Access-Control-Allow-Origin", "*");

        networkRequest.setHeaders(netheaders);
        networkRequest.setParams(netparams, RequestNetworkController.REQUEST_PARAM);
        networkRequest.startRequestNetwork(
                RequestNetworkController.POST,
                defServerUrl + "/api/create_user.php",
                "creatuser",
                registrationNetworkListener);
    }

    public void settingProfile(String username) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String photourl = user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : defAvatarUrl;
        UserProfileChangeRequest profileUpdates =
                new UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .setPhotoUri(Uri.parse(photourl))
                        .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(
                                                    getBaseContext(),
                                                    "Updated profile",
                                                    Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
    }

    @Override
    protected void onPause() {
        if(myfirebaseauth.getCurrentUser() != null){
            myfirebaseauth.signOut();
            }
        super.onPause();
        
        
    }
}
