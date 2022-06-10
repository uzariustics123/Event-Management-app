package com.macas.nmsc.sict;

import android.app.*;
//import com.onesignal.OneSignal;

public class MacasApplication extends Application {
    private static final String ONESIGNAL_APP_ID = "c27ae4f3-5845-4240-a969-c07d208a4033";
  
    @Override
    public void onCreate() {
        super.onCreate();
        
        // Enable verbose OneSignal logging to debug issues if needed.
//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        
        // OneSignal Initialization
        //OneSignal.initWithContext(this);
        //OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}