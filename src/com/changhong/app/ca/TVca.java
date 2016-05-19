package com.changhong.app.ca;

import com.guoantvbox.cs.tvdispatch.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TVca extends Activity implements OnClickListener {

	private Button caoperator, cainfoBtn, caclassBtn, worktimeBtn, pinBtn, signalBtn,
			emailBtn,camotherchildbtn;

	Animation scaleBigAnim, scaleSmallAnim;

	ImageView caFocus;

	FrameLayout frameLayout = null;

	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camain);
		initView();
	}

	private void initView() {
//		// TODO Auto-generated method stub
		mContext = TVca.this;
		caoperator = (Button) findViewById(R.id.button_operator);
		caoperator.setOnClickListener(this);
		cainfoBtn = (Button) findViewById(R.id.button_cainfo);
		cainfoBtn.setOnClickListener(this);
		pinBtn = (Button) findViewById(R.id.button_pininfo);
		caclassBtn = (Button) findViewById(R.id.button_watchlevel);
		worktimeBtn = (Button) findViewById(R.id.button_worktime);
		camotherchildbtn = (Button) findViewById(R.id.button_cacard);
		pinBtn.setOnClickListener(this);
		caclassBtn.setOnClickListener(this);
		worktimeBtn.setOnClickListener(this);
		camotherchildbtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button_operator:
			startActivity(new Intent(mContext, com.changhong.app.ca.CAoperatorInfo.class));
			break;
		case R.id.button_cainfo:
			startActivity(new Intent(mContext, CAinfo.class));
			break;
		case R.id.button_watchlevel:
			startActivity(new Intent(mContext, CAclass.class));
			break;
		case R.id.button_worktime:
			startActivity(new Intent(mContext, CAworkTime.class));
			break;
		case R.id.button_pininfo:
			startActivity(new Intent(mContext, CApin.class));
			break;
		case R.id.button_cacard:
			startActivity(new Intent(mContext, CAMotherChildCopy.class));
			break;
//		case R.id.button_email:
//			startActivity(new Intent(mContext, CAEmail.class));
//			break;
		}
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
	
	

}
