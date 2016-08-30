package com.example.myandroidservicesample;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button startBtn, stopBtn, bindBtn, unbindBtn, callRemoteServiceBtn;
	private IPerson iPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startBtn = (Button)findViewById(R.id.startButton01);
        stopBtn = (Button)findViewById(R.id.stopButton02);
        bindBtn = (Button)findViewById(R.id.bindButton03);
        unbindBtn = (Button)findViewById(R.id.unbindButton04);
        callRemoteServiceBtn = (Button)findViewById(R.id.callRemoteServiceButton05);
        
        startBtn.setOnClickListener(startListener);
        stopBtn.setOnClickListener(stopListener);
        bindBtn.setOnClickListener(bindListener);
        unbindBtn.setOnClickListener(unbindListener);
        callRemoteServiceBtn.setOnClickListener(callRemoteService);
    }
    
    private OnClickListener startListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setAction("com.example.myandroidservicesample.action.MY_SERVICE");
			startService(intent);
		}    	
    };
    
    private OnClickListener stopListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setAction("com.example.myandroidservicesample.action.MY_SERVICE");
			stopService(intent);
		}    	
    };
    
    private ServiceConnection conn = new ServiceConnection(){

		@Override
		synchronized public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i("SERVICE", "connnected sucessful!");
			//Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_LONG).show();
			iPerson = IPerson.Stub.asInterface(service);
			if (iPerson != null)
			{
				try{
					iPerson.setName("steve");
					iPerson.setAge(39);
					String msg = iPerson.display();
					Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
				}catch(RemoteException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.i("SERVICE", "disconnnected sucessful!");
			Toast.makeText(MainActivity.this, "断开连接", Toast.LENGTH_LONG).show();
			
		}
    	
    };

    private OnClickListener bindListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setAction("com.example.myandroidservicesample.action.MY_SERVICE");
			bindService(intent,conn,Service.BIND_AUTO_CREATE);
		}    	
    };

    private OnClickListener unbindListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setAction("com.example.myandroidservicesample.action.MY_SERVICE");
			unbindService(conn);
		}    	
    };    

    private OnClickListener callRemoteService = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setAction("com.example.myandroidservicesample.action.MY_REMOTE_SERVICE");
			bindService(intent,conn,Service.BIND_AUTO_CREATE);
		}    	
    };

}
