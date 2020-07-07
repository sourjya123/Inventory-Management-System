package com.inventory.InventoryManagementSystem.Model;

import java.util.Date;

public class CustomException {

	private String message;
	private String details;
	private Date timeStamp;
	private String errorCode;

	public CustomException(String message, String details, Date timeStamp, String errorCode) {
		super();
		this.message = message;
		this.details = details;
		this.timeStamp = timeStamp;
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
