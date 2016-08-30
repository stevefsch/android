package com.example.myandroidsqlitesample;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class QueryActivity extends ListActivity{

	public QueryActivity() {
		// TODO Auto-generated constructor stub
	}
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.setTitle("����ղ���Ϣ");
        final DBHelper helpter =  new DBHelper(this);
        Cursor c = helpter.query();
        String from[] = {"_id","name","url","desc"};
        int to[] = {R.id.text0,R.id.text1,R.id.text2,R.id.text3};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row,c,from,to);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        
        listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				final long temp = arg3;
				builder.setMessage("���Ҫɾ����¼��?").setPositiveButton("��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						helpter.del((int)temp);
						Cursor c = helpter.query();
						String[] from = {"_id","name","url","desc"};
						int to[] = {R.id.text0,R.id.text1,R.id.text2,R.id.text3};
						SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.row,c,from,to);
				        ListView listView = getListView();
				        listView.setAdapter(adapter);						
					}
				}).setNegativeButton("��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				AlertDialog ad = builder.create();
				ad.show();
			}
        	
        });
        
        helpter.close();

    }
}
