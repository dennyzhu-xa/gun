package com.gun.common.pojo;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/**
 * Purpose: Mail所需TemplateMailMessageDTO
 * @author MooreChen
 * @since  JDK 1.5
 * @date   2014/9/24
 * @MaintenancePersonnel MooreChen
 */
public class TemplateMailMessageDTO extends SimpleMailMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4967132456321147173L;
	
	//-------------------------待辦移轉Mail格式 Start -------------------------
	public final static String TABLE_BLOCK		= "<table border=" +"\"" + 1 +"\""+ ">";
	public final static String HEAD_HEIGHT		= "<tr align=" + "\"center\"" +" "+ "bgcolor=\"#"+99+"CCFF\">";
	public final static String HEAD_TD_START	= "<td><b>";
	public final static String HEAD_TD_END		= "</b></td>";
	
	public final static String BODY_HEIGHT 	= "<tr>";
	public final static String BODY_TD_START	= "<td>";
	public final static String BODY_TD_END		= "</td>";
	
	public final static String TR_END			= "</tr>";
	public final static String TABLE_END		= "</table>";
	//-------------------------待辦移轉Mail格式 End -------------------------
	
	
	public static enum ATTRIBUTE {
		ACT_TASK_NAME("actTaskName"),
		DELAY_DAYS("delayDays"),
		CNTR_ID("cntrId"),
		TASKASNEE("taskAsnee"),
		EMP_NAME("empName"),
		REVIEW_NO("reviewNo"),
		EXT("ext"),
		CNTR_DSPR_EMP_NO("cntrDsprEmpNo"),
		CNTR_AUTO_EXTD_CMPL_FLG("cntrAutoExtdCmplFlg"),
		RECV_NAME("recvName"),
		RECV_EMAIL_ADDR("recvEmailAddr"),
		CNTR_NAME("cntrName"),
		CNTR_SMRY("cntrSmry"),
		CREATE_DATE("createDate"),
		PM_EMP_DATA("pmEmpData"),
		EBU_EMP_DATA("ebuEmpData"),
		MGN_EMP_DATA("mgnEmpData"),
		ALERT_DATE("alertDate"),
		ECONTRACT_URL("eContractUrl"),
		CMMN("cmmn"),
		CDESC("cdesc"),
		KPI_RULE_DAYS("kpiRuleDays"),
		ACCESS_REVIEW_NO("accessReviewNo"),//調閱編號
		TRANSFER_TYPE("transferType"),
		LIST_TABLE("listTable"),
		RVW_NO("rvwNo"),//申請單編號		
		;

		private String code;
		ATTRIBUTE(String paramTypeName) {
			code = paramTypeName;
		}
		public String getCode() {
			return code;
		}	
	};
	
	/**
	 * Constructor:
	 */
	public TemplateMailMessageDTO() {
		
	}

	/**
	 * Find a template of a subject in following sequence :<br>
	 * 1. Find in DB by applying 'subjectTemplate' as a key.
	 * 2. Find in classpath by applying 'subjectTemplate' as a classpath name.
	 */
	private String subjectTemplate;
	
	/**
	 * replace ${xxx} in subjectTemplate by varialbes
	 */
	private Map<String, Object> subjectVariables;
	
	/**
	 * Find a template of text(mail content) in following sequence :<br>
	 * 1. Find in DB by applying 'subjectTemplate' as a key.
	 * 2. Find in classpath by applying 'subjectTemplate' as a classpath name.
	 */
	private String textTemplate;
	
	/**
	 * replace ${xxx} in textTemplate by varialbes
	 */
	private Map<String, Object> textVariables;
	
	/**
	 * Character Set
	 */
	private String charset = "UTF-8";
	
	/**
	 * Attachments : full path filename
	 */
	private String[] attachments;

	/**
	 * @return the subjectTemplate
	 */
	public String getSubjectTemplate() {
		return subjectTemplate;
	}

	/**
	 * @param subjectTemplate the subjectTemplate to set
	 */
	public void setSubjectTemplate(String subjectTemplate) {
		this.subjectTemplate = subjectTemplate;
	}

	/**
	 * @return the subjectVariables
	 */
	public Map<String, Object> getSubjectVariables() {
		return subjectVariables;
	}

	/**
	 * @param subjectVariables the subjectVariables to set
	 */
	public void setSubjectVariables(Map<String, Object> subjectVariables) {
		this.subjectVariables = subjectVariables;
	}

	/**
	 * @return the textTemplate
	 */
	public String getTextTemplate() {
		return textTemplate;
	}

	/**
	 * @param textTemplate the textTemplate to set
	 */
	public void setTextTemplate(String textTemplate) {
		this.textTemplate = textTemplate;
	}

	/**
	 * @return the textVariables
	 */
	public Map<String, Object> getTextVariables() {
		return textVariables;
	}

	/**
	 * @param textVariables the textVariables to set
	 */
	public void setTextVariables(Map<String, Object> textVariables) {
		this.textVariables = textVariables;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @return the attachments
	 */
	public String[] getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}
	
}
