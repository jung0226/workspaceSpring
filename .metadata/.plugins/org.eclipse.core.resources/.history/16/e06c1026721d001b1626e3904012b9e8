package com.bitcamp.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConn {
	protected Connection con=null;
	protected PreparedStatement pstmt =null;
	protected ResultSet rs = null;
	protected String sql =null;
	
	public DBConn() {}
	//DB연결 (DB회선을 받아옴)
	public void dbConn() {
		try {
			Context ctx= new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			
			DataSource ds = (DataSource)envCtx.lookup("jdbc/myoracle");
			con = ds.getConnection();//비어있는 회선을 꺼내서 con에 담아줌.			
		}catch(Exception e) {
			System.out.println("DB연결 에러 발생 ");
			e.printStackTrace();
		}
	}
	//DB닫기 (연결회선 끊으면 회선이 반환됨.)
	public void dbClose() {
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
		}catch(Exception e) {
			
		}
	}
	
}
