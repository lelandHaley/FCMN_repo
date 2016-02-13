package com.FCMN.core;

/**
 * This class is a connection class, 
 * 	fromConnector is a connector of one pattern
 * 	toConnector is a connector of other pattern
 * 	this class record which patterns connect
 * 
 * Date : Feb 11, 2016
 * @author junrongwang
 *
 */
public class Connection {
	
	int fromConnector; // the connector of one pattern
	int toConnector;   // the connector of other pattern
	
	public Connection(){
		
	}
	
	/**
	 * The constructor method of this class
	 * @param fromConnector : the id of the connector
	 * @param toConnector   : the id of other connector
	 */
	public Connection(int fromConnector, int toConnector){
		
		this.fromConnector = fromConnector;
		this.toConnector = toConnector;
	}
	
	/**
	 * The following method is some setter and getter methods
	 * @return
	 */
	public int getFromConnector() {
		return fromConnector;
	}

	public void setFromConnector(int fromConnector) {
		this.fromConnector = fromConnector;
	}

	public int getToConnector() {
		return toConnector;
	}

	public void setToConnector(int toConnector) {
		this.toConnector = toConnector;
	}

	/**
	 * This method is a format to show a connection information
	 */
	@Override
	public String toString() {
		return String.format("Connection [fromConnector=%s, toConnector=%s]",
				fromConnector, toConnector);
	}
	
	public String getConnectionInfo() {
		return "fromConnector:" + this.getFromConnector() + "  "
               + "toConnector:" + this.getToConnector() + "\n";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
