package com.changhong.app.ca;

import com.changhong.dvb.CA;
import com.changhong.dvb.DVB;
import com.changhong.dvb.DVBManager;
import com.guoantvbox.cs.tvdispatch.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CAclass extends Activity implements OnClickListener {

	private EditText rateText;
	private EditText pwdText;
	private Button sureBtn;
	private DVBManager dvbManager;
	private CA ca;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caclass);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		dvbManager = DVB.getManager();
		ca = dvbManager.getCaInstance();
		rateText = (EditText) findViewById(R.id.class_input_rate);
		pwdText = (EditText) findViewById(R.id.class_input_code);
		sureBtn = (Button) findViewById(R.id.button_class_sure);
		sureBtn.setOnClickListener(this);

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent arg1) {

		switch (keyCode) {

		case KeyEvent.KEYCODE_VOLUME_DOWN: {

			return true;
		}
		case KeyEvent.KEYCODE_VOLUME_UP: {

			return true;
		}
		case KeyEvent.KEYCODE_VOLUME_MUTE: {
			return true;
		}

		}
		return super.onKeyDown(keyCode, arg1);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String rateString = rateText.getText().toString();
		String pwdString = pwdText.getText().toString();
		if (rateString.length() != 0 && pwdString.length() != 0) {
			if (Integer.parseInt(rateString) >= 4
					&& Integer.parseInt(rateString) <= 18) {
				// ����CA
				int rate = Integer.parseInt(rateString);
				int result = ca.setRating(pwdString, rate);
				if (result ==0) {
					finish();
					Toast.makeText(this, "已设置观看级别" + rate, Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(CAclass.this, "观看级别设置失败,检查一下密码是否正确!", Toast.LENGTH_LONG)
							.show();
				}

			} else {
				Toast.makeText(CAclass.this, "观看级别只能是4到18的数字!",
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(CAclass.this, "观看级别和密码不能为空!", Toast.LENGTH_LONG)
					.show();
		}
	}

}
