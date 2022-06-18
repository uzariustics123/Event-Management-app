package com.macas.nmsc.sict.Utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.macas.nmsc.sict.R;

public class LoaderView extends AlertDialog {
    Context context;
    public LoaderView(@NonNull Context context) {
        super(context);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = getLayoutInflater();
        View loaderview = layoutInflater.inflate(R.layout.loader_layout,null);
        this.setView(loaderview);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setCancelable(false);
    }
}
