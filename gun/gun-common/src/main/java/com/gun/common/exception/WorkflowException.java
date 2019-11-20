package com.gun.common.exception;

import com.gun.common.pojo.LotteryMessage;

/**
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/4/5
 * @maintenance Felixli
 */
@SuppressWarnings("unused")
public class WorkflowException extends LotteryException {
	private static final long serialVersionUID = 5298408517662735282L;
  private static final int BAD_REQUEST 			= 400;
	private static final int UNAUTHORIZED 			= 401;
	private static final int FORBIDDEN 				= 403;
	private static final int NOT_FOUND 				= 404;
	private static final int METHOD_NOT_ALLOWED 	= 405;
	private static final int CONFLICT 				= 409;
	private static final int UNSUPPORTED_MEDIA_TYPE = 415;
	private static final int INTERNAL_SERVER_ERROR 	= 500;
	/**
	 * WorkflowException建構子
	 */
	public WorkflowException() {
		super();
	}

	/**
	 * WorkflowException建構子
	 * @param message
	 */
	public WorkflowException(String message) {
		super(message);
	}

	/**
	 * WorkflowException建構子
	 * @param cause
	 */
	public WorkflowException(Throwable cause) {
		super(cause);
	}

	/**
	 * WorkflowException建構子
	 * @param message
	 * @param cause
	 */
	public WorkflowException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * WorkflowException建構子
	 * @param message
	 */
	public WorkflowException(LotteryMessage message) {
		super(message);
	}

	/**
	 * WorkflowException建構子
	 * @param message
	 * @param cause
	 */
	public WorkflowException(LotteryMessage message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * WorkflowException建構子
	 * @param code
	 * @param arguments
	 */
	public WorkflowException(String code, String[] arguments) {
		super(code, arguments);
	}
	/**
	 * WorkflowException建構子
	 * @param code
	 * @param arguments
	 * @param cause
	 */
	public WorkflowException(String code, String[] arguments, Throwable cause) {
		super(code, arguments, cause);
	}

	/**
	 * ActivitiRestHumanWorkflowException建構子
	 * @param action the action user submits.
	 * @param wfe wfe the WorkflowException exception.
	 * @param args arguments for message description
	 */
}
