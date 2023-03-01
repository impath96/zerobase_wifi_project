package com.zerobase.wifi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BookmarkDto {
	
	Long BM_ID;						// 북마크 고유 번호
	Long BM_G_ID;					// 북마크 그룹 고유 번호(FK)
	String WF_MGR_NO;				// 와이파이 관리 번호(FK)
	String BM_G_NAME;				// 북마크 그룹 이름
	String WF_NAME;					// 와이파이 이름
	LocalDateTime BM_CREATED_TIME;	// 북마크 생성 일자
	
	/*
	Long BM_ID;
	LocationDateTime BM_CREATED_TIME;
	// 북마크 그룹 
	BookmarkGroupDto bookmarkGroup;
	// 와이파이 정보
	WifiInfoDto wifiInfoDto;
	 */
}
