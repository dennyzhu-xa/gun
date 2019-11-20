package com.gun.common.pojo.form;

import java.util.List;

import com.gun.common.entity.pojo.OrderRecordDTO;
import com.gun.common.entity.pojo.OrderRecordDetailsDTO;


/**
 * 
 * Purpose:交易记录form
 * @author davidsheng
 * @since  JDK 1.7
 * @date   2017/7/4
 * @MaintenancePersonnel cybersoft
 */
public class TransactionRecordForm extends SystemForm{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5907724848430972455L;

	public static final String FORM_NAME = "transactionRecordForm";
	
	public static final String REPORT_PURCHASE_RECORD_JRXML_NAME       = "PurchaseRecord";
	/**
	 * 查询条件：用户id
	 */
	private String userID;
	
	/**
	 * 查询条件：userName(purchaseRecord)
	 */
	private String queryPRUserName;
	/**
	 * 查询条件：开始时间(purchaseRecord)
	 */
	private String queryPRStartTime;
	/**
	 * 查询条件：结束时间(purchaseRecord)
	 */
	private String queryPREndTime;
	/**
	 * 交易记录list
	 */
	private List<OrderRecordDTO> transactionRecordList;
	
	/**
	 * 购买记录
	 */
	private List<OrderRecordDetailsDTO> purchaseRecordList;
	/**
	 * @return the transactionRecordList
	 */
	public List<OrderRecordDTO> getTransactionRecordList() {
		return transactionRecordList;
	}

	/**
	 * @param transactionRecordList the transactionRecordList to set
	 */
	public void setTransactionRecordList(
			List<OrderRecordDTO> transactionRecordList) {
		this.transactionRecordList = transactionRecordList;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the queryPRUserName
	 */
	public String getQueryPRUserName() {
		return queryPRUserName;
	}

	/**
	 * @param queryPRUserName the queryPRUserName to set
	 */
	public void setQueryPRUserName(String queryPRUserName) {
		this.queryPRUserName = queryPRUserName;
	}

	/**
	 * @return the purchaseRecordList
	 */
	public List<OrderRecordDetailsDTO> getPurchaseRecordList() {
		return purchaseRecordList;
	}

	/**
	 * @param purchaseRecordList the purchaseRecordList to set
	 */
	public void setPurchaseRecordList(List<OrderRecordDetailsDTO> purchaseRecordList) {
		this.purchaseRecordList = purchaseRecordList;
	}

	/**
	 * @return the queryPRStartTime
	 */
	public String getQueryPRStartTime() {
		return queryPRStartTime;
	}

	/**
	 * @param queryPRStartTime the queryPRStartTime to set
	 */
	public void setQueryPRStartTime(String queryPRStartTime) {
		this.queryPRStartTime = queryPRStartTime;
	}

	/**
	 * @return the queryPREndTime
	 */
	public String getQueryPREndTime() {
		return queryPREndTime;
	}

	/**
	 * @param queryPREndTime the queryPREndTime to set
	 */
	public void setQueryPREndTime(String queryPREndTime) {
		this.queryPREndTime = queryPREndTime;
	}

}
