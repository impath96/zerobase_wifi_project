package com.zerobase.wifi.dao;

import com.zerobase.wifi.dto.BookmarkGroupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDao extends SqliteConnection{
	
	public int insert(BookmarkGroupDto bookmarkGroup) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String COLUM = "BM_G_NAME, BM_G_ORDER";
		String sql = "INSERT INTO BOOKMARK_GROUP ("+ COLUM +")" +
					" VALUES (?,?)";
		int result = 0;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bookmarkGroup.getName());
			pstmt.setInt(2, bookmarkGroup.getOrder());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, null);
		}
		
		return result;
	}

	public List<BookmarkGroupDto> selectAll() {
		List<BookmarkGroupDto> bookmarkGroupList = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOOKMARK_GROUP";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			bookmarkGroupList = new ArrayList<>();
			while(rs.next()) {
				BookmarkGroupDto bookmarkGroup = new BookmarkGroupDto();
				bookmarkGroup.setId(rs.getLong("BM_G_ID"));
				bookmarkGroup.setName(rs.getString("BM_G_NAME"));
				bookmarkGroup.setOrder(rs.getInt("BM_G_ORDER"));
				// String -> LocalDateTime으로 변환 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime createdDatetime = LocalDateTime.parse(rs.getString("BM_G_CREATED_DATETIME"), formatter);
				bookmarkGroup.setCreatedDatetime(createdDatetime);

				if(rs.getString("BM_G_UPDATED_DATETIME") != null && !rs.getString("BM_G_UPDATED_DATETIME").isEmpty()) {
					LocalDateTime updatedDatetime = LocalDateTime.parse(rs.getString("BM_G_UPDATED_DATETIME"), formatter);
					bookmarkGroup.setUpdatedDatetime(updatedDatetime);
				}
				
				bookmarkGroupList.add(bookmarkGroup);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, rs);
		}
		return bookmarkGroupList;
	}
	
	public BookmarkGroupDto selectById(Long id) {
		BookmarkGroupDto bookmarkGroup = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT BM_G_ID, BM_G_NAME, BM_G_ORDER, BM_G_CREATED_DATETIME, BM_G_UPDATED_DATETIME" +
					 " FROM BOOKMARK_GROUP" + 
					 " WHERE BM_G_ID = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bookmarkGroup = new BookmarkGroupDto();
				bookmarkGroup.setId(id);
				bookmarkGroup.setName(rs.getString("BM_G_NAME"));
				bookmarkGroup.setOrder(rs.getInt("BM_G_ORDER"));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime createdDatetime = LocalDateTime.parse(rs.getString("BM_G_CREATED_DATETIME"), formatter);
				bookmarkGroup.setCreatedDatetime(createdDatetime);
				if(rs.getString("BM_G_UPDATED_DATETIME") != null && !rs.getString("BM_G_UPDATED_DATETIME").isEmpty()) {
					LocalDateTime updatedDatetime = LocalDateTime.parse(rs.getString("BM_G_UPDATED_DATETIME"), formatter);
					bookmarkGroup.setUpdatedDatetime(updatedDatetime);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, rs);
		}
		return bookmarkGroup;
	}
	
	public int update(BookmarkGroupDto bookmarkGroup) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "UPDATE BOOKMARK_GROUP" + 
					 " SET BM_G_NAME  = ?," + 
					 "     BM_G_ORDER = ?," +
					 "     BM_G_UPDATED_DATETIME = datetime('now','localtime')" + 
					 " WHERE BM_G_ID = ?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bookmarkGroup.getName());
			pstmt.setInt(2, bookmarkGroup.getOrder());
			pstmt.setLong(3, bookmarkGroup.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(connection, pstmt, null);
		}
		return result;
	}
	
	public int delete(Long id) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM BOOKMARK_GROUP WHERE BM_G_ID = ?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(connection, pstmt, null);
		}
		return result;
	}
	
	

}
