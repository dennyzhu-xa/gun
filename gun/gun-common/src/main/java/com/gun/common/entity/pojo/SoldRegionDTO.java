package com.gun.common.entity.pojo;

import java.util.List;
/**
 * Purpose: 地区售卖彩票信息
 * @author albertsun
 * @since  JDK 1.6
 * @date   2017/9/6
 * @MaintenancePersonnel albertsun
 */
public class SoldRegionDTO {
	public static enum ATTRIBUTE {
	    PERIODS("periods"),
	    SOLD_NUMBER("soldNumber"),
	    PROVINCE("province"),
	    CITY("city"),
	    NUMBER_OF_PEOPLE("numberOfPeople"),
	      ;
	      private String value;
	      ATTRIBUTE(String value) {
	        this.value = value;
	      };
	      public String getValue() {
	        return this.value;
	      }
	  };
	 /**
	  * 彩票期数
	  */
	  private String periods;
	  /**
	   * 售出彩票数量
	   */
	  private int soldNumber;
	  /**
	   * 省
	   */
	  private String province;
	  /**
	   * 市
	   */
	  private String city;
	  /**
	   * 注册人数
	   */
	  private int numberOfPeople;
	  /**
	   * 地区售卖彩票信息DTOList
	   */
	private List<SoldRegionDTO> soldRegionList;

	/**
	 * @return the soldRegionList
	 */
	public final List<SoldRegionDTO> getSoldRegionList() {
		return soldRegionList;
	}

	/**
	 * @param soldRegionList the soldRegionList to set
	 */
	public final void setSoldRegionList(List<SoldRegionDTO> soldRegionList) {
		this.soldRegionList = soldRegionList;
	}

	/**
	 * @return the periods
	 */
	public final String getPeriods() {
		return periods;
	}

	/**
	 * @param periods the periods to set
	 */
	public final void setPeriods(String periods) {
		this.periods = periods;
	}
	
	/**
	 * @return the province
	 */
	public final String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public final void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public final String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public final void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the soldNumber
	 */
	public final int getSoldNumber() {
		return soldNumber;
	}

	/**
	 * @param soldNumber the soldNumber to set
	 */
	public final void setSoldNumber(int soldNumber) {
		this.soldNumber = soldNumber;
	}

	/**
	 * @return the numberOfPeople
	 */
	public final int getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * @param numberOfPeople the numberOfPeople to set
	 */
	public final void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

}
