package com.zerobase.wifi.service;

import com.zerobase.wifi.dao.WifiDao;
import com.zerobase.wifi.dto.Position;
import com.zerobase.wifi.dto.WifiInfoDto;

import java.util.List;

public class WifiService {
	
	private WifiDao wifiDao;
	
	public WifiInfoDto getWifiDetail(String mgrNo) {
		wifiDao = new WifiDao();
		return wifiDao.selectByMgrNo(mgrNo);
		
	}
	
	public List<WifiInfoDto> getList(Position position) {
		wifiDao = new WifiDao();
		return wifiDao.selectAllByPosition(position);
		
	}

}
