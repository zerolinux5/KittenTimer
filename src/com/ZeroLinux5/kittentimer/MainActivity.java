package com.ZeroLinux5.kittentimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private long counter;
	private CountDownTimer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Prevents the screen from dimming and going to sleep. 
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		counter = 0;
		timer = null;		
	}
	
	@Override
	protected void onPause() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		super.onPause();
	}
	
	public void clickStartStop(View v) {
		
	}
	
	public void clickPlus(View v) {
		counter += 60 * 1000;
	}
	
	public void clickMinus(View v) {
		counter -= 60 * 1000;
		counter = Math.max(0, counter);		
	}
	
	public void clickReset(View v) {
		
	}
}
