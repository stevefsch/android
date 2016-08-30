package com.example.myandroidtestactivitys;

import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.database.Cursor;
import android.provider.Contacts.People;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ListViewActivity extends ListActivity{

	public ListViewActivity() {
		// TODO Auto-generated constructor stub
	}

	protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);

        //methods 1
        
        String[] strs = {"Java","C","C++","VB"};
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strs);
    	
    	setListAdapter(adapter);
    	
        
        //method2
        /*
    	Cursor c = getContentResolver().query(People.CONTENT_URI, null, null,null,null);
    	startManagingCursor(c);
    	
    	ListAdapter listAdapter = new SimpleCursorAdapter(this, 
    			android.R.layout.simple_list_item_1,
    			c,
    			new String[] {People.NAME},
    			new int[] {android.R.id.text1});
    	setListAdapter(listAdapter);
    	*/
	
	}
}
