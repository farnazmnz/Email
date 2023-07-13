package main;

public class Message {
	private String body;
	private String receiver;
	private String sender;
	private String time;
	public Message(String body, String receiver, String sender) {
		this.body = body;
		this.receiver = receiver;
		this.sender = sender;
		this.time="0";
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBody() {
		return body;
	}
	public String getReceiver() {
		return receiver;
	}
	public String getSender() {
		return sender;
	}
	
	

}
