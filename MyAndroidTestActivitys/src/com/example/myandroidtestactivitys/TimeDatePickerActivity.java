package com.example.myandroidtestactivitys;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeDatePickerActivity extends Activity{
	private Button b1,b2;
	private TextView tv0,tv1,tv2;
	private Calendar c;
	private int m_year,m_month,m_day;
	private int m_hour,m_minute;

	public TimeDatePickerActivity() {
		// TODO Auto-generated constructor stub
	}
	
    protected void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timedatepicker);
        
        Intent intent = this.getIntent();
        ComponentName cn = (ComponentName)intent.getComponent();
        String packageName = cn.getPackageName();
        String className = cn.getClassName();
        String action = intent.getAction();
        
        tv0 = (TextView)findViewById(R.id.TextView00);
        tv0.setText("组件包名称:" + packageName + "\n" + "组件类名称:" + className +"\n" + "活动名称:" + action);
        
        		
		b1= (Button)findViewById(R.id.Button01);
		b2= (Button)findViewById(R.id.Button02);
		
		c= Calendar.getInstance();
		
		m_year = c.get(Calendar.YEAR);
		m_month = c.get(Calendar.MONTH);
		m_day = c.get(Calendar.DAY_OF_MONTH);
		
		m_hour = c.get(Calendar.HOUR);
		m_minute = c.get(Calendar.MINUTE);
		
		tv1 = (TextView)findViewById(R.id.TextView01);
		tv1.setText(m_year + ":" + (m_month+1) +":" + m_day);
		
		tv2 = (TextView)findViewById(R.id.TextView02);
		tv2.setText(m_hour+":"+m_minute);
		
		
		
        b1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		showDialog(0);				
			}        	
        });

        b2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		showDialog(1);				
			}        	
        });
        
    }
    
    protected Dialog onCreateDialog(int id){
    	if (id == 0)
    	{
    		return new TimePickerDialog(this, (OnTimeSetListener) l1, m_hour, m_minute,false);
    	}
    	else
    	{
    		return new DatePickerDialog(this, (OnDateSetListener) l2, m_year, m_month, m_day);
    	}
    }

    private OnTimeSetListener l1 = new OnTimeSetListener(){

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			m_hour = hourOfDay;
			m_minute = minute;
			tv2.setText(m_hour+":"+m_minute);
		}
    	
    };
    
    private OnDateSetListener l2 = new OnDateSetListener(){

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			m_year = year;
			m_month = monthOfYear;
			m_day = dayOfMonth;
			tv1.setText(m_year+":"+(m_month+1)+":"+m_day);
		}
    	
    };
}
