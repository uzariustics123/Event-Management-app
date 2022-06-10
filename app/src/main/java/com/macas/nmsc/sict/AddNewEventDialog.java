package com.macas.nmsc.sict;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.app.Activity;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.HashMap;

public class AddNewEventDialog extends DialogFragment {

    private View root_view;
    private HashMap<String, Object> eventData = new HashMap<>();
    SharedPreferences sharedPreferences;
	MainActivity act;
    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        Dialog dialog = super.onCreateDialog(savedInstance);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sharedPreferences =
                getActivity().getSharedPreferences("CreateActivityPref", Activity.MODE_PRIVATE);

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        root_view = inflater.inflate(R.layout.create_event, container, false);
        initActivity(root_view);
        return root_view;
    }

    public void initActivity(View view) {
        ImageButton closebtn = view.findViewById(R.id.bt_close);
        closebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeEventDialog();
                    }
                });
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
