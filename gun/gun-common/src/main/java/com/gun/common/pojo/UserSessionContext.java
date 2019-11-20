package com.gun.common.pojo;

import java.util.List;
import java.util.Map;

import com.gun.common.entity.FunctionType;


/**
 * Purpose: 登入者資訊物件
 * @author akumadeng
 * @since  JDK 1.5
 * @date   2014/8/4
 * @MaintenancePersonnel akumadeng
 */
public class UserSessionContext extends ValueObject<Integer> implements IUserSessionContext {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -170637724823079270L;

	/**
	 * session Id
	 */
	private String sessionId;
	/**
	 * 密碼
	 */
	private String password;
	/**
	 * 使用者帳號
	 */
	private String userId;
	
	
	/**
	 * 員工帳號
	 */
	private String aliasName;
	/**
	 * 員工編號
	 */
	private String empNo;
	/**
	 * 中文姓名
	 */
	private String empName;
	/**
	 * 英文姓名
	 */
	private String engName;
	/**
	 * 分機
	 */
	private String ext;
	/**
	 * 所屬部門代號
	 */
	private String deptCode;
	/**
	 * 主管身份註記
	 */
	private Boolean isManager;
	/**
	 * 祕書身份註記
	 */
	private Boolean isSecretary;	
	/**
	 * 主辦單位負責人身份註記
	 */
	private Boolean isMajor;
	/**
	 * 代理人清單
	 */
	private List<String> agtEmpNoList;
	/**
	 * 功能權限清單
	 */
	private List<FunctionType> functionList;
	/**
	 * 子功能權限清單
	 */
	private Map<String, List<FunctionType>> subFunctionList;
	
	/**
	 * 隸屬特殊角色清單
	 */
//	private List<RolePrvg> specialRoleList;
	
	/**
	 * 選中的一級功能選單
	 */
	private String selectParentFuncid;
	/**
	 * 選中的二級功能選單
	 */
	private String selectFuncId;
	/**
	 * eform判斷實際登入者參數
	 */
	private String eformLoginName;
	
	private Integer roleId;
	
	private String locale;
	

	/**
	 * Constructor:
	 */
	public UserSessionContext() {
		
	}
	

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}




	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}




	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}




	/**
	 * @return the aliasName
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * @param aliasName the aliasName to set
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	/**
	 * @return the empNo
	 */
	public String getEmpNo() {
		return empNo;
	}

	/**
	 * @param empNo the empNo to set
	 */
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the engName
	 */
	public String getEngName() {
		return engName;
	}

	/**
	 * @param engName the engName to set
	 */
	public void setEngName(String engName) {
		this.engName = engName;
	}

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param ext the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the isManager
	 */
	public Boolean getIsManager() {
		return isManager;
	}

	/**
	 * @param isManager the isManager to set
	 */
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	/**
	 * @return the isSecretary
	 */
	public Boolean getIsSecretary() {
		return isSecretary;
	}

	/**
	 * @param isSecretary the isSecretary to set
	 */
	public void setIsSecretary(Boolean isSecretary) {
		this.isSecretary = isSecretary;
	}

	/**
	 * @return the isMajor
	 */
	public Boolean getIsMajor() {
		return isMajor;
	}

	/**
	 * @param isMajor the isMajor to set
	 */
	public void setIsMajor(Boolean isMajor) {
		this.isMajor = isMajor;
	}

	/**
	 * @return the agtEmpNoList
	 */
	public List<String> getAgtEmpNoList() {
		return agtEmpNoList;
	}

	/**
	 * @param agtEmpNoList the agtEmpNoList to set
	 */
	public void setAgtEmpNoList(List<String> agtEmpNoList) {
		this.agtEmpNoList = agtEmpNoList;
	}

	/**
	 * @return the selectParentFuncid
	 */
	public String getSelectParentFuncid() {
		return selectParentFuncid;
	}


	/**
	 * @param selectParentFuncid the selectParentFuncid to set
	 */
	public void setSelectParentFuncid(String selectParentFuncid) {
		this.selectParentFuncid = selectParentFuncid;
	}


	/**
	 * @return the selectFuncId
	 */
	public String getSelectFuncId() {
		return selectFuncId;
	}


	/**
	 * @param selectFuncId the selectFuncId to set
	 */
	public void setSelectFuncId(String selectFuncId) {
		this.selectFuncId = selectFuncId;
	}


	/**
	 * @return the eformLoginName
	 */
	public String getEformLoginName() {
		return eformLoginName;
	}


	/**
	 * @param eformLoginName the eformLoginName to set
	 */
	public void setEformLoginName(String eformLoginName) {
		this.eformLoginName = eformLoginName;
	}


  
  /**
   * @return the roleId
   */
  public Integer getRoleId() {
    return roleId;
  }


  
  /**
   * @param roleId the roleId to set
   */
  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  
  /**
   * @return the functionList
   */
  public List<FunctionType> getFunctionList() {
    return functionList;
  }


  
  /**
   * @param functionList the functionList to set
   */
  public void setFunctionList(List<FunctionType> functionList) {
    this.functionList = functionList;
  }


  
  /**
   * @return the subFunctionList
   */
  public Map<String, List<FunctionType>> getSubFunctionList() {
    return subFunctionList;
  }


  
  /**
   * @param subFunctionList the subFunctionList to set
   */
  public void setSubFunctionList(Map<String, List<FunctionType>> subFunctionList) {
    this.subFunctionList = subFunctionList;
  }


  
  /**
   * @return the locale
   */
  public String getLocale() {
    return locale;
  }


  
  /**
   * @param locale the locale to set
   */
  public void setLocale(String locale) {
    this.locale = locale;
  }

}
