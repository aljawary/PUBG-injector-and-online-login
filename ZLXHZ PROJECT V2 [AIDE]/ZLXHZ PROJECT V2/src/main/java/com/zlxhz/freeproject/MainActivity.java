/* 
Credits: (Please Dont Remove This 🥺)
Join Telegram @zlxhz For More UI Injector
Thanks You For Using My Base/ UI
My Telegram Channel https://t.me/zlxhz
*/


package com.zlxhz.freeproject;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import com.zlxhz.freeproject.R;
import android.provider.Settings;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import com.zlxhz.freeproject.FloatingActivity;
import android.content.pm.PackageManager;
import android.widget.Toast;
import java.util.*;
import android.os.*;
import android.util.*;
import java.net.URL;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.net.ssl.TrustManagerFactory;
import android.util.LogPrinter;
import java.net.Authenticator;
import android.app.IntentService;
import android.util.Log;
import android.os.HandlerThread;
import android.content.Intent;
import android.view.*;
import android.view.View.*;
import com.zlxhz.freeproject.LoginActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.app.IntentService;
import android.content.Intent;
public class MainActivity extends Activity { 

    static {
        System.loadLibrary("zeclayx");
    }

	private static native String Tele();
	private static native String TeleCH();
    private Button Start_btn, Stop_btn, logout, ZTelegram, ZTelegramCH;
    public static int REQUEST_OVERLAY_PERMISSION = 5469;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        initialization();
        FloatingPermissions();
		
        Start_btn = (Button) findViewById(R.id.Start_btn);
		Stop_btn = (Button) findViewById(R.id.Stop_btn);
		ZTelegram = (Button) findViewById(R.id.telegram);
		ZTelegramCH = (Button) findViewById(R.id.telegramch);
		logout = (Button) findViewById(R.id.logout_btn);

        Start_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartFloatingService();
                }
            });
        Stop_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StopFloatingService();
                }
            });
       ZTelegram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
	            GoToTelegram();
				}
            });
       ZTelegramCH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
	            GoToTelegramCH();
				}
            });
        logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
					startActivity(intent);
					StopFloatingService();
					finish();
					}
            });
    }

    private void FloatingPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(this)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("This application requires window overlays access permission, please allow first.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface p1, int p2) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                                       Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
                        }
                    });
                builder.setCancelable(false);
                builder.show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    FloatingPermissions();
                } 
            }
        }
    }
    private void StartFloatingService(){
        startService(new Intent(this, FloatingActivity.class));
    }
	
	private void GoToTelegram(){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Tele()));
		startActivity(browserIntent);
	}

    private void GoToTelegramCH(){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(TeleCH()));
		startActivity(browserIntent);
	}


    private void StopFloatingService(){
        stopService(new Intent(this, FloatingActivity.class));
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            StopFloatingService();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;                       
                }
            }, 2000);
    } 


    private void initialization(){
    	if (!LoginActivity.Check()) { //Jump Login Protection
        	finishAffinity();
        } else {
         //Import cpp from assets
        MvAssets(getFilesDir() + "/","cpp");
        //MvAssets(getFilesDir() + "/","cpp2");   
    	}
		Handler Handler = new Handler();
		Handler.postDelayed(new Runnable() {
				@Override
				public void run() {}}, 1000);
	}

	private boolean MvAssets(String outPath, String fileName)
	{
		File file = new File(outPath);
		if (!file.exists())
		{
			if (!file.mkdirs())
			{
				Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
				return false;
			}
		}
		try
		{
			InputStream inputStream = getAssets().open(fileName);
			File outFile = new File(file, fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			byte[] buffer = new byte[1024];
			int byteRead;
			while (-1 != (byteRead = inputStream.read(buffer)))
			{
				fileOutputStream.write(buffer, 0, byteRead);
			}
			inputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
} 


