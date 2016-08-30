package com.example.myandroidframeanimation;


import android.app.Activity; 
import android.graphics.drawable.AnimationDrawable; 
import android.os.Bundle; 
import android.view.View; 
import android.view.View.OnClickListener; 
import android.view.Window; 
import android.widget.Button; 
import android.widget.ImageView; 

public class MainActivity extends Activity {
    private ImageView animationIV; 
    private Button buttonA, buttonB; 
    private AnimationDrawable animationDrawable; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

         animationIV = (ImageView) findViewById(R.drawable.animation1); 
         buttonA = (Button) findViewById(R.id.buttonA); 
         buttonB = (Button) findViewById(R.id.buttonB); 

        
         buttonA.setOnClickListener(new OnClickListener() 
         { 
             @Override 
             public void onClick(View v) { 
                 // TODO Auto-generated method stub 
                 animationIV.setImageResource(R.drawable.animation1); 
                 animationDrawable = (AnimationDrawable) animationIV.getDrawable(); 
                 animationDrawable.start(); 
             } 
         });  
       
        buttonB.setOnClickListener(new OnClickListener() 
        { 
           @Override 
             public void onClick(View v) { 
                 // TODO Auto-generated method stub 
                animationDrawable = (AnimationDrawable) animationIV.getDrawable(); 
                animationDrawable.stop(); 
            } 
         }); 
    } 

}
