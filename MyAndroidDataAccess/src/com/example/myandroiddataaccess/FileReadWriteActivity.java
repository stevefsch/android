package com.example.myandroiddataaccess;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

public class FileReadWriteActivity extends Activity{

	private static final String FILE_NAME = "temp.txt";
	
	private Button readBtn, writeBtn;
	private EditText et1, et2;
	
	public FileReadWriteActivity() {
		// TODO Auto-generated constructor stub
	}
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filereadwrite);
        
        readBtn = (Button)findViewById(R.id.ReadButton01);
        writeBtn = (Button)findViewById(R.id.WriteButton01);
        
        et1 = (EditText)findViewById(R.id.EditText02);
        et2 = (EditText)findViewById(R.id.EditText03);
        
        readBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et2.setText(read());
			}
        	
        });
        
        writeBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				write(et1.getText().toString());				
			}
        	
        });
    }	
    
    private String read(){
    	try{
    		FileInputStream fis = openFileInput(FILE_NAME);
    		byte[] buffer = new byte[fis.available()];
    		fis.read(buffer);
    		return new String(buffer);
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    private void write(String content){
    	try{
    		FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
    		fos.write(content.getBytes());
    		fos.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

}
