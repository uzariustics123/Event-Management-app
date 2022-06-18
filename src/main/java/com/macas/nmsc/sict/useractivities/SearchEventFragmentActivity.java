package com.macas.nmsc.sict.useractivities;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.macas.nmsc.sict.MainActivity;
import com.macas.nmsc.sict.R;
import com.macas.nmsc.sict.databinding.FragmentSearchEventActivityBinding;

import java.util.HashMap;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SearchEventFragmentActivity extends DialogFragment {

    private View root_view;
    private final HashMap<String, Object> eventData = new HashMap<>();
    SharedPreferences sharedPreferences;
    MainActivity act;
    FragmentSearchEventActivityBinding binding;
    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        Dialog dialog = super.onCreateDialog(savedInstance);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = FragmentSearchEventActivityBinding.inflate(getLayoutInflater());
        sharedPreferences =
                getActivity().getSharedPreferences("CreateActivityPref", Activity.MODE_PRIVATE);

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        root_view = inflater.inflate(R.layout.create_event, container, false);
        binding = FragmentSearchEventActivityBinding.inflate(inflater,container,false);
        initActivity(binding.getRoot());
        return binding.getRoot();
    }

    public void initActivity(View view) {

    }

    @Override
    public void onViewStateRestored(Bundle restoreState) {
        super.onViewStateRestored(restoreState);
    }

    public void removeEventDialog(){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.remove(this).commit();
    }


}