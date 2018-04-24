package com.example.administrator.yunproject.Until;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;

//import static com.project.hudong.Activity.WebViewActivity.adId;

public class JSObject {
	/**
	 * userToken参数加密串(android )
	 */
	public final static String ANDROID_USER_TOKEN_PWD="8-1f83cccD7cf365";
		/*
		 * 绑定的object对象
		 * */
	    private Context context;
		private int adId;
		public JSObject(Context context, int adId){
			this.context = context;
			this.adId = adId;
		}

		/*
		 * JS调用android的方法
		 * @JavascriptInterface仍然必不可少
		 *
		 * */
		@JavascriptInterface
		public String saveRedBag(){
			SharedPreferences sharedPreferencesGood = context.getSharedPreferences("mySP", Context.MODE_PRIVATE);
			String userToken = sharedPreferencesGood.getString("userToken", "");
//			Toast.makeText(context, "红包调用", Toast.LENGTH_SHORT).show();
			String platform = "1";

//			MCLog.e("TAG","saveRedBagToken===" + userToken);
			String json ="{'userToken':'"+userToken+"','adId':'"+adId+"','platform':'"+platform+"'}";
			return json;
		}


//		@JavascriptInterface
//		public void saveToken(String token){
//			//创建异或获取一个已经存在的sharedPreferences对象（单例的）
//			SharedPreferences sharedPreferences = context.getSharedPreferences("mySP", Context.MODE_PRIVATE);
//			//创建数据编辑器
//			SharedPreferences.Editor editor = sharedPreferences.edit();
//			try {
//				String userToken = AESUtils.aesDecrypt(token,ANDROID_USER_TOKEN_PWD);
////				Toast.makeText(context, userToken, Toast.LENGTH_SHORT).show();
//				editor.putString("userToken", userToken);
////				MCLog.e("TAG","userToken===" + userToken);
//				//保存数据
//				editor.commit();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		@JavascriptInterface
		public void goBack(){

		}



//	//跳转到我的收货
//	public void hdslharvest(){
//		WebViewActivity.instance.finish();
//		Intent mHdslIntent = new Intent(context,GoodActivity.class);
//		context.startActivity(mHdslIntent);
//
//	}



//	@JavascriptInterface
//	public void hdslpop(){
//		WebViewActivity.instance.finish();
////		Intent mHdslIntent = new Intent(context,GoodActivity.class);
////		context.startActivity(mHdslIntent);
//
//	}


//	public String
}
