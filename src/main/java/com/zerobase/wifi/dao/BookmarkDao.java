package com.zerobase.wifi.dao;

import com.zerobase.wifi.dto.BookmarkDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDao extends SqliteConnection {
	
	public int insert(String wifiMgrNo, Long bookmarkGroupId) {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOOKMARK(BM_G_ID, BM_WF_MGR_NO) VALUES (?, ?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, bookmarkGroupId);
			pstmt.setString(2, wifiMgrNo);
			
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, null);
		}
		
		return result;
	}
	
	public List<BookmarkDto> selectAll() {
		
		List<BookmarkDto> bookmarkList = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT BM.BM_ID, BM.BM_G_ID, WF.X_SWIFI_MGR_NO, BMG.BM_G_NAME, WF.X_SWIFI_MAIN_NM, BM.BM_CREATED_TIME" + 
					" FROM BOOKMARK BM" + 
				    " JOIN WIFI_INFO WF" +
				    	" ON BM.BM_WF_MGR_NO = WF.X_SWIFI_MGR_NO" +
				    " LEFT OUTER JOIN BOOKMARK_GROUP BMG" +
				    	" ON BM.BM_G_ID = BMG.BM_G_ID";
		
		try {
			bookmarkList = new ArrayList<BookmarkDto>();
			
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookmarkDto bookmark = new BookmarkDto();
				bookmark.setBM_ID(rs.getLong("BM_ID"));
				bookmark.setBM_G_ID(rs.getLong("BM_G_ID"));
				bookmark.setWF_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				bookmark.setWF_NAME(rs.getString("X_SWIFI_MAIN_NM"));
				bookmark.setBM_G_NAME(rs.getString("BM_G_NAME"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(rs.getString("BM_CREATED_TIME"), formatter);
				bookmark.setBM_CREATED_TIME(dateTime);
				bookmarkList.add(bookmark);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, rs);
		}
		return bookmarkList;
	}


    public BookmarkDto selectById(Long id) {
		BookmarkDto bookmark = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT BM.BM_ID, BM.BM_G_ID, WF.X_SWIFI_MGR_NO, BMG.BM_G_NAME, WF.X_SWIFI_MAIN_NM, BM.BM_CREATED_TIME" +
				" FROM BOOKMARK BM" +
				" JOIN WIFI_INFO WF" +
				" ON BM.BM_WF_MGR_NO = WF.X_SWIFI_MGR_NO" +
				" JOIN BOOKMARK_GROUP BMG" +
				" ON BM.BM_G_ID = BMG.BM_G_ID" +
				" WHERE BM.BM_ID = ?";

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				bookmark = new BookmarkDto();
				bookmark.setBM_ID(id);
				bookmark.setBM_G_ID(rs.getLong("BM_G_ID"));
				bookmark.setBM_G_NAME(rs.getString("BM_G_NAME"));
				bookmark.setWF_NAME(rs.getString("X_SWIFI_MAIN_NM"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime createdDatetime = LocalDateTime.parse(rs.getString("BM_CREATED_TIME"), formatter);
				bookmark.setBM_CREATED_TIME(createdDatetime);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, rs);
		}
		return bookmark;
    }

	public int delete(Long id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM BOOKMARK WHERE BM_ID = ?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
