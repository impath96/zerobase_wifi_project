package com.zerobase.wifi.service;

import com.zerobase.wifi.dao.HistoryDao;
import com.zerobase.wifi.dto.HistoryDto;

import java.util.List;

public class HistoryService {
	
	private HistoryDao historyDao;
	
	// 히스토리 목록 저장하기
	public void saveHistory(HistoryDto history) {
		// DB에 저장할 때 날짜랑 시간, ID은 자동으로 생성
		historyDao = new HistoryDao();
		historyDao.insert(history);
		
	}
	
	// 모든 히스토리 목록 가져오기
	public List<HistoryDto> getHistoryList() {
		historyDao = new HistoryDao();
		return historyDao.selectAll();
	}

}
