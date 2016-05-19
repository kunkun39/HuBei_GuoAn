package com.guoantvbox.cs.tvdispatch;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.changhong.app.book.BookInfo;



public class EpgWarn extends Activity implements OnClickListener{


	SysApplication	objApplication;
	Context  context;
	private final UI_Handler mUiHandler = new UI_Handler(this);
	private TextView	tvSecond;
	private static int iSecond = 60;
	
	private Button buttonok,buttoncancel;
	
	private      com.guoantvbox.cs.tvdispatch.TextMarquee textView;
	private BookInfo bookInfo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.epgwarn);
		
		context=EpgWarn.this;
		Log.i("EpgWarn","EpgWarn   ----》onCreate");
		
        objApplication	=	SysApplication.getInstance();
        objApplication.initDtvApp(this);
        
        
        
	   bookInfo=(BookInfo) getIntent().getSerializableExtra("bookinfo");
	   

		Log.i("EpgWarn","Reciver  is   running ------ bookInfo.bookTimeStart-----bookInfo.bookChannelName ---- bookInfo.bookEnventName" +bookInfo.bookTimeStart+"    " +bookInfo.bookChannelName+" "+ bookInfo.bookEnventName  );
		mUiHandler.sendEmptyMessage(0);
		
		iSecond = 60;
		
		if(!objApplication.isBookedChannel(bookInfo))
		{
			Common.LOGE("Book channel is deleted,exit !");
			
		Log.i("EpgWarn","Book channel is deleted,exit");
			finish();
			objApplication.exit();
			android.os.Process.killProcess(android.os.Process.myPid());

		}
		
		
		 objApplication.delBookChannel(bookInfo.bookDay, bookInfo.bookTimeStart);
		
		String bookContent=new String();
		
		/*bookContent="你预约的：+bookInfo.bookChannelName
				+"\n"+bookInfo.bookDay+"/"+
				bookInfo.bookTimeStart+"\n"+
				bookInfo.bookEnventName;*/
		
		
		bookContent="你预约的："+bookInfo.bookChannelName
				+" "+bookInfo.bookDay+"/"+
			bookInfo.bookTimeStart+" "+
				bookInfo.bookEnventName;
		
		initView();
		textView.setText(bookContent);
		
		
//		doFinish();
		buttonok.setOnClickListener(this);
		buttoncancel.setOnClickListener(this);
	}
	private void doFinish()
	{
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				finish();
				
				objApplication.exit();
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		}, 0);
	}
	private void initView() {
		// TODO Auto-generated method stub
		
		buttonok=(Button)findViewById(R.id.guankanjiemu);
		buttoncancel=(Button)findViewById(R.id.cancelguankanjiemu);
		tvSecond=(TextView)findViewById(R.id.idsecond);
		
		buttonok.setFocusable(true);
		buttonok.setFocusableInTouchMode(true);
		buttonok.requestFocus();
		buttonok.requestFocusFromTouch();
		
		textView=(com.guoantvbox.cs.tvdispatch.TextMarquee)findViewById(R.id.jiemuinfo);
		
		
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == R.id.guankanjiemu)
		{
		
			objApplication.playChannel(bookInfo.bookChannelIndex, false);
			
	    Intent showBanneForYuYueDialog = new Intent();
	    showBanneForYuYueDialog.setAction("showBanneForYuYueDialog");
	    context.sendBroadcast(showBanneForYuYueDialog);
		mUiHandler.removeMessages(0);
		
		Intent intent=new Intent(EpgWarn.this,Main.class);
        startActivity(intent);
	     
	        finish();
		}
		else
		{
			mUiHandler.removeMessages(0);
		       finish();
			
		}

	}
	
	@Override
	protected void onPause() {
		
		super.onPause();
		
		if(mUiHandler != null)
		{
			mUiHandler.removeMessages(0);
		}

		
	}

	static class UI_Handler extends Handler {
		WeakReference<EpgWarn> mActivity;

		UI_Handler(EpgWarn activity) {
			mActivity = new WeakReference<EpgWarn>(activity);
		}

		@Override
		public void handleMessage(Message msg) {

			EpgWarn theActivity = mActivity.get();
			

			Log.i("Epgwarn", "now second  ->  " + iSecond);
			if(iSecond >0 )
			{
				theActivity.tvSecond.setText("("+iSecond+")");
				theActivity.mUiHandler.sendEmptyMessageDelayed(0, 1000);
				iSecond--;
				if(iSecond == 0)
				{
					Log.i("Epgwarn", "Start main !");
			
					theActivity.objApplication.playChannel(theActivity.bookInfo.bookChannelIndex, false);
					Intent intent=new Intent(theActivity,Main.class);
					theActivity.startActivity(intent);
				
					theActivity.finish();
				}
			}


			
		}
	}
	
}
