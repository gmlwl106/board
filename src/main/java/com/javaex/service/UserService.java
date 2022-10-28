package com.javaex.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	//아이디 중복체크
	public String idCheck(UserVo userVo) {
		int cnt = userDao.idCheck(userVo);
		
		if(cnt > 0) {
			return "fail";
		} else {
			return "success";
		}
	}

	//회원 등록
	public String join(UserVo userVo) {
		int cnt = userDao.insertUser(userVo);
		
		if(cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	//로그인
	public UserVo login(UserVo userVo) {
		return userDao.selectID(userVo);
	}
	
	//외부 DB에서 유저 정보 가져오기
	public void getUser() {
		
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
			query += "	select user_name	";
			query += "	,user_id	";
			query += "	,user_pw	";
			query += "	from member	";

			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 쿼리로 만들기		
			
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				String name = rs.getString(1);
				String id = rs.getString(2);
				String password = rs.getString(3);	
					
				int num = (int)(Math.random()*100);
				String gender = ""; //성별 랜덤
				if(num > 50) {
					gender = "male";
				} else {
					gender = "female";
				}
				
				UserVo userVo = new UserVo(0, name, id, password, gender);
				System.out.println(userVo);
				
				userDao.insertUser(userVo);
				
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
	}

}
