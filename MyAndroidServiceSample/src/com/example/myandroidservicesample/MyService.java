package com.example.myandroidservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service{

	public MyService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("SERVICE", "onBind......");
		Toast.makeText(MyService.this, "onBind......", Toast.LENGTH_LONG).show();
		return null;
	}
	
	public void onCreate(){
		Log.i("SERVICE", "onCreate......");
		Toast.makeText(MyService.this, "onCreate......", Toast.LENGTH_LONG).show();		
	}

	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i("SERVICE", "onStart......");
		Toast.makeText(MyService.this, "onStart......", Toast.LENGTH_LONG).show();
	}

	public void onDestroy(){
		Log.i("SERVICE", "onDestroy......");
		Toast.makeText(MyService.this, "onDestroy......", Toast.LENGTH_LONG).show();		
	}
	
}
