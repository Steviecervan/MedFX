package medfx;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = -100L;
	
	private String sender;
	private String receiver;
	private String contents;
	
	public Message(String sender, String receiver, String contents) {
		this.sender = sender;
		this.receiver = receiver;
		this.contents = contents;
	}
	
	//	Getters
	public String getSender() {
		return this.sender;
	}
	
	public String getReceiver() {
		return this.receiver;
	}
	
	public String getContents() {
		return this.contents;
	}
}
