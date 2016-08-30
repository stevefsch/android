package com.example.myandroidtestactivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteActivity extends Activity{

	public AutoCompleteActivity() {
		// TODO Auto-generated constructor stub
	}
	
	private AutoCompleteTextView atv;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.autocompletetextview);
        
		Intent intent = this.getIntent();
		//Bundle b = intent.getBundleExtra("data");
        
        atv = (AutoCompleteTextView)findViewById(R.id.AutoCompleteTextView01);
        String[] strs = {"abc","bcd","cde","def"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,strs);
        atv.setAdapter(adapter);    
    }

}
