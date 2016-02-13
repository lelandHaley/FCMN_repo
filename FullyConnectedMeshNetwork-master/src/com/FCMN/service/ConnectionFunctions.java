package com.FCMN.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.FCMN.DB.Connect;

/**
 * 
 * This class is used to retrieve some data from connection table
 * 	or store some data from connection table in the database
 * 
 * date: Feb 11, 2016
 * @author junrongwang
 *
 */
public class ConnectionFunctions {
	
	private static Connection myConn;
	private static PreparedStatement myStmt;
	private static ResultSet myRs;
	NodeFunctions nodeFunctions;
	

	/**
	 * This method is used to add a connection from two connectors
	 * @param fromConnector : the id of a connector
	 * @param toConnector   : the id of another connector
	 * @return boolean		: true if add successfully
	 * 						  false id add failed
	 */
	public boolean addAConnection(int fromConnector, int toConnector){
		
		boolean flag = false;
		
		//prepare statement
		String sql ="insert into connection(fromConnector, toConnector) values (?,?)";
		
		try {
			//connect to database
			myConn = Connect.getConnection();
			myStmt = myConn.prepareStatement(sql);
					
			//set params		
			myStmt.setInt(1, fromConnector);
			myStmt.setInt(2, toConnector);
			
			//execute the statement
			flag = myStmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.releaseConn(myRs, myStmt, myConn);
		}
				
		return flag;		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
