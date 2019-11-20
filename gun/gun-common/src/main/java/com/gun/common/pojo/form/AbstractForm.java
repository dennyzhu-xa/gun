package com.gun.common.pojo.form;

import com.gun.common.pojo.IUserSessionContext;
import com.gun.common.pojo.ValueObject;

/**
 * @author Felixli
 * @since  JDK 1.5
 * @date   2017/3/22
 * @maintenance Felixli
 */
public abstract class AbstractForm extends ValueObject<String> {

	private static final long serialVersionUID = 2813325685119469773L;
	public static final String PAGE_SIZE = "pageSize";
	private IUserSessionContext userSessionContext;
	
	private String actionId;
	private String pageSet; //每頁顯示幾筆資料參數
  private Integer pageNo; //當前是第幾頁
  private String chgPageFlag;//是否換頁註記 
  private String messageCode;
  private String selectPageFuncId;
  private String notDataCode;
  /**
   * count per page
   */
  private Integer start;

  private Integer limit;
	/**
	 * eform簽核連結之baseURL
	 */
	private String baseUrl;
    /**
     * 分頁元件
     */
//    private List<SysPrmrDef> recPerPageList;
    
	/**
	 * AbstractForm建構子
	 */
	public AbstractForm() {
	}
	/**
	 * @return the userSessionContext
	 */
	public IUserSessionContext getUserSessionContext() {
		return userSessionContext;
	}
	/**
	 * @param userSessionContext the userSessionContext to set
	 */
	public void setUserSessionContext(IUserSessionContext userSessionContext) {
		this.userSessionContext = userSessionContext;
	}
	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}
	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	/**
	 * @return the pageSet
	 */
	public String getPageSet() {
		return pageSet;
	}
	/**
	 * @param pageSet the pageSet to set
	 */
	public void setPageSet(String pageSet) {
		this.pageSet = pageSet;
	}
	
	
  /**
   * @return the pageNo
   */
  public Integer getPageNo() {
    return pageNo;
  }
  
  /**
   * @param pageNo the pageNo to set
   */
  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }
  /**
	 * @return the chgPageFlag
	 */
	public String getChgPageFlag() {
		return chgPageFlag;
	}
	/**
	 * @param chgPageFlag the chgPageFlag to set
	 */
	public void setChgPageFlag(String chgPageFlag) {
		this.chgPageFlag = chgPageFlag;
	}
	/**
	 * @return the recPerPageList
	 */
//	public List<SysPrmrDef> getRecPerPageList() {
//		return recPerPageList;
//	}
//	/**
//	 * @param recPerPageList the recPerPageList to set
//	 */
//	public void setRecPerPageList(List<SysPrmrDef> recPerPageList) {
//		this.recPerPageList = recPerPageList;
//	}
	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}
	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
  
  /**
   * @return the start
   */
  public Integer getStart() {
    return start;
  }
  
  /**
   * @param start the start to set
   */
  public void setStart(Integer start) {
    this.start = start;
  }
  
  /**
   * @return the limit
   */
  public Integer getLimit() {
    return limit;
  }
  
  /**
   * @param limit the limit to set
   */
  public void setLimit(Integer limit) {
    this.limit = limit;
  }
  
  /**
   * @return the messageCode
   */
  public String getMessageCode() {
    return messageCode;
  }
  
  /**
   * @param messageCode the messageCode to set
   */
  public void setMessageCode(String messageCode) {
    this.messageCode = messageCode;
  }
  
  /**
   * @return the selectPageFuncId
   */
  public String getSelectPageFuncId() {
    return selectPageFuncId;
  }
  
  /**
   * @param selectPageFuncId the selectPageFuncId to set
   */
  public void setSelectPageFuncId(String selectPageFuncId) {
    this.selectPageFuncId = selectPageFuncId;
  }
  
  /**
   * @return the notDataCode
   */
  public String getNotDataCode() {
    return notDataCode;
  }
  
  /**
   * @param notDataCode the notDataCode to set
   */
  public void setNotDataCode(String notDataCode) {
    this.notDataCode = notDataCode;
  }

}
