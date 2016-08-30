package com.example.myandroidsqlitesample;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {
	private EditText et1,et2,et3;
	private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        
        this.setTitle("����ղ���Ϣ");
        et1 = (EditText)findViewById(R.id.EditTextName);
        et2 = (EditText)findViewById(R.id.EditTextUrl);
        et3 = (EditText)findViewById(R.id.EditTextDesc);
        
        b1 = (Button)findViewById(R.id.ButtonAdd);
        b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = et1.getText().toString();
				String url = et2.getText().toString();
				String desc = et3.getText().toString();
				
				ContentValues values = new ContentValues();
				values.put("name", name);
				values.put("url", url);
				values.put("desc", desc);
				DBHelper helper = new DBHelper(getApplicationContext());
				helper.insert(values);
				Intent intent = new Intent(AddActivity.this,QueryActivity.class);
				startActivity(intent);
			}
        	
        });
    }
}
