package com.gun.common.pojo.form;

import java.util.List;

import com.gun.common.entity.pojo.LotteryReportDTO;
import com.gun.common.entity.pojo.NearbyStoresDTO;
import com.gun.common.pojo.ParamItem;

/**
 * @author felixli
 *
 */
public class LotteryReportForm extends SystemForm{

  /**
   * 
   */
  private static final long serialVersionUID = -1876023963192575477L;
  
  public static final String FORM_NAME = "lotteryReportForm";
  
  public static final String NUMBER_OF_PERIODS_LIST = "numberOfPeriodsList";
  
  /**
   * Report - stock balance
   */
  public static final String REPORT_JRXML_NAME              = "StockBalance";
  public static final String REPORT_SUB_SOLD_JRXML_NAME     = "StockBalance_Subreport_Sold";
  public static final String REPORT_SUB_UNSOLD_JRXML_NAME   = "StockBalance_subreport_Unsold";
  public static final String REPORT_SUB_SOLD_NAME           = "sub_sold";
  public static final String REPORT_SUB_UNSOLD_NAME         = "sub_unsold";
  
  /**
   * Report - sale ticket list
   */
  public static final String REPORT_SALE_TICKET_JRXML_NAME              = "SaleTicketList";
  public static final String REPORT_MERCHANT_SETTLEMEN_JRXML_NAME       = "MerchantInformation";
  
  /**
   * 期数
   */
  private String queryNumberOfPeriods;
  /**
   * 当期彩票售出数量
   */
  private Integer currentSoldNumber;
  /**
   * 当期彩票未售出数量
   */
  private Integer currentUnsoldNumber;
  
  /**
   * 期数列表
   */
  private List<ParamItem> numberOfPeriodsList;
  
  private List<LotteryReportDTO> lotterySaleList;
  
  private List<LotteryReportDTO> saleTicketList;
  
  private List<NearbyStoresDTO> merchantSettlementList; 
  
  private String queryDealerName;
  
  private String queryDealerCategory;

  
  /**
   * @return the queryNumberOfPeriods
   */
  public String getQueryNumberOfPeriods() {
    return queryNumberOfPeriods;
  }

  
  /**
   * @param queryNumberOfPeriods the queryNumberOfPeriods to set
   */
  public void setQueryNumberOfPeriods(String queryNumberOfPeriods) {
    this.queryNumberOfPeriods = queryNumberOfPeriods;
  }


  
  /**
   * @return the numberOfPeriodsList
   */
  public List<ParamItem> getNumberOfPeriodsList() {
    return numberOfPeriodsList;
  }


  
  /**
   * @param numberOfPeriodsList the numberOfPeriodsList to set
   */
  public void setNumberOfPeriodsList(List<ParamItem> numberOfPeriodsList) {
    this.numberOfPeriodsList = numberOfPeriodsList;
  }


  
  /**
   * @return the lotterySaleList
   */
  public List<LotteryReportDTO> getLotterySaleList() {
    return lotterySaleList;
  }


  
  /**
   * @param lotterySaleList the lotterySaleList to set
   */
  public void setLotterySaleList(List<LotteryReportDTO> lotterySaleList) {
    this.lotterySaleList = lotterySaleList;
  }


  
  /**
   * @return the currentSoldNumber
   */
  public Integer getCurrentSoldNumber() {
    return currentSoldNumber;
  }


  
  /**
   * @param currentSoldNumber the currentSoldNumber to set
   */
  public void setCurrentSoldNumber(Integer currentSoldNumber) {
    this.currentSoldNumber = currentSoldNumber;
  }


  
  /**
   * @return the currentUnsoldNumber
   */
  public Integer getCurrentUnsoldNumber() {
    return currentUnsoldNumber;
  }


  
  /**
   * @param currentUnsoldNumber the currentUnsoldNumber to set
   */
  public void setCurrentUnsoldNumber(Integer currentUnsoldNumber) {
    this.currentUnsoldNumber = currentUnsoldNumber;
  }


  
  /**
   * @return the saleTicketList
   */
  public List<LotteryReportDTO> getSaleTicketList() {
    return saleTicketList;
  }


  
  /**
   * @param saleTicketList the saleTicketList to set
   */
  public void setSaleTicketList(List<LotteryReportDTO> saleTicketList) {
    this.saleTicketList = saleTicketList;
  }


	/**
	 * @return the queryDealerName
	 */
	public String getQueryDealerName() {
		return queryDealerName;
	}
	
	
	/**
	 * @param queryDealerName the queryDealerName to set
	 */
	public void setQueryDealerName(String queryDealerName) {
		this.queryDealerName = queryDealerName;
	}


	/**
	 * @return the queryDealerCategory
	 */
	public String getQueryDealerCategory() {
		return queryDealerCategory;
	}


	/**
	 * @param queryDealerCategory the queryDealerCategory to set
	 */
	public void setQueryDealerCategory(String queryDealerCategory) {
		this.queryDealerCategory = queryDealerCategory;
	}


	/**
	 * @return the merchantSettlementList
	 */
	public List<NearbyStoresDTO> getMerchantSettlementList() {
		return merchantSettlementList;
	}


	/**
	 * @param merchantSettlementList the merchantSettlementList to set
	 */
	public void setMerchantSettlementList(List<NearbyStoresDTO> merchantSettlementList) {
		this.merchantSettlementList = merchantSettlementList;
	}
  
}
