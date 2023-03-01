package com.zerobase.wifi.dao;

import java.sql.*;

public class SqliteConnection {
	// DB 연결 동작(메서드) - 1,2단계
	public Connection getConnection(){

		String DRIVER = "org.sqlite.JDBC";
		String fileLocation = "C:\\Users\\wldus\\zerobase\\project\\wifi\\testdb.sqlite3";
		String url = "jdbc:sqlite:" + fileLocation;
		// 1. 드라이버 로드

		// 2. DB 연결
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;

	}

	// DB 자원해제 동작
	public void close(Connection connection, PreparedStatement pstmt, ResultSet rs) {

		try {

			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();

			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
