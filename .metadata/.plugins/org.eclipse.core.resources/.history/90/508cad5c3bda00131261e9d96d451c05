package com.example.myandroidtestactivitys;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TabHostActivity extends TabActivity implements TabHost.TabContentFactory{

	public TabHostActivity () {
		// TODO Auto-generated constructor stub
	}
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost th = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.tabhost, th.getTabContentView(),true);
        
        //you need to delete anyone of the two methods
        //method 1
        th.addTab(th.newTabSpec("all").setIndicator("所有通话记录").setContent(R.id.TabView01));
        th.addTab(th.newTabSpec("ok").setIndicator("已接电话").setContent(R.id.TabView02));
        th.addTab(th.newTabSpec("cancel").setIndicator("未接电话").setContent(R.id.TabView03));
        
        //method 2
        th.addTab(th.newTabSpec("all").setIndicator("所有通话记录").setContent(this));
        th.addTab(th.newTabSpec("ok").setIndicator("已接电话").setContent(this));
        th.addTab(th.newTabSpec("cancel").setIndicator("未接电话").setContent(this));        
    }	
    
    public View createTabContent(String tag){
    	ListView lv = new ListView(this);
    	List<String> list = new ArrayList<String>();
    	list.add(tag);
    	
    	if (tag.equals("all")){
    		list.add("Tom");
    		list.add("Kite");
    		list.add("rose");
    	}else if (tag.equals("ok")){
    		list.add("Tom");
    		list.add("Kite");    		
    	}
    	else if (tag.equals("cancel")){
    		list.add("rose");
    	}
    	else
    	{
    		
    	}
    	
    	ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_checked,list);
    	lv.setAdapter(adapter);
    	return lv;
    }

}

