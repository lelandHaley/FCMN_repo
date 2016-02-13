package com.FCMN.core;

/**
 * This class is a node class. 
 * 	The node is a node in the network pattern and 
 * 	can send message to any other node of a pattern
 * @author junrongwang
 *
 */
public class Node {
	
	int nodeId;
	String patternId;
	int active;		// 0: not active       1: inactive
	int connector;	// 0: not a connector  1: connector
	
	public Node(){
		
	}
	
	public Node(int nodeId, String patternId, int active, 
		int connector){
		
		this.nodeId = nodeId;
		this.patternId = patternId;
		this.active = active;
		this.connector = connector;
	}
	/**
	 * The below methods are some getter and setter methods
	 * @return
	 */
	
	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getPatternId() {
		return patternId;
	}

	public void setPatternId(String patternId) {
		this.patternId = patternId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getConnector() {
		return connector;
	}

	public void setConnector(int connector) {
		this.connector = connector;
	}
	

	/**
	 * This method is a format to show a node information
	 */
	@Override
	public String toString() {
		return String.format("Node [nodeId=%s, patternId=%s, "
			+ "active=%s, connector=%s]",
			nodeId, patternId, active, connector);
	}
	
	public String getNodeInfo() {
		return "nodeId:" + this.getNodeId() + "  "
            + "patternId:" + this.getPatternId() + "  "
             + "active:" + this.getActive() + "  "
           +"connector:" + this.getConnector() + "\n";
	}

}
