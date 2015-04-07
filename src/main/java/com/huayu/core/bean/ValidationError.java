package com.huayu.core.bean;

public class ValidationError {
	private String message;
	//private String messageTemplage;
	private String propertyPath;
	private Object invalidValue;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/*public String getMessageTemplage() {
		return messageTemplage;
	}
	public void setMessageTemplage(String messageTemplage) {
		this.messageTemplage = messageTemplage;
	}*/
	public String getPropertyPath() {
		return propertyPath;
	}
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}
	public Object getInvalidValue() {
		return invalidValue;
	}
	public void setInvalidValue(Object invalidValue) {
		this.invalidValue = invalidValue;
	}
	
	@Override
	public String toString() {
		StringBuffer str=new StringBuffer();
		
		str.append("{message: ")
		.append(message)
		/*.append(", messageTemplage: ")
		.append(messageTemplage)*/
		.append(", propertyPath: ")
		.append(propertyPath)
		.append(", invalidValue: ")
		.append(invalidValue)
		.append("}");
		
		return str.toString();
	}
}
