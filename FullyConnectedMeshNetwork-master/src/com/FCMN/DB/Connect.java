
package com.FCMN.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to get connect to the database
 * 	and close connection to the database
 * 
 * modify date : Feb 11, 2016
 * 
 * @author junrongwang
 *
 */
public class Connect {

	//the url of the database to connect
	private static String url = 
		"jdbc:mysql://127.0.0.1:3307/FullyConnectedMeshNetwork"; 
	// the name of the user to access the database
	private static String user = "root"; 
	// the password of the user to access the database
	private static String password = "";
	// the connection name
	private static Connection conn = null; 
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * This method is used to get connect to the database
	 * @return Connection : return this connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/**
	 * This method is used to close connection to the database
	 * @param rs	: the result set of after executing the sql statement
	 * @param stmt	: the statement that using to retrieve data from database
	 * @param conn	: the connection that use to connect the database
	 */
	public static void releaseConn(ResultSet rs, Statement stmt, 
		Connection conn){
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (stmt != null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if (conn != null){
							try {
								conn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
