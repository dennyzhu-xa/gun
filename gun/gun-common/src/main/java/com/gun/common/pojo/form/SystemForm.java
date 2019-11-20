package com.gun.common.pojo.form;

import java.util.List;
import java.util.Map;

import com.gun.common.entity.pojo.FunctionTypeDTO;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.UserSessionContext;


public class SystemForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5692351221544003689L;
	
	public static final String FORM_NAME = "systemForm";
	
  public static enum ATTRIBUTE {
    IDS("ids"),
    SUCCESS("success"),
    DATA("data"),
    RESULT("result"),
    MESSAGE_CODE("messageCode"),
    MESSAGE("message")
    ;
    
    private String value;
    ATTRIBUTE(String value) {
      this.value = value;
    };
    public String getValue() {
      return this.value;
    }
  };
	
	public SystemForm() {
		
	}

	/**
	 * 用戶List
	 */
	private List<SysUserInfoDTO> userList;
	/**
	 * 角色List
	 */
	private List<ParamItem> roleResults;
	 /**
   * 功能權限清單
   */
  private List<FunctionTypeDTO> functionList;
  /**
   * 子功能權限清單
   */
  private Map<String, List<FunctionTypeDTO>> subFunctionList;
	

	/**
	 * @return the 用戶List
	 */
	public List<SysUserInfoDTO> getUserList() {
		return userList;
	}
	

	/**
	 * @param 用戶List the userList to set
	 */
	public void setUserList(List<SysUserInfoDTO> userList) {
		this.userList = userList;
	}

  
  /**
   * @return the roleResults
   */
  public List<ParamItem> getRoleResults() {
    return roleResults;
  }


  
  /**
   * @param roleResults the roleResults to set
   */
  public void setRoleResults(List<ParamItem> roleResults) {
    this.roleResults = roleResults;
  }


  
  /**
   * @return the functionList
   */
  public List<FunctionTypeDTO> getFunctionList() {
    return functionList;
  }


  
  /**
   * @param functionList the functionList to set
   */
  public void setFunctionList(List<FunctionTypeDTO> functionList) {
    this.functionList = functionList;
  }


  
  /**
   * @return the subFunctionList
   */
  public Map<String, List<FunctionTypeDTO>> getSubFunctionList() {
    return subFunctionList;
  }


  
  /**
   * @param subFunctionList the subFunctionList to set
   */
  public void setSubFunctionList(Map<String, List<FunctionTypeDTO>> subFunctionList) {
    this.subFunctionList = subFunctionList;
  }


}
