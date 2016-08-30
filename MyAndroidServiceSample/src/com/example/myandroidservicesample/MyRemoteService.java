package com.example.myandroidservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.myandroidservicesample.IPerson.Stub;

public class MyRemoteService extends Service{
	private Stub iPerson = new IPersonImpl();

	public MyRemoteService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return iPerson;
	}

}
