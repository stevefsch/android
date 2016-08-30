package com.example.myandroidcontentprovidersamples;

import com.example.myandroidcontentprovidersamples.Employees.Employee;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert();
        query();
        update();
        del();
        query();
    }
    private void del(){
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	getContentResolver().delete(uri, null, null);
    }
    
    private void update(){
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	ContentValues values = new ContentValues();
    	values.put(Employee.NAME, "steve kong");
    	values.put(Employee.GENDER,"male");
    	values.put(Employee.AGE, 38);
    	getContentResolver().update(uri, values, null, null);
    }
    
    private void query(){
    	String[] PROJECTION = new String[]{
    			Employee._ID, //0
    			Employee.NAME, //1
    			Employee.GENDER, //2
    			Employee.AGE   //3
    	};
    	
    	Cursor c = managedQuery(Employee.CONTENT_URI,PROJECTION,null,null,Employee.DEFAUTL_SORT_ORDER);
    	
    	if (c.moveToFirst()){
    		for (int i = 0;i < c.getCount();i++)
    		{
    			c.moveToPosition(i);
    			String name  = c.getString(1);
    			String gender = c.getString(2);
    			int age = c.getInt(3);
    			
    			Log.i("emp", name + ":" + gender + ":" + age);
    		}
    	}
    }
    
    private void insert(){
    	Uri uri = Employee.CONTENT_URI;
    	ContentValues values = new ContentValues();
    	values.put(Employee.NAME, "Bill");
    	values.put(Employee.GENDER, "male");
    	values.put(Employee.AGE, 30);
    	getContentResolver().insert(uri, values);
    }
    
}
