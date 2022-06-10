package com.macas.nmsc.sict;

import android.text.format.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class TimeDifference {
    public static String when;
    public static String timeFormatWithAmPm="K:mm a";
	public static String timeFormatWithSlah="hh:mm a";
    public static void when(long refference) {}

    public static long getReferenceTimeParse(String date) throws ParseException {
        long reftime = new SimpleDateFormat("hh:mm:ss dd/mm/yy").parse(date).getTime();
        return reftime;
    }
    private CharSequence getTimeReference(long mReferenceTime) {
        long now = System.currentTimeMillis();
        long difference = now - mReferenceTime; 
        
        return (difference >= 0 &&  difference<=DateUtils.MINUTE_IN_MILLIS) ? 
                "just now": 
                DateUtils.getRelativeTimeSpanString(
                    mReferenceTime,
                    now,
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_RELATIVE);
                    
    }
}
