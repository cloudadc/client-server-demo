package io.cloudadc.backend.websocket;

import java.util.Date;

public class Msg implements java.io.Serializable{
	
	private static final long serialVersionUID = 5878730944679940113L;

	private String id;
	
	private String msg;
	
	private Date date;

	public Msg(String id, String msg, Date date) {
		super();
		this.id = id;
		this.msg = msg;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public byte[] payload() {
		return this.toString().getBytes();
	}

	@Override
	public String toString() {
		return "{\"session\": \"" + id + "\", \"msg\": \"" + msg + "\", \"date\": \"" + date + "\"}";
	}

}
