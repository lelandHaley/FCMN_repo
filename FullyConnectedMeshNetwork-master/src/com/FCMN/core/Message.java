package com.FCMN.core;

/**
 * This class is used to record sending a message 
 * 	from which node to which node
 * 	and what the message is
 * 	as well as whether the message is successfully send
 * 
 * Date : Feb 11, 2016
 * @author junrongwang
 *
 */
public class Message {
	
	int messageId;
	int sender;
	int receiver;
	String message;
	int status;
	
	public Message(){
		
	}
	
	/**
	 * The constructor method of this class
	 * @param fromConnector : the id of the connector
	 * @param toConnector   : the id of other connector
	 */
	public Message(int messageId, int sender, int receiver,
			String message, int status){
		
		this.messageId = messageId;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.status = status;
	}
	
	/**
	 * The following method is some setter and getter methods
	 * @return
	 */

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * This method is a format to show a message information
	 */
	@Override
	public String toString() {
		return String.format("Message [messageId=%s, sender=%s,"
				+ " receiver=%s, message=%s, status=%s]",
				messageId, sender, receiver, message, status);
	}
	
	public String getMessageInfo() {
		return "messageId:" + this.getMessageId() + "  "
                + "sender:" + this.getSender() + " "
		      + "receiver:" + this.getReceiver() + " "
		       + "message:" + this.getMessage() + " "
		        + "status:" + this.getStatus() + "\n";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
