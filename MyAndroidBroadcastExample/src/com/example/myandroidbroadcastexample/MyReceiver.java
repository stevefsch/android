package com.example.myandroidbroadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{

	public MyReceiver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String msg = intent.getStringExtra("msg");
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		if (intent.getAction() == "com.example.myandroidbroadcastexample.action.MY_ACTION")
		{
			Toast.makeText(context, "帅哥帅哥,我是美女,我是美女",Toast.LENGTH_LONG).show();
		}
		else if (intent.getAction() == "com.example.myandroidbroadcastexample.action.BC_ACTION")
		{
			
		}
		else if (intent.getAction() == "com.example.myandroidbroadcastexample.action.NM_ACTION")
		{
			Intent i = new Intent();
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setClass(context, DisplayActivity.class);
			context.startActivity(i);
		}
	}
}
