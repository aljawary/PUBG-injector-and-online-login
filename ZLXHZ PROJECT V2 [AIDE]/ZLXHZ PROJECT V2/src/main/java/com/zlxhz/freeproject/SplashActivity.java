/* 
Credits: (Please Dont Remove This 🥺)
Join Telegram @zlxhz For More UI Injector
Thanks You For Using My Base/ UI
My Telegram Channel https://t.me/zlxhz
*/


package com.zlxhz.freeproject;

import android.app.Activity;
import android.os.Bundle;
import com.zlxhz.freeproject.R;
import android.os.Handler;
import android.content.Intent;
import android.content.Context;

public class SplashActivity extends Activity { 

    static {
        System.loadLibrary("zeclayx");
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		int TIME_SPLASH = 1500;
		new Handler().postDelayed(new Runnable() {
				@Override
				public void run() { 
					startActivity(new Intent(getApplicationContext(), LoginActivity.class));
					finish();
				}
			}, TIME_SPLASH);
	}

}

