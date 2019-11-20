package com.gun.common.exception;

import com.gun.common.pojo.LotteryMessage;

/**
 * This is a convert exception extends class Exception. 
 * @author felixli
 * @since JDK 1.7
 */
public class ConvertException extends LotteryException {
	/**
   * 
   */
  private static final long serialVersionUID = -5808524835579053638L;
  /**
	 * @param message
	 */
	public ConvertException(LotteryMessage message) {
		super(message);
	}

	/**
	 * @param errorCode
	 * @param msg
	 * @param cause
	 */
	public ConvertException(String errorCode, String msg, Throwable cause) {
		super(errorCode, msg, cause);
	}

	/**
	 * @param errorCode
	 * @param msg
	 */
	public ConvertException(String errorCode, String msg) {
		super(errorCode, msg);
	}

	/**
	 * @param errorCode
	 * @param args
	 * @param cause
	 */
	public ConvertException(String errorCode, String[] args, Throwable cause) {
		super(errorCode, args, cause);
	}

	/**
	 * @param errorCode
	 * @param args
	 */
	public ConvertException(String errorCode, String[] args) {
		super(errorCode, args);
	}

	/**
	 * Constructor for ConvertException.
	 */
	public ConvertException() {
			super();
	}

	/**
	 * Constructor for ConvertException.
	 * @param msg the detail message 
	 */
	public ConvertException(String msg) {
			super(msg);
	}
	/**
	 * Constructor for ConvertException.
	 * @param cause the cause 
	 */
	public ConvertException(Throwable cause) {
			super(cause);
	}
	/**
	 * Constructor for ConvertException.
	 * @param msg the detail message 
	 * @param cause the cause 
	 */
	public ConvertException(String msg, Throwable cause) {
			super(msg, cause);
}
}
