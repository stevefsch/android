package com.example.myandroidactivitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText username, password;
	private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        b1=(Button)findViewById(R.id.Button01);
        b1.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
	        	username = (EditText)findViewById(R.id.username);
	        	password = (EditText)findViewById(R.id.password);
	        	
	        	String str_username = username.getText().toString();
	        	String str_password = password.getText().toString();
	        	
	        	Bundle b = new Bundle();
	        	b.putString("username", str_username);
	        	b.putString("password", str_password);
	        	
	        	Intent intent = new Intent(MainActivity.this,NextActivity.class);
	        	intent.putExtras(b);
	        	startActivityForResult(intent,0);
        	}
        });
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	Bundle b = data.getExtras();
    	String str_username = b.getString("username");
    	String str_password = b.getString("password");
    	username.setText(str_username);
    	password.setText(str_password);
    }
}
