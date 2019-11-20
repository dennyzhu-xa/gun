/**
 * 
 */
package com.gun.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Purpose: FET_CMS_SDS_LOG.xlsx
 */
public class ActionLogInfo implements Serializable {
	private static final long serialVersionUID = 3011583049914807344L;

//		/**
//		 * 執行動作(CmsConstants.LOG_ACT_FLAG)
//		 */
//		private String actFlag="";
//		/**
//		 * 功能代號
//		 */
//		private String funcId="";
//		/**
//		 * 查詢/異動筆數
//		 */
//		private int dataCnt=0;
//		
//		/**
//		 * 存取類別(CmsConstants.LOG_ACC_METHOD_*)
//		 */
//		private String method="DB";
//				
//		/**
//		 * 是否存取個資(CmsConstants.LOG_PD_FLAG_*)
//		 */
//		private String pdFlag="0";
//		/**
//		 * DB Connection account
//		 */
//		private String apDbNo="";
//		/**
//		 * sql statement
//		 */
//		private String message="";
//		/**
//		 * DB Server IP
//		 */
//		private String dbServerIp="";
//		/**
//		 * DB Server Instance
//		 */
//		private String dbServerName="";
		
		
		
		private String actFlg;		//存取行為 A:新增 D:刪除 E:修改 Q:查詢 O:匯出下載 T:傳輸 R:報表 P:列印 S:送簽
		private String afterVal; 	//異動後值(僅 D:刪除 / E:修改)
		private String apDbNo;		//存取物件帳號 (AP連結DB所使用之帳號)
		private String beforeVal; 	//異動後值(僅 D:刪除 / E:修改)
		private String clientIp;	//來源IP、設備Hostname或終端機ID 
		private Integer dataCnt;	//資料筆數
		private String dbName;		//DB_Name if API=o / If DB=m
		private String dbServer;	//DB_Server(hostname或IP)
		private String empNo;		//UID使用者登入帳號、員工編號、識別碼
		private String funId;		//
		private String hostName;	//伺服器名稱(作業對象IP、伺服器系統名稱)
		private Integer logId;		//
		private String message;		//SQL Statement 內容 Method = 'DB' (必填) 
		private String messageKey;	//新增/刪除/修改/查詢Keyword值 存放where 條件
		private String method;		//存取Method:  API/DB Connection, API:API存取 / DB:DB Connect
		private String pdFlg;		//PD_Flag(是否存取個資) 1=yes / 0=no
		private Double processSec;	//
		private Date recordTime;	//日期時間 (yyyy/mm/dd hh:mm:ss)
		private String returnVal;	//成功執行之回覆內容 (建議應留存關鍵Keyword) 
		private String sessionId;	//AP SessionID
		private String statusCode;	//執行程式名稱/交易代號/交易名稱
		private String statusFlg;	//資料存取成功或失敗訊息(Success/Failure)  1.成功 / 2.失敗

		public ActionLogInfo(){
		}

		/**
		 * Purpose:存取行為 A:新增 D:刪除 E:修改 Q:查詢 O:匯出下載 T:傳輸 R:報表 P:列印 S:送簽
		 */
		public String getActFlg() {
			return actFlg;
		}

		/**
		 * Purpose:存取行為 A:新增 D:刪除 E:修改 Q:查詢 O:匯出下載 T:傳輸 R:報表 P:列印 S:送簽
		 */
		public void setActFlg(String actFlg) {
			this.actFlg = actFlg;
		}

		/**
		 * Purpose:異動後值(僅 D:刪除 / E:修改)
		 */
		public String getAfterVal() {
			return afterVal;
		}
		
		/**
		 * Purpose:異動後值(僅 D:刪除 / E:修改)
		 */
		public void setAfterVal(String afterVal) {
			this.afterVal = afterVal;
		}
		
		/**
		 * Purpose:存取物件帳號 (AP連結DB所使用之帳號)
		 */
		public String getApDbNo() {
			return apDbNo;
		}
		
		/**
		 * Purpose:存取物件帳號 (AP連結DB所使用之帳號)
		 */
		public void setApDbNo(String apDbNo) {
			this.apDbNo = apDbNo;
		}
		
		/**
		 * Purpose:異動後值(僅 D:刪除 / E:修改)
		 */
		public String getBeforeVal() {
			return beforeVal;
		}
		
		/**
		 * Purpose:異動後值(僅 D:刪除 / E:修改)
		 */
		public void setBeforeVal(String beforeVal) {
			this.beforeVal = beforeVal;
		}
		
		/**
		 * Purpose:來源IP、設備Hostname或終端機ID 
		 */
		public String getClientIp() {
			return clientIp;
		}
		
		/**
		 * Purpose:來源IP、設備Hostname或終端機ID 
		 */
		public void setClientIp(String clientIp) {
			this.clientIp = clientIp;
		}
		
		/**
		 * Purpose:資料筆數
		 */
		public Integer getDataCnt() {
			return dataCnt;
		}
		
		/**
		 * Purpose:資料筆數
		 */
		public void setDataCnt(Integer dataCnt) {
			this.dataCnt = dataCnt;
		}
		
		/**
		 * Purpose:DB_Name if API=o / If DB=m
		 */
		public String getDbName() {
			return dbName;
		}
		
		/**
		 * Purpose:DB_Name if API=o / If DB=m
		 */
		public void setDbName(String dbName) {
			this.dbName = dbName;
		}
		
		/**
		 * Purpose:DB_Server(hostname或IP)
		 */
		public String getDbServer() {
			return dbServer;
		}
		
		/**
		 * Purpose:DB_Server(hostname或IP)
		 */
		public void setDbServer(String dbServer) {
			this.dbServer = dbServer;
		}
		
		/**
		 * Purpose:UID使用者登入帳號、員工編號、識別碼
		 */
		public String getEmpNo() {
			return empNo;
		}
		
		/**
		 * Purpose:UID使用者登入帳號、員工編號、識別碼
		 */
		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}
		
		/**
		 * Purpose:功能代號
		 */
		public String getFunId() {
			return funId;
		}
		
		/**
		 * Purpose:功能代號
		 */
		public void setFunId(String funId) {
			this.funId = funId;
		}
		
		/**
		 * Purpose:伺服器名稱(作業對象IP、伺服器系統名稱)
		 */
		public String getHostName() {
			return hostName;
		}
		
		/**
		 * Purpose:伺服器名稱(作業對象IP、伺服器系統名稱)
		 */
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		
		/**
		 * Purpose:
		 */
		public Integer getLogId() {
			return logId;
		}
		
		/**
		 * Purpose:
		 */
		public void setLogId(Integer logId) {
			this.logId = logId;
		}
		
		/**
		 * Purpose:SQL Statement 內容 Method = 'DB' (必填) 
		 */
		public String getMessage() {
			return message;
		}
		
		/**
		 * Purpose:SQL Statement 內容 Method = 'DB' (必填) 
		 */
		public void setMessage(String message) {
			this.message = message;
		}
		
		/**
		 * Purpose:新增/刪除/修改/查詢Keyword值 存放where 條件
		 */
		public String getMessageKey() {
			return messageKey;
		}
		
		/**
		 * Purpose:新增/刪除/修改/查詢Keyword值 存放where 條件
		 */
		public void setMessageKey(String messageKey) {
			this.messageKey = messageKey;
		}
		
		/**
		 * Purpose:存取Method:  API/DB Connection, API:API存取 / DB:DB Connect
		 */
		public String getMethod() {
			return method;
		}
		
		/**
		 * Purpose:存取Method:  API/DB Connection, API:API存取 / DB:DB Connect
		 */
		public void setMethod(String method) {
			this.method = method;
		}
		
		/**
		 * Purpose:PD_Flag(是否存取個資) 1=yes / 0=no
		 */
		public String getPdFlg() {
			return pdFlg;
		}
		
		/**
		 * Purpose:PD_Flag(是否存取個資) 1=yes / 0=no
		 */
		public void setPdFlg(String pdFlg) {
			this.pdFlg = pdFlg;
		}
		
		/**
		 * Purpose:
		 */
		public Double getProcessSec() {
			return processSec;
		}
		
		/**
		 * Purpose:
		 */
		public void setProcessSec(Double processSec) {
			this.processSec = processSec;
		}
		
		/**
		 * Purpose:日期時間 (yyyy/mm/dd hh:mm:ss)
		 */
		public Date getRecordTime() {
			return recordTime;
		}
		
		/**
		 * Purpose:日期時間 (yyyy/mm/dd hh:mm:ss)
		 */
		public void setRecordTime(Date recordTime) {
			this.recordTime = recordTime;
		}
		
		/**
		 * Purpose:成功執行之回覆內容 (建議應留存關鍵Keyword)
		 */
		public String getReturnVal() {
			return returnVal;
		}
		
		/**
		 * Purpose:成功執行之回覆內容 (建議應留存關鍵Keyword)
		 */
		public void setReturnVal(String returnVal) {
			this.returnVal = returnVal;
		}
		
		/**
		 * Purpose:AP SessionID
		 */
		public String getSessionId() {
			return sessionId;
		}
		
		/**
		 * Purpose:AP SessionID
		 */
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		
		/**
		 * Purpose:執行程式名稱/交易代號/交易名稱
		 */
		public String getStatusCode() {
			return statusCode;
		}
		
		/**
		 * Purpose:執行程式名稱/交易代號/交易名稱
		 */
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}
		
		/**
		 * Purpose:資料存取成功或失敗訊息(Success/Failure)  1.成功 / 2.失敗
		 */
		public String getStatusFlg() {
			return statusFlg;
		}
		
		/**
		 * Purpose:資料存取成功或失敗訊息(Success/Failure)  1.成功 / 2.失敗
		 */
		public void setStatusFlg(String statusFlg) {
			this.statusFlg = statusFlg;
		}
		
}
