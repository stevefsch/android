package com.example.myandroiddataaccess;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText myEditText;
	private Button b1, b2;
	private static final String TEMP_SMS="temp sms";

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myEditText = (EditText)findViewById(R.id.EditText01);
        b1 = (Button)findViewById(R.id.Button01);
        
        SharedPreferences pre = getSharedPreferences(TEMP_SMS,MODE_WORLD_READABLE);
        String content = pre.getString("sms_content", "");
        myEditText.setText(content);
        
        b2 = (Button)findViewById(R.id.Button02);
        b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this,FileReadWriteActivity.class);				
				startActivity(intent);				
			}
        	
        });
    }
    
    protected void onStop(){
    	SharedPreferences.Editor editor = getSharedPreferences(TEMP_SMS,MODE_WORLD_WRITEABLE).edit();
    	editor.putString("sms_content", myEditText.getText().toString());
    	editor.commit();
    	super.onStop();
    }
}
