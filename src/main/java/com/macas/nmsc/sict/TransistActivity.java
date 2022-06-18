package com.macas.nmsc.sict;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.macas.nmsc.sict.Utils.CubicBezierInterpolator;
import androidx.appcompat.app.AppCompatDelegate;
import java.util.Timer;
import java.util.TimerTask;

public class TransistActivity extends AppCompatActivity {
    
    private final Timer _timer = new Timer();
	
	private final double cx = 0;
	private final double cy = 0;
	private ImageView imageview1;
	private final Intent ints = new Intent();
	private TimerTask tim;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.transist_act);
    imageview1 = findViewById(R.id.imageview1);
    
    imageview1.setImageBitmap(BitmapHolder.bitmap);
		imageview1.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener(){
			@Override
			public void onViewAttachedToWindow(View v){
				tim = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								try {
									beginTransition();
								} catch (Exception e) {
									 
								}
							}
						});
					}
				};
				_timer.schedule(tim, 200);
			}
			@Override
			public void onViewDetachedFromWindow(View v){
				 
			}
		});
    
  }
  
  public void beginTransition() {
      
		int cx = getIntent().getIntExtra("cx",0);
		int cy =  getIntent().getIntExtra("cy",0);
		int r = getIntent().getIntExtra("radius",0);
		//float finalRadius = (float) Math.hypot(cx, cy);
        float startRadius = (float) imageview1.getHeight();
		float finalRadius = 2500f;
		Animator anim = ViewAnimationUtils.createCircularReveal(imageview1, cx, cy, startRadius, 0);
		 anim.setDuration(2000);
		anim.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
		anim.addListener(new Animator.AnimatorListener(){
			@Override
			public void onAnimationStart(Animator animator){
				imageview1.setVisibility(View.VISIBLE);
				 
			}
			@Override
			public void onAnimationEnd(Animator animator){
				imageview1.setVisibility(View.GONE);
				_exit();
			}
			@Override
			public void onAnimationCancel(Animator animator){
			}
			@Override
			public void onAnimationRepeat(Animator animator){
			}
		});
		anim.start();
	}
    public void _exit() {
		tim = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						finish();
					}
				});
			}
		};
		_timer.schedule(tim, 500);
	}
}
