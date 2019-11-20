package com.gun.common.exception;

import com.gun.common.pojo.LotteryMessage;

/**
 * 
 * @author felixli
 * @since  JDK 1.7
 * @date   2017年2月7日
 * @MaintenancePersonnel felixli
 */
public class LotteryException extends Exception implements ILotteryException {
	private static final long serialVersionUID = 7353282464774510297L;
	LotteryMessage message;
	/**
	 * CmsException建構子
	 */
	public LotteryException() {
		this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}

	/**
	 * CmsException建構子
	 * @param message
	 */
	public LotteryException(String message) {
		super(message);
		this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}

	/**
	 * CmsException建構子
	 * @param cause
	 */
	public LotteryException(Throwable cause) {
		this((String)null, cause);
	}

	/**
	 * CmsException建構子
	 * @param message
	 * @param cause
	 */
	public LotteryException(String message, Throwable cause) {
		super(message, cause);
		if (cause instanceof ILotteryException) {
			this.message = ((ILotteryException)cause).getLotteryMessage();
		}
		if (this.message == null) this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}
	public LotteryException(LotteryMessage message) {
		this.message = message;
	}
	public LotteryException(LotteryMessage message, Throwable cause) {
		super(cause);
		this.message = message;
		if (this.message == null) {
			if (cause instanceof ILotteryException) {
				this.message = ((ILotteryException)cause).getLotteryMessage();
			}
		}	
		if (this.message == null) this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE);
	}
	public LotteryException(String code, String[] arguments) {
		this(new LotteryMessage(LotteryMessage.STATUS_FAILURE, code, arguments));
	}
	public LotteryException(String code, String[] arguments, Throwable cause) {
		this(new LotteryMessage(LotteryMessage.STATUS_FAILURE, code, arguments), cause);
	}
	/** (non-Javadoc)
	 * @see com.gun.common.exception.ILotteryException#getCmsMessage()
	 */
	public LotteryMessage getLotteryMessage() {
		return this.message;
	}
	
	/**
	 * Constructor for IpmsCommonException
	 * @param errorCode error code
	 * @param msg the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). 
	 */
	public LotteryException(String errorCode, String msg, Throwable cause) {
			super(msg, cause);
			this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, errorCode, new String[]{msg});
	}
	
	/**
	 * Constructor for IpmsCommonException
	 * @param errorCode error code
	 * @param msg the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 */
	public LotteryException(String errorCode, String msg) {
			super(msg);
			this.message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, errorCode, new String[]{msg});
	}

}
