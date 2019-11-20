package com.gun.common.pojo;

import java.io.Serializable;

/** 
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/3/21
 * @MaintenancePersonnel Felixli
 */
public class LotteryMessage implements Serializable{
	private static final long serialVersionUID = -2867765168187974178L;
	public static int STATUS_SUCCESS = 1;
	public static int STATUS_FAILURE = 0;

	private int status = STATUS_SUCCESS;

	private String code;
	
	private String argument;

	private String[] arguments;
	
	private Integer total;

	public LotteryMessage() {
			this.status = STATUS_SUCCESS;
	}
	public LotteryMessage(int status) {
		this.status = status;
	}
	public LotteryMessage(int status, String code) {
			this.status = status;
			this.code = code;
	}
	public LotteryMessage(int status, String code, String[] arguments) {
			this(status, code);
			this.arguments = arguments;
	}
	
	 public LotteryMessage(int status, String code, String argument) {
     this(status, code);
     this.argument = argument;
 }

	 
	/**
	 * Returns the message code.
	 * @return String
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Returns the status.
	 * @return int
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Returns a boolean indicating whether this return message is successful or failed.
	 * @return boolean
	 */
	public boolean isSuccess() {
			  return (this.status == STATUS_SUCCESS);
	}
	/**
	 * Returns this message arguments.
	 * @return
	 */
	public String getArguments() {
		if (this.arguments == null || this.arguments.length==0) return "";
		int size = this.arguments.length;
		StringBuffer sb = new StringBuffer("");
		for (int i=0; i<size; i++) {
			if(i>0) sb.append(",");
			sb.append(this.arguments[i].toString());
		}
		return sb.toString();
	}
  
  /**
   * @return the argument
   */
  public String getArgument() {
    return argument;
  }
/**
 * @return the total
 */
public Integer getTotal() {
	return total;
}
/**
 * @param total the total to set
 */
public void setTotal(Integer total) {
	this.total = total;
}

  
  
}
