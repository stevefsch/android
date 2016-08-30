package com.example.myandroidthread;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        MyView v = new MyView(this,null);
        setContentView(v);
    }
    
    class MyView extends View implements Runnable{
    	public MyView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
			setFocusable(true);
			new Thread(this).start();
		}

		private int x = 20, y = 20;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			RefreshHandler mRedrawhandler = new RefreshHandler();
			while(!Thread.currentThread().isInterrupted()){
				Message m = new Message();
				m.what = 0x101;
				mRedrawhandler.sendMessage(m);
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
		protected void onDraw(Canvas canvas){
			super.onDraw(canvas);
			Paint p = new Paint();
			p.setColor(Color.GREEN);
			canvas.drawCircle(x,y,10,p);			
		}
    	
		class RefreshHandler extends Handler{
			public RefreshHandler(){
				
			}
			
			public void handleMessage(Message msg){
				if (msg.what == 0x101){
					MyView.this.update();
					MyView.this.invalidate();
				}
				super.handleMessage(msg);
			}
		}
		
		private void update(){
			int h = getHeight();
			y += 5;
			if (y >= h)
				h = 20;
		}
    }
}
