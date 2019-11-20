package com.gun.common.entity.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.pojo.ValueObject;

public class CBBankRecordDTO extends ValueObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4342760844241941137L;

	//枚举
		public static enum ATTRIBUTE {
				REF("ref"),
				MERCHANT("merchant"),
				ANDROID_USER_ID("androidUserId"),
				INVOICE("invoice"),
				CURRENCY("currency"),
				ITEMNAME("itemname"),
				ITEMDESC("itemdesc"),
				PROCEED("proceed"),
				BANKREF("bankref"),
				AMOUNT("amount"),
				ACCOUNT("account"),
				PROCESSTIME("processtime"),
				CREATED_DATE("createdDate"),
				UPDATED_DATE("updatedDate"),
				FINISHED_DATE("finishedDate")
			    ;
			    
			    private String value;
			    ATTRIBUTE(String value) {
			      this.value = value;
			    };
			    public String getValue() {
			      return this.value;
			    }
			  };
			  
	private String ref;
	private BigDecimal androidUserId;
	private String merchant;
	private String invoice;
	private String currency;
	private String itemname;
	private String itemdesc;
	private String proceed;
	private String bankref;
	private String amount;
	private String account;
	private String processtime;
	private Date createdDate;
	private Date updatedDate;
	private Date finishedDate;
	private String username;
	private String fullName;
	
	public CBBankRecordDTO() {
		super();
	}
	
	public CBBankRecordDTO(String ref, BigDecimal androidUserId, String merchant, String invoice, String currency, String itemname, String itemdesc,
			String proceed, String bankref, String amount, String account, String processtime, Date createdDate,
			Date updatedDate, Date finishedDate) {
		super();
		this.ref = ref;
		this.androidUserId = androidUserId;
		this.merchant = merchant;
		this.invoice = invoice;
		this.currency = currency;
		this.itemname = itemname;
		this.itemdesc = itemdesc;
		this.proceed = proceed;
		this.bankref = bankref;
		this.amount = amount;
		this.account = account;
		this.processtime = processtime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.finishedDate = finishedDate;
	}

	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @return the androidUserId
	 */
	public BigDecimal getAndroidUserId() {
		return androidUserId;
	}

	/**
	 * @param androidUserId the androidUserId to set
	 */
	public void setAndroidUserId(BigDecimal androidUserId) {
		this.androidUserId = androidUserId;
	}

	/**
	 * @return the merchant
	 */
	public String getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	/**
	 * @return the invoice
	 */
	public String getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the itemname
	 */
	public String getItemname() {
		return itemname;
	}

	/**
	 * @param itemname the itemname to set
	 */
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	/**
	 * @return the itemdesc
	 */
	public String getItemdesc() {
		return itemdesc;
	}

	/**
	 * @param itemdesc the itemdesc to set
	 */
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}

	/**
	 * @return the proceed
	 */
	public String getProceed() {
		return proceed;
	}

	/**
	 * @param proceed the proceed to set
	 */
	public void setProceed(String proceed) {
		this.proceed = proceed;
	}

	/**
	 * @return the bankref
	 */
	public String getBankref() {
		return bankref;
	}

	/**
	 * @param bankref the bankref to set
	 */
	public void setBankref(String bankref) {
		this.bankref = bankref;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the processtime
	 */
	public String getProcesstime() {
		return processtime;
	}

	/**
	 * @param processtime the processtime to set
	 */
	public void setProcesstime(String processtime) {
		this.processtime = processtime;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the finishedDate
	 */
	public Date getFinishedDate() {
		return finishedDate;
	}

	/**
	 * @param finishedDate the finishedDate to set
	 */
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
