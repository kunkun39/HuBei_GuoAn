package com.guoantvbox.cs.tvdispatch;

import org.json.JSONException;
import org.json.JSONObject;

import com.changhong.app.constant.Advertise_Constant;

import android.util.Log;

public class Common {
	private static final String TAG = "com.changhong.app.dtv";
	private static final boolean b_NomalInfo = true;
	private static final boolean b_DebugInfo = true;
	private static final boolean b_ERRInfo = true;

	public static void LOGI(String mMessage) {
		if (b_NomalInfo) {
			Log.i(TAG, mMessage);
		}
	}

	public static void LOGD(String mMessage) {
		if (b_DebugInfo) {
			Log.d(TAG, mMessage);
		}
	}

	public static void LOGE(String mMessage) {
		if (b_ERRInfo) {
			Log.e(TAG, mMessage);
		}
	}

	/**
	 * @authorOscarChang
	 * @params to get advertise
	 * @channeId
	 */
	public static JSONObject getParams(String channelId, String positionId, String IpAddress) {
		JSONObject reqParams = new JSONObject();
		try {
			// channelId = "1010";
			// reqParams.put("catalog_id", "catalog_id");//
			// 在VOD应用或者其他以树状方式呈现信息的应用下，当前所在页面位置标识
			reqParams.put("channel_id", channelId);// 在广播应用下的频道标识
			reqParams.put("adposition_id", positionId);// 广告位Id
			reqParams.put("ip", IpAddress);// IP或者Node Group ID（必填）
			reqParams.put("user_id", Advertise_Constant.CARD_USER_ID);// CA智能卡卡号
			reqParams.put("application_id", Advertise_Constant.APPLICATION_ID);

			// reqParams.put("decision_id", "decision_id");// 决策Id
			reqParams.put("device_id", Advertise_Constant.DEVICE_ID);// 设备标识（必填）
			reqParams.put("device_type", Advertise_Constant.DEVICE_TYPE);// 设备类型，手机、机顶盒（必填）
			// reqParams.put("provider_id", "provider_id");
			// reqParams.put("provider_asset_id", "provider_asset_id");
			// reqParams.put("application_path", "application_path");

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return reqParams;
	}

}
