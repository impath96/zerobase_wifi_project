package com.zerobase.wifi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
	
	private Double lat;
	private Double lnt;
	
	public Position(Double lat, Double lnt) {
		this.lat = lat;
		this.lnt = lnt;
	}
	
	public boolean isEmpty() {
		return this.lat == 0.0 && this.lnt == 0.0;
	}
	
	
}
