package com.gun.common.pojo.form;

import java.util.List;

import com.gun.common.entity.pojo.AndroidUserDTO;




/**
 * 
 * Purpose: 客戶信息Form；
 * @author samDuan
 * @since  JDK 1.7
 * @date   2017/7/26
 * @MaintenancePersonnel samDuan
 */
public class CustomerInformationForm extends SystemForm{

  /**
   * 
   */
  private static final long serialVersionUID = 2280909875640128427L;
  
  public static final String FORM_NAME 							= "customerInformationForm";
  /**
   * 報表的名字
   */
  public static final String JXML_CUSTOMER_INFORMATION			= "customer_information";
  /**
   * 報表匯出的名字
   */
  public static final String CUSTOMER_INFORMATION_SURFACE		= "CUSTOMER_INFORMATION_SURFACE";
  /**
   * 用戶信息集合
   */
  private List<AndroidUserDTO> androidUserDTOList;
  /**
   * 查詢條件
   */
  private String queryPhoneNumber;
  
  private String queryMoblieNo;
  

/**
 * @return the androidUserDTOList
 */
public List<AndroidUserDTO> getAndroidUserDTOList() {
	return androidUserDTOList;
}

/**
 * @param androidUserDTOList the androidUserDTOList to set
 */
public void setAndroidUserDTOList(List<AndroidUserDTO> androidUserDTOList) {
	this.androidUserDTOList = androidUserDTOList;
}

/**
 * @return the queryPhoneNumber
 */
public String getQueryPhoneNumber() {
	return queryPhoneNumber;
}

/**
 * @param queryPhoneNumber the queryPhoneNumber to set
 */
public void setQueryPhoneNumber(String queryPhoneNumber) {
	this.queryPhoneNumber = queryPhoneNumber;
}

public String getQueryMoblieNo() {
	return queryMoblieNo;
}

public void setQueryMoblieNo(String queryMoblieNo) {
	this.queryMoblieNo = queryMoblieNo;
}


  
}
