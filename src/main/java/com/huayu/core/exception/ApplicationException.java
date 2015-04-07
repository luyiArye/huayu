package com.huayu.core.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.huayu.core.bean.ValidationError;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 8563126331361802238L;

	public ApplicationException(String exCode) {
		super("["+exCode+"] "+ApplicationExceptionCode.EX_MAP.get(exCode));
		
		this.exCode = exCode;
		this.exDesc = ApplicationExceptionCode.EX_MAP.get(exCode);
	}

	public ApplicationException(String exCode, Throwable e) {
		super("["+exCode+"] "+ApplicationExceptionCode.EX_MAP.get(exCode), e);
		
		this.exCode = exCode;
		this.exDesc = ApplicationExceptionCode.EX_MAP.get(exCode);
		this.exStack = getStackTraceMessage(e);
	}

	private String exCode;
	private String exDesc;
	private String exStack;
	private List<ValidationError> validationErrors;

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public String getExStack() {
		return exStack;
	}

	public void setExStack(String exStack) {
		this.exStack = exStack;
	}

	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public String getExDesc() {
		return exDesc;
	}

	public void setExDesc(String exDesc) {
		this.exDesc = exDesc;
	}

	private String getStackTraceMessage(Throwable e) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			
			e.printStackTrace(pw);
		} 
		finally {
			pw.flush();
			sw.flush();
			
			if (sw != null) {
				try {
					sw.close();
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (pw != null) {
				pw.close();
			}
		}

		return sw.toString();
	}
}
