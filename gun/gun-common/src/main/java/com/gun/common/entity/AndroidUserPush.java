package com.gun.common.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * AndroidUserPush generated by hbm2java
 */
public class AndroidUserPush extends ValueObject<BigDecimal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4102607272771591223L;

	private BigDecimal pushId;
	private String theme;
	private String content;
	private BigDecimal userId;
	private Date createdDate;
	private String failureTime;
	private int pushState;
	private String pushSuccess;
	private String pushType;
	private byte[] pushImg;
	private String lotteryRound;
	/**
	 * @return the pushId
	 */
	public BigDecimal getPushId() {
		return pushId;
	}
	/**
	 * @param pushId the pushId to set
	 */
	public void setPushId(BigDecimal pushId) {
		this.pushId = pushId;
	}
	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}
	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the userId
	 */
	public BigDecimal getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
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
	 * @return the failureTime
	 */
	public String getFailureTime() {
		return failureTime;
	}
	/**
	 * @param failureTime the failureTime to set
	 */
	public void setFailureTime(String failureTime) {
		this.failureTime = failureTime;
	}
	/**
	 * @return the pushState
	 */
	public int getPushState() {
		return pushState;
	}
	/**
	 * @param pushState the pushState to set
	 */
	public void setPushState(int pushState) {
		this.pushState = pushState;
	}
	/**
	 * @return the pushSuccess
	 */
	public String getPushSuccess() {
		return pushSuccess;
	}
	/**
	 * @param pushSuccess the pushSuccess to set
	 */
	public void setPushSuccess(String pushSuccess) {
		this.pushSuccess = pushSuccess;
	}
	/**
	 * @return the pushType
	 */
	public String getPushType() {
		return pushType;
	}
	/**
	 * @param pushType the pushType to set
	 */
	public void setPushType(String pushType) {
		this.pushType = pushType;
	}
	/**
	 * @return the pushImg
	 */
	public byte[] getPushImg() {
		return pushImg;
	}
	/**
	 * @param pushImg the pushImg to set
	 */
	public void setPushImg(byte[] pushImg) {
		this.pushImg = pushImg;
	}
	public String getLotteryRound() {
		return lotteryRound;
	}
	public void setLotteryRound(String lotteryRound) {
		this.lotteryRound = lotteryRound;
	}
	
	
}
