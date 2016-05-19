package com.guoantvbox.cs.tvdispatch;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.changhong.dvb.CA;
import com.changhong.dvb.CA_MailContent;
import com.changhong.dvb.DVB;
import com.changhong.dvb.DVBManager;
import com.changhong.dvb.ProtoMessage.DVB_CA_TYPE;

public class EmailContent_z extends Activity{


	private DVBManager dvbManager = null;
	private CA thisCa = null;
	private int miId;
	private String miTime,miTitle;
	private TextView headView,timeView,contentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yj_content_z);
		
		dvbManager=DVB.getManager();
		thisCa=dvbManager.getCaInstance();
		
		miId=getIntent().getIntExtra("id", -1);
		
		
		miTitle=getIntent().getStringExtra("title");
		initView();
	}
	
	private void initView() {
		// ��ʼ���ؼ�
		headView=(TextView)findViewById(R.id.yj_lmdnrbt_z);
		contentView=(TextView)findViewById(R.id.yj_lmdnrnr_z);
		
		headView.setText(miTitle);
		CA_MailContent mailContent = null;
		String text = null;
	
		Log.i("Email_z",
				"mailContent----------------------->miId="+miId);
		if(miId >= 0)
		{
			mailContent = thisCa.getMailContent(miId);
			
			
			Log.i("Email_z",
					"mailContent----------------------->"+mailContent);
		}
		else
		{
		     return;
		}
		if(mailContent != null)
		{
			
			DVB_CA_TYPE curCaType = thisCa.getCurType();
			if(DVB_CA_TYPE.CA_NOVEL  == curCaType)
			{
				text=mailContent.mCaMailContentNovel.mContent;
			}
			else if(DVB_CA_TYPE.CA_SUMA  == curCaType)
			{
				text = mailContent.mCaMailContentSuma.mContent;
			}
			
			Log.i("Email_z",
					"text----------------------->"+text);
			contentView.setText(text);
		}
	}

}
