package com.gun.common.entity.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DealerAccountLogDTO {
	private String transactionNumber;
	private String transactionType;
	private int dealerId;
	private String dealerAccount;
	private String dealerName;
	private BigDecimal depositAmount;
	private BigDecimal amountCap;
	private BigDecimal addedAmount;
	private BigDecimal surplusAmount;
	private BigDecimal prepaidAmount;
	private String note;
	private Date transactionDate;
	private String transactionById;
	private String transactionByName;
	private String logTime;
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	public BigDecimal getAmountCap() {
		return amountCap;
	}
	public void setAmountCap(BigDecimal amountCap) {
		this.amountCap = amountCap;
	}
	public BigDecimal getAddedAmount() {
		return addedAmount;
	}
	public void setAddedAmount(BigDecimal addedAmount) {
		this.addedAmount = addedAmount;
	}
	public BigDecimal getSurplusAmount() {
		return surplusAmount;
	}
	public void setSurplusAmount(BigDecimal surplusAmount) {
		this.surplusAmount = surplusAmount;
	}
	public BigDecimal getPrepaidAmount() {
		return prepaidAmount;
	}
	public void setPrepaidAmount(BigDecimal prepaidAmount) {
		this.prepaidAmount = prepaidAmount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionById() {
		return transactionById;
	}
	public void setTransactionById(String transactionById) {
		this.transactionById = transactionById;
	}
	public String getTransactionByName() {
		return transactionByName;
	}
	public void setTransactionByName(String transactionByName) {
		this.transactionByName = transactionByName;
	}
	public String getDealerAccount() {
		return dealerAccount;
	}
	public void setDealerAccount(String dealerAccount) {
		this.dealerAccount = dealerAccount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
}
