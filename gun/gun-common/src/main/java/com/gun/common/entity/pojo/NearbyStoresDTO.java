package com.gun.common.entity.pojo;

import java.math.BigDecimal;

import com.gun.common.pojo.ValueObject;

/**
 * 
 * @author felixli
 *
 */
public class NearbyStoresDTO extends ValueObject<Integer> {

  private static final long serialVersionUID = 5495866029593232230L;
  
  //枚举
  public static enum ATTRIBUTE {
    ID("id"),
    DEALER_ACCOUNT("dealerAccount"),
    DEALER_PASSWORD("dealerPassword"),
    DEALER_NAME("dealerName"),
    DEALER_CATEGORY("dealerCategory"),
    PERSON_IN_CHARGE("personInCharge"),
    DEPOSIT_AMOUNT("depositAmount"),
    RECHARGE_AMOUNT_CAP("rechargeAmountCap"),
    PREPAID_AMOUNT("prepaidAmount"),
    RECHARGE_BALANCE("rechargeBalance"),
    CONTACT_PHONE_NUMBER("contactPhoneNumber"),
    RESERVE_TELEPHONE("reserveTelephone"),
    LONGITUDE("longitude"),
    LATITUDE("latitude"),
    ADDRESS("address"),
    CREATED_DATE("createdDate"),
    CREATED_DATE_START("createdDateStart"),
    CREATED_DATE_END("createdDateEnd"),
    CREATED_BYID("createdById"),
    CREATED_BYNAME("createdByName"),
    UPDATE_DATE("updateDate"),
    UPDATE_BYID("updateById"),
    TOKEN("token"),
    ACCUMULATED_AMOUNT("accumulatedAmount"),
    UPDATE_BYNAME("updateByName"),
    RESET_FLAG("resetFlag"),
    ADDED_AMOUNT("addedAmount");
      private String value;
      ATTRIBUTE(String value) {
        this.value = value;
      };
      public String getValue() {
        return this.value;
      }
  };
  
  private String dealerAccount;
  private String dealerPassword;
  private String dealerName;
  private String dealerCategory;
  private String personInCharge;
  private BigDecimal rechargeAmountCap;
  private BigDecimal prepaidAmount;
  private BigDecimal rechargeBalance;
  private String contactPhoneNumber;
  private String reserveTelephone;
  private String longitude;
  private String latitude;
  private String address;
  private String createdDate;
  private String createdById;
  private String createdByName;
  private String updateDate;
  private String updateById;
  private String updateByName;
  private String token;
  private String firstLanding;
  private BigDecimal accumulatedAmount;
  private String status;
  private BigDecimal addedAmount;
  private BigDecimal depositAmount;
  private String note;
  private String resetFlag;
  private Integer totalUsers;
  private Integer totalPrepaid;
  

  public NearbyStoresDTO() {
  }


  
  /**
   * Constructor:
   */
  public NearbyStoresDTO(String dealerAccount, String dealerPassword, String dealerName, String dealerCategory, String personInCharge, BigDecimal rechargeAmountCap,
          BigDecimal rechargeBalance, String contactPhoneNumber, String reserveTelephone, String longitude, String latitude, String address, String createdDate,
          String createdById, String createdByName, String updateDate, String updateById, String updateByName, String token ,String firstLanding,BigDecimal accumulatedAmount, String status, BigDecimal addedAmount) {
    this.dealerAccount = dealerAccount;
    this.dealerPassword = dealerPassword;
    this.dealerName = dealerName;
    this.dealerCategory = dealerCategory;
    this.personInCharge = personInCharge;
    this.rechargeAmountCap = rechargeAmountCap;
    this.rechargeBalance = rechargeBalance;
    this.contactPhoneNumber = contactPhoneNumber;
    this.reserveTelephone = reserveTelephone;
    this.longitude = longitude;
    this.latitude = latitude;
    this.address = address;
    this.createdDate = createdDate;
    this.createdById = createdById;
    this.createdByName = createdByName;
    this.updateDate = updateDate;
    this.updateById = updateById;
    this.updateByName = updateByName;
    this.token = token;
    this.firstLanding = firstLanding;
    this.accumulatedAmount = accumulatedAmount;
    this.status = status;
    this.addedAmount = addedAmount;
  }



  
  /**
   * @return the dealerAccount
   */
  public String getDealerAccount() {
    return dealerAccount;
  }



  
  /**
   * @param dealerAccount the dealerAccount to set
   */
  public void setDealerAccount(String dealerAccount) {
    this.dealerAccount = dealerAccount;
  }



  
  /**
   * @return the dealerPassword
   */
  public String getDealerPassword() {
    return dealerPassword;
  }



  
  /**
   * @param dealerPassword the dealerPassword to set
   */
  public void setDealerPassword(String dealerPassword) {
    this.dealerPassword = dealerPassword;
  }



  /**
   * @return the dealerName
   */
  public String getDealerName() {
    return dealerName;
  }





  
  /**
   * @param dealerName the dealerName to set
   */
  public void setDealerName(String dealerName) {
    this.dealerName = dealerName;
  }





  
  /**
   * @return the dealerCategory
   */
  public String getDealerCategory() {
    return dealerCategory;
  }





  
  /**
   * @param dealerCategory the dealerCategory to set
   */
  public void setDealerCategory(String dealerCategory) {
    this.dealerCategory = dealerCategory;
  }





  
  /**
   * @return the personInCharge
   */
  public String getPersonInCharge() {
    return personInCharge;
  }





  
  /**
   * @param personInCharge the personInCharge to set
   */
  public void setPersonInCharge(String personInCharge) {
    this.personInCharge = personInCharge;
  }





  
  /**
   * @return the rechargeAmountCap
   */
  public BigDecimal getRechargeAmountCap() {
    return rechargeAmountCap;
  }





  
  /**
   * @param rechargeAmountCap the rechargeAmountCap to set
   */
  public void setRechargeAmountCap(BigDecimal rechargeAmountCap) {
    this.rechargeAmountCap = rechargeAmountCap;
  }





  
  /**
   * @return the rechargeBalance
   */
  public BigDecimal getRechargeBalance() {
    return rechargeBalance;
  }





  
  /**
   * @param rechargeBalance the rechargeBalance to set
   */
  public void setRechargeBalance(BigDecimal rechargeBalance) {
    this.rechargeBalance = rechargeBalance;
  }





  
  /**
   * @return the contactPhoneNumber
   */
  public String getContactPhoneNumber() {
    return contactPhoneNumber;
  }





  
  /**
   * @param contactPhoneNumber the contactPhoneNumber to set
   */
  public void setContactPhoneNumber(String contactPhoneNumber) {
    this.contactPhoneNumber = contactPhoneNumber;
  }





  
  /**
   * @return the reserveTelephone
   */
  public String getReserveTelephone() {
    return reserveTelephone;
  }





  
  /**
   * @param reserveTelephone the reserveTelephone to set
   */
  public void setReserveTelephone(String reserveTelephone) {
    this.reserveTelephone = reserveTelephone;
  }





  
  /**
   * @return the longitude
   */
  public String getLongitude() {
    return longitude;
  }





  
  /**
   * @param longitude the longitude to set
   */
  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }





  
  /**
   * @return the latitude
   */
  public String getLatitude() {
    return latitude;
  }





  
  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }





  
  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }





  
  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }





  
  /**
   * @return the createdDate
   */
  public String getCreatedDate() {
    return createdDate;
  }





  
  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }





  
  /**
   * @return the createdById
   */
  public String getCreatedById() {
    return createdById;
  }





  
  /**
   * @param createdById the createdById to set
   */
  public void setCreatedById(String createdById) {
    this.createdById = createdById;
  }





  
  /**
   * @return the createdByName
   */
  public String getCreatedByName() {
    return createdByName;
  }





  
  /**
   * @param createdByName the createdByName to set
   */
  public void setCreatedByName(String createdByName) {
    this.createdByName = createdByName;
  }





  
  /**
   * @return the updateDate
   */
  public String getUpdateDate() {
    return updateDate;
  }





  
  /**
   * @param updateDate the updateDate to set
   */
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }





  
  /**
   * @return the updateById
   */
  public String getUpdateById() {
    return updateById;
  }





  
  /**
   * @param updateById the updateById to set
   */
  public void setUpdateById(String updateById) {
    this.updateById = updateById;
  }





  
  /**
   * @return the updateByName
   */
  public String getUpdateByName() {
    return updateByName;
  }





  
  /**
   * @param updateByName the updateByName to set
   */
  public void setUpdateByName(String updateByName) {
    this.updateByName = updateByName;
  }



  
  /**
   * @return the prepaidAmount
   */
  public BigDecimal getPrepaidAmount() {
    return prepaidAmount;
  }



  
  /**
   * @param prepaidAmount the prepaidAmount to set
   */
  public void setPrepaidAmount(BigDecimal prepaidAmount) {
    this.prepaidAmount = prepaidAmount;
  }



	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	
	
	
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}



  
  /**
   * @return the firstLanding
   */
  public String getFirstLanding() {
    return firstLanding;
  }



  
  /**
   * @param firstLanding the firstLanding to set
   */
  public void setFirstLanding(String firstLanding) {
    this.firstLanding = firstLanding;
  }



	/**
	 * @return the accumulatedAmount
	 */
	public BigDecimal getAccumulatedAmount() {
		return accumulatedAmount;
	}
	
	
	
	/**
	 * @param accumulatedAmount the accumulatedAmount to set
	 */
	public void setAccumulatedAmount(BigDecimal accumulatedAmount) {
		this.accumulatedAmount = accumulatedAmount;
	}



  
  /**
   * @return the resetFlag
   */
  public String getResetFlag() {
    return resetFlag;
  }



  
  /**
   * @param resetFlag the resetFlag to set
   */
  public void setResetFlag(String resetFlag) {
    this.resetFlag = resetFlag;
  }



  
  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }



  
  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }



public BigDecimal getAddedAmount() {
	return addedAmount;
}



public void setAddedAmount(BigDecimal addedAmount) {
	this.addedAmount = addedAmount;
}



public String getNote() {
	return note;
}



public void setNote(String note) {
	this.note = note;
}



public BigDecimal getDepositAmount() {
	return depositAmount;
}



public void setDepositAmount(BigDecimal depositAmount) {
	this.depositAmount = depositAmount;
}



public Integer getTotalUsers() {
	return totalUsers;
}



public void setTotalUsers(Integer totalUsers) {
	this.totalUsers = totalUsers;
}



public Integer getTotalPrepaid() {
	return totalPrepaid;
}



public void setTotalPrepaid(Integer totalPrepaid) {
	this.totalPrepaid = totalPrepaid;
}
  
}
