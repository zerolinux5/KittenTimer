package com.ZeroLinux5.kittentimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {

	private long counter;
	private CountDownTimer timer;
	private int countingDown = 0;
	
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
		    if (timer != null) {
		        timer.cancel();
		        countingDown = 0;
		    }
		    displayCount(counter);
		    if (counter > 0) {
		    	timerSet();
		    	countingDown = 1;
		    }
	}
	
	protected void displayCount(long milliseconds) {
		// variables
		TextView countString = (TextView) findViewById(R.id.textView1);
		long seconds = milliseconds / 1000;
		long minutes = seconds / 60;
		
	    seconds = seconds % 60;  
	    minutes = minutes % 60;  
	    
	    String sec = String.valueOf(seconds);  
	    String min = String.valueOf(minutes);  

	    if (seconds < 10)  
	        sec = "0" + seconds;  
	    if (minutes < 10)  
	        min= "0" + minutes;  
	    
		countString.setText(min + " : " + sec);
	}

	public void clickPlus(View v) {
		counter += 60 * 1000;
		if (countingDown == 1){
			clickStartStop(v);
		} else {
			displayCount(counter);
		}
	}
	
	public void clickMinus(View v) {
		counter -= 60 * 1000;
		counter = Math.max(0, counter);		
		if (countingDown == 1){
			clickStartStop(v);
		} else {
			displayCount(counter);
		}
	}
	
	public void clickReset(View v) {
		counter = 0;
		displayCount(counter);
	    if (timer != null) {
	        timer.cancel();
	        countingDown = 0;
	    }
	}
	
	private void timerSet(){
        timer = new CountDownTimer(counter, 1000) {
            public void onTick(long remainingTimeMillis) {
                counter = remainingTimeMillis;
                displayCount(counter);
            }
            public void onFinish() {
                displayCount(0);
                counter = 0;
            }
        };
    timer.start();
	}

}
