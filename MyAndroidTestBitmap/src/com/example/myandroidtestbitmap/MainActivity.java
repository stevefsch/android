package com.example.myandroidtestbitmap;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView myImageView;
	private Button b1,b2,b3,b4;
	private ImageView imageView;
	private AnimationDrawable danceAnimation;	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //mothod 2
        //myImageView = (ImageView)findViewById(R.id.ImageView01);
        //myImageView.setImageResource(R.drawable.steve);
        
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.steve);
        try {
			setWallpaper(bm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        imageView = (ImageView)findViewById(R.id.ImageView01);

        /*
        imageView.setBackgroundResource(R.anim.dance);
        danceAnimation = (AnimationDrawable)imageView.getBackground();
        danceAnimation.start();
        */
        b1 = (Button)findViewById(R.id.Button01);
        b2 = (Button)findViewById(R.id.Button02);
        b3 = (Button)findViewById(R.id.Button03);
        b4 = (Button)findViewById(R.id.Button04);
        
        b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//mothod 1
				/*
				Animation scaleAnimation = new ScaleAnimation(0f,1f,0f,1f,
						Animation.RELATIVE_TO_SELF,0.5f,
						Animation.RELATIVE_TO_SELF,0.5f);
				scaleAnimation.setDuration(3000);
				*/
				
				//method 2
				Animation scaleAnimation =AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
				imageView.startAnimation(scaleAnimation);
			}
        	
        });
        
        b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//method 1
				/*
				 * Animation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
				alphaAnimation.setDuration(3000);
				*/
				
				//method 2
				Animation alphaAnimation =AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
				imageView.startAnimation(alphaAnimation);
			}
        	
        });
        
        b3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//method 1
				/*
				Animation translateAnimation = new TranslateAnimation(10,100,10,100);
				translateAnimation.setDuration(3000);
				*/
				
				//method 2
				Animation translateAnimation =AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
				imageView.startAnimation(translateAnimation);
			}
        	
        });
        
        b4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//method 1
				/*
				Animation rotateAnimation = new RotateAnimation(0f,+360f,
						Animation.RELATIVE_TO_SELF,0.5f,
						Animation.RELATIVE_TO_SELF,0.5f);
				rotateAnimation.setDuration(3000);
				*/
				
				//method 2
				Animation rotateAnimation =AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
				imageView.startAnimation(rotateAnimation);
			}
        	
        });

    }
}
