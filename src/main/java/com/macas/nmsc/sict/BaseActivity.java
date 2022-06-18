package com.macas.nmsc.sict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.macas.nmsc.sict.Utils.ExceptionHandler;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }
}