package com.study.myapp.dao;

//CRUD작업
//
//C: INSERT
//R: SELECT
//U: UPDATE
//D: DELETE
//
//~~DAO : database 작업 클래스
//~~DTO(Data Transfer Object) : value => 객체
//
//lombok : getter/setter, 생성자를 쉽게 작성할 수 있도록 도와줌 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 여러개의 DAO 파일에서 중복된 부분 처리

public class JdbcUtil {
	
//	//static 역할 ===> 제일 처음 실행시켜줌
//	static {
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static Connection getConnection() {
//		
//		Connection con = null;
//		
//		try {
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "javadb";
//			String password = "12345";
//			
//			con = DriverManager.getConnection(url, user, password);
//			con.setAutoCommit(false);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return con;
//	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}










