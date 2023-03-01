package com.zerobase.wifi.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 
public class HttpClientUtils {

	public static String doGet(String requestURL) throws IOException {
		String message = null;
		
		OkHttpClient client = new OkHttpClient();
		Request httpRequest = new Request.Builder()
				.url(requestURL)
				.build();
                    
        //동기 처리시 execute함수 사용 
		Response httpResponse = client.newCall(httpRequest).execute();
		message = httpResponse.body().string();
		int code = httpResponse.code();
		
		return message;
	}
	
}
