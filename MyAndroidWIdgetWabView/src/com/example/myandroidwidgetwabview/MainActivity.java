package com.example.myandroidwidgetwabview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://www.sina.com.cn");
        webview.setWebViewClient(new HelloWebViewClient());
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack())
    	{
    		webview.goBack();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    private class HelloWebViewClient extends WebViewClient{
    	public boolean shouldOverrideUrlLoading(WebView view, String url){
    		view.loadUrl(url);
    		return true;
    	}
    }
}
