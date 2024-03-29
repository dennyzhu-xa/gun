package com.gun.common.entity.pojo;
// Generated 2017-7-5 9:47:48 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.entity.AndroidUser;
import com.gun.common.pojo.ValueObject;

/**
 * AndroidUser generated by hbm2java
 */
public class AndroidUserDTO extends ValueObject<BigDecimal> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -45257167823443581L;

	public static enum ATTRIBUTE {
		ID("id"),
		USERNAME("username"),
		PASSWORD("password"),
		PASSWORD_SALT("passwordSalt"),
		FULL_NAME("fullName"),
		NRC("nrc"),
		BIRTHDAY("birthday"),
		GENDER("gender"),
		ADDRESS("address"),
		STATUS("status"),
		TOKEN("token"),
		CREATE_BY_ID("createById"),
		CREATED_BY_NAME("createdByName"),
		CREATED_DATE("createdDate"),
		UPDATED_BY_ID("updatedById"),
		UPDATED_BY_NAME("updatedByName"),
		UPDATED_DATE("updatedDate"),
		BALANCE("balance"),
		FACEBOOK_USER_ID("facebookUserId"),
		LAST_LOGON_TIME("lastLogonTime"),
		LAST_PURCHASE_TIME("lastPurchaseTime");
		private String value;
		ATTRIBUTE(String value) {
			this.value = value;
		};
		public String getValue() {
			return this.value;
		}
	}

	private String username;
	private String password;
	private String passwordSalt;
	private String fullName;
	private String nrc;
	private String birthday;
	private String mobileNo;
	private String gender;
	private String address;
	private String detailAddress;
	private byte status;
	private String token;
	private String createById;
	private String createdByName;
	private Date createdDate;
	private String updatedById;
	private String updatedByName;
	private Date updatedDate;
	private Date lastLogonTime;
	private Date lastPurchaseTime;
	private BigDecimal balance;
	private String facebookUserId;
	private String facebookAccount;
	private String pictureUri;
	private String bankCardNumber;
	private BigDecimal  totalPrizeAmount;//中獎金額
	private Integer winningTimes;//中獎次數
	private BigDecimal totalExpenditure;//總花費
	private Integer purchaseTimes;//購買次數
	private Integer pieces;//購買張數

	public AndroidUserDTO() {
	}
	
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPictureUri() {
		return pictureUri;
	}

	public void setPictureUri(String pictureUri) {
		this.pictureUri = pictureUri;
	}
	public String getFacebookUserId() {
		return facebookUserId;
	}

	public void setFacebookUserId(String facebookUserId) {
		this.facebookUserId = facebookUserId;
	}

	public String getFacebookAccount() {
		return facebookAccount;
	}

	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNrc() {
		return this.nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreateById() {
		return this.createById;
	}

	public void setCreateById(String createById) {
		this.createById = createById;
	}

	public String getCreatedByName() {
		return this.createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedById() {
		return this.updatedById;
	}

	public void setUpdatedById(String updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedByName() {
		return this.updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getLastLogonTime() {
		return lastLogonTime;
	}

	public void setLastLogonTime(Date lastLogonTime) {
		this.lastLogonTime = lastLogonTime;
	}

	public Date getLastPurchaseTime() {
		return lastPurchaseTime;
	}

	public void setLastPurchaseTime(Date lastPurchaseTime) {
		this.lastPurchaseTime = lastPurchaseTime;
	}

	public AndroidUser transformer(AndroidUser androidUser){
		if (androidUser == null){
			androidUser = new AndroidUser();
		}
		androidUser.setId(this.getId());
		androidUser.setUsername(this.username);
		androidUser.setPassword(this.password);
		androidUser.setPasswordSalt(this.passwordSalt);
		androidUser.setFullName(this.fullName);
		androidUser.setNrc(this.nrc);
		androidUser.setBirthday(this.birthday);
		androidUser.setGender(this.gender);
		androidUser.setAddress(this.address);
		androidUser.setDetailAddress(this.detailAddress);
		androidUser.setMobileNo(this.mobileNo);
		androidUser.setStatus(this.status);
		androidUser.setToken(this.token);
		androidUser.setCreateById(this.createById);
		androidUser.setCreatedByName(this.createdByName);
		androidUser.setCreatedDate(this.createdDate);
		androidUser.setUpdatedById(this.updatedById);
		androidUser.setUpdatedByName(this.updatedByName);
		androidUser.setUpdatedDate(this.updatedDate);
		androidUser.setLastLogonTime(this.lastLogonTime);
		androidUser.setLastPurchaseTime(this.lastPurchaseTime);
		androidUser.setFacebookAccount(this.facebookAccount);
		androidUser.setFacebookUserId(this.facebookUserId);
		androidUser.setPictureUri(this.pictureUri);
		androidUser.setBankCardNumber(this.bankCardNumber);
		return androidUser;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getTotalPrizeAmount() {
		return totalPrizeAmount;
	}

	public void setTotalPrizeAmount(BigDecimal totalPrizeAmount) {
		this.totalPrizeAmount = totalPrizeAmount;
	}

	public Integer getWinningTimes() {
		return winningTimes;
	}

	public void setWinningTimes(Integer winningTimes) {
		this.winningTimes = winningTimes;
	}

	public BigDecimal getTotalExpenditure() {
		return totalExpenditure;
	}

	public void setTotalExpenditure(BigDecimal totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}

	/**
	 * @return the purchaseTimes
	 */
	public Integer getPurchaseTimes() {
		return purchaseTimes;
	}

	/**
	 * @param purchaseTimes the purchaseTimes to set
	 */
	public void setPurchaseTimes(Integer purchaseTimes) {
		this.purchaseTimes = purchaseTimes;
	}

	public Integer getPieces() {
		return pieces;
	}

	public void setPieces(Integer pieces) {
		this.pieces = pieces;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	
}
