package com.gun.common.exception;

import com.gun.common.pojo.ExceptionType;

public class BusinessException extends RuntimeException {

	private int code;
	private String message;
	
	public BusinessException(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }
	
	public BusinessException(ExceptionType exceptionType, String detailMessage) {
		this.code = exceptionType.getCode();
		this.message = exceptionType.getMessage() + ":" + detailMessage;
	}

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
	
}
