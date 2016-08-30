package com.example.myandroidwidgetsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class MainActivity extends Activity{
	private Button register, cancel;
	private ToggleButton marriged;
	private RadioButton male,female;
	private EditText username, password;
	private Spinner position;
	private CheckBox reading, swimming;

	public MainActivity() {
		// TODO Auto-generated constructor stub
	}
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		
		male = (RadioButton)findViewById(R.id.male);
		female = (RadioButton)findViewById(R.id.female);
		
		reading = (CheckBox)findViewById(R.id.reading);
		swimming = (CheckBox)findViewById(R.id.swimming);
		
		marriged = (ToggleButton)findViewById(R.id.marriged);
		
		position = (Spinner)findViewById(R.id.position);
		
		String[] str = {"CEO","CFO","PM"};
		
		ArrayAdapter aa =  new ArrayAdapter(this, android.R.layout.simple_spinner_item,str);
		
		position.setAdapter(aa);
		
		register = (Button)findViewById(R.id.register);
		cancel = (Button)findViewById(R.id.cancel);
		
		register.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Bundle b = new Bundle();
				b.putString("username", "�û�����" + username.getText().toString());
				b.putString("password", "�û�����" + password.getText().toString());
				
				if (male.isChecked()){
					b.putString("gender", "�Ա�:��");
				}
				else{
					b.putString("gender", "�Ա�:Ů");
				}
				
				String temp = "����:";
				
				if (reading.isChecked()){
					temp += "����";
				}
				
				if (swimming.isChecked()){
					temp += " ";
					temp += "��Ӿ";
				}
				
				b.putString("hobby", temp);
				
				if (marriged.isChecked()){
					b.putString("marriged", "����ѻ�");
				}
				else
				{
					b.putString("marriged", "���δ��");
				}
				
				b.putString("position", "ְλ" + position.getSelectedItem().toString());
				
				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
				intent.putExtra("data", b);
				startActivity(intent);
			}
		});
	}
}
