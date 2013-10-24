package com.manish.googleprintdemo;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
/**
 * 
 * @author manish
 *
 */

public class MainActivity extends Activity {
	Button btnPrint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnPrint=(Button)findViewById(R.id.button1);
		
		btnPrint.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				 if (isNetworkAvailable() == false) {
						Toast.makeText(MainActivity.this,
								"Network connection not available, Please try later",
								Toast.LENGTH_SHORT).show();
					} else {
						File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/personal/xyz.pdf");
						Intent printIntent = new Intent(MainActivity.this, PrintDialogActivity.class);
						printIntent.setDataAndType(Uri.fromFile(file), "application/pdf");
						printIntent.putExtra("title", "Android print demo");
						startActivity(printIntent);
					}
			}
		});
	}

	public boolean isNetworkAvailable() {

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			Log.e("Network Testing", "***Available***");
			return true;
		}
		Log.e("Network Testing", "***Not Available***");
		return false;
	}

}
