package com.zerobase.wifi.dao;

import com.zerobase.wifi.dto.HistoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao extends SqliteConnection{

	
	// history 저장 
	public void insert(HistoryDto history) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO HISTORY (X_COORDINATE, Y_COORDINATE) VALUES (?, ?)";

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setDouble(1, history.getX_coordinate());
			pstmt.setDouble(2, history.getY_coordinate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(connection, pstmt, rs);
		}

	}
	// 모든 히스토리 목록 가져오기
	public List<HistoryDto> selectAll() {
		
		List<HistoryDto> historyList = null; 
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT HISTORY_ID, X_COORDINATE, Y_COORDINATE, CREATED_DATETIME FROM HISTORY ORDER BY HISTORY_ID DESC";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			historyList = new ArrayList<>();
			while(rs.next()) {
				HistoryDto history = new HistoryDto();
				history.setId(rs.getLong(1));
				history.setX_coordinate(rs.getDouble(2));
				history.setY_coordinate(rs.getDouble(3));
				// String -> LocalDateTime으로 변환 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(rs.getString(4), formatter);
				history.setCreated_datetime(dateTime);

				historyList.add(history);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, rs);
		}
		return historyList;
	}
	
	
	
}
