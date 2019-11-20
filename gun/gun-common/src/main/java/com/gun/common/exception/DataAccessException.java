package com.gun.common.exception;

import com.gun.common.pojo.LotteryMessage;

/**
 * @author Edward Yen
 * @since  JDK 1.5
 * @date   2014/7/25
 * @maintenance Edward Yen
 */
public class DataAccessException extends ServiceException {
	private static final long serialVersionUID = -4635066014876230051L;
	/**
	 * DataAccessException建構子
	 */
	public DataAccessException() {
		super();
	}

	/**
	 * DataAccessException建構子
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * DataAccessException建構子
	 * @param cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * DataAccessException建構子
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * DataAccessException建構子
	 * @param message
	 */
	public DataAccessException(LotteryMessage message) {
		super(message);
	}

	/**
	 * DataAccessException建構子
	 * @param message
	 * @param cause
	 */
	public DataAccessException(LotteryMessage message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * DataAccessException建構子
	 * @param code
	 * @param arguments
	 */
	public DataAccessException(String code, String[] arguments) {
		super(code, arguments);
	}
	/**
	 * DataAccessException建構子
	 * @param code
	 * @param arguments
	 * @param cause
	 */
	public DataAccessException(String code, String[] arguments, Throwable cause) {
		super(code, arguments, cause);
	}


}
