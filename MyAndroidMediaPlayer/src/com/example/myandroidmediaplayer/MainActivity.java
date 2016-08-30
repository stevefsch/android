package com.example.myandroidmediaplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements MediaPlayer.OnCompletionListener{
	private ImageButton play,pause,stop;
	private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (ImageButton)findViewById(R.id.play);
        pause = (ImageButton)findViewById(R.id.pause);
        stop = (ImageButton)findViewById(R.id.stop);
        
        play.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				play();
			}
        	
        });

        pause.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pause();
			}
        	
        });

        stop.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stop();
			}
        	
        });
        setup();
    }
    
    public void onDestroy(){
    	if(stop.isEnabled()){
    		stop();
    	}
    }

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		stop();
	}
	
	private void play(){
		mp.start();
		play.setEnabled(false);
		pause.setEnabled(true);
		stop.setEnabled(true);
	}
	
	private void stop(){
		mp.stop();
		pause.setEnabled(false);
		stop.setEnabled(false);
		
		try{
			mp.prepare();
			mp.seekTo(0);
			play.setEnabled(true);
		}
		catch(Throwable t){
			error(t);
		}
	}
	
	private void pause(){
		mp.pause();
		play.setEnabled(true);
		pause.setEnabled(false);
		stop.setEnabled(true);
	}
	
	private void loadClip(){
		try{
			String path = "http://play.baidu.com/?__m=mboxCtrl.playSong&__a=5946955&__o=/song/5946955||playBtn&fr=altg4||www.baidu.com#loaded";
			Uri uri = Uri.parse(path);
			mp=MediaPlayer.create(this, uri);
			mp.setOnCompletionListener(this);
		}
		catch(Throwable t)
		{
			error(t);
		}
			
	}

	private void setup(){
		loadClip();
		play.setEnabled(true);
		pause.setEnabled(false);
		stop.setEnabled(false);
	}
	
	private void error(Throwable t){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("±®¥Ì¡À").setMessage(t.toString()).setPositiveButton("Ok",null).show();
		
			
	}
}
