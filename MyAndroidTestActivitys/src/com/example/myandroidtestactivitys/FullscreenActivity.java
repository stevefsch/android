package com.example.myandroidtestactivitys;

import com.example.myandroidtestactivitys.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;

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
    
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25;    
    
    public static final String MY_ACTION = "com.example.myandroidtestactivitys.MY_ACTION";

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
        

        b1 = (Button)findViewById(R.id.button01);        
        b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(FullscreenActivity.this,AutoCompleteActivity.class);				
				//Bundle b = new Bundle();
				//b.putString("name", null);
				//intent.putExtra("data", b);			
				startActivity(intent);				
			}
        	
        });

        b2 = (Button)findViewById(R.id.button02);        
        b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FullscreenActivity.this,TabHostActivity.class);				
				startActivity(intent);				
			}        	
        });

        b3 = (Button)findViewById(R.id.button03);        
        b3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				showDialog(0);
			}        	
        });

        b4 = (Button)findViewById(R.id.button04);        
        b4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
		        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		        //setProgressBarIndeterminateVisibility(true);
			}        	
        });

        b5 = (Button)findViewById(R.id.button05);        
        b5.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//method 1
				ComponentName cn = new ComponentName(FullscreenActivity.this,"com.example.myandroidtestactivitys.TimeDatePickerActivity");
				Intent intent = new Intent();
				intent.setComponent(cn);
				intent.setAction(MY_ACTION);
				
				//method 2
				//Intent intent = new Intent(FullscreenActivity.this,TimeDatePickerActivity.class);
				
				startActivity(intent);				
			}        	
        });

        b6 = (Button)findViewById(R.id.button06);        
        b6.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FullscreenActivity.this,ListViewActivity.class);				
				startActivity(intent);				
			}        	
        });

        b7 = (Button)findViewById(R.id.dummy_button);        
        b7.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
								
				//intent.setAction(Intent.ACTION_GET_CONTENT);
				//intent.setType("vnd.android.cursor.item/phone");
				intent.setAction(Intent.ACTION_VIEW);
				Uri uri = Uri.parse("content://contacts/people/1");
				intent.setData(uri);
				startActivity(intent);				
			}        	
        });

        b8 = (Button)findViewById(R.id.button08);        
        b8.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.setType("vnd.android.cursor.item/phone");
				startActivity(intent);				
			}        	
        });

        b9 = (Button)findViewById(R.id.button09);        
        b9.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				//intent.setAction(Intent.ACTION_VIEW);
				intent.setAction("android.intent.action.VIEW");
				Uri uri = Uri.parse("http://www.sina.com.cn");
				intent.setData(uri);
				startActivity(intent);				
			}        	
        });

        b10 = (Button)findViewById(R.id.button10);        
        b10.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_VIEW);
				Uri uri = Uri.parse("geo:39.3256,116.2312");
				intent.setData(uri);
				startActivity(intent);				
			}        	
        });

        b11 = (Button)findViewById(R.id.button11);        
        b11.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);				
			}        	
        });
        
        b12 = (Button)findViewById(R.id.button12);        
        b12.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_DIAL);
				startActivity(intent);				
			}        	
        });

        b13 = (Button)findViewById(R.id.button13);        
        b13.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_CALL);
				Uri uri = Uri.parse("tel:13818026267");
				intent.setData(uri);
				startActivity(intent);				
			}        	
        });

        b14 = (Button)findViewById(R.id.button14);        
        b14.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_SEND);
				startActivity(intent);				
			}        	
        });

        b15 = (Button)findViewById(R.id.button15);        
        b15.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_SENDTO);
				startActivity(intent);				
			}        	
        });

        b16 = (Button)findViewById(R.id.button16);        
        b16.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_PICK_ACTIVITY);
				startActivity(intent);				
			}        	
        });

        b17 = (Button)findViewById(R.id.button17);        
        b17.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_SEARCH);
				startActivity(intent);				
			}        	
        });

        b18 = (Button)findViewById(R.id.button18);        
        b18.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_WEB_SEARCH);
				startActivity(intent);				
			}        	
        });

        b19 = (Button)findViewById(R.id.button19);        
        b19.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_FACTORY_TEST);
				startActivity(intent);				
			}        	
        });
        
        b20 = (Button)findViewById(R.id.button20);        
        b20.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();							
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("plain/text");
				intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"qfkong@sedwt.com.cn"});
				intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "from android");
				intent.putExtra(android.content.Intent.EXTRA_TEXT, "this is a test");
				startActivity(Intent.createChooser(intent, "发送邮件..."));				
			}        	
        });

        b21 = (Button)findViewById(R.id.button21);        
        b21.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			}        	
        });

        b22 = (Button)findViewById(R.id.button22);        
        b22.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			}        	
        });

        b23 = (Button)findViewById(R.id.button23);        
        b23.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			}        	
        });
        
        b24 = (Button)findViewById(R.id.button24);        
        b24.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			}        	
        });

        b25 = (Button)findViewById(R.id.button25);        
        b25.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
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
    
    protected Dialog onCreateDialog(int id){
    	ProgressDialog dialog = new ProgressDialog(this);
    	dialog.setTitle("测试对话框");
    	dialog.setIndeterminate(true);
    	dialog.setMessage("程序正在加载测试请稍候!");
    	dialog.setCancelable(true);
    	return dialog;
    }
}
