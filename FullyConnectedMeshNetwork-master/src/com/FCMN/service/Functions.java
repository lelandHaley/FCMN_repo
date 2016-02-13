package com.FCMN.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.FCMN.DB.Connect;

public class Functions {

	private static Connection conn;
	private Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private int rc;

	public int addANode(String groupId, int active, int connector){
		boolean flag = false;
		int returnValue = 0;
		System.out.println("group "+groupId+" connector "+connector+" active "+active);
		String sql ="insert into node(groupId, added, active, connector) values (?,?,?,?)";
		try {
			conn = Connect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupId);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, active);
			pstmt.setInt(4, connector);
			int i = -1;
			i = pstmt.executeUpdate();
			if (i>-1) {
				flag = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.releaseConn(rs, pstmt, conn);
		}

		return 10010;

	}

	/*public static void main(String[] args) throws Exception{
		Functions func = new Functions();
		func.addANode("test", 1, 0);
	}*/
}
