package com.guoantvbox.cs.tvdispatch;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SetMenu_z extends Activity {

	Button channer_inforItem, CA_inforItem, program_managerItem,
			program_searchItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.set_activity_z);

		channer_inforItem = (Button) findViewById(R.id.channer_inforItem);
		CA_inforItem = (Button) findViewById(R.id.CA_inforItem);
		program_managerItem = (Button) findViewById(R.id.program_managerItem);
		program_searchItem = (Button) findViewById(R.id.program_searchItem);

		channer_inforItem.setOnClickListener(buttOnClickListener);
		CA_inforItem.setOnClickListener(buttOnClickListener);
		program_managerItem.setOnClickListener(buttOnClickListener);
		program_searchItem.setOnClickListener(buttOnClickListener);

	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	 getWindow().getDecorView().setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 getWindow().getDecorView().setVisibility(View.INVISIBLE);
	}

	OnClickListener buttOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			switch (arg0.getId()) {

			case R.id.program_managerItem:

				Intent Jmgl_z = new Intent(SetMenu_z.this,
						ProgramManager_z.class);

				startActivity(Jmgl_z);
				
	

				break;
			case R.id.program_searchItem:

		
			
			
				Intent mintent = new Intent();
			mintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
			| Intent.FLAG_ACTIVITY_CLEAR_TASK);

				mintent.setComponent(new ComponentName(
						"com.chonghong.dtv_scan",
						"com.chonghong.dtv_scan.Dtv_Scan_Enter"));

				try {
				  startActivity(mintent);
				} catch (ActivityNotFoundException e) {
					Log.e("set_menu_activity_z",
							"Unable to launch Dtv_Scan_Enter" + " intent="
									+ mintent, e);
				}


				break;
			case R.id.channer_inforItem:

				Intent program_menu_pindaoxinxi = new Intent(
						SetMenu_z.this, Channel_message_z.class);

				startActivity(program_menu_pindaoxinxi);

				break;
			case R.id.CA_inforItem:
		
				Intent program_menu_CA = new Intent(
						SetMenu_z.this, com.changhong.app.ca.TVca.class);

				startActivity(program_menu_CA);

				break;

			default:
				break;
			}

		}

	};
	@Override
	public boolean onKeyDown(int arg0, KeyEvent arg1) {
		// charle �����Ĳ���������Ҫ����
		switch (arg0) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			{
				
				return true;
			}
		case KeyEvent.KEYCODE_VOLUME_UP:
			{
				
				return true;	
			}
		case KeyEvent.KEYCODE_VOLUME_MUTE:
			{
				return true;
			}
		
		}
		return super.onKeyDown(arg0, arg1);
	}
}
