package com.gun.common.pojo;

import java.io.Serializable;

/**
 * @author felixli
 * @since  JDK 1.5
 * @date   2017/3/22
 * @maintenance felixli
 */
public class Model<P, R> implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = -7522683469369566368L;
  private String actionId;
	private P request;
	private R response;
	private LotteryMessage message;
	private PageInfo pageInfo;
	private String logMessageKey="";
	private String logMessage="";
	private String redirectApplyType;		//依據合申請單類別檢視對應畫面
	
	public Model() {
	}
	public Model(P request) {
		this.request = request;
	}	
	public Model(P request, R response, LotteryMessage message) {
		this(null, request, response, message,null);
	}
	/**
	 * SessionContext建構子
	 * @param actionId
	 * @param request
	 * @param response
	 * @param message
	 */
	public Model(String actionId, P request, R response, LotteryMessage message,PageInfo pageInfo) {
		super();
		this.actionId = actionId;
		this.request = request;
		this.response = response;
		this.message = message;
		this.pageInfo = pageInfo;
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
	 * @return the request
	 */
	public P getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(P request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public R getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(R response) {
		this.response = response;
	}

	/**
	 * @return the message
	 */
	public LotteryMessage getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(LotteryMessage message) {
		this.message = message;
	}
	
	public boolean isSuccess() {
		return (this.message != null && this.message.isSuccess());
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String getLogMessageKey() {
		return logMessageKey;
	}
	public void setLogMessageKey(String logMessageKey) {
		this.logMessageKey = logMessageKey;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	/**
	 * @return the redirectApplyType
	 */
	public String getRedirectApplyType() {
		return redirectApplyType;
	}
	/**
	 * @param redirectApplyType the redirectApplyType to set
	 */
	public void setRedirectApplyType(String redirectApplyType) {
		this.redirectApplyType = redirectApplyType;
	}

}
