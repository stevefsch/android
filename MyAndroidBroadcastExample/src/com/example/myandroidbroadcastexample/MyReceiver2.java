package com.example.myandroidbroadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver{

	public MyReceiver2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("my_tag", "BOOT_COMPLETED~~~~~~~~~~~~~~~~~");
		Toast.makeText(context, "BOOT_COMPLETED~~~~~~~~~~~~~~~~~",Toast.LENGTH_LONG).show();
	}

}
