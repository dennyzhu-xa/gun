package com.gun.common.pojo;

public class RechargeResult {
	private String merchant;
	private String ref;
	private String proceed;
	private String bankref;
	private String account;
	private String amount;
	private String processtime;
	
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getProceed() {
		return proceed;
	}
	public void setProceed(String proceed) {
		this.proceed = proceed;
	}
	public String getBankref() {
		return bankref;
	}
	public void setBankref(String bankref) {
		this.bankref = bankref;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProcesstime() {
		return processtime;
	}
	public void setProcesstime(String processtime) {
		this.processtime = processtime;
	}
	@Override
	public String toString() {
		return "RechargeResult [merchant=" + merchant + ", ref=" + ref + ", proceed=" + proceed + ", bankref=" + bankref
				+ ", account=" + account + ", amount=" + amount + ", processtime=" + processtime + "]";
	}
}
