package com.zerobase.wifi.dao;

import com.zerobase.wifi.dto.Position;
import com.zerobase.wifi.dto.WifiInfoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WifiDao extends SqliteConnection{


	public int insertAll(List<WifiInfoDto> wifiInfoList) {
//		int OPT_BATCH_SIZE = 500;
		int inserted = 0;
		String colum = new StringBuilder().append("(")
				.append("X_SWIFI_MGR_NO, ")
				.append("X_SWIFI_WRDOFC, ")
				.append("X_SWIFI_MAIN_NM, ")
				.append("X_SWIFI_ADRES1, ")
				.append("X_SWIFI_ADRES2, ")
				.append("X_SWIFI_INSTL_FLOOR, ")
				.append("X_SWIFI_INSTL_TY, ")
				.append("X_SWIFI_INSTL_MBY, ")
				.append("X_SWIFI_SVC_SE, ")
				.append("X_SWIFI_CMCWR, ")
				.append("X_SWIFI_CNSTC_YEAR, ")
				.append("X_SWIFI_INOUT_DOOR, ")
				.append("X_SWIFI_REMARS3, ")
				.append("LAT, ")
				.append("LNT, ")
				.append("WORK_DTTM )")
				.toString();
		String sql = "INSERT INTO WIFI_INFO " + colum + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			// 입력받은 데이터를 Batch 처리
			for (int i = 0; i < wifiInfoList.size(); i++) {
				WifiInfoDto wifiInfoDto = wifiInfoList.get(i);
				// 입력 데이터 매핑
				pstmt.setString(1, wifiInfoDto.getX_SWIFI_MGR_NO());
				pstmt.setString(2, wifiInfoDto.getX_SWIFI_WRDOFC());
				pstmt.setString(3, wifiInfoDto.getX_SWIFI_MAIN_NM());
				pstmt.setString(4, wifiInfoDto.getX_SWIFI_ADRES1());
				pstmt.setString(5, wifiInfoDto.getX_SWIFI_ADRES2());
				pstmt.setString(6, wifiInfoDto.getX_SWIFI_INSTL_FLOOR());
				pstmt.setString(7, wifiInfoDto.getX_SWIFI_INSTL_TY());
				pstmt.setString(8, wifiInfoDto.getX_SWIFI_INSTL_MBY());
				pstmt.setString(9, wifiInfoDto.getX_SWIFI_SVC_SE());
				pstmt.setString(10, wifiInfoDto.getX_SWIFI_CMCWR());
				pstmt.setString(11, wifiInfoDto.getX_SWIFI_CNSTC_YEAR());
				pstmt.setString(12, wifiInfoDto.getX_SWIFI_INOUT_DOOR());
				pstmt.setString(13, wifiInfoDto.getX_SWIFI_REMARS3());
				pstmt.setDouble(14, wifiInfoDto.getLNT());
				pstmt.setDouble(15, wifiInfoDto.getLAT());
				pstmt.setString(16, wifiInfoDto.getWORK_DTTM());
				
				pstmt.executeUpdate();
//				// Batch에 추가
//				pstmt.addBatch();
//				pstmt.clearParameters();

//				// Batch 실행
//				if (i % OPT_BATCH_SIZE == 0) {
////					pstmt.executeBatch();
////					pstmt.clearBatch();
//					connection.commit();
//				}
			}
			connection.commit();
//
//			// 입력 건수 조회
//			pstmt.executeBatch();
//			pstmt.clearBatch();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, null);
		}
		return inserted;
	}

	public List<WifiInfoDto> selectAllByPosition(Position position) {
		
		List<WifiInfoDto> wifiInfoList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		// SQL 준비
		String distanceColumn = "round((6371*acos(cos(radians(LAT))*cos(radians(CUR_LAT))*cos(radians(CUR_LNT) - radians(LNT)) + sin(radians(LAT))*sin(radians(CUR_LAT)))), 4) as DISTANCE";
		String colum = new StringBuilder().append("X_SWIFI_MGR_NO, ")
										.append("X_SWIFI_WRDOFC, ")
										.append("X_SWIFI_MAIN_NM, ")
										.append("X_SWIFI_ADRES1, ")
										.append("X_SWIFI_ADRES2, ")
										.append("X_SWIFI_INSTL_FLOOR, ")
										.append("X_SWIFI_INSTL_TY, ")
										.append("X_SWIFI_INSTL_MBY, ")
										.append("X_SWIFI_SVC_SE, ")
										.append("X_SWIFI_CMCWR, ")
										.append("X_SWIFI_CNSTC_YEAR, ")
										.append("X_SWIFI_INOUT_DOOR, ")
										.append("X_SWIFI_REMARS3, ")
										.append("LAT, ")
										.append("LNT, ")
										.append("WORK_DTTM ")
										.toString();
		
		String sql = "SELECT TBL.*, " + distanceColumn +
				" FROM (SELECT " + colum + ", ? as CUR_LAT, ? as CUR_LNT from WIFI_INFO) TBL" +
				" ORDER BY DISTANCE" +
				" LIMIT 20";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setDouble(1, position.getLat());
			pstmt.setDouble(2, position.getLnt());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WifiInfoDto wifi = new WifiInfoDto();
				wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));		
				wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));		
				wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));		
				wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));		
				wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));		
				wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));	
				wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));	
				wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));	
				wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));		
				wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));		
				wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));	
				wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));	
				wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));		
				wifi.setLAT(rs.getDouble("LAT"));					
				wifi.setLNT(rs.getDouble("LNT"));					
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
				wifi.setDISTANCE(rs.getDouble("DISTANCE"));
				
				wifiInfoList.add(wifi);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, rs);
		}
		
		return wifiInfoList;
	}

	public WifiInfoDto selectByMgrNo(String mgrNo) {
		
		Connection connection = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		WifiInfoDto wifiDetail = null;
		String sql = "SELECT 0.0000 AS DISTANCE, * FROM WIFI_INFO WHERE X_SWIFI_MGR_NO = ?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mgrNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				wifiDetail = new WifiInfoDto();
				wifiDetail.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));		
				wifiDetail.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));		
				wifiDetail.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));		
				wifiDetail.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));		
				wifiDetail.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));		
				wifiDetail.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));	
				wifiDetail.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));	
				wifiDetail.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));	
				wifiDetail.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));		
				wifiDetail.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));		
				wifiDetail.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));	
				wifiDetail.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));	
				wifiDetail.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));		
				wifiDetail.setLAT(rs.getDouble("LAT"));					
				wifiDetail.setLNT(rs.getDouble("LNT"));					
				wifiDetail.setWORK_DTTM(rs.getString("WORK_DTTM"));
				wifiDetail.setDISTANCE(rs.getDouble("DISTANCE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, pstmt, rs);
		}
		
		return wifiDetail;
	}
}
