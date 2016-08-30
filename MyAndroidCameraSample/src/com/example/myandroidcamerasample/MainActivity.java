package com.example.myandroidcamerasample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView)findViewById(R.id.preview);
        surfaceHolder = surfaceView.getHolder();
        SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				camera.stopPreview();
				camera.release();
				camera = null;
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				camera = Camera.open();
				try{
					camera.setPreviewDisplay(holder);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				Camera.Parameters parameters = camera.getParameters();
				parameters.setPreviewSize(width, height);
				parameters.setPictureFormat(PixelFormat.JPEG);
				camera.setParameters(parameters);
				camera.startPreview();
			}
		};
		
		surfaceHolder.addCallback(surfaceCallback);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
    }
    
	Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			SavePictureTask task = new SavePictureTask();
			task.execute(data);
			camera.startPreview();
		}
	};

    
    private void takePic(){
    	camera.stopPreview();
    	camera.takePicture(null, null, pictureCallback);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if (keyCode == KeyEvent.KEYCODE_CAMERA || keyCode == KeyEvent.KEYCODE_SEARCH)
    	{
    		takePic();
    		return true;
    	}
    	return super.onKeyDown(keyCode,event);
    }
}
