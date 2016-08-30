package com.example.myandroidcamerasample;

import java.io.File;
import java.io.FileOutputStream;

import android.os.AsyncTask;
import android.os.Environment;

public class SavePictureTask extends AsyncTask<byte[],String,String>{

	public SavePictureTask() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String doInBackground(byte[]... params) {
		// TODO Auto-generated method stub
		File picture = new File(Environment.getExternalStorageDirectory(),"picture.jpg");
		if (picture.exists()) picture.delete();
		
		try{
			FileOutputStream fos = new FileOutputStream(picture.getPath());
			fos.write(params[0]);
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
