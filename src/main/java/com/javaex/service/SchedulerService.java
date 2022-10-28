package com.javaex.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.javaex.dao.ProductDao;
import com.javaex.vo.ProductVo;

@Service
public class SchedulerService {
	
	@Autowired
	private ProductDao proDao;
	
	@Scheduled(cron = "0 15 13 * * *")
	public void doJob() {
		Date date = Date.from(Instant.now());
		System.out.println(date);
		System.out.println("<<<<<<<<<<스케줄링을 시작합니다>>>>>>>>>>");
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //결과를 받아옴
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@192.168.0.79:1521:xe";
			conn = DriverManager.getConnection(url, "S20220604", "tiger");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += "select pro_num, "
					+ "pro_type1, "
					+ "pro_type2, "
					+ "user_id, "
					+ "pro_price, "
					+ "pro_title, "
					+ "pro_write, "
					+ "pro_photo, "
					+ "pro_pic, "
					+ "to_char(pro_date, 'yyyy-mm-dd'), "
					+ "amount, "
					+ "sell_amount, "
					+ "pro_status"
					+ " from product";

			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 쿼리로 만들기		
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				
				ProductVo proVo = new ProductVo();
				proVo.setProNum(rs.getInt(1));
				proVo.setProType1(rs.getInt(2));
				proVo.setProType2(rs.getInt(3));
				proVo.setUserId(rs.getString(4));
				proVo.setProPrice(rs.getInt(5));
				proVo.setProTitle(rs.getString(6));
				proVo.setProWrite(rs.getString(7));
				proVo.setProPhoto(rs.getString(8));
				proVo.setProPic(rs.getString(9));
				proVo.setProDate(rs.getString(10));
				proVo.setAmount(rs.getInt(11));
				proVo.setSellAmount(rs.getInt(12));
				proVo.setProStatus(rs.getInt(13));
				
				System.out.println(proVo);
				
				//DB에 추가
				int cnt = proDao.insertProduct(proVo);
				
				if(cnt <= 0) {
					break;
				} else {
					System.out.println("[DB에 추가 성공]");
					System.out.println();
				}

				
			} //알아서 끝까지 가면 while문 끝냄
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		System.out.println("<<<<<<<<<<스케줄링을 종료했습니다>>>>>>>>>>");
	}
}
