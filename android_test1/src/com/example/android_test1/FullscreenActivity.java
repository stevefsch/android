package com.example.android_test1;

import com.example.android_test1.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    
    private static final int ITEM1 = Menu.FIRST;
    private static final int ITEM2 = Menu.FIRST + 1;
    private static final int ITEM3 = Menu.FIRST + 2;

    private static final int CONTEXT_ITEM1 = Menu.FIRST + 3;
    private static final int CONTEXT_ITEM2 = Menu.FIRST + 4;
    private static final int CONTEXT_ITEM3 = Menu.FIRST + 5;

    private static final int SUB_ITEM1 = Menu.FIRST + 6;
    private static final int SUB_ITEM2 = Menu.FIRST + 7;
    
    private TextView myTV;
    private ImageView myImageView;
    
    private Button myBtn;
    private Button myBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        
        myTV = (TextView)findViewById(R.id.TextView01);
        registerForContextMenu(myTV);
        
        myImageView = (ImageView)findViewById(R.id.bitmapImageView01);
        Resources r = getResources();
        Drawable d = r.getDrawable(R.drawable.ic_launcher);
        myImageView.setImageDrawable(d);
        
        myBtn = (Button)findViewById(R.id.dummy_button);
        
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        
        myBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		builder.setMessage("请在view上长按右键").
        		setPositiveButton("yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						myTV.setText("please press me for long time");
					}
				}).setNegativeButton("no",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						myTV.setText("please press me for long time");
					}
				});
        		AlertDialog ad = builder.create();
        		ad.show();				
			}
        });
        
        myBtn1 = (Button)findViewById(R.id.button1);
        
        final int l = Toast.LENGTH_LONG;
        final int s = Toast.LENGTH_SHORT;
        final String s1 = "我多显示议会";
        myBtn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast t1 = Toast.makeText(getApplicationContext(), s1, l);
				t1.show();
			}
        	
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	SubMenu start = menu.addSubMenu("开始");

    	start.add(0,SUB_ITEM1,0,"新游戏");
    	start.add(0,SUB_ITEM2,0,"继续游戏");
    	menu.add(0,ITEM2,0,"退出游戏");
    	
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case ITEM2:
    		setTitle("退出游戏");
    		break;
    	case SUB_ITEM1:
    		setTitle("新游戏");
    		break;
    	case SUB_ITEM2:
    		setTitle("继续游戏");
    		break;
    	}
    	return true;
    }
    
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    	menu.add(0,CONTEXT_ITEM1,0,"red");
    	menu.add(0,CONTEXT_ITEM2,0,"green");
    	menu.add(0,CONTEXT_ITEM3,0,"blue");
    }
    
    public boolean onContextItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case CONTEXT_ITEM1:
    		myTV.setBackgroundColor(Color.RED);
    		break;

    	case CONTEXT_ITEM2:
    		myTV.setBackgroundColor(Color.GREEN);
    		break;

    	case CONTEXT_ITEM3:
    		myTV.setBackgroundColor(Color.BLUE);
    		break;

    	}
    	
    	return true;
    }    
}
