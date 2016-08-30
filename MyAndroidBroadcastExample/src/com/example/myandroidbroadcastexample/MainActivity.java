package com.example.myandroidbroadcastexample;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlarmManager;
import android.app.PendingIntent;

public class MainActivity extends Activity {
	private static final String MY_ACTION = "com.example.myandroidbroadcastexample.action.MY_ACTION";
	private static final String NM_ACTION = "com.example.myandroidbroadcastexample.action.NM_ACTION";
	private static final String BC_ACTION = "com.example.myandroidbroadcastexample.action.BC_ACTION";
	
	private Button btn, setAlarmBtn, cancelAlarmBtn,startNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        IntentFilter filter = new IntentFilter();
        MyReceiver2 r = new MyReceiver2();
        registerReceiver(r,filter);
        
        
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(MY_ACTION);
				intent.putExtra("msg", "广播:\n美女美女,我是帅锅,收到请回答,请回答!");
				sendBroadcast(intent);
			}        	
        });

        final AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent1 = new Intent();
        intent1.setAction(BC_ACTION);
        intent1.putExtra("msg", "你该去约会了!");
        final PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,0,intent1,0);
        final long time = System.currentTimeMillis();
        
        setAlarmBtn = (Button)findViewById(R.id.SetAlarmButton03);
        setAlarmBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				am.setRepeating(AlarmManager.RTC_WAKEUP,time,800*1000,pi);
			}
        	
        });

        cancelAlarmBtn = (Button)findViewById(R.id.CancelAlarmButton04);
        cancelAlarmBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				am.cancel(pi);
			}
        	
        });
        
        
        startNotificationBtn = (Button)findViewById(R.id.sendNotificationButton05);
        startNotificationBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(NM_ACTION);
				intent.putExtra("msg", "通知管理测试了!");
				sendBroadcast(intent);
			}
        	
        });
    
    }
}
