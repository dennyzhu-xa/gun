package com.gun.common.utils;

import com.gun.common.system.config.WfSystemConfigManager;

/**
 * Purpose: 定義一般常數
 * @author felixli
 * @since  JDK 1.7
 * @date   2016/6/01
 * @MaintenancePersonnel felixli
 */
public interface LotteryConstants {

    public static final String PAGE_PARAM_ACTION_ID     = "actionId";
    public static final String PAGE_PARAM_MODEL       = "model";
    public final static String SESS_ATTR_COMMON       = "common";
    public final static String SESS_ATTR_USER_SESSION_CTX   = "userSessionContext";
    public final static String DOWN_LOAD            = "downLoad";
    public final static String RAND             = "rand";

    
    public final static String LOGIN              = "login";
    public final static String EFORM_LOGIN        = "eformlogin";
    public final static String MAIL_LOGIN         = "maillogin";
    public final static String LOGOUT             = "logout";
    public final static String JSP_STAGE          = "stage/";
    public final static String JSP_SYS          = "SYS/";
    public final static String AP_NAME            = "/gun-web/";
    public final static String INIT               = "init";
    public final static String LONGITUDE          = "longitude";
    public final static String LATITUDE           = "latitude";
    public final static String ADDRESS            = "address";
    public final static String DEALER_CATEGORY_FIX      = "fixed";
    public final static String DEALER_CATEGORY_UNFIX    = "unfix";
    
    
    public final static String JSP_LCE          = "LCE/";
    public final static String JSP_LRS          = "LRS/";
    public final static String JSP_LTR          = "LTR/";
    
    public static final String JRXML_PATH				= "/com/cyber/lottery/common/report/jrxml/";
    
    
    public static enum ACTION {
      INIT("init"),
      QUERY("query"),
      ADD("add"),
      INSERT("insert"),
      DELETE("delete"),
      EDIT("edit"),
      MODIFY("Modify"),
      ;
      private String value;
      ACTION(String value) {
        this.value = value;
      }
      public String getValue() {
        return this.value;
      }
    }
    
    //********************************代碼區塊 Srart
    //基本常數區
    public final static String YES              = "Y";
    public final static String NO               = "N";
    public final static Character Character_YES       = 'Y';
    public final static Character Character_NO        = 'N';
    public final static Integer ZERO            = 0;
    public final static Integer ONE             = 1;
    public final static Integer TWO             = 2;
    public final static int FIVE            = 5;
    public static final String STRING_ZERO          ="0";
    public static final String STRING_ONE         ="1";
    public static final String STRING_TWO         ="2";
    public static final String STRING_THREE       ="3";
    public static final String STRING_NINE        ="9";
    public static final String PARAM_STRING_C     ="C"; //存取行為 A:新增
    public final static String RFA              = "RFA";
    public final static String STRING_EMPTY             = "";
    public final static String STRING_S             = "S";
    public final static String STRING_R             = "R";
    public final static String STRING_PRE_TOPUP     = "Pre-Topup";
    public final static String STRING_SUFF_TOPUP    = "Suff-Topup";
    //符號
    public static final String MARK_PERCENT           = "%";//百分號
    public static final String MARK_SEPARATE        = "/";//分隔符
    public static final String MARK_COLON         = ":";//冒號
    public static final String MARK_SLASH                     = "\\";//雙斜线
    public final static String MARK_DECIMAL		  = "######0.00";
    public final static String MARK_MB			  = " MB";
    public final static String MARK_UNDERLINE     = "_";//下劃線
    public final static String MARK_BLANK         = " ";//空格
    public final static String MARK_DASH          = "-";//破折號
    public final static String MARK_POINT         = ".";//點
    public final static String MARK_COMMA         = ",";//逗號
    public final static String MARK_SEMICOLON       = "'";
    public static final String MARK_CARET         = "~";
    public final static String MARK_LEFT_BRACKET          = "(";
    public final static String MARK_RIGHT_BRACKET         = ")";
    public final static String MARK_VERTICAL          = "|";
    public final static String MARK         = "?";
    public final static String MARK_AND         = "&";
    public final static String MARK_AT          = "@";
    public final static String MARK_TAB_Wrap          = "\r\n";
    public final static String MARK_Wrap          = "\n";
    public final static String MARK_EQUAL_SIGN          = "=";
    
    //Log參數代碼
    public final static String LOG_ACT_TYPE_LOGIN       = "I";
    public final static String LOG_ACT_TYPE_LOGOUT        = "O";
    public final static String LOG_ACT_RESULT_SUCCESS       = "S";
    public final static String LOG_ACT_RESULT_FAILURE       = "F";
    //framework message resource bundle
    public static final String FWK_APPLICATION_RESOURCE_BUNDLE_DEFAULT                  = "com.cyber.lottery.message.lottery_messages_en";
    public static final String FWK_APPLICATION_RESOURCE_BUNDLE                          = "com.cyber.lottery.message.lottery_messages";
    
    
    //網域帳號認證
    public final static String SSO_LOGIN          = "ssologin";
    
    public final static String TREE_CHILDREN          = "children";

    //存檔動作判斷
    public final static String Modify           ="Modify";
    //存檔動作判斷
    public final static String Add              ="Add";
    //新增附件(取得公司別)Menu
    public final static String CMP_TYPE         = "CMP_TYPE";
    //合約形態
    public final static String REC_PER_PAGE             = "REC_PER_PAGE";
    //CMS_BATCH_LOG常數
    /**程式版本*/
    public final static String BATCH_VERSION            = "1";
    /**訊息等級:成功*/
    public final static String BATCH_MESSAGE_LEVEL_SUCCESS      = "S";
    /**訊息等級:失敗*/
    public final static String BATCH_MESSAGE_LEVEL_FAILURE      = "F";
    public final static String TRUE_VALUE               = "true";
    public final static String FALSE_VALUE              = "fasle";
    public final static String COL_PRVG_VIEW_CNTR_TERM      = "VIEW_CNTR_TERM";
    public final static String COL_PRVG_WRRN_COL        = "WRRN_COL";
    public final static String COL_PRVG_EDIT_CNTR_FLNG      = "EDIT_CNTR_FLNG";
    public static final String FILE_TXT_MSEXCEL         = "xls";
    public static final String FILE_TXT_MSEXCEL_X         = "xlsx";
    
    //用户权限Id
    public static final String USER_ROLE_ID     = "roleId";
    public static final String ROOT     = "root";
    
    public static final String LOTTERY_CODED_FORMAT_ISO_8859_1      = "ISO-8859-1";
    public static final String LOTTERY_CODED_FORMAT_UTF_8           = "UTF-8";
    public final static String MAIL_VALIDATION_CODE                         = "mailValidationCode";

    //BPID_CODE
    public static final String LOTTERY_CATEGORY                     = "LOTTERY_CATEGORY";
    public static final String LOTTERY_SUB_CATEGORY                 = "LOTTERY_SUB_CATEGORY";
    public static final String LOTTERY_WINNIN_NAME                  = "LOTTERY_WINNIN_NAME";
        
    /**
     * JSP、controller.do區塊
     */
    public final static String  DOT_DO                    = ".do";
    
    //********************************頁面名稱 Start
    public final static String INDEX               = "main";
    public final static String FIRST_PAGE               = "firstPage";//首頁
    public final static String LOTTERY_LOGIN            = "lottery_login";//登入頁面
    public final static String LOTTERY_USER_INFO        = "UserInfo";//用户查询頁面
    
    public final static String LOTTERY_LIST_INPUT_WINNIN_NO    = "InputWinninNoList";//輸入中獎號碼期數頁面
    public final static String LOTTERY_EDIT_INPUT_WINNIN_NO    = "InputWinninNoEdit";//輸入中獎號碼頁面
    public final static String LOTTERY_RESOURCE_LIST    = "ResourceList";//功能清单頁面
    public final static String LOTTERY_ROLE_INFO        = "RoleInfo";//功能清单頁面
    public final static String LOTTERY_LIST_CUSTOMER_INFORMATION    = "CustomerInformationList";//用戶信息頁面
    public final static String LOTTERY_LIST_CUSTOMER_WINNING    	= "CustomerWinningList";//用戶中奖信息頁面
    
    public final static String LOTTERY_LIST_INPUT_STOCK 	   		  = "InputStockList";//输入
    public final static String LOTTERY_DEALER_MAINTENANCE 	          = "DealerMaintenance";//经销商维护规则初始化页面
    public final static String LOTTERY_DEALER_AMOUNT_SETTING 	      = "DealerAmountSetting";//经销商维护规则初始化页面
    public final static String LOTTERY_LIST_CHECK_REMAINING_TICKET 	  = "CheckRemainingTicketList";
    public final static String LOTTERY_LIST_QUESTIONS_AND_ANSWERS 	  = "QuestionsAndAnswersList";
    public final static String APK_MANAGEMENT 	   		  			  = "ApkManagement";
    public final static String STOCK_BALANCE_REPORT 	   		  	  = "StockBalanceReport";
    public final static String SALE_TICKET_LIST_REPORT                = "SaleTicketListReport";
    public final static String SOLD_REGION_REPORT    	   		  	  = "SoldRegionReport";
    public final static String LOTTERY_LIST_USER_FEEDBACK 	  		  = "UserFeedbackList";
    public final static String LOTTERY_LIST_ROUND_MANAGEMENT 	  	  = "RoundManagement";
    public final static String MERCHANT_SETTLEMENT_REPORT             = "MerchantSettlementReport";
    public final static String LOTTERY_LIST_ADVERTISEMENT 	  	      = "AdvertisementManagement";
    public final static String SHOW_ANSWER 	  	      				  = "ShowAnswer";
    public final static String SHOW_QUESTION 	  	      			  = "ShowQuestion";
    public final static String LOTTERY_PURCHASE_RECORD 	  	      	  = "PurchaseRecord";
    public final static String MERCHANT_RECHARGE_RECORD 	  	      = "MerchantRechargeRecord";
    public final static String USER_RECHARGE_RECORD 	  	      	  = "UserRechargeRecord";
    public final static String INIT_BATCH_APPROVAL_MANAGEMENT_LIST 	  = "InitBatchApprovalManagementList";
    
    public final static String BASE_PARAMETER_ITEM_LIST             = "BaseParameterItemList";
    public final static String BASE_PARAMETER_ITEM_EDIT             = "BaseParameterItemEdit";
    
    public final static String APP_USER_LIST                        = "APPUserList";
    
    //********************************頁面名稱 End
    
  //********************************Action Start
    public final static String FIRST_PAGE_INIT                        = "firstPageInit";//首頁-初始化.do
    public final static String GET_AUTHORITY                          = "getAuthority";
    public final static String GET_AUTHORIZATION_LIST                 = "getAuthorizationList";
    public final static String GET_AUTHORITY_PAGINATION               = "getAuthorityPagination";
    public final static String SAVE_AUTHORITY                         = "saveAuthority";
    public final static String DELETE_AUTHORITY                       = "deleteAuthority";
    public final static String QUERY_USER_INFO                        = "queryUserInfo";
    public final static String QUERY_USER_INIT                        = "initUserInfo";
    public final static String CHECK_USER_INFO                        = "checkUserInfo";
    public final static String GET_ROLE_NAME                          = "getRoleName";
    public final static String SAVE_SYS_USER                          = "saveSysUser";
    public final static String DELETE_SYS_USER                        = "deleteSysUser";
    public final static String INIT_ROLE                              = "initRole";
    public final static String SAVE_ROLE_AUTHORITY                    = "saveRoleAuthority";
    public final static String SAVE_ROLE_DEF                          = "saveRoleDef";
    public final static String DELETE_ROLE_DEF                        = "deleteRoleDef";
    public final static String CHECK_PASSWORD                         = "checkPassword";
    public final static String RESET_PASSWORD                         = "resetPassword";
    public final static String FORGOT_PASSWORD                         = "forgotPassword";
    public final static String VALIDATION_CODE                         = "validationCode";
    public final static String QUERY_LOTTERY_RULE                      = "queryLotteryRule";
    public final static String SAVE_LOTTERY_RULE                       = "saveLotteryRule";
    public final static String DELETE_LOTTERY_RULE                     = "deleteLotteryRule";
    public final static String IS_USED_RULE                            = "isUsedRule";
    public final static String GET_SALE_PRICE                            = "getSalePrice";
    public final static String IS_APPROVALLED                          = "isApprovalled";
    public final static String QUERY_DATE                              = "queryDate";
    public final static String DELETE_LOTTERY_NUMBER                   = "deletelotterynumber";
    public final static String DELETE_ALL_LOTTERY_NUMBER               = "deleteAlllotterynumber";
    public final static String APPROVAL_LOTTERY_RULE                   = "approvalLotteryRule";
    public final static String SHOW_CATEGORY_IMAGE                     = "showCategoryImage";
    public final static String HAS_IMG                     			   = "hasImg";
    public final static String GET_LOTTERY_CATEGORY                    = "getLotteryCategory";
    public final static String QUERY_LOTTERY_NUMBER                    = "queryLotteryNumber";
    public final static String QUERY_TRANSACTION_RECORD                = "Android/queryTransactionRecord";
    public final static String GET_ALL_NEARBY_STORES                   = "getAllNearbyStores";
    public final static String CHECK_NEARBY_STORES                     = "checkNearbyStores";
    public final static String SAVE_NEARBY_STORES                      = "saveNearbyStores";
    public final static String RESET_MERCHANT_PASSWORD_OR_AMOUNT       = "resetMerchantPassWordOrAmount";
    public final static String DELETE_NEARBY_STORES                    = "deleteNearbyStores";
    public final static String GET_GOOG_LELATLNG 					   = "getGoogleLatLng";
    public final static String GET_NEARBY_STORES                   	   = "Android/getNearbyStores";
    public final static String INIT_RESOURCE                   	       = "initResource";
    public final static String QUERY_PARENT_FUNCTION_LIST              = "queryParentFunction";
    public final static String QUERY_SUB_CATEGORY_LIST                 = "querySubCategory";
    public final static String QUERY_WINNIN_NAME_LIST                  = "queryWinninNameList";
    public final static String QUERY_BY_WINNIN_NAME                    = "queryByWinninName";
    public final static String QUERY_LOTTERY_CATEGORY                  = "queryLotteryCategory";
    public final static String SAVE_FUNCTION                           = "saveFunction";
    public final static String DELETE_FUNCTION                         = "deleteFunction";
    public final static String QUERY_USER_ROLE                         = "queryUserRole";
    public final static String DEALER_MAINTENANCE_INIT                 = "initDealerMaintenance";
    public final static String DEALER_MAINTENANCE_QUERY                = "queryDealerMaintenance";
    public final static String SETTING_ACCOUNT_STATUS                  = "settingAccountStatus";
    public final static String DEALER_AMOUNT_SETTING_INIT              = "initDealerAmountSetting";
    public final static String DEALER_AMOUNT_SETTING_QUERY             = "queryDealerAmountSetting";
    public final static String SETTING_AMOUNT             			   = "settingAmount";
    public final static String REFILL             			   		   = "refill";
    
    
    public final static String INIT_INPUT_WINNIN_NO                   = "initInputWinninNo";
    public final static String QUERY_PERIOD    	                      = "queryPeriod";
    public final static String QUERY_INPUT_WINNIN_NO   	              = "queryInputWinninNo";
    public final static String SAVE_INPUT_WINNIN_NO                   = "saveInputWinninNo";
    public final static String DELETE_INPUT_WINNIN_NO                 = "deleteInputWinninNo";
    public final static String SAVE_WINNIN_POSTER                     = "saveWinninPoster";
    public final static String SHOW_WINNIN_POSTER                     = "showWinninPoster";
    public final static String SHOW_WINNIN_POSTER_ANDROID             = "showWinninPosterAndroid";
    public final static String CONFIRM_INPUT_WINNIN_NO                = "confirmInputWinninNo";
    public final static String QUERY_INPUT_WINNIN_NO_API              = "queryInputWinninNoApi";
    public final static String QUERY_INPUT_WINNIN_NO_API_NEW          = "queryInputWinninNoApiNew";
    public final static String QUERY_ALL_PERIOD         			  = "queryAllPeriod";
    public final static String QUERY_WIN_PRIZE_API			          = "queryWinPrizeApi";
    public final static String CHECK_WINNIN_NUMBER			          = "checkWinninNumber";
    public final static String EXPORT_CUSTOMER_INFORMATION			  = "exportCustomerInformation";
    
    public final static String INIT_CUSTOMER_INFORMATION              = "initCustomerInformation";
    public final static String QUERY_CUSTOMER_INFORMATION    		  = "queryCustomerInformation";
    
    public final static String INIT_CUSTOMER_WINNING              	  = "initCustomerWinning";
    public final static String QUERY_CUSTOMER_WINNING    		  	  = "queryCustomerWinning";
    //规则相关的actionId
    public final static String INIT_INPUT_STOCK                       = "initInputStock";
    public final static String INIT_CHECK_REMAINING_TICKET            = "initCheckRemainingTicket";
    public final static String GET_CURRENT_ROUND                      = "getCurrentRound";
    public final static String GET_ROUND_BY_PERIOD                    = "getRoundByPeriod";
    public final static String INIT_BATCH_APPROVAL_MANAGEMENT         = "initBatchApprovalManagement";
    public final static String BATCH_APPROVAL_EMERGENCY               = "batchApprovalEmergency";
    public final static String GET_DEALER_ACCOUNT_TOPUP               = "getDealerAccount";
    
    
    //Q&A相关的actionId
    public final static String QUERY_QUESTIONS_AND_ANSWERS             = "queryQuestionsAndAnswers";
    public final static String ANDROID_QUERY_QUESTIONS_AND_ANSWERS     = "Android/queryQuestionsAndAnswers";
    public final static String SAVE_QUESTIONS_AND_ANSWERS              = "saveQuestionsAndAnswers";
    public final static String DELETE_QUESTIONS_AND_ANSWERS            = "deleteQuestionsAndAnswers";
    public final static String GET_HELP_CATEGORY                       = "getHelpCategory";
    public final static String INIT_QUESTIONS_AND_ANSWERS              = "initQ&A";
    public final static String ANDROID_SHOW_ANSWER                     = "Android/showAnswer";
    public final static String ANDROID_QUERY_COLUMN_AND_CONTENT        = "Android/queryColumnAndContent";
    
    //ApkManagement相关的antionId
    public final static String INIT_APK_MANAGEMENT					   = "initApk";
    public final static String SAVE_APK					               = "saveApk";
    public final static String QUERY_APK                      		   = "queryApk";
    public final static String DELETE_APK                      		   = "deleteApk";
    public final static String CHECK_APK							   = "checkApk";
    public final static String DOWNLOAD								   = "download";
    public final static String APK_DOWNLOAD							   = "apkDownload";
    public static final String DOWNLOAD_URL							   = "downlownURL";
	public static final String VERSION_NAME							   = "versionName";
	public static final String VERSION_CODE							   = "versionCode";
	public static final String DOWNLOAD_TYPE						   = "application/x-download";
	public static final String SAVE_PATH						   	   = "apk";
	public static final String CATEGORY_IMAGE_SAVE_PATH				   = "categoryImage";
	
	//Lottery Report actionId
    public final static String INIT_STOCK_BALANCE            = "initStockBalance";
    public final static String QUERY_STOCK_BALANCE           = "queryStockBalance";
    public final static String EXPORT_STOCK_BALANCE          = "exportStockBalance";
    public final static String INIT_SOLD_REGION              = "initSoldRegion";
    public final static String GET_CITY_LIST                 = "getCityList";
    public final static String INIT_SALE_TICKET_LIST         = "initSaleTicketList";
    public final static String QUERY_SALE_TICKETLIST         = "querySaleTicketList";
    public final static String EXPORT_SALE_TICKET_LIST       = "exportSaleTicketList";
    public final static String QUERY_SOLD_REGION_LIST         = "querySoldRegionList";
    public final static String EXPORT_SOLD_REGION_LIST        = "exportSoldRegionList";
    public final static String INIT_MERCHANT_SETTLEMENT      = "initMerchantSettlement";
    public final static String QUERY_MERCHANT_SETTLEMENT     = "queryMerchantSettlement";
    public final static String EXPORT_MERCHANT_SETTLEMEN     = "exportMerchantSettlemen";
    
    //用户反馈 actionId
    public final static String INIT_USER_FEEDBACK            = "initUserFeedback"; 
    public final static String QUERY_USER_FEEDBACK           = "queryUserFeedback";
    public final static String CONFIRM_RESOLVED              = "confirmResolved";
    
    //期数维护 actionId
    public final static String INIT_ROUND_MANAGEMENT         = "initRoundManagement";
    public final static String SAVE_ROUND         			 = "saveRound";
    public final static String QUERY_ROUND         			 = "queryRound";
    public final static String DELETE_ROUND        			 = "deleteRound";
    public final static String IS_REPEAT_ROUND        		 = "isRepeatRound";
    public final static String APPROVAL_ROUND        		 = "approvalRound";
    
    //广告维护actionId
    public final static String INIT_ADVERTISEMENT            = "initAdvertisementManagement";
    public final static String QUERY_ADVERTINFO_PRIORITY     = "queryAdvertinfoPriority";
    public final static String SAVE_ADVERTISEMENT            = "saveAdvertisement";
    public final static String QUERY_ADVERTISEMENT           = "queryAdvertisement";
    public final static String SHOW_ADVERTISEMENT_IMAGE      = "showAdvertisementImage";
    public final static String SHOW_ADVERT_IMAGE      		 = "showAdvertImage";
    public final static String IS_ADD_ADVERTISEMENT          = "isAddAdvertisement";
    public final static String DELETE_ADVERTISEMENT          = "deleteAdvertisement";
    public final static String APPROVALA_DVERTISEMENT        = "approvalAdvertisement";
    
    //文件下载actionId
    public final static String FILE_DOWNLOAD        	     = "fileDownload";
    
    //交易记录actionId
    public final static String INIT_PURCHASE_RECORD        	 = "initPurchaseRecord";
    public final static String QUERY_PURCHASE_RECORD         = "queryPurchaseRecord";
    public final static String EXPORT_PURCHASE_RECORD        = "exportPurchaseRecord";
    public final static String INIT_MERCHANT_RECHARGE_RECORD = "initMerchantRechargeRecord";
    public final static String QUERY_MERCHANT_RECHARGE_RECORD= "queryMerchantRechargeRecord";
    public final static String EXPORT_MERCHANT_RECORD        = "exportMerchantRecord";
    public final static String EXPORT_INVOICE_RECORD		 = "exportInvoiceRecord";
    public final static String INIT_USER_RECHARGE_RECORD     = "initUserRechargeRecord";
    public final static String QUERY_USER_TOP_UP_RECORD      = "queryUserTopUpRecord";
    public final static String EXPORT_USER_TOP_UP_RECORD     = "exportUserTopUpRecord";
    
    public final static String INIT_BASE_PARAMETER_ITEM      = "initParameterSetting";
    public final static String QUERY_BASE_PARAMETER_ITEM     = "queryParameterSetting";
    public final static String INIT_EDIT_BASE_PARAMETER_ITEM = "initEditParameterSetting";
    public final static String EDIT_BASE_PARAMETER_ITEM      = "editParameterSetting";
    public final static String GET_PARAMETER                 = "getParameter";
    public final static String LOAD_CATEGORY_IMG             = "loadCategoryImg";
    public final static String CHECK_BASEPARAMETERITEMDEF    = "checkBaseParameterItemDef";
    public final static String DELETE_BASEPARAMETERITEMDEF   = "deleteBaseParameterItemDef";
    
    public final static String INIT_APP_USER                          = "initAPPUser";
    public final static String QUERY_APP_USER                         = "queryAPPUser";
    public final static String SETTING_APP_USER_ACCOUNT_STATUS        = "settingAppUserAccountStatus";
    
    //********************************Action End
		
    public final static int   XOR_CONST                  = 0X99; //密钥 
    public final static String  LOTTERY                  = "cyber_lottery"; //密钥 
    
    //********************APP端interface

	
    //********************service***************//
    
    
    //*********************Button Text*****************//
    public final static String BUTTON_ADD                        = "Add";
    public final static String BUTTON_EDIT                       = "Edit";
    public final static String BUTTON_DELETE                     = "Delete";
    public final static String BUTTON_VIEW                       = "View";
    public final static String BUTTON_IMPORT                     = "Import";
    public final static String BUTTON_QUERY                      = "Query";
    public final static String BUTTON_EXPORT                     = "Export";
    public final static String BUTTON_APPROVAL                   = "Approval";
    public final static String BUTTON_SAVE                   = "Save";
    
    
   //*********************Button IconCls*****************//
    public final static String BUTTON_ICON_NAME                   = "iconCls";
    public final static String BUTTON_ICON_USER                   = "icon-user";
    public final static String BUTTON_ICON_USER_ADD               = "icon-user-add";
    public final static String BUTTON_ICON_SAVE                   = "icon-save";
    public final static String BUTTON_ICON_CANCEL                 = "icon-cancel";
    public final static String BUTTON_ICON_STOP                   = "icon-stop";
    public final static String BUTTON_ICON_GRID                   = "icon-grid";
    public final static String BUTTON_ICON_ADD                    = "icon-add";
    public final static String BUTTON_ICON_DELETE                 = "icon-delete";
    public final static String BUTTON_ICON_EDIT                   = "icon-edit";
    public final static String BUTTON_ICONEDIT                    = "edit";
    public final static String BUTTON_ICON_IMAGE                  = "icon-image";
    public final static String BUTTON_ICON_ATTACH                 = "icon-attach";
    public final static String BUTTON_ICON_ACCEPT                 = "icon-accept";
    public final static String BUTTON_ICON_RESET                  = "icon-reset";
    public final static String BUTTON_ICON_SEARCH                 = "icon-search";
    public final static String BUTTON_ICON_VIEW                   = "icon-view";
    public final static String BUTTON_ICON_IDENTIFY               = "icon-identify";
    public final static String BUTTON_ICON_PICTURES               = "icon-pictures";
    public final static String BUTTON_ICON_DISARM                 = "icon-disarm";
    public final static String BUTTON_ICON_IMPORT                 = "icon-import";
    public final static String BUTTON_ICON_EXPORT                 = "icon-export";
    public final static String BUTTON_ICON_APPROVAL               = "icon-approval";
    
    //*********************审核状态*****************//
    public final static String APPROVAL_EDITING_ID                = "0";//编辑中
    public final static String APPROVAL_EDITING_NAME              = "UnApproved";//编辑中
    public final static String APPROVAL_AUDITED_ID                = "1";//已审核
    public final static String APPROVAL_AUDITED_NAME              = "Approved";//已审核
    
    //*********************账号状态*****************//
    public final static String ACCOUNT_NORMAL                     = "NORMAL";
    public final static String ACCOUNT_FROZEN                     = "FROZEN";
    
    //*********************彩票售卖状态*****************//
    public final static String MAY_PURCHASE  	              	  = "0";//可购买 
    public final static String ALREADY_BOUGHT               	  = "1";//已卖出
    
    
    
    //***************功能权限Session String  Start************************//
    public final static String ROLE_LIST                              = "roleList";
    public final static String USER_MANAGEMENT_ROLE                   = "userManagementRole";
    public final static String RESOURCE_MANAGEMENT_ROLE               = "resourceManagementRole";
    public final static String ROLE_MANAGEMENT_ROLE                   = "roleManagementRole";
    public final static String INPUT_WINNIN_NO_ROLE                   = "inputWinninNoRole";
    public final static String INPUT_STOCK_ROLE                       = "inputStockRole";
    public final static String CHECK_REMAINING_TICKET_ROLE            = "checkRemainingTicketRole";
    public final static String QUESTION_AND_ANSWER_ROLE               = "questionAndAnswerRole";
    public final static String CUSTOMER_INFORMATION_ROLE              = "customerInformationRole";
    public final static String DEALER_MAINTENANCE_ROLE                = "dealerMaintenanceRole";
    public final static String DEALER_AMOUNT_SETTING_ROLE             = "dealerAmountSettingRole";
    public final static String APK_MANAGEMENT_ROLE                    = "apkManagementRole";
    public final static String STOCK_BALANCE_ROLE                     = "stockBalanceRole";
    public final static String SOLD_REGION_ROLE                       = "soldRegionRole";
    public final static String SALE_TICKET_LIST_ROLE                  = "saleTicketListRole";
    public final static String USER_FEEDBACK_ROLE                     = "userFeedbackRole";
    public final static String ROUND_MANAGEMENT_ROLE                  = "roundManagementRole";
    public final static String MERCHANT_SETTLEMENT_ROLE               = "merchantSettlementRole";
    public final static String ADVERTISEMENT_ROLE                     = "advertisementRole";
    public final static String PURCHASE_RECORD_ROLE                   = "purchaseRecordRole";
    public final static String MERCHANT_RECHARGE_RECORD_ROLE          = "merchantRechargeRecordRole";
    public final static String USER_RECHARGE_RECORD_ROLE              = "userRechargeRecordRole";
    public final static String BASE_PARAMETER_ITEM_ROLE               = "baseParameterItemRole";
    public final static String APP_USER_ROLE                          = "appUserRole";
    public final static String CUSTOMER_WINNING_ROLE              	  = "customerWinningRole";
    
    
    
  //***************功能权限Session String  End************************//
    
  //*********************帮助类别*****************//
    public final static Object ONE_CATEGORY						  = "1";
    public final static Object TWO_CATEGORY						  = "2";
    public final static String HELP 							  = "help";//帮助
    public final static String CONTACT 							  = "contact";//帮助
    public final static String ABOUT 							  = "about";
    
    
  //*********************system.config Properties*****************//
    
    public final static String SYSTEM_CONFIG_MAIL                         = "MAIL";
    public final static String SYSTEM_CONFIG_MAIL_HOST_IS_SSL             = "hostIsSSL";
    public final static String SYSTEM_CONFIG_MAIL_HOST                    = "host";
    public final static String SYSTEM_CONFIG_MAIL_ADMINID                 = "adminId";
    public final static String SYSTEM_CONFIG_MAIL_ADMINPASSWORD           = "adminPassword";
    public final static String SYSTEM_CONFIG_MAIL_FROMMAIL                = "fromMail";
    public final static String SYSTEM_CONFIG_DB_CONNECTION                = "DB_CONNECTION";
    public final static String SYSTEM_CONFIG_DB_CONNECTION_URL            = "url";
    public final static String SYSTEM_CONFIG_DB_CONNECTION_USER           = "user";
    public final static String SYSTEM_CONFIG_DB_CONNECTION_PASSWORD       = "password";
    public static final String JSON_CONTENT_TYPE						  = "text/json;charset=utf-8";
    public static final String LOCAL_WEB_IP								  = WfSystemConfigManager.getProperty(LotteryConstants.APK, "ip");
    public static final String LOCAL_WEB_PORT							  = WfSystemConfigManager.getProperty(LotteryConstants.APK, "port");
    public static final String FILE										  = "FILE";
    public static final String DEFAULT_TYPE								  = "defaultType";
    public static final String APK										  = "APK";
    public static final String ADVERT									  = "ADVERT";
    public static final String POSTER									  = "POSTER";
    public static final String PNG									  	  = ".png";
    public static final String PATH										  = "path";
    public static final String DEAL_STATUS_ZERO							  = "Pending";
    public static final String DEAL_STATUS_ONE							  = "Resolved";
    public static final String LOCALHOST_URL							  = "http://"+LOCAL_WEB_IP+":"+LOCAL_WEB_PORT+"/gun-web/Android/download.do?download=";
    public static final String ADVER_IMAGE_URL							  = "http://"+LOCAL_WEB_IP+":"+LOCAL_WEB_PORT+"/gun-web/Android/Advert/adverIamge.do?imagePath=";
    public static final String POSTER_IMAGE_URL							  = "http://"+LOCAL_WEB_IP+":"+LOCAL_WEB_PORT+"/gun-web/Android/Lottery/posterIamge.do?imagePath=";
    public static final String ALL_STRING 								  = "All";
    
  //***************推送功能 String  Start************************//
    public final static String THEME_TOPUP_MESSAGE                        = "Topup Message";
    public final static String THEME_lOTTERY_WINNING_MESSAGE              = "Winning";
    public final static String BONUS						              = "Bonus: ";
    public final static String CONTENT_TOPUP_SUCCESS                      = "Topup Success!";
    public final static String CONTENT_MMK                     			  = " MMK";
    public final static String CONTENT_LAKHS_MMK                     			  = " Lakhs MMK";
    public final static String PUSH_TYPE_TOPUP                    	  	  = "Topup";
    public final static String PUSH_TYPE_WINNING                   	  	  = "WINNING";
    public final static String PUSH_TYPE_CHECK                   	  	  = "CHECK";
    public final static String PUSH_BY_QUERY                   	  	   	  = " By Query";
  //***************推送功能 String  End************************//
    
  //***************cb-bank  Start************************//
    public final static String SYSTEM_CONFIG_CB_BANK                      = "CB_BANK";
    public final static String SYSTEM_CONFIG_CB_BANK_COMMON_KEY           = "commonKey";
    public final static String SYSTEM_CONFIG_CB_BANK_MERCHANT_KEY         = "merchantKey";
    public final static String SYSTEM_CONFIG_CB_BANK_MERCHANT_ID          = "merchantid";
    public final static String SYSTEM_CONFIG_CB_BANK_CURRENCY             = "currency";
    public final static String SYSTEM_CONFIG_CB_BANK_ITEMNAME             = "itemname";
    public final static String SYSTEM_CONFIG_CB_BANK_ITEMDESC             = "itemdesc";
    public final static String SYSTEM_CONFIG_CB_BANK_HOST_DOMAIN_NAME     = "hostDomainName";
    public final static String SYSTEM_CONFIG_CB_BANK_PAY_LINK     		  = "payLink";
    
    public final static String DEFAULT_COMMON_KEY						  = "CBPAYMENT";
    public final static String DEFAULT_MERCHANT_KEY					 	  = "STARTMOEYKEY";
    public final static String DEFAULT_MERCHANT_ID				 	  	  = "STARTMOEY";
    public final static String DEFAULT_CURRENCY				 	  	  	  = "MMK";
    public final static String DEFAULT_ITEMNAME				 	  	  	  = "MOEYAN";
    public final static String DEFAULT_ITEMDESC				 	  	  	  = "MOEYAN";
    public final static String DEFAULT_PAY_LINK				 	  	  	  = "http://online.cbbank.com.mm/CBPayEnc/servletcontroller";
  //***************cb-bank  End************************//
    
  //***************kbz-bank  Start************************//
    public final static String SYSTEM_CONFIG_KBZ_BANK                     = "KBZ_BANK";
    public final static String SYSTEM_CONFIG_KBZ_BANK_MERCHANT_ID         = "merchantCode";
    public final static String SYSTEM_CONFIG_KBZ_BANK_CURRENCY            = "currency";
    public final static String SYSTEM_CONFIG_KBZ_BANK_ITEMNAME            = "itemname";
    public final static String SYSTEM_CONFIG_KBZ_BANK_ITEMDESC            = "itemdesc";
    public final static String SYSTEM_CONFIG_KBZ_BANK_PAY_LINK     		  = "payLink";
    
    public final static String DEFAULT_MERCHANT_CODE				 	  = "SMY0001";
    public final static String DEFAULT_CURRENCY_KBZ				 	  	  = "MMK";
    public final static String DEFAULT_ITEMNAME_KBZ				 	  	  = "KBZ BANK";
    public final static String DEFAULT_ITEMDESC_KBZ			 	  	  	  = "KBZ BANK";
    public final static String DEFAULT_PAY_LINK_KBZ				 	  	  = "https://directpaydemo.kbzbank.com/B001/rediffpur";
    //***************kbz-bank  End************************//
}




