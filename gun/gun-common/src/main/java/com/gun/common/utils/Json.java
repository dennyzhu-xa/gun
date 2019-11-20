package com.gun.common.utils;

import com.gun.common.exception.BusinessException;

/**
 * JSON模型
 * 
 * @author Felxili
 * 
 */
public class Json implements java.io.Serializable {

	private static final long serialVersionUID = -743472355822836313L;
  
	private int code;
	
	private String message;
  
	private Object result;
  
	/**
	 * @return the result
	 */
	public Object getResult() {
	    return result;
	}

	/**
	 * Constructor:
	 */
	public Json() {}

	public Json(Exception exception) {
		if (exception instanceof BusinessException) {
			BusinessException businessException = (BusinessException) exception;
			this.code = businessException.getCode();
			this.message = businessException.getMessage();
		} else {
			this.code = 500;
			this.message = "An error occurred in the system. Please contact the administrator";
		}
	}

	public Json(Object result) {
		this.code = 200;
		this.message = "request successful";
		this.result = result;
	}
  
	public void setResult(Object result) {
		this.result = result;
	}

	public boolean success() {
		return this.code == 200;
	}

	public boolean authorizationError() {
		return this.code == 401;
	}

	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
