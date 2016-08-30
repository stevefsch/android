package com.example.myandroidactivitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NextActivity extends Activity{

	private Button b2;
	public NextActivity() {
		// TODO Auto-generated constructor stub
	}
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);
        b2 = (Button)findViewById(R.id.Button02);
        b2.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent = getIntent();
        		NextActivity.this.setResult(0,intent);
        		NextActivity.this.finish();
        	}
        });
    }
}
