/* 
Credits: (Please Dont Remove This 🥺)
Join Telegram @zlxhz For More UI Injector
Thanks You For Using My Base/ UI
My Telegram Channel https://t.me/zlxhz
*/


package com.zlxhz.freeproject;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.ImageView;
import android.widget.Magnifier.Builder;
import java.net.URLConnection;
import java.io.IOException;
import android.annotation.SuppressLint;
import org.json.JSONObject;
import org.json.JSONException;


public class LoginActivity extends Activity {

    static {
        System.loadLibrary("zeclayx");
    }

    static native boolean Check();
    private native String loginURL();
    private String USER = "USER";
    private String PASS = "PASS";
    private Prefs prefs;
    private Button signin;
	private String duser, dpass;
	private EditText mail, pass;
	private Boolean is_conect=false;
	private CheckBox rememberme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
		
		mail = findViewById(R.id.mail);
	    pass = findViewById(R.id.pass);
	    rememberme = findViewById(R.id.rememberme);
        signin = findViewById(R.id.init);

        prefs = Prefs.with(this);
        mail.setText(prefs.read(USER, ""));
        pass.setText(prefs.read(PASS, ""));
 
        signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mail.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                              new lf(LoginActivity.this).execute(loginURL());
						 	} else {
							Toast.makeText(LoginActivity.this, "Cant Be Empty!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
	}
	
	
	    class lf extends AsyncTask<String, String, String> {

        private Context context;
        private ProgressDialog progress;

        public lf (Context ctx){
            this.context = ctx;
            this.progress = new ProgressDialog(ctx);
            this.progress.setCancelable(false);
            this.progress.setMessage("Sign in...");
        }

        @Override
        protected void onPreExecute() {
            if (!progress.isShowing()){
                progress.show();
            }
        }

        @Override
        protected String doInBackground(String... string) {
            BufferedReader buffR = null;
            try {
                String url_address = string[0];
                URL url = new URL(url_address);
                URLConnection urlConnection = url.openConnection();
                buffR = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer strBuffer = new StringBuffer();
                String line;
                while ((line = buffR.readLine()) != null){
                    strBuffer.append(line);
                }
                return strBuffer.toString();
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if (buffR != null){
                    try {
                        buffR.close();
                    } catch (IOException e) {}
                }
            }
            return null;
        }

        @SuppressLint({"WrongConstant", "ShowToast"})
        @Override
        protected void onPostExecute(String result1) {
            if (progress.isShowing()) progress.dismiss();
            if (result1 != null) {
                try {
                    JSONObject object = new JSONObject(result1);
                    duser = object.get("user").toString();
                    dpass = object.get("pass").toString();
                    is_conect = object.getBoolean("is_conect");
                    if (is_conect) {
                  if (mail.getText().toString().equals(duser) && pass.getText().toString().equals(dpass)) {
                 	if (rememberme.isChecked()){
                    	prefs.write(USER, mail.getText().toString());
                        prefs.write(PASS, pass.getText().toString());
                        Check();
                    	startActivity(new Intent(getApplicationContext(), MainActivity.class));
				        finish();
                        } else {
                        prefs.write(USER, "");
                        prefs.write(PASS, "");
                        Check();
                    	startActivity(new Intent(getApplicationContext(), MainActivity.class));
					    finish();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Invalid User or Pass!", Toast.LENGTH_SHORT).show();
                        }
                        } else {
                        	Toast.makeText(getBaseContext(), "cant connect to Server\n[is_conect=false]!", Toast.LENGTH_SHORT).show();
                        	}
                } catch (final JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getBaseContext(), "Error Internet Or Server!", Toast.LENGTH_SHORT).show();
            }
        }
    }
	
}


