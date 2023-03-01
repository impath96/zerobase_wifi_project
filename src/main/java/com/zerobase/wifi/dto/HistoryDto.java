package com.zerobase.wifi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class HistoryDto {

	private Long id;						// 위치 히스토리 고유번호
	private Double x_coordinate;			// 위치 히스토리 X좌표
	private Double y_coordinate;			// 위치 히스토리 Y좌표
	private LocalDateTime created_datetime;	// 위치 히스토리 생성일자
}
