package com.zerobase.wifi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookmarkGroupDto {
	
	Long id;						// 북마크 그룹 고유 번호
	String name;					// 북마크 그룹 이름
	Integer order;					// 북마크 그룹 순서
	LocalDateTime createdDatetime;	// 북마크 그룹 생성 일자
	LocalDateTime updatedDatetime;  // 북마크 그룹 수정 일자
	
}
