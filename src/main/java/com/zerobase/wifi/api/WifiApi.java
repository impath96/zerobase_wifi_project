package com.zerobase.wifi.api;

import com.zerobase.wifi.util.HttpClientUtils;

import java.io.IOException;

public class WifiApi {
	
	private final int maxPageSize = 1000;
	private String baseURL = "http://openapi.seoul.go.kr:8088/635970494c616b733132356c46524846/json/TbPublicWifiInfo/";
	
	public String getWifiInfoList(int pageIndex) {
		int startPage = maxPageSize * pageIndex + 1;
		int endPage = (pageIndex + 1) * maxPageSize;
		System.out.println(startPage + " ~ " + endPage + "시작");
		
		String url = baseURL + startPage +"/" + endPage;
		
		String jsonString = null;
		
		try {
			jsonString = HttpClientUtils.doGet(url);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println(startPage + " ~ " + endPage + "끝");

		return jsonString;
		
		
	}

}
