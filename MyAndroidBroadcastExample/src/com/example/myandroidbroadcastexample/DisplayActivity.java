package com.example.myandroidbroadcastexample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DisplayActivity extends Activity{
	private Button cancelBtn;
	private Notification n;
	private NotificationManager nm;
	private static final int ID = 1;

	public DisplayActivity() {
		// TODO Auto-generated constructor stub
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		
		cancelBtn = (Button)findViewById(R.id.CancelButton02);
		String service = NOTIFICATION_SERVICE;
		nm = (NotificationManager)getSystemService(service);
		
		n = new Notification();
		int icon = n.icon = R.drawable.ic_launcher;
		String tickerText = "Test Notification";
		long when = System.currentTimeMillis();
		n.icon = icon;
		n.tickerText = tickerText;
		n.when = when;
		
		Intent intent = new Intent(this,MainActivity.class);
		PendingIntent pi  = PendingIntent.getActivity(this,0,intent,0);
		
		n.setLatestEventInfo(this,"MyTitle","MyContent",pi);
		nm.notify(ID, n);
		
		cancelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nm.cancel(ID);
			}
			
		});
	}

}
