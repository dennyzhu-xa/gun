package com.gun.common.entity;

import java.sql.Timestamp;

import com.gun.common.pojo.ValueObject;

public class SpacerLog extends ValueObject<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int spacerId;
	private char logType;
	private int oldQuantity;
	private int updateQuantity;
	private String logRemark;
	private String modifyId;
	private String modifyName;
	private Timestamp modifyDate;

	
	public int getSpacerId() {
		return spacerId;
	}
	public void setSpacerId(int spacerId) {
		this.spacerId = spacerId;
	}
	public char getLogType() {
		return logType;
	}
	public void setLogType(char logType) {
		this.logType = logType;
	}
	public int getOldQuantity() {
		return oldQuantity;
	}
	public void setOldQuantity(int oldQuantity) {
		this.oldQuantity = oldQuantity;
	}
	public int getUpdateQuantity() {
		return updateQuantity;
	}
	public void setUpdateQuantity(int updateQuantity) {
		this.updateQuantity = updateQuantity;
	}
	
	public String getLogRemark() {
		return logRemark;
	}
	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
