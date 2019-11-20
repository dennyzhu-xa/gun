package com.gun.common.entity.pojo;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * SysUserInfo generated by hbm2java
 */
public class LotteryNumberDTO extends ValueObject<BigDecimal> {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2209501167360545394L;
	
	//枚举
	public static enum ATTRIBUTE {
		ID("id"),
		RULE_ID("ruleId"),
		LOTTERY_PERIODS("lotteryPeriods"),
		LETTER_RULE("letterRule"),
		NUMBER_RULE("numberRule"),
		LOTTERY_NUMBER("lotteryNumber"),
		PURCHASE_BY_NAME("purchaseByName"),
		ON_SALE("onSale"),
		OFF_SALE("offSale"),
		LOTTERY_TIMES("lotteryTimes"),
		LOTTERY_CATEGORY("lotteryCategory"),
		SUB_CATEGORY_NAME("subCategoryName"),
		PURCHASE_DATE("purchaseDate"),
		PURCHASE_STATUS("purchaseStatus")
	    ;
	    private String value;
	    ATTRIBUTE(String value) {
	      this.value = value;
	    };
	    public String getValue() {
	      return this.value;
	    }
	};
	
	private BigDecimal ruleId;//规则id
	private String lotteryPeriods;//彩票期数
	private String lotteryNumber;//彩票号码
	private String purchaseByName;//购买人
	private Date purchaseDate;//购买日期
	private String purchaseStatus;//购买状态(已卖/未卖)
	private String letterRule;//字符
	private String numberRule;//数字
	private String lotteryTimes;//开奖时间
	private String onSale;//开售日期
	private String offSale;//停售日期
	private String lotteryCategory;//彩票类别
	private String subCategoryName;
	/**
	 * Constructor:无参构造
	 */
	public LotteryNumberDTO() {
	}
	
	/**
	 * Constructor:有参构造
	 */
	public LotteryNumberDTO(BigDecimal ruleId, String lotteryPeriods,
			String lotteryNumber, String purchaseByName, Date purchaseDate,
			String purchaseStatus, String letterRule, String numberRule,
			String lotteryTimes, String onSale, String offSale,
			String lotteryCategory, String subCategoryName) {
		super();
		this.ruleId = ruleId;
		this.lotteryPeriods = lotteryPeriods;
		this.lotteryNumber = lotteryNumber;
		this.purchaseByName = purchaseByName;
		this.purchaseDate = purchaseDate;
		this.purchaseStatus = purchaseStatus;
		this.letterRule = letterRule;
		this.numberRule = numberRule;
		this.lotteryTimes = lotteryTimes;
		this.onSale = onSale;
		this.offSale = offSale;
		this.lotteryCategory = lotteryCategory;
		this.subCategoryName = subCategoryName;
	}

	/**
	 * @return the ruleId
	 */
	public BigDecimal getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(BigDecimal ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return the lotteryPeriods
	 */
	public String getLotteryPeriods() {
		return lotteryPeriods;
	}

	/**
	 * @param lotteryPeriods the lotteryPeriods to set
	 */
	public void setLotteryPeriods(String lotteryPeriods) {
		this.lotteryPeriods = lotteryPeriods;
	}

	/**
	 * @return the lotteryNumber
	 */
	public String getLotteryNumber() {
		return lotteryNumber;
	}

	/**
	 * @param lotteryNumber the lotteryNumber to set
	 */
	public void setLotteryNumber(String lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}

	/**
	 * @return the purchaseByName
	 */
	public String getPurchaseByName() {
		return purchaseByName;
	}

	/**
	 * @param purchaseByName the purchaseByName to set
	 */
	public void setPurchaseByName(String purchaseByName) {
		this.purchaseByName = purchaseByName;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the purchaseStatus
	 */
	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	/**
	 * @param purchaseStatus the purchaseStatus to set
	 */
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * @return the letterRule
	 */
	public String getLetterRule() {
		return letterRule;
	}

	/**
	 * @param letterRule the letterRule to set
	 */
	public void setLetterRule(String letterRule) {
		this.letterRule = letterRule;
	}

	/**
	 * @return the numberRule
	 */
	public String getNumberRule() {
		return numberRule;
	}

	/**
	 * @param numberRule the numberRule to set
	 */
	public void setNumberRule(String numberRule) {
		this.numberRule = numberRule;
	}

	/**
	 * @return the lotteryTimes
	 */
	public String getLotteryTimes() {
		return lotteryTimes;
	}

	/**
	 * @param lotteryTimes the lotteryTimes to set
	 */
	public void setLotteryTimes(String lotteryTimes) {
		this.lotteryTimes = lotteryTimes;
	}

	/**
	 * @return the onSale
	 */
	public String getOnSale() {
		return onSale;
	}

	/**
	 * @param onSale the onSale to set
	 */
	public void setOnSale(String onSale) {
		this.onSale = onSale;
	}

	/**
	 * @return the offSale
	 */
	public String getOffSale() {
		return offSale;
	}

	/**
	 * @param offSale the offSale to set
	 */
	public void setOffSale(String offSale) {
		this.offSale = offSale;
	}

	/**
	 * @return the lotteryCategory
	 */
	public String getLotteryCategory() {
		return lotteryCategory;
	}

	/**
	 * @param lotteryCategory the lotteryCategory to set
	 */
	public void setLotteryCategory(String lotteryCategory) {
		this.lotteryCategory = lotteryCategory;
	}

	/**
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return subCategoryName;
	}

	/**
	 * @param subCategoryName the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
}