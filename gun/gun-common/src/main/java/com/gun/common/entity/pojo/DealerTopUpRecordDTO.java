package com.gun.common.entity.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * @author neiljing
 */
public class DealerTopUpRecordDTO extends ValueObject<Integer> {

	private static final long serialVersionUID = 4412238345476385147L;
	
	//枚举
	  public static enum ATTRIBUTE {
	    ID("id"),
	    NEARBY_STORES_ID("nearbyStoresId"),
	    DEALER_NAME("dealerName"),
	    CONTACT_PHONE_NUMBER("contactPhoneNumber"),
	    RESERVE_TELEPHONE("reserveTelephone"),
	    RECHARGE_AMOUNT("rechargeAmount"),
	    RECHARGE_AMOUNT_CAP("rechargeAmountCap"),
	    PREPAID_AMOUNT("prepaidAmount"),
	    CREATED_DATE("createdDate"),
	    CREATED_BY_ID("createdById"),
	    CREATED_BY_NAME("createdByName");
	      private String value;
	      ATTRIBUTE(String value) {
	        this.value = value;
	      };
	      public String getValue() {
	        return this.value;
	      }
	  };
	
	private int nearbyStoresId;
	private String dealerName;
	private String contactPhoneNumber;
	private String reserveTelephone;
	private BigDecimal rechargeAmount;//充值金额
	private BigDecimal rechargeAmountCap;//原总额度
	private BigDecimal prepaidAmount;//原已使用额度
	private Date createdDate;
	private String createdById;
	private String createdByName;
	
	public DealerTopUpRecordDTO() {
	}

	public DealerTopUpRecordDTO(int nearbyStoresId, String dealerName, String contactPhoneNumber, String reserveTelephone,
			BigDecimal rechargeAmount, BigDecimal rechargeAmountCap, BigDecimal prepaidAmount, Date createdDate,
			String createdById, String createdByName) {
		super();
		this.nearbyStoresId = nearbyStoresId;
		this.dealerName = dealerName;
		this.contactPhoneNumber = contactPhoneNumber;
		this.reserveTelephone = reserveTelephone;
		this.rechargeAmount = rechargeAmount;
		this.rechargeAmountCap = rechargeAmountCap;
		this.prepaidAmount = prepaidAmount;
		this.createdDate = createdDate;
		this.createdById = createdById;
		this.createdByName = createdByName;
	}

	public int getNearbyStoresId() {
		return nearbyStoresId;
	}

	public void setNearbyStoresId(int nearbyStoresId) {
		this.nearbyStoresId = nearbyStoresId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getReserveTelephone() {
		return reserveTelephone;
	}

	public void setReserveTelephone(String reserveTelephone) {
		this.reserveTelephone = reserveTelephone;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public BigDecimal getRechargeAmountCap() {
		return rechargeAmountCap;
	}

	public void setRechargeAmountCap(BigDecimal rechargeAmountCap) {
		this.rechargeAmountCap = rechargeAmountCap;
	}

	public BigDecimal getPrepaidAmount() {
		return prepaidAmount;
	}

	public void setPrepaidAmount(BigDecimal prepaidAmount) {
		this.prepaidAmount = prepaidAmount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

}
