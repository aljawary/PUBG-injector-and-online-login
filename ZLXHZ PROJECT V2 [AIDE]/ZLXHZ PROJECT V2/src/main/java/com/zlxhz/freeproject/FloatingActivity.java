/* 
Credits: (Please Dont Remove This 🥺)
Join Telegram @zlxhz For More UI Injector
Thanks You For Using My Base/ UI
My Telegram Channel https://t.me/zlxhz
*/


package com.zlxhz.freeproject;

import android.app.Service;
import android.os.IBinder;
import android.content.Intent;
import android.os.PowerManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.view.WindowManager;
import com.zlxhz.freeproject.R;
import android.view.WindowManager.LayoutParams;
import android.os.Build;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.app.AlertDialog;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.content.SharedPreferences;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.io.File;
import android.graphics.Color;
import android.content.pm.PackageManager;
import android.app.ActivityManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CompoundButton;
import java.util.logging.Handler;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.app.Activity;
import android.text.Html;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import org.json.JSONObject;

public class FloatingActivity extends Service {

    static {
        System.loadLibrary("zeclayx");
    }

	private PowerManager.WakeLock mWakeLock;

    public static final String LOG_TAG = "ZLXHZ";

	private View mainView;

	private WindowManager windowManagerMainView;

	private WindowManager.LayoutParams paramsMainView;

	private LinearLayout layout_main_view;

	private RelativeLayout layout_icon_control_view;

	
	private static boolean flashv1=false;
	private static boolean flashv2=false;

	@Override
	public IBinder onBind(Intent p1)
    {
		return null;
	}

    @SuppressLint({"InvalidWakeLockTag", "WakelockTimeout"})
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (mWakeLock == null)
        {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, LOG_TAG);
            mWakeLock.acquire();
        }
        return START_NOT_STICKY;
    }
    
	private void ShowMainView()
    {
        mainView = LayoutInflater.from(this).inflate(R.layout.activity_service, null);
        paramsMainView = getParams();
        windowManagerMainView = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManagerMainView.addView(mainView, paramsMainView);
        InitShowMainView();
    }
	Boolean isInBackground;
	private void InitShowMainView()
    {
		layout_icon_control_view = mainView.findViewById(R.id.layout_icon_control_view);
		layout_main_view = mainView.findViewById(R.id.layout_main_view);
		LinearLayout layout_close_main_view = mainView.findViewById(R.id.layout_close_main_view);
		layout_close_main_view.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
                {
					layout_main_view.setVisibility(View.GONE);
					layout_icon_control_view.setVisibility(View.VISIBLE);
				}
			});
        RelativeLayout layout_view = mainView.findViewById(R.id.layout_view);
        layout_view.setOnTouchListener(onTouchListener());
        InitBaseView();
	}
	
	
    private void InitBaseView()
    {
        ImageView btnMenu1 = mainView.findViewById(R.id.menu_1);
        ImageView btnMenu2 = mainView.findViewById(R.id.menu_2);
        final LinearLayout layoutMenu1 = mainView.findViewById(R.id.layout_menu1);
        final LinearLayout layoutMenu2 = mainView.findViewById(R.id.layout_menu2);
        btnMenu1.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1)
                {
                    layoutMenu1.setVisibility(View.VISIBLE);
                    layoutMenu2.setVisibility(View.GONE);
                }
            });
          btnMenu2.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1)
                {
                    layoutMenu2.setVisibility(View.VISIBLE);
                    layoutMenu1.setVisibility(View.GONE);
                }
            });

        Switch X1 = mainView.findViewById(R.id.X1);
        X1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                           ExecZLX("/cpp 9999","Bypass Logo Actived");
                    }
                }
            });

        Switch X2 = mainView.findViewById(R.id.X2);
        X2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                       ExecZLX("/cpp 9999","Bypass Lobby Actived");
                    }
                }
            });


        Switch X3 = mainView.findViewById(R.id.X3);
        X3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                    ExecZLX("/cpp 9999","Small Crosshair ON");
                    }
                    else
                    {
                    ExecZLX("/cpp 9999","Small Crosshair OFF");
                    }
                }
            });

        RadioGroup reducerecoil = mainView.findViewById(R.id.reducerecoil);
        RadioButton ROFF = mainView.findViewById(R.id.ROFF);
        ROFF.setChecked(true);
        reducerecoil.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ROFF:
                           ExecZLX("/cpp 9999","Normal Recoil");
                        break;
                    case R.id.RLESS:
                           ExecZLX("/cpp 9999","Less Recoil");
                        break;
                    case R.id.RFULL:
                          ExecZLX("/cpp 9999","No Recoil");
                        break;
                }
            }
        });
           
        SeekBar seekbar_wideview = mainView.findViewById(R.id.seekbar_wideview);
        final TextView textview_seekbar = mainView.findViewById(R.id.textview_wideview);        
        seekbar_wideview.incrementProgressBy(10);
        seekbar_wideview.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar p1, int progress, boolean p3)
                {
                    progress = progress / 1;
                    progress = progress * 1;
                    textview_seekbar.setText(String.valueOf(progress));
                }
                @Override
                public void onStartTrackingTouch(SeekBar p1)
                {
                }
                @Override
                public void onStopTrackingTouch(SeekBar p1)
                {
                    int progress = p1.getProgress();
                    progress = progress / 1;
                    progress = progress * 1;
                    if (progress == 0)
                    {
                        ExecZLX("/cpp 9999","Wide View OFF");
                    }
                    else if (progress == 1)
                    {
                        ExecZLX("/cpp 9999","Wide View 1/5");
                    }
                    else if (progress == 2)
                    {
                        ExecZLX("/cpp 9999","Wide View 2/5");
                    }
                    else if (progress == 3)
                    {
                        ExecZLX("/cpp 9999","Wide View 3/5");
                    }
                    else if (progress == 4)
                    {
                        ExecZLX("/cpp 9999","Wide View 4/5");
                    }
                    else if (progress == 5)
                    {
                        ExecZLX("/cpp 9999","Wide View 5/5");
                    }
                }
            });
        seekbar_wideview.setProgress(0);
        textview_seekbar.setText(String.valueOf(seekbar_wideview.getProgress()));
        
            
        Switch X4H = mainView.findViewById(R.id.X4H);
        X4H.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                     ExecZLX("/cpp 9999","Auto Headshot High ON");
                    }
                    else
                    {
                     ExecZLX("/cpp 9999","Auto Headshot High OFF");
                    }
                }
            });
            
        Switch X4L = mainView.findViewById(R.id.X4L);
        X4L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                     ExecZLX("/cpp 9999","Auto Headshot Low ON");
                    }
                    else
                    {
                     ExecZLX("/cpp 9999","Auto Headshot Low OFF");
                    }
                }
            });
            
        Switch X5 = mainView.findViewById(R.id.X5);
        X5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                     ExecZLX("/cpp 9999","Aimbot ON");
                    }
                    else
                    {
                     ExecZLX("/cpp 9999","Aimbot OFF");
                    }
                }
            });

            

        Switch X6 = mainView.findViewById(R.id.X6);
        X6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                     ExecZLX("/cpp 9999","Night Mode ON");
                    }
                    else
                    {
                     ExecZLX("/cpp 9999","Night Mode OFF");
                    }
                }
            });
            
        Switch X7 = mainView.findViewById(R.id.X7);
        X7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                     ExecZLX("/cpp 9999","Remove Fog ON");
                    }
                    else
                    {
                     ExecZLX("/cpp 9999","Remove Fog OFF");
                    }
                }
            });
            
        Switch X8 = mainView.findViewById(R.id.X8);
        X8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                     ExecZLX("/cpp 9999","Remove Grass ON");
                    }
                    else
                    {
                     ExecZLX("/cpp 9999","Remove Grass OFF");
                    }
                }
            });
            
        Switch X9 = mainView.findViewById(R.id.X9);
        X9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                     ExecZLX("/cpp 9999","Black Sky ON");
                    }
                    else
                    {
                     ExecZLX("/cpp 9999","Black Sky OFF");
                    }
                }
            });
						
	    final Button X14 = mainView.findViewById(R.id.X14);
        X14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	if (!flashv1) {
	             	ExecZLX("/cpp 9999","Flash V1 ON");
				    flashv1=true;
					X14.setText("FLASH V1 ON");
					} else {
				   ExecZLX("/cpp 9999","Flash V1 OFF");
				    flashv1=false;
				    X14.setText("FLASH V1 OFF");
					}
                }
            });

	    final Button X15 = mainView.findViewById(R.id.X15);
        X15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	if (!flashv2) {
						ExecZLX("/cpp 9999","Flash V2 ON");
						flashv2=true;
						X15.setText("FLASH V2 ON");
					} else {
						ExecZLX("/cpp 9999","Flash V2 OFF");
						flashv2=false;
						X15.setText("FLASH V2 OFF");
					}
                }
            });

   }

  
   
	private View.OnTouchListener onTouchListener()
    {
        return new View.OnTouchListener() {
            final View collapsedView = layout_icon_control_view;
            final View expandedView = layout_main_view;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        initialX = paramsMainView.x;
                        initialY = paramsMainView.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);
                        if (Xdiff < 10 && Ydiff < 10)
                        {
                            if (isViewCollapsed())
                            {
								collapsedView.setVisibility(View.GONE);
								expandedView.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        paramsMainView.x = initialX + (int) (event.getRawX() - initialTouchX);
                        paramsMainView.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManagerMainView.updateViewLayout(mainView, paramsMainView);
                        return true;
                }
                return false;
            }
        };
    }

	private boolean isViewCollapsed()
    {
        return mainView == null || layout_icon_control_view.getVisibility() == View.VISIBLE;
    }
	private WindowManager.LayoutParams getParams()
    {
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			getLayoutType(),
			getFlagsType(),
			PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 0;
        return params;
    }

	private static int getLayoutType()
    {
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_TOAST;
        }
        else
        {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        return LAYOUT_FLAG;
    }
	private int getFlagsType()
    {
        int LAYOUT_FLAG = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        return LAYOUT_FLAG;
    }
	private int dpToPx(int dp)
    {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void ExecZLX(String path, String toastHZ)
    {
        try
        {
        	// ROOT
            ExecuteElf("su -c chmod 777 " + getFilesDir() + path);
            ExecuteElf("su -c " + getFilesDir() + path);
            // VIRTUAL
            ExecuteElf("chmod 777 " + getFilesDir() + path);
            ExecuteElf("" + getFilesDir() + path);
            Toast.makeText(FloatingActivity.this, toastHZ, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
        }
    }

    private void ExecuteElf(String shell)
	{
		String s=shell;
		try
		{
			Runtime.getRuntime().exec(s, null, null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate()
    {
		super.onCreate();
        ShowMainView();
	}

	@Override
	public void onDestroy()
    {
		super.onDestroy();
		if (mWakeLock != null)
        {
            mWakeLock.release();
            mWakeLock = null;
        }
		if (mainView != null)
        {
			windowManagerMainView.removeView(mainView);
		}
	}
}
