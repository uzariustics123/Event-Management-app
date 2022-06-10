package com.macas.nmsc.sict;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.Window;
import com.macas.nmsc.sict.BitmapHolder;


public class BitmapHolder {
    public static Bitmap bitmap = null;
	
    public static void draws(View view) {
		try {
            
			 view.setDrawingCacheEnabled(true);
			Bitmap b = Bitmap.createBitmap(view.getWidth() , view.getHeight(), Bitmap.Config.ARGB_8888);                
			    Canvas c = new Canvas(b);
			    view.draw(c);
			view.setDrawingCacheEnabled(false);
			bitmap = b;
		} catch(Exception e){
			
		}
	}
    
    
}
