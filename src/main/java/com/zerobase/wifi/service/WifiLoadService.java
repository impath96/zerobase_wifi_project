package com.zerobase.wifi.service;

import com.google.gson.*;
import com.zerobase.wifi.api.WifiApi;
import com.zerobase.wifi.dao.WifiDao;
import com.zerobase.wifi.dto.WifiInfoDto;

import java.util.ArrayList;
import java.util.List;

// 서울시 wifi 정보 데이터베이스에 저장하는 클래스
public class WifiLoadService {
	
	private WifiDao wifiDao;
	
	public WifiLoadService() {
		wifiDao = new WifiDao();
	}
	
	// 와이파이 정보 저장 후 총 개수 반환
	public int loadWifi() {
		WifiApi wifiApi = new WifiApi();
		Gson gson = new Gson();
		int listTotalCnt = 0;
		int pageIndex = 0;
		while(true) {
			// 서울시 공공 Wifi API 호출 부분 
			String wifiInfoList = wifiApi.getWifiInfoList(pageIndex++); 
			
			List<WifiInfoDto> wifiInfoDtoList = new ArrayList<>();
			JsonElement jsonElement = JsonParser.parseString(wifiInfoList);
			
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			
			JsonObject TbPublicWifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");
			if(TbPublicWifiInfo == null) {
				break;
			}
			listTotalCnt = TbPublicWifiInfo.get("list_total_count").getAsInt();
			
			JsonArray row = TbPublicWifiInfo.getAsJsonArray("row");
			
			if(row != null) {
				row.forEach(e -> {
					JsonObject x = e.getAsJsonObject();
					WifiInfoDto fromJson = gson.fromJson(x, WifiInfoDto.class);
					wifiInfoDtoList.add(fromJson);
					
				});
			}
//			long start = System.nanoTime();
			wifiDao.insertAll(wifiInfoDtoList);
//			long end = System.nanoTime();
//			System.out.println((end - start)/1000000 + "ms");
		}
		return listTotalCnt;
		
	}
	
	

}
