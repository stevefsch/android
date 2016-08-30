package com.example.myandroidtest3;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity{

    private EditText myEdit1, myEdit2;
    private CheckBox cb1;
    private Button b1,b2;
	
	public MainActivity() {
		// TODO Auto-generated constructor stub
	}
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myEdit1 = (EditText)findViewById(R.id.EditText01);
        myEdit2 = (EditText)findViewById(R.id.EditText02);
        cb1 = (CheckBox)findViewById(R.id.CheckBox01);
        b1 = (Button)findViewById(R.id.Button01);
        b2 = (Button)findViewById(R.id.Button02);
        myEdit1.setOnKeyListener(new OnKeyListener(){
        	public boolean onKey(View v, int keyCode, KeyEvent event){
        		myEdit1.setText("");
				return false;        		
        	}
        });
        
        myEdit2.setOnKeyListener(new OnKeyListener(){
        	public boolean onKey(View v, int keyCode, KeyEvent event){
        		myEdit2.setText("");
        		return false;
        	}
        });
        
        myEdit1.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
        		Toast.makeText(getApplicationContext(), myEdit1.getText(), Toast.LENGTH_LONG);				
			}
        });

        myEdit2.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
        		Toast.makeText(getApplicationContext(), myEdit2.getText(), Toast.LENGTH_LONG);				
			}
        });
        
        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), cb1.isChecked()+"", Toast.LENGTH_LONG);
			}
        	
        });
        
        b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), b1.getText(), Toast.LENGTH_LONG);
			}
        	
        });

        b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), b2.getText(), Toast.LENGTH_LONG);
			}
        	
        });
	
    }
}
