package com.gun.common.exception;

import com.gun.common.pojo.LotteryMessage;

/**
 * @author Edward Yen
 * @since  JDK 1.5
 * @date   2014/7/25
 * @maintenance Edward Yen
 */
public class ServiceException extends RuntimeException implements ILotteryException{
	private static final long serialVersionUID = 6301117759024451918L;
	private LotteryMessage message;
	/**
	 * ServiceException建構子
	 */
	public ServiceException() {
		this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}

	/**
	 * ServiceException建構子
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
		this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}

	/**
	 * ServiceException建構子
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		this((String)null, cause);
	}

	/**
	 * ServiceException建構子
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		if (cause instanceof ILotteryException) {
			this.message = ((ILotteryException)cause).getLotteryMessage();
		}
		if (this.message == null) this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}
	public ServiceException(LotteryMessage message) {
		this.message = message;
	}
	public ServiceException(LotteryMessage message, Throwable cause) {
		super(cause);
		this.message = message;
		if (this.message == null) {
			if (cause instanceof ILotteryException) {
				this.message = ((ILotteryException)cause).getLotteryMessage();
			}
		}	
		if (this.message == null) this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}
	public ServiceException(String code, String[] arguments) {
		this(new LotteryMessage(LotteryMessage.STATUS_FAILURE, code, arguments));
	}
	public ServiceException(String code, String[] arguments, Throwable cause) {
		this(new LotteryMessage(LotteryMessage.STATUS_FAILURE, code, arguments), cause);
	}	
	public LotteryMessage getLotteryMessage() {
		return this.message;
	}

}
