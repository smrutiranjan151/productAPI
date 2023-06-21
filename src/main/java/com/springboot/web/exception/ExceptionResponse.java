package com.springboot.web.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String desc;

	/**
	 * @param timestamp
	 * @param message
	 * @param desc
	 */
	public ExceptionResponse(Date timestamp, String message, String desc) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.desc = desc;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

}
