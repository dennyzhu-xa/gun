package com.gun.common.pojo.form;

import com.gun.common.pojo.LotteryMessage;


/**
 * Purpose: 首頁Form
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/6/01
 * @MaintenancePersonnel Felixli
 */
public class FirstPageForm extends AbstractForm{

	private static final long serialVersionUID = -7295056666012558557L;

	public enum ATTRIBUTE {
		CASE_DATA_LIST("caseDataList"),			//處理中提案清單
		SYS_ANNU_LIST("sysAnnuList"),			//公告事項清單
		SIGN_HISTORY_LIST("signHistoryList"),	//待辦事項清單

		;
		private String code;

		ATTRIBUTE(String paramTypeName) {
			code = paramTypeName;
		}

		public String getCode() {
			return code;
		}
	}

	public static final String FORM_NAME 		= "firstPageForm";

	//初始化firstPage傳入message code
	private String messageCode;

	//初始化firstPage傳入message object
	private LotteryMessage cmsMessage;

//	//處理中提案清單
//	private List<CmsComplexColumnDTO> caseDataList;
//
//	//公告事項清單
//	private List<SysPrmrDef> sysAnnuList;
//
//	//待辦事項清單
//	private List<CmsComplexColumnDTO> signHistoryList;

	//案件流水號
	private String caseSeqno;

	//員工編號
	private String empNo;

	private String taskSeqNo;		//關卡流水號
 	private String signEmpNo;		//待簽核人員編號
 	private boolean windowCloseFlag;
 	private String rfaNo="";
 	private String assigneeData="";

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
	 * @return the cmsMessage
	 */
	public LotteryMessage getCmsMessage() {
		return cmsMessage;
	}

	/**
	 * @param cmsMessage the cmsMessage to set
	 */
	public void setCmsMessage(LotteryMessage cmsMessage) {
		this.cmsMessage = cmsMessage;
	}
//
//	/**
//	 * @return the caseDataList
//	 */
//	public List<CmsComplexColumnDTO> getCaseDataList() {
//		return caseDataList;
//	}
//
//	/**
//	 * @param caseDataList the caseDataList to set
//	 */
//	public void setCaseDataList(List<CmsComplexColumnDTO> caseDataList) {
//		this.caseDataList = caseDataList;
//	}
//
//	/**
//	 * @return the sysAnnuList
//	 */
//	public List<SysPrmrDef> getSysAnnuList() {
//		return sysAnnuList;
//	}
//
//	/**
//	 * @param sysAnnuList the sysAnnuList to set
//	 */
//	public void setSysAnnuList(List<SysPrmrDef> sysAnnuList) {
//		this.sysAnnuList = sysAnnuList;
//	}
//
//	/**
//	 * @return the signHistoryList
//	 */
//	public List<CmsComplexColumnDTO> getSignHistoryList() {
//		return signHistoryList;
//	}
//
//	/**
//	 * @param signHistoryList the signHistoryList to set
//	 */
//	public void setSignHistoryList(List<CmsComplexColumnDTO> signHistoryList) {
//		this.signHistoryList = signHistoryList;
//	}

	/**
	 * @return the caseSeqno
	 */
	public String getCaseSeqno() {
		return caseSeqno;
	}

	/**
	 * @param caseSeqno the caseSeqno to set
	 */
	public void setCaseSeqno(String caseSeqno) {
		this.caseSeqno = caseSeqno;
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
	 * @return the taskSeqNo
	 */
	public String getTaskSeqNo() {
		return taskSeqNo;
	}

	/**
	 * @param taskSeqNo the taskSeqNo to set
	 */
	public void setTaskSeqNo(String taskSeqNo) {
		this.taskSeqNo = taskSeqNo;
	}

	/**
	 * @return the signEmpNo
	 */
	public String getSignEmpNo() {
		return signEmpNo;
	}

	/**
	 * @param signEmpNo the signEmpNo to set
	 */
	public void setSignEmpNo(String signEmpNo) {
		this.signEmpNo = signEmpNo;
	}

	/**
	 * @return the windowCloseFlag
	 */
	public boolean isWindowCloseFlag() {
		return windowCloseFlag;
	}

	/**
	 * @param windowCloseFlag the windowCloseFlag to set
	 */
	public void setWindowCloseFlag(boolean windowCloseFlag) {
		this.windowCloseFlag = windowCloseFlag;
	}

	/**
	 * @return the rfaNo
	 */
	public String getRfaNo() {
		return rfaNo;
	}

	/**
	 * @param rfaNo the rfaNo to set
	 */
	public void setRfaNo(String rfaNo) {
		this.rfaNo = rfaNo;
	}

	/**
	 * @return the assigneeData
	 */
	public String getAssigneeData() {
		return assigneeData;
	}

	/**
	 * @param assigneeData the assigneeData to set
	 */
	public void setAssigneeData(String assigneeData) {
		this.assigneeData = assigneeData;
	}

}
