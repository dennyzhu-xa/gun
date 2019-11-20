package com.gun.common.pojo.form;

import java.util.List;

import com.gun.common.entity.pojo.UserFeedbackDTO;

/**
 * 
 * Purpose: 用户反馈form 
 * @author davidsheng
 * @since  JDK 1.7
 * @date   2017/7/4
 * @MaintenancePersonnel cybersoft
 */
public class UserFeedbackForm extends SystemForm{

	/**
	 * 	序列号
	 */
	private static final long serialVersionUID = -456323029876288046L;

	public static final String FORM_NAME = "userFeedbackForm";
	
	/**
	 * 查询条件：建议
	 */
	private String querySuggestion;
	/**
	 * 建议list
	 */
	private List<UserFeedbackDTO> userFeedbackDTOList;
	/**
	 * @return the querySuggestion
	 */
	public String getQuerySuggestion() {
		return querySuggestion;
	}
	/**
	 * @param querySuggestion the querySuggestion to set
	 */
	public void setQuerySuggestion(String querySuggestion) {
		this.querySuggestion = querySuggestion;
	}
	/**
	 * @return the userFeedbackDTOList
	 */
	public List<UserFeedbackDTO> getUserFeedbackDTOList() {
		return userFeedbackDTOList;
	}
	/**
	 * @param userFeedbackDTOList the userFeedbackDTOList to set
	 */
	public void setUserFeedbackDTOList(List<UserFeedbackDTO> userFeedbackDTOList) {
		this.userFeedbackDTOList = userFeedbackDTOList;
	}
	
	
}
