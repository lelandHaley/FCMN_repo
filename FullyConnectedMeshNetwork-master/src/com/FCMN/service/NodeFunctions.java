package com.FCMN.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.FCMN.DB.Connect;
import com.FCMN.core.Node;

/**
 * This class is used to retrieve some data from node table
 * 	or store some data from node table in the database
 * @author junrongwang
 *
 */
public class NodeFunctions {
	
	private static Connection myConn;
	//private static PreparedStatement myStmt;
	//private static ResultSet myRs;
	List<String> existingPatternList;
	ConnectionFunctions connectionFunction;
	

	/**
	 * This method is used to add a node to the database
	 * @param patternId 			: the id of the pattern which the node belongs
	 * @param connectedPatterns		: if patternId is a new pattern, then user need to
	 * 								  	figure out which patterns this pattern connect
	 * @return boolean				:  true : add a node successfully
	 * 								  false : sql exception, add a node failed
	 * 									
	 */
	public boolean addANodeToNewPattern(String patternId, List<String> connectedPatterns){
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		boolean flag = false;
		//prepare statement
		String sql ="insert into node(patternId, active, connector) values (?,?,?)";
		try {
			//connect to database
			myConn = Connect.getConnection();
			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			//set params
			myStmt.setString(1, patternId);
			myStmt.setInt(2, 1);
			myStmt.setInt(3, 1);
					
			//execute the statement
			flag = (myStmt.executeUpdate()==1);
			
			int nodeId = 0;
			myRs = myStmt.getGeneratedKeys();
            while (myRs.next()) {
            	nodeId = myRs.getInt(1);
                System.out.println("Key returned from getGeneratedKeys():"
                        + myRs.getInt(1));
            } 
			System.out.println("nodeId :" + nodeId);
			//connect to other connector
			connectionFunction = new ConnectionFunctions();
			for(int i = 0; i<connectedPatterns.size(); i++){
				connectionFunction.addAConnection(nodeId, 
				(getConnectorNodeOfAPattern(connectedPatterns.get(i))).getNodeId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.releaseConn(myRs, myStmt, myConn);
		}	
		return flag;

	}
	
	/**
	 * This method is used to add a node to an existing pattern
	 * 	and which mean the node is not a connector 
	 * 	So just need to judge whether the pattern is full
	 * @param patternId : the pattern you want to add this node to
	 * @return 			: -1 This pattern is full, add a node failed
	 * 					   0 Add a node failed to execute sql statement!
	 * 					   1 Add a node successfully
	 */
	public int addANodeToExistingPattern(String patternId){
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int returnValue = 0;
		
		//prepare statement
		String sql ="insert into node(patternId, active, connector) values (?,?,?)";
		try {
			//connect to database
			myConn = Connect.getConnection();
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, patternId);
			myStmt.setInt(2, 1);
			myStmt.setInt(3, 0);
		
			//judge whether the nodes of this pattern is bigger than 6 after added
			//	if there already had 6, then add a node failed
			if((getAllNodesOfAPattern(patternId).size()) <= 5){
				//execute the statement
				returnValue = myStmt.executeUpdate();
				// add a node successfully
				if (returnValue>-1) {
					returnValue = 1;
					System.out.println("Add a node successfully!");
				}else{
					returnValue = 0;
					System.out.println("Add a node failed to execute sql statement!");
				}
			}else{
				returnValue = -1;
				System.out.println("This pattern is full, add a node failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.releaseConn(myRs, myStmt, myConn);
		}
		
		return returnValue;
	}
	
	
	/**
	 * This method is used to check whether a pattern is existing or not
	 * @param patternId : the id of the pattern
	 * @return boolean	: true if this pattern exist
	 * 					  false if this pattern not exist
	 * @throws Exception
	 */
	public boolean checkWhetherAPatternIsExisting(String patternId) throws Exception{
		
		boolean flag = false;
		
		//get the existing pattern from the database
		existingPatternList = new ArrayList<String>();
		existingPatternList = getExistingPatternList();
		if(existingPatternList.isEmpty()){
			System.out.println("There is no pattern in database right now!");
		}else{
			for(String str: existingPatternList) {
				//if the node adds to the existing pattern, 
				//	then this node is not a connector
			    if(str.equals(patternId)){
			    	System.out.println("This pattern is existing!");
			    	flag =  true;
			    }
			}
			flag =  false;
		}
		return flag;
	}
	
	/**
	 * This method is used to get all patterns in the database
	 * @return List<String> : the pattern id list
	 * @throws Exception
	 */
	public List<String> getExistingPatternList() throws Exception{
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		existingPatternList = new ArrayList<String>();
		
		//prepare statement
		String sql ="select distinct patternId from node";
		try {
			//connect to database
			myConn = Connect.getConnection();
			myStmt = myConn.prepareStatement(sql);
			
			//execute the statement
			myRs = myStmt.executeQuery();
				
			while(myRs.next()){
				existingPatternList.add(myRs.getString("patternId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.releaseConn(myRs, myStmt, myConn);
		}
		
		return existingPatternList;
	}
	
	/**
	 * This method is used to get the list of node id of a pattern
	 * @param patternId      : the id of the pattern
	 * @return List<Integer> : the list of node id in this pattern
	 */
	public List<Integer> getAllNodesOfAPattern(String patternId){
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		List<Integer> nodeIdList = new ArrayList<Integer>();
		
		//prepare statement
		String sql ="select nodeId from "
				+ "node where patternId = ?";
		try {
			//connect to database
			myConn = Connect.getConnection();
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, patternId);
			
			//execute the statement
			myRs = myStmt.executeQuery();
						
			while(myRs.next()){
				nodeIdList.add(myRs.getInt("nodeId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.releaseConn(myRs, myStmt, myConn);
		}
				
		return nodeIdList;
	}
	
	/**
	 * This method is used to get a connector of a pattern
	 * @param patternId : the id of the pattern
	 * @return Node		: the connector node of this pattern
	 */
	public Node getConnectorNodeOfAPattern(String patternId){

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Node node = new Node();
		node = null;
		
		//prepare statement
		String sql ="select * from "
				+ "node where patternId = ? and connector = ?";
		try {
			//connect to database
			myConn = Connect.getConnection();
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, patternId);
			myStmt.setInt(2, 1);
			
			//execute the statement
			myRs = myStmt.executeQuery();
				
			while(myRs.next()){
				node = convertRowToNode(myRs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.releaseConn(myRs, myStmt, myConn);
		}
		return node;
	}
	
	/**
	 * This is method to convert the resultSet of a row of node
	 * @param myRs      : the result of a sql statement
	 * @return node		: the node of a row
	 * @throws SQLException
	 */
	private Node convertRowToNode(ResultSet myRs) throws SQLException {
		
		int nodeId = myRs.getInt("nodeId");
		String patternId = myRs.getString("patternId");
		int active =  myRs.getInt("active");
		int connector =  myRs.getInt("connector");
		
		Node tempNode = new Node(nodeId, patternId, active, connector);
		
		return tempNode;
	}
	
	public static void main(String[] args) throws Exception{
		NodeFunctions func = new NodeFunctions();
		List<String> connectPttern = new ArrayList<String>();
		connectPttern.add("b");
		//System.out.println(func.getExistingPatternList());
		//System.out.println(func.checkWhetherAPatternIsExisting("a"));
		//System.out.println(func.addANodeToExistingPattern("a"));
		//System.out.println(func.getConnectorNodeOfAPattern("a"));
		//System.out.println(func.getTotalNodesOfAPattern("a"));
		//System.out.println(func.addANodeToExistingPattern("a"));
		System.out.println(func.addANodeToNewPattern("c",connectPttern));
	}
	
}
