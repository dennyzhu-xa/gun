package com.gun.common.utils;

/**
 * Purpose: SQL語法庫
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/3/22
 * @MaintenancePersonnel Felixli
 */
public class SqlSyntaxFactory {

  /**
   * Constructor:
   */
  public SqlSyntaxFactory(){

  }
  
  //用戶登陸查詢
  public final static String GET_USER_BY_NAME =  "SELECT su.ID AS id,su.EMAIL AS email," +
                           " roleDef.ROLE_NAME AS roleName, " +
                           "CONVERT(varchar(100), su.LAST_LOGINTIME, 120) AS lastLogintime," +
                           "su.PASSWORD AS password,su.USER_NAME AS userName," +
                           "su.ROLE_ID AS roleId,su.TELEPHONE AS telephone,su.USER_ID as userId " +
                           "FROM SYS_USER_INFO su,ROLE_DEF roleDef WHERE 1=1 " +
                           "AND su.ROLE_ID = roleDef.ROLE_ID  "+
                           "AND (:userId IS NULL OR su.USER_ID=:userId)  ";
  
  //根據權限查詢用戶
  public final static String GET_USER_BY_ROLE_ID =  "SELECT su.ID AS id,su.USER_NAME AS userName " +
                                                    "FROM SYS_USER_INFO su WHERE 1=1 " +
                                                    "AND (:roleId IS NULL OR su.ROLE_ID=:roleId)  ";
  
  /**
   * 查詢左邊樹
   */
  public static final String QUERY_FUNCTION_TPYE      ="SELECT fun.FUNCTION_ID AS functionId,fun.FUNCTION_NAME AS functionName,fun.FUNCTION_URL AS functionUrl,fun.PARENT_FUNCTION_ID AS parentFunctionId,fun.BUTTONS AS buttons FROM dbo.FUNCTION_TYPE fun "+
                                                        "INNER JOIN dbo.ROLE_AUTHORITY ra ON fun.FUNCTION_ID=ra.FUNCTION_ID  "+
                                                         "AND (:roleId IS NULL OR ra.ROLE_ID=:roleId) ";
  
  /**
   * function List 
   */
  public static final String QUERY_FUNCTION_TPYE_LIST      ="SELECT fun.FUNCTION_ID AS functionId,fun.FUNCTION_NAME AS functionName,fun.FUNCTION_URL AS functionUrl,fun.PARENT_FUNCTION_ID AS parentFunctionId," + 
                                                             "fun.FUNCTION_DESCRIPTION AS functionDescription,fun.FUNCTION_ORDER AS functionOrder,"+
                                                             "fun.BUTTONS AS buttons,fun.FUNCTION_CODE AS functionCode "+
                                                             "FROM dbo.FUNCTION_TYPE fun WHERE 1=1 ";
  /**
   * 获取全部父function
   */
  public static final String QUERY_PARENT_FUNCTION_TPYE      ="SELECT fun.FUNCTION_ID AS value,fun.FUNCTION_NAME AS name FROM dbo.FUNCTION_TYPE fun where fun.FUNCTION_ID=fun.PARENT_FUNCTION_ID";
  //用戶查詢
  public final static String GET_USER_INFO =  "SELECT su.ID AS id,su.EMAIL AS email," +
                                              " roleDef.ROLE_NAME AS roleName, " +
                                              "CONVERT(varchar(100), su.LAST_LOGINTIME, 120) AS lastLogintime," +
                                              "su.PASSWORD AS password,su.USER_NAME AS userName," +
                                              "su.ROLE_ID AS roleId,su.TELEPHONE AS telephone,su.USER_ID as userId " +
                                              "FROM SYS_USER_INFO su,ROLE_DEF roleDef WHERE 1=1 " +
                                              "AND su.ROLE_ID = roleDef.ROLE_ID  ";
  
  //依據用戶權限獲得tree List
  public final static String QUERY_AUTHORITY_BY_ROLE = "select distinct a.SEQ as id,a.BUTTONS as buttons," +
                               "a.CHECKED as checked,a.EXPANDED as expanded,a.ICON_CLS as iconCls," +
                               "a.LEAF as leaf,a.MENU_CODE as menuCode,a.MENU_CONFIG as menuConfig," +
                               "a.MENU_NAME as menuName,a.PARENT_ID as parentId,a.SORT_ORDER as sortOrder," +
                               "a.URL as url " +
                               "from AUTHORITY a,ROLE_AUTHORITY ra " +
                               "where cast(a.SEQ as varchar(1000)) = ra.FUNCTION_ID " +
                               "and a.PARENT_ID is null and ra.ROLE_ID = ? ORDER BY a.SORT_ORDER";
  //依據用戶權限獲得tree List
  public final static String QUERY_AUTHORITY_BY_ROLE_AND_PARENTID = "select distinct a.SEQ as id,a.BUTTONS as buttons," +
                                      "a.CHECKED as checked,a.EXPANDED as expanded,a.ICON_CLS as iconCls," +
                                      "a.LEAF as leaf,a.MENU_CODE as menuCode,a.MENU_CONFIG as menuConfig," +
                                      "a.MENU_NAME as menuName,a.PARENT_ID as parentId,a.SORT_ORDER as sortOrder," +
                                      "a.URL as url " +
                                    " from AUTHORITY a,ROLE_AUTHORITY ra " +
                                    "where cast(a.SEQ as varchar(1000)) = ra.FUNCTION_ID " +
                                    "and a.PARENT_ID = ? and ra.ROLE_ID = ? ORDER BY a.sort_order";
  //用戶權限查詢
  public final static String QUERY_ROLE_AUTHORITY_BY_ROLE =  "SELECT ra.SEQ AS id,ra.FUNCTION_ID AS functionId," +
                                 "ra.ROLE_ID AS roleId " +
                                 " FROM ROLE_AUTHORITY ra WHERE ra.ROLE_ID= ?";
  //資源管理查詢
  public final static String QUERY_AUTHORITY =  "SELECT auth.SEQ AS id,auth.BUTTONS AS buttons,auth.CHECKED AS checked," +
                          "auth.EXPANDED AS expanded, auth.ICON_CLS AS iconCls,auth.LEAF AS leaf," +
                          "auth.MENU_CODE AS menuCode,auth.MENU_CONFIG AS menuConfig," +
                          "auth.MENU_NAME AS menuName,auth.PARENT_ID AS parentId," +
                          "auth.SORT_ORDER AS sortOrder,auth.URL AS url " +
                          "FROM authority auth ORDER BY auth.sort_order";
  //資源管理查詢
  public final static String QUERY_AUTHORITY_ALL =  "SELECT auth.SEQ AS id,auth.BUTTONS AS buttons,auth.CHECKED AS checked," +
                          "auth.EXPANDED AS expanded, auth.ICON_CLS AS iconCls,auth.LEAF AS leaf," +
                          "auth.MENU_CODE AS menuCode,auth.MENU_CONFIG AS menuConfig," +
                          "auth.MENU_NAME AS menuName,auth.PARENT_ID AS parentId," +
                          "auth.SORT_ORDER AS sortOrder,auth.URL AS url " +
                          "FROM authority auth where 1=1 ";
  
//資源管理查詢
  public final static String QUERY_ROLE_ALL =  "SELECT roleDef.ROLE_ID AS value,roleDef.ROLE_NAME AS name " +
                          "FROM ROLE_DEF roleDef WHERE 1=1 "+
                           "AND (:roleName IS NULL OR roleDef.ROLE_NAME=:roleName)";
  
//android用戶登陸查詢
  public final static String GET_ANDROID_USER_BY_ANDROIDUSER =  "select a.ID as id, a.USERNAME as username, "
      + "a.PASSWORD as password, a.PASSWORD_SALT as passwordSalt, a.FULL_NAME as fullName, a.NRC as nrc, "
      + "a.BIRTHDAY as birthday, a.MOBILE_NO as mobileNo, a.GENDER as gender, a.ADDRESS as address, a.DETAIL_ADDRESS as detailAddress, a.STATUS as status, "
      + "a.TOKEN as token, a.CREATE_BY_ID as createById, a.CREATED_BY_NAME as createdByName, "
      + "a.CREATED_DATE as createdDate, a.UPDATED_BY_ID updatedById, a.UPDATED_BY_NAME updatedByName, "
      + "a.UPDATED_DATE as updatedDate, a.LAST_LOGON_TIME as lastLogonTime, a.LAST_PURCHASE_TIME as lastPurchaseTime, "
      + "a.FACEBOOK_USER_ID as facebookUserId, a.FACEBOOK_ACCOUNT as facebookAccount, a.PICTURE_URI as pictureUri, a.BANK_CARD_NUMBER as bankCardNumber "
      + "from android_user a where 1=1 ";
  
//android用戶登陸查詢By Id
  public final static String GET_ANDROID_USER_BY_ID = "select a.ID as id, a.USERNAME as username, a.FULL_NAME as fullName, "
      + "a.NRC as nrc, a.BIRTHDAY as birthday, a.GENDER as gender, a.ADDRESS as address, a.MOBILE_NO as mobileNo, a.FACEBOOK_ACCOUNT as facebookAccount, "
      + "a.CREATED_DATE as createdDate, a.UPDATED_DATE as updatedDate, a.LAST_LOGON_TIME as lastLogonTime, a.DETAIL_ADDRESS as detailAddress, "
      + "a.LAST_PURCHASE_TIME as lastPurchaseTime, w.BALANCE as balance, a.BANK_CARD_NUMBER as bankCardNumber from android_user a "
      + "LEFT JOIN WALLET w ON a.ID = w.ANDROID_USER_ID where 1=1 AND a.ID=:id";
  
//android用戶钱包登陸查詢By androidUserId
  public final static String GET_WALLET_BY_ANDROID_USER_ID = "SELECT w.ID as id, w.CREATED_DATE as createdDate, w.UPDATED_DATE as updatedDate, w.ANDROID_USER_ID as androidUserId, w.BALANCE as balance, w.WALLET_TYPE as walletType " + 
  		"FROM dbo.WALLET w WHERE w.ANDROID_USER_ID = :androidUserId";
  
  //lottery规则查询
  public final static String GET_LOTTERY_RULE =  "SELECT lr.LOTTERY_CATEGORY_NAME AS lotteryCategoryName, CONVERT(varchar(100), lr.CREATED_DATE, 120) AS createdDate,lr.CREATE_BY_ID AS createById,lr.UPDATED_BY_ID AS updatedById,lr.UPDATED_BY_NAME AS updatedByName,lr.ID AS id,lr.LOTTERY_PERIODS AS lotteryPeriods,lr.LETTER_ROLE AS letterRole," +
                "lr.NUMBER_ROLE AS numberRole,CONVERT(varchar(100), p.ON_SALE, 120) AS onSale,lr.END_NUMBER AS endNumber,lr.CATEGORY_IMAGE_NAME AS categoryImageName,CONVERT(varchar(100), lr.UPDATED_DATE, 120) AS updatedDate," + 
                "CONVERT(varchar(100), p.OFF_SALE, 120) AS offSale,CONVERT(varchar(100), p.LOTTERY_TIMES, 120) AS lotteryTimes,lr.EFFECTIVE_DATE AS effectiveDate,lr.SUBCATEGORY_EFFECTIVE_DATE AS subcategoryEffectiveDate," + 
                "lr.STATUS AS status,lr.BEGIN_LETTER AS beginLetter,lr.END_LETTER AS endLetter,lr.BEGIN_NUMBER AS beginNumber,lr.LOTTERY_NUMBER AS lotteryNumber," +
                "lr.STATUS_NAME AS statusName,lr.SUB_CATEGORY_ID AS subCategoryId,lr.SUB_CATEGORY_NAME AS subCategoryName,lr.LOTTERY_CATEGORY AS lotteryCategory, lr.SALE_PRICE AS salePrice" +
                " FROM LOTTERY_RULE lr LEFT JOIN PERIOD p ON p.PERIOD = lr.LOTTERY_PERIODS  WHERE 1=1";
  
  //查询彩票类别
  public final static String QUERY_CATEGORY_ALL = "SELECT lr.ID AS id, lr.LOTTERY_CATEGORY AS category, lr.SUB_CATEGORY_NAME AS subCategory, (CASE WHEN lr.CATEGORY_IMAGE IS NULL THEN 0 ELSE 1 END) AS isSelling,\r\n" + 
  		"	ln.ticketCount AS ticketCount, lr.CATEGORY_IMAGE_NAME AS imagePath, CONVERT(varchar(100),lr.CREATED_DATE,120) AS createdDate FROM LOTTERY_RULE lr LEFT JOIN (SELECT RULE_ID, COUNT(*) AS ticketCount FROM dbo.LOTTERY_NUMBER WHERE PURCHASE_STATUS = '0' GROUP BY RULE_ID) ln ON lr.ID = ln.RULE_ID\r\n" + 
  		"WHERE lr.LOTTERY_PERIODS =:round AND lr.STATUS = '1' AND lr.LOTTERY_CATEGORY <> '' AND ln.ticketCount IS NOT NULL";
  
  //查询彩票类别2
  public final static String QUERY_CATEGORY_ALL2 = "SELECT tab.* FROM(SELECT lr.ID AS id, convert(int,lr.LOTTERY_CATEGORY) AS category, lr.LOTTERY_CATEGORY_NAME AS categoryName, "
  		+ "lr.SUB_CATEGORY_ID AS subCategory, lr.SUB_CATEGORY_NAME AS subCategoryName, CONVERT(varchar(100), lr.CREATED_DATE, 120) AS createdDate, "
  		+ "STUFF((SELECT ','+LOTTERY_NUMBER FROM dbo.LOTTERY_NUMBER WHERE RULE_ID = lr.ID AND PURCHASE_STATUS = '0' for xml path('')),1,1,'') AS lotteryNumber, lr.SALE_PRICE AS salePrice "
  		+ "FROM dbo.LOTTERY_RULE lr WHERE lr.LOTTERY_PERIODS =:round AND lr.STATUS = '1' AND lr.LOTTERY_CATEGORY <> '' ) tab WHERE tab.lotteryNumber IS NOT NULL";
  
  //查询未售卖的彩票号码
  public final static String GET_LOTTERY_NUMBER = "SELECT ln.ID AS id,lr.ID AS ruleId,ln.LOTTERY_PERIODS AS lotteryPeriods,ln.LOTTERY_NUMBER AS lotteryNumber, " +
              "CONVERT( VARCHAR( 100 ) ,p.ON_SALE ,120 ) AS onSale ,CONVERT( VARCHAR( 100 ) ,p.OFF_SALE ,120 ) AS offSale , ln.PURCHASE_STATUS AS purchaseStatus," +
              "lr.LETTER_ROLE AS letterRule,lr.NUMBER_ROLE AS numberRule,ln.PURCHASE_BY_NAME AS purchaseByName,ln.PURCHASE_DATE AS purchaseDate," +
              "CONVERT( VARCHAR( 100 ) ,p.LOTTERY_TIMES ,120 ) AS lotteryTimes,lr.LOTTERY_CATEGORY_NAME AS lotteryCategory,lr.SUB_CATEGORY_NAME AS subCategoryName " +
              "FROM LOTTERY_NUMBER ln LEFT JOIN LOTTERY_RULE lr ON ln.RULE_ID = lr.ID " +
              "LEFT JOIN PERIOD p ON p.PERIOD = lr.LOTTERY_PERIODS WHERE ln.PURCHASE_STATUS = '0'";
  
  //清空彩票号码表中的数据
  public final static String DELETE_BY_LOTTERYPERIODS= "DELETE FROM LOTTERY_NUMBER WHERE LOTTERY_NUMBER.LOTTERY_PERIODS <>:lotteryPeriods";
  
  public final static String GET_ALL_NEARBY_STORES = "SELECT ns.ID AS id,ns.LONGITUDE AS longitude,ns.DEALER_ACCOUNT AS dealerAccount,ns.DEALER_PASSWORD AS dealerPassword, " +
          "ns.LATITUDE AS latitude,ns.ADDRESS AS address,ns.DEALER_NAME AS dealerName, "+
          "ns.DEALER_CATEGORY AS dealerCategory,ns.PERSON_IN_CHARGE AS personInCharge,ns.RECHARGE_AMOUNT_CAP AS rechargeAmountCap, "+
          "ns.RECHARGE_AMOUNT_CAP-ns.PREPAID_AMOUNT AS rechargeBalance,ns.PREPAID_AMOUNT AS prepaidAmount,ns.CONTACT_PHONE_NUMBER AS contactPhoneNumber,ns.RESERVE_TELEPHONE AS reserveTelephone, "+
          "ns.CREATED_BY_NAME AS createdByName,ns.UPDATE_BY_NAME AS updateByName,"+
          "CONVERT(varchar(100), ns.CREATED_DATE, 120) AS createdDate,CONVERT(varchar(100), ns.UPDATE_DATE, 120) AS updateDate, "+
          "ns.STATUS AS status "+
          "FROM NEARBY_STORES ns "+
          "WHERE 1=1 "+
          "AND (:dealerName IS NULL OR ns.DEALER_NAME like :dealerName) "+
          "AND (:dealerAccount IS NULL OR ns.DEALER_ACCOUNT=:dealerAccount) "+
          "AND (:address IS NULL OR ns.ADDRESS like :address) "+
          "AND (:longitude IS NULL OR ns.LONGITUDE=:longitude) "+
          "AND (:latitude IS NULL OR ns.LATITUDE=:latitude) "+
          "AND (:dealerCategory IS NULL OR ns.DEALER_CATEGORY=:dealerCategory) "+
          "AND (:createdDateStart IS NULL OR (CONVERT(varchar(100), ns.CREATED_DATE, 120))>=:createdDateStart) "+
          "AND (:createdDateEnd IS NULL OR (CONVERT(varchar(100), ns.CREATED_DATE, 120))<=:createdDateEnd) "+
          "ORDER BY ns.CREATED_DATE DESC";
  //查询3个月之类的交易记录
  public final static String QUERY_TRANSACTION_RECORD = "SELECT ore.ORDER_NUMBER AS orderNumber, de.WINNIN_SUM AS winninSum,\r\n" + 
  		"	ore.ORDER_AMOUNT AS orderAmount, ore.ROUND as round, ore.CATEGORY as category, ore.CREATED_DATE AS createdDate, per.PERIOD_STATE AS periodState\r\n" + 
  		"FROM ORDER_RECORD ore LEFT JOIN( SELECT ORDER_NUMBER, SUM( WINNIN_SUM ) AS WINNIN_SUM FROM dbo.ORDER_RECORD_DETAILS " +
  		"GROUP BY ORDER_NUMBER) de ON ore.ORDER_NUMBER = de.ORDER_NUMBER LEFT JOIN dbo.PERIOD per ON ore.ROUND = per.PERIOD WHERE ore.CREATED_DATE BETWEEN DATEADD(MONTH,- 2," +
  		" getDate()) AND getDate() AND ore.ANDROID_USER_ID =:userId ORDER BY ore.CREATED_DATE DESC";
  
//期號查詢
  public final static String QUERY_PERIOD = "SELECT p.ID AS id,p.CREATE_BY_ID AS createById," +
									  		" p.PERIOD AS period," +
									  		" p.OFF_SALE AS offSale, " +
									  		" p.ON_SALE AS onSale, " +
									  		" p.PERIOD_STATE AS periodState, " +
									  		" p.IMAGE_URL AS imageUrl, p.UPDATED_DATE AS updatedDate " +
									        " FROM PERIOD p where p.APPROVE_STATE = '1' ";
//中獎號碼查詢
  public final static String QUERY_WINNIN_NO = "SELECT iwo.WINNIN_ID AS id,CONVERT(varchar(100), iwo.CREATED_DATE, 120) AS createdDateString," +
	  											" p.PERIOD_STATE AS periodState,iwo.EFFECTIVE_DATE AS effectiveDate," +
										  		" iwo.WINNIN_PERIOD AS winninPeriod," +
										  		" iwo.OUR_COMPANY_PRODUCT AS ourCompanyProduct," +
										  		" iwo.WINNIN_GRADE AS winninGrade, " +
										  		" iwo.WINNIN_NAME AS winninName, iwo.WINNIN_NAME_STRING AS winninNameString," +
										  		" iwo.WINNIN_NUMBER AS winninNumber, param.ITEM_DESC as winninDesc," +
										  		"iwo.WINNIN_SUM AS winninSum,(SELECT DISTINCT UPDATED_BY_ID FROM INPUT_WINNIN_NO WHERE INPUT_WINNIN_NO.UPDATED_DATE = (SELECT MAX(UPDATED_DATE) FROM INPUT_WINNIN_NO WHERE iwo.WINNIN_PERIOD = :winninPeriod)) updatedById " +
									            "FROM INPUT_WINNIN_NO iwo left join PERIOD p on p.period = iwo.WINNIN_PERIOD LEFT JOIN BASE_PARAMETER_ITEM_DEF param ON param.EFFECTIVE_DATE=iwo.EFFECTIVE_DATE AND param.ITEM_VALUE=iwo.WINNIN_NAME WHERE param.BPTD_CODE='LOTTERY_WINNIN_NAME' ";
  public final static String QUERY_WINNIN_NO_COUNT = "SELECT COUNT(*)" +
		  "FROM INPUT_WINNIN_NO iwo left join PERIOD p on p.period = iwo.WINNIN_PERIOD LEFT JOIN BASE_PARAMETER_ITEM_DEF param ON param.EFFECTIVE_DATE=iwo.EFFECTIVE_DATE AND param.ITEM_VALUE=iwo.WINNIN_NAME WHERE param.BPTD_CODE='LOTTERY_WINNIN_NAME' ";
//中獎號碼Id查詢
  public final static String QUERY_WINNIN_NO_NUMBER = "SELECT WINNIN_ID AS winninId  FROM INPUT_WINNIN_NO ";
  
  //lottery結束時間
  public final static String QUERY_LOTTERY_OFF_SALE =  "SELECT  p.OFF_SALE AS endDate " + 
		  					" FROM LOTTERY_RULE lr WHERE 1=1" ; 
  //查询Q&A/contact
  public final static String GET_QUESTIONS_AND_ANSWERS = "SELECT qa.ID AS id,qa.QUESTION AS question," +
			"qa.ANSWER AS answer,qa.CATEGORY_NAME AS categoryName,qa.CATEGORY AS category " + 
			"FROM QUESTIONS_AND_ANSWERS qa  where 1=1 ";

  public final static String GET_ALL_LOTTERY_NUMBER = "SELECT ln.RULE_ID AS ruleId ,ln.LOTTERY_PERIODS AS lotteryPeriods ," +
		  	"ln.LETTER_RULE AS letterRule ,ln.NUMBER_RULE AS numberRule ,ln.LOTTERY_NUMBER AS lotteryNumber ,"+
		  	"ln.PURCHASE_BY_NAME AS purchaseByName ,ln.PURCHASE_DATE AS purchaseDate ,ln.PURCHASE_STATUS AS purchaseStatus " +
		  	"FROM LOTTERY_NUMBER ln ";
  

//查詢記錄購買
  public final static String QUERY_ORDER_RECORD_DETAILS_BY_PERIOD = " SELECT tr.ID as id,tr.LOTTERY_ROUND AS lotteryRound,tr.LOTTERY_NUMBER AS lotteryNumber, au.USERNAME AS androidUserName FROM ORDER_RECORD_DETAILS tr LEFT JOIN ORDER_RECORD orr ON tr.ORDER_NUMBER = orr.ORDER_NUMBER LEFT JOIN ANDROID_USER au ON orr.ANDROID_USER_ID = au.ID where 1=1 ";

//客戶資料匯出
  public final static String CUSTOMER_INFORMATION = "SELECT au.ID AS id ,au.USERNAME as username," +
  		"au.FACEBOOK_ACCOUNT as facebookAccount,au.FULL_NAME AS fullName ," +
  		"au.LAST_LOGON_TIME AS lastLogonTime ,au.MOBILE_NO AS mobileNo, au.NRC AS nrc ," +
  		"au.BIRTHDAY AS birthday ,au.GENDER AS gender ,uptr.totalExpenditure AS totalExpenditure ," +
  		"au.LAST_PURCHASE_TIME AS lastPurchaseTime , au.ADDRESS+' '+au.DETAIL_ADDRESS AS address, " +
  		"tr.totalPrizeAmount AS totalPrizeAmount ,tr.pieces AS pieces ,tr.winningTimes AS winningTimes ,w.BALANCE AS balance ," +
  		"uptr.purchaseTimes AS purchaseTimes FROM dbo.ANDROID_USER au LEFT JOIN" +
  		"(select COUNT( ord.ID ) as pieces,COUNT(ord.WINNIN_SUM) as winningTimes, sum(ord.WINNIN_SUM) as totalPrizeAmount," +
  		"dor.ANDROID_USER_ID as userId from dbo.ORDER_RECORD dor LEFT JOIN dbo.ORDER_RECORD_DETAILS ord " +
  		"on ord.ORDER_NUMBER = dor.ORDER_NUMBER  GROUP by dor.ANDROID_USER_ID) tr ON " +
  		"au.ID = tr.userId LEFT JOIN dbo.WALLET w ON au.ID = w.ANDROID_USER_ID LEFT JOIN" +
  		"(select COUNT(ANDROID_USER_ID) as purchaseTimes, sum(dor.order_amount) as totalExpenditure," +
  		"dor.ANDROID_USER_ID as userId from dbo.ORDER_RECORD dor GROUP by dor.ANDROID_USER_ID) uptr " +
  		"ON au.ID = uptr.userId WHERE 1 = 1";
  
  public final static String QUERY_APP_USER_ALL = "SELECT au.ID AS id,au.USERNAME AS username,au.FULL_NAME AS fullName,au.NRC AS nrc,"+
                                                  "au.BIRTHDAY AS birthday,au.GENDER AS gender,"+
                                                  "(CASE WHEN au.DETAIL_ADDRESS IS NOT NULL AND au.DETAIL_ADDRESS<>''  THEN au.ADDRESS+'-'+au.DETAIL_ADDRESS ELSE au.ADDRESS END) AS address,"+
                                                  "au.STATUS AS status,au.UPDATED_BY_NAME AS updatedByName,au.UPDATED_DATE AS updatedDate,"+
                                                  "au.LAST_LOGON_TIME AS lastLogonTime,au.LAST_PURCHASE_TIME AS lastPurchaseTime,"+
                                                  "au.MOBILE_NO AS mobileNo,au.BANK_CARD_NUMBER AS bankCardNumber,au.CREATED_DATE AS createdDate "+
                                                  "FROM dbo.ANDROID_USER au "+
                                                  "WHERE 1=1 ";
  
//根据电话查询对应的用户信息
  public final static String GET_ANDROIDUSER_BY_PHNO = "SELECT au.ID AS id FROM ANDROID_USER au WHERE au.USERNAME = :phNo";
  
  //查询充值记录(商户端)
  public final static String QUERY_DEALER_TOP_UP_RECORD = "SELECT tur.ANDROID_USER_ID AS androidUserId,tur.TOP_UP_AMOUNT AS topUpAmount," +
		  "tur.CREATED_DATE AS createdDate,tur.DEALER_ID AS dealerId,au.USERNAME AS userName FROM TOP_UP_RECORD tur " +
		  "left join ANDROID_USER au ON tur.ANDROID_USER_ID = au.ID WHERE tur.DEALER_ID = :dealerId AND tur.CREATED_DATE " +
		  "BETWEEN DATEADD(MONTH ,- 3 ,:currentDate) AND :currentDate ORDER BY tur.CREATED_DATE DESC";
  
  //根据帐号查询经销商信息
  public final static String GET_DEALER_USER_BY_ACCOUNT = "SELECT ns.ID AS id,ns.DEALER_ACCOUNT AS dealerAccount,ns.DEALER_PASSWORD AS dealerPassword," +
		  "ns.DEALER_NAME AS dealerName,ns.DEALER_CATEGORY AS dealerCategory,ns.PERSON_IN_CHARGE AS personInCharge,ns.RECHARGE_AMOUNT_CAP AS rechargeAmountCap," +
		  "ns.PREPAID_AMOUNT AS prepaidAmount,ns.CONTACT_PHONE_NUMBER AS contactPhoneNumber,ns.RESERVE_TELEPHONE AS reserveTelephone," +
		  "ns.LONGITUDE AS longitude,ns.LATITUDE AS latitude,ns.ADDRESS AS address,ns.CREATED_DATE AS createdDate,ns.CREATED_BY_ID AS createdById," +
		  "ns.CREATED_BY_NAME AS createdByName,ns.UPDATE_DATE AS updateDate,ns.UPDATE_BY_ID AS updateById,ns.UPDATE_BY_NAME AS updateByName," +
		  "ns.TOKEN AS token,ns.FIRST_LANDING AS firstLanding,ns.ACCUMULATED_AMOUNT AS accumulatedAmount,ns.STATUS AS status " +
          "FROM NEARBY_STORES ns "+
          "WHERE ns.DEALER_ACCOUNT =:dealerAccount";
  
//根据帐号查询经销商信息
  public final static String GET_DEALER_USER_BY_ACCOUNT_FOR_DTO = "SELECT ns.ID AS id,ns.DEALER_ACCOUNT AS dealerAccount,ns.DEALER_PASSWORD AS dealerPassword," +
		  "ns.DEALER_NAME AS dealerName,ns.DEALER_CATEGORY AS dealerCategory,ns.PERSON_IN_CHARGE AS personInCharge,ns.RECHARGE_AMOUNT_CAP AS rechargeAmountCap," +
		  "ns.PREPAID_AMOUNT AS prepaidAmount,ns.CONTACT_PHONE_NUMBER AS contactPhoneNumber,ns.RESERVE_TELEPHONE AS reserveTelephone," +
		  "ns.LONGITUDE AS longitude,ns.LATITUDE AS latitude,ns.ADDRESS AS address,CONVERT(varchar(100), ns.CREATED_DATE, 120)  AS createdDate,ns.CREATED_BY_ID AS createdById," +
		  "ns.CREATED_BY_NAME AS createdByName,CONVERT(varchar(100), ns.UPDATE_DATE, 120)  AS updateDate,ns.UPDATE_BY_ID AS updateById,ns.UPDATE_BY_NAME AS updateByName," +
		  "ns.TOKEN AS token,ns.FIRST_LANDING AS firstLanding,ns.ACCUMULATED_AMOUNT AS accumulatedAmount,ns.STATUS AS status,ns.DEPOSIT_AMOUNT as depositAmount " +
          "FROM NEARBY_STORES ns "+
          "WHERE ns.DEALER_ACCOUNT =:dealerAccount";
  
  // 根据账号模糊查询
  public final static String FIND_DEALER_ACCOUNT_BY_ACC_STAT = "select ns.DEALER_ACCOUNT from NEARBY_STORES ns WHERE ns.DEALER_ACCOUNT like :dealerAccount order by ns.DEALER_ACCOUNT ";
  
  //查询充值记录(用户端)
  public final static String QUERY_ANDROID_TOP_UP_RECORD = "SELECT tur.ANDROID_USER_ID AS androidUserId, au.USERNAME AS userName, au.FULL_NAME AS fullName, tur.TOP_UP_AMOUNT AS topUpAmount,'3' AS state," +
		  "tur.CREATED_DATE AS createdDate,tur.DEALER_ID AS dealerId,ns.DEALER_ACCOUNT AS dealerName FROM TOP_UP_RECORD tur " +
		  "left join NEARBY_STORES ns ON tur.DEALER_ID = ns.ID LEFT JOIN ANDROID_USER au ON au.id = tur.ANDROID_USER_ID WHERE 1 = 1";
  
//查询CB BANK充值记录(用户端)
  public final static String QUERY_CB_BANK_ANDROID_TOP_UP_RECORD = "SELECT cbb.ANDROID_USER_ID AS androidUserId, au.USERNAME AS username, au.FULL_NAME AS fullName, cbb.AMOUNT AS amount," +
		  "cbb.CREATED_DATE AS createdDate,cbb.UPDATED_DATE AS updatedDate,cbb.ITEMNAME AS itemname,cbb.PROCEED AS proceed FROM CB_BANK_RECORD cbb LEFT JOIN ANDROID_USER au ON au.id = cbb.ANDROID_USER_ID " +
		  "WHERE 1=1 ";
  
  public final static String GET_DEALER_USER_BY_TOKEN = "SELECT ns.ID AS id,ns.LONGITUDE AS longitude,ns.DEALER_ACCOUNT AS dealerAccount,ns.DEALER_PASSWORD AS dealerPassword, " +
          "ns.LATITUDE AS latitude,ns.ADDRESS AS address,ns.DEALER_NAME AS dealerName, "+
          "ns.DEALER_CATEGORY AS dealerCategory,ns.PERSON_IN_CHARGE AS personInCharge,ns.RECHARGE_AMOUNT_CAP AS rechargeAmountCap, "+
          "ns.PREPAID_AMOUNT AS prepaidAmount,ns.CONTACT_PHONE_NUMBER AS contactPhoneNumber,ns.RESERVE_TELEPHONE AS reserveTelephone, "+
          "ns.CREATED_BY_NAME AS createdByName,ns.UPDATE_BY_NAME AS updateByName, ns.STATUS AS status "+
          "FROM NEARBY_STORES ns "+
          "WHERE ns.TOKEN =:token";
  
  //查询未售卖的彩票号码count
  public final static String GET_LOTTERY_NUMBER_COUNT = "SELECT COUNT(*)" +
              "FROM LOTTERY_NUMBER ln LEFT JOIN LOTTERY_RULE lr on ln.RULE_ID = lr.ID " +
              "WHERE ln.PURCHASE_STATUS = '0'";
  
  public static final String QUERY_SUB_CATEGORY ="SELECT lc.CATEGORY_ID AS value,lc.CATEGORY_NAME AS name FROM dbo.LOTTERY_SUB_CATEGORY lc";
  
  //查询apk
  public static final String GET_ALL_APK = "SELECT am.ID AS id, am.APP_NAME AS appName ,am.PACKAGE_NAME AS packageName ,am.VERSION_CODE AS versionCode ," +
		  		"am.VERSION_NAME AS versionName ,am.RELEASE_TIME AS releaseTime ,am.FILESIZE AS filesize ,am.CONTEXT AS context,am.DOWNLOAD AS download " +
		  		"FROM APK_MANAGEMENT am WHERE 1=1";
  
  
  public static final String QUERY_CHECK_TICKET_RECORD = "SELECT ctr.ID AS id, ctr.ANDROID_USER_ID AS androidUserId, ctr.ROUND AS round, ctr.LETTER AS letter, "
	  		+ "ctr.BEGIN_NUMBER AS beginNumber, ctr.END_NUMBER AS endNumber, ctr.WINNIN_SUM AS winninSum, ctr.CREATED_DATE AS createdDate FROM dbo.CHECK_TICKET_RECORD ctr "
	  		+ " WHERE ctr.ANDROID_USER_ID = :androidUserId AND ctr.CREATED_DATE BETWEEN DATEADD(MONTH, -3, getDate()) AND getDate() ORDER BY ctr.CREATED_DATE DESC";
	  
  public static final String QUERY_CHECK_TICKET_RECORD_BY_ROUND = "SELECT ctr.ID AS id, ctr.ANDROID_USER_ID AS androidUserId, ctr.ROUND AS round, "
	  		+ "ctr.LETTER AS letter, ctr.BEGIN_NUMBER AS beginNumber, ctr.END_NUMBER AS endNumber, ctr.WINNIN_SUM AS winninSum, ctr.CREATED_DATE AS createdDate, "
	  		+ "au.USERNAME AS androidUserName FROM dbo.CHECK_TICKET_RECORD ctr LEFT JOIN ANDROID_USER au ON ctr.ANDROID_USER_ID = au.ID WHERE ctr.ROUND =:round";
  
  public static final String GET_RANDOM_TICKET_BY_ROUND = "SELECT top 10 ln.ID AS id, ln.LOTTERY_PERIODS AS lotteryPeriods, ln.LETTER_RULE AS letterRule, "
  		+ "ln.NUMBER_RULE AS numberRule, ln.LOTTERY_NUMBER AS lotteryNumber FROM LOTTERY_NUMBER ln LEFT JOIN LOTTERY_RULE lr on ln.RULE_ID = lr.ID "
  		+ " WHERE ln.PURCHASE_STATUS = '0' AND (lr.LOTTERY_CATEGORY = '' OR lr.LOTTERY_CATEGORY is null) "
  		+ "AND ln.LOTTERY_PERIODS = :round ORDER BY NewID()";
  
  public static final String GET_TICKET_BY_LETTER_AND_NUMBER = "SELECT top 20 ln.ID AS id, ln.LOTTERY_PERIODS AS lotteryPeriods, ln.LETTER_RULE AS letterRule,"
  		+ " ln.NUMBER_RULE AS numberRule, ln.LOTTERY_NUMBER AS lotteryNumber FROM LOTTERY_NUMBER ln LEFT JOIN LOTTERY_RULE lr on ln.RULE_ID = lr.ID "
  		+ " WHERE ln.PURCHASE_STATUS = '0' AND (lr.LOTTERY_CATEGORY = '' OR lr.LOTTERY_CATEGORY is null) "
  		+ "AND ln.LOTTERY_PERIODS = :round "
  		+ "AND (cast(ln.LETTER_RULE as int) > :beginLetter OR cast(ln.LETTER_RULE as int) = :beginLetter) "
  		+ "AND (cast(ln.LETTER_RULE as int) < :endLetter OR cast(ln.LETTER_RULE as int) = :endLetter) "
  		+ "AND (cast(ln.NUMBER_RULE as int) > :beginNumber OR cast(ln.NUMBER_RULE as int) = :beginNumber) "
  		+ "AND (cast(ln.NUMBER_RULE as int) <:endNumber OR cast(ln.NUMBER_RULE as int) = :endNumber) "
  		+ "ORDER BY ln.LETTER_RULE ASC, ln.NUMBER_RULE ASC";
  
  public static final String GET_CURRENT_ROUND = "SELECT p.ID AS id,p.PERIOD AS period,p.APPROVE_STATE AS approveState,p.ON_SALE AS onSale,p.APPROVE_STATE_NAME AS approveStateName,"
		+ "p.LOTTERY_SALE_PRICE AS lotterySalePrice,p.OFF_SALE AS offSale,p.LOTTERY_TIMES AS lotteryTimes,p.CREATE_BY_ID AS createById, p.SHOW_SALE_PRICE as showSalePrice from PERIOD p WHERE p.APPROVE_STATE = '1' AND p.ON_SALE ="
		+ "(SELECT ON_SALE FROM PERIOD where ON_SALE =(SELECT MAX(ON_SALE ) FROM PERIOD WHERE APPROVE_STATE = '1' and convert( datetime, ON_SALE ) < :currentDate))";
  public static final String GET_PERIOD_BY_ROUND = "SELECT p.ID AS id,p.PERIOD AS period,p.APPROVE_STATE AS approveState,p.ON_SALE AS onSale,p.APPROVE_STATE_NAME AS approveStateName,"
		  + "p.LOTTERY_SALE_PRICE AS lotterySalePrice,p.OFF_SALE AS offSale,p.LOTTERY_TIMES AS lotteryTimes,p.CREATE_BY_ID AS createById, p.SHOW_SALE_PRICE AS showSalePrice from PERIOD p WHERE p.PERIOD = :period";
  
  public static final String GET_TICKET_BY_CATEGORY = "SELECT ln.ID AS id, ln.LOTTERY_PERIODS AS lotteryPeriods, ln.LETTER_RULE AS letterRule, ln.NUMBER_RULE AS numberRule, "
  		+ "ln.LOTTERY_NUMBER AS lotteryNumber FROM LOTTERY_NUMBER ln LEFT JOIN LOTTERY_RULE lr on ln.RULE_ID = lr.ID "
  		+ " WHERE ln.PURCHASE_STATUS = '0' AND lr.LOTTERY_CATEGORY =:category AND "
  		+ "lr.SUB_CATEGORY_NAME =:subCategory AND ln.LOTTERY_PERIODS =:round";
  
  public static final String GET_TICKET_BY_RULE_ID = "SELECT ln.ID AS id, ln.LOTTERY_PERIODS AS lotteryPeriods, ln.LETTER_RULE AS letterRule, ln.NUMBER_RULE AS numberRule, "
		  + "ln.LOTTERY_NUMBER AS lotteryNumber FROM LOTTERY_NUMBER ln WHERE ln.PURCHASE_STATUS = '0' AND ln.RULE_ID = :id";
  public static final String GET_TICKET_BY_RULE_IDS = "SELECT ln.ID AS id, ln.LOTTERY_PERIODS AS lotteryPeriods, ln.LETTER_RULE AS letterRule, ln.NUMBER_RULE AS numberRule, "
		  + "ln.LOTTERY_NUMBER AS lotteryNumber FROM LOTTERY_NUMBER ln WHERE ln.PURCHASE_STATUS = '0' AND ln.RULE_ID in (:id)";
  
  
  public static final String GET_NUMBER_OF_PERIODS = "SELECT per.PERIOD as name,per.PERIOD as value FROM dbo.PERIOD per where per.APPROVE_STATE = 1 ORDER BY per.CREATED_DATE DESC";
  
  public static final String GET_LOTTERY_SALES_VOLUMES_BY_PERIODS1 = "SELECT a.COUNT1 AS soldNumber,b.COUNT2 AS notForsaleNumber,c.LOTTERY_SALE_PRICE AS lotterySalePrice FROM "
                                                                  + "(SELECT COUNT(ln1.PURCHASE_STATUS) AS COUNT1 FROM dbo.LOTTERY_NUMBER ln1 WHERE ln1.PURCHASE_STATUS='1' AND ln1.LOTTERY_PERIODS=:periods) a, "
                                                                  + "(SELECT COUNT(ln2.PURCHASE_STATUS) COUNT2 FROM dbo.LOTTERY_NUMBER ln2 WHERE ln2.PURCHASE_STATUS='0' AND ln2.LOTTERY_PERIODS=:periods) b, "                                                                  + "(SELECT p.LOTTERY_SALE_PRICE FROM dbo.PERIOD p WHERE (p.PERIOD_STATE = '1' OR p.PERIOD_STATE = '0') AND p.PERIOD=:periods) c ";
  
  public static final String GET_LOTTERY_SALES_VOLUMES_BY_PERIODS2 = "SELECT a.COUNT1 AS soldNumber,b.COUNT2 AS notForsaleNumber,c.LOTTERY_SALE_PRICE AS lotterySalePrice FROM "
          + "(SELECT COUNT(ln1.PURCHASE_STATUS) AS COUNT1 FROM dbo.DELETE_LOTTERY_NUMBER ln1 WHERE ln1.PURCHASE_STATUS='1' AND ln1.LOTTERY_PERIODS=:periods) a, "
          + "(SELECT COUNT(ln2.PURCHASE_STATUS) COUNT2 FROM dbo.DELETE_LOTTERY_NUMBER ln2 WHERE ln2.PURCHASE_STATUS='0' AND ln2.LOTTERY_PERIODS=:periods) b, "
          + "(SELECT p.LOTTERY_SALE_PRICE FROM dbo.PERIOD p WHERE (p.PERIOD_STATE = '1' OR p.PERIOD_STATE = '0') AND p.PERIOD=:periods) c ";
  
  public static final String GET_CURRENT_SOLD1 = "SELECT lnum.lottery_number AS lotteryNumber,lru.LOTTERY_CATEGORY AS category,lru.SUB_category_NAME AS subCategory,null AS deleteDate FROM dbo.LOTTERY_NUMBER lnum "
                                               + "LEFT JOIN dbo.LOTTERY_RULE lru ON lnum.RULE_ID=lru.ID "
                                               + "WHERE lnum.PURCHASE_STATUS='1' AND lnum.LOTTERY_PERIODS=:periods UNION ALL "
                                               + "SELECT lnum.lottery_number AS lotteryNumber,lru.LOTTERY_CATEGORY AS category,lru.SUB_category_NAME AS subCategory,lnum.DELETE_DATE AS deleteDate FROM dbo.DELETE_LOTTERY_NUMBER lnum "
                                               + "LEFT JOIN dbo.LOTTERY_RULE lru ON lnum.RULE_ID=lru.ID "
                                               + "WHERE lnum.PURCHASE_STATUS='1' AND lnum.LOTTERY_PERIODS=:periods";
  
  public static final String GET_CURRENT_UNSOLD1 = "SELECT lnum.lottery_number AS lotteryNumber,lru.LOTTERY_CATEGORY AS category,lru.SUB_category_NAME AS subCategory,null AS deleteDate FROM dbo.LOTTERY_NUMBER lnum "
                                                + "LEFT JOIN dbo.LOTTERY_RULE lru ON lnum.RULE_ID=lru.ID "
                                                + "WHERE lnum.PURCHASE_STATUS='0' AND lnum.LOTTERY_PERIODS=:periods UNION ALL "
                                                + "SELECT lnum.lottery_number AS lotteryNumber,lru.LOTTERY_CATEGORY AS category,lru.SUB_category_NAME AS subCategory,lnum.DELETE_DATE AS deleteDate FROM dbo.DELETE_LOTTERY_NUMBER lnum "
                                                + "LEFT JOIN dbo.LOTTERY_RULE lru ON lnum.RULE_ID=lru.ID "
                                                + "WHERE lnum.PURCHASE_STATUS='0' AND lnum.LOTTERY_PERIODS=:periods";
  
  public static final String GET_CURRENT_SOLD2 = "SELECT lnum.lottery_number AS lotteryNumber,lru.LOTTERY_CATEGORY AS category,lru.SUB_category_NAME AS subCategory,lnum.DELETE_DATE AS deleteDate FROM dbo.DELETE_LOTTERY_NUMBER lnum "
                                                + "LEFT JOIN dbo.LOTTERY_RULE lru ON lnum.RULE_ID=lru.ID "
                                                + "WHERE lnum.PURCHASE_STATUS='1' AND lnum.LOTTERY_PERIODS=:periods";

  public static final String GET_CURRENT_UNSOLD2 = "SELECT lnum.lottery_number AS lotteryNumber,lru.LOTTERY_CATEGORY AS category,lru.SUB_category_NAME AS subCategory,lnum.DELETE_DATE AS deleteDate FROM dbo.DELETE_LOTTERY_NUMBER lnum "
                                               + "LEFT JOIN dbo.LOTTERY_RULE lru ON lnum.RULE_ID=lru.ID "
                                               + "WHERE lnum.PURCHASE_STATUS='0' AND lnum.LOTTERY_PERIODS=:periods";
  
  public static final String QUERY_ALL_APK_MANAGEMENT_LATEST = "select a.VERSION_CODE as versionCode,a.VERSION_NAME as versionName,"
			+ "a.DOWNLOAD as download from dbo.APK_MANAGEMENT a order by a.RELEASE_TIME desc ";
  
  public static final String QUERY_BY_PERIODS = "SELECT ln.RULE_ID AS ruleId ,ln.LOTTERY_PERIODS AS lotteryPeriods ," +
		  	"ln.LETTER_RULE AS letterRule ,ln.NUMBER_RULE AS numberRule ,ln.LOTTERY_NUMBER AS lotteryNumber ,"+
		  	"ln.PURCHASE_BY_NAME AS purchaseByName ,ln.PURCHASE_DATE AS purchaseDate ,ln.PURCHASE_STATUS AS purchaseStatus " +
		  	"FROM LOTTERY_NUMBER ln Where ln.LOTTERY_PERIODS <>:lotteryPeriods";
  
  public static final String QUERY_SALE_TICKET_LIST1 = "SELECT lnum.lottery_number AS lotteryNumber,aruser.FULL_NAME AS purchaser,lnum.PURCHASE_DATE AS purchaserDate,"
                                                     + "aruser.GENDER AS sex,aruser.NRC AS iDCard,aruser.MOBILE_NO AS telephone,aruser.USERNAME AS mail,aruser.ADDRESS AS address "
                                                     + "FROM dbo.LOTTERY_NUMBER lnum "
                                                     + "LEFT JOIN dbo.ANDROID_USER aruser ON lnum.PURCHASE_BY_NAME = aruser.USERNAME "
                                                     + "WHERE lnum.PURCHASE_STATUS = '1' "
                                                     + "AND lnum.LOTTERY_PERIODS =:periods";
  
  public static final String QUERY_SALE_TICKET_LIST2 = "SELECT lnum.lottery_number AS lotteryNumber,aruser.FULL_NAME AS purchaser,lnum.PURCHASE_DATE AS purchaserDate,"
                                                     + "aruser.GENDER AS sex,aruser.NRC AS iDCard,aruser.MOBILE_NO AS telephone,aruser.USERNAME AS mail,aruser.ADDRESS AS address "
                                                     + "FROM dbo.DELETE_LOTTERY_NUMBER lnum "
                                                     + "LEFT JOIN dbo.ANDROID_USER aruser ON lnum.PURCHASE_BY_NAME = aruser.USERNAME "
                                                     + "WHERE lnum.PURCHASE_STATUS = '1' "
                                                     + "AND lnum.LOTTERY_PERIODS =:periods";
  //根据期数查询规则
  public static final String GET_BY_PERIODS = "SELECT lr.CREATE_BY_ID AS createById,lr.ID AS id,lr.LOTTERY_PERIODS AS lotteryPeriods,lr.LETTER_ROLE AS letterRole," +
          "lr.NUMBER_ROLE AS numberRole,CONVERT(varchar(100), p.ON_SALE, 120) AS onSale,lr.END_NUMBER AS endNumber,lr.LOTTERY_NUMBER AS lotteryNumber," + 
          "CONVERT(varchar(100), p.OFF_SALE, 120) AS offSale,CONVERT(varchar(100), p.LOTTERY_TIMES, 120) AS lotteryTimes," + 
          "lr.STATUS AS status,lr.BEGIN_LETTER AS beginLetter,lr.END_LETTER AS endLetter,lr.BEGIN_NUMBER AS beginNumber," +
          "lr.STATUS_NAME AS statusName,lr.SUB_CATEGORY_ID AS subCategoryId,lr.SUB_CATEGORY_NAME AS subCategoryName,lr.LOTTERY_CATEGORY AS lotteryCategory" +
          " FROM LOTTERY_RULE lr LEFT JOIN PERIOD p ON p.PERIOD = lr.LOTTERY_PERIODS"
          +" WHERE lr.LOTTERY_PERIODS = :queryPeriod";
  
  //查询期数表中信息(期数不相等)
  public static final String GET_PERIOD_BY_PERIODS= "SELECT p.OFF_SALE AS offSale,p.ON_SALE AS onSale FROM PERIOD p " + 
		  "WHERE p.PERIOD <> :lotteryPeriods";
  
  //查询期数表中信息(期数相等)
  public static final String GET_ROUND_BY_PERIOD= "SELECT p.OFF_SALE AS offSale,p.ON_SALE AS onSale,p.LOTTERY_TIMES AS lotteryTimes, p.LOTTERY_SALE_PRICE AS lotterySalePrice FROM PERIOD p " + 
		  "WHERE p.PERIOD = :lotteryPeriods";
  
  public static final String GET_USER_FEEDBACK = "SELECT uf.ID AS id,uf.SUGGESTION AS suggestion,uf.MOBILE_NO AS mobileNo,uf.DEAL_NAME AS dealName,uf.DEAL_STATUS AS dealStatus FROM USER_FEEDBACK uf WHERE 1=1 ";
  
  public static final String GET_All_ROUND = "SELECT p.ID AS id, p.PERIOD AS period, p.APPROVE_STATE AS approveState, p.ON_SALE AS onSale,p.APPROVE_STATE_NAME AS approveStateName,p.UPDATED_DATE AS updatedDate," + 
		  "p.LOTTERY_SALE_PRICE AS lotterySalePrice,p.OFF_SALE AS offSale,p.LOTTERY_TIMES AS lotteryTimes,p.UPDATED_BY_NAME AS updatedByName, p.UPDATED_BY_ID AS updatedById, p.SHOW_SALE_PRICE AS showSalePrice from PERIOD p WHERE 1=1 ";
  
  public static final String MERCHANT_SETTLEMENT = "SELECT tur.totalPrepaid,tur.totalUsers, ns.DEALER_ACCOUNT AS dealerAccount, ns.DEALER_NAME AS dealerName,ns.DEALER_CATEGORY AS dealerCategory, ns.RECHARGE_AMOUNT_CAP AS rechargeAmountCap,"
		  + "ns.PREPAID_AMOUNT AS prepaidAmount,(ns.RECHARGE_AMOUNT_CAP - ns.PREPAID_AMOUNT) AS rechargeBalance,ns.ADDRESS AS address, CONVERT(varchar(100), ns.CREATED_DATE, 120) AS createdDate FROM NEARBY_STORES ns "
		  + " LEFT JOIN (SELECT z.DEALER_ID, z.totalUsers, y.totalPrepaid FROM (SELECT x.DEALER_ID, COUNT(x.DEALER_ID) AS totalUsers FROM (SELECT DISTINCT ANDROID_USER_ID, DEALER_ID FROM dbo.TOP_UP_RECORD) x GROUP BY DEALER_ID) z LEFT JOIN (SELECT DEALER_ID, COUNT(DEALER_ID) AS totalPrepaid FROM dbo.TOP_UP_RECORD GROUP BY DEALER_ID) y ON y.DEALER_ID = z.DEALER_ID) tur ON tur.DEALER_ID = ns.ID "
		  + " WHERE 1=1";
  
  public static final String GET_ORDER_RECORD_DETAILS_BY_ORDER_NUMBER = "SELECT ord.ID AS id, ord.ORDER_NUMBER AS orderNumber, ord.LOTTERY_ROUND AS lotteryRound, ord.LOTTERY_NUMBER AS lotteryNumber, iwn.WINNIN_SUM AS winninSum, iwn.WINNIN_NAME_STRING AS winninName, iwn.WINNIN_GRADE AS winninGrade FROM dbo.ORDER_RECORD_DETAILS ord "
  		+ " LEFT JOIN (SELECT RULE_ID, LOTTERY_NUMBER, LOTTERY_PERIODS FROM dbo.DELETE_LOTTERY_NUMBER UNION SELECT RULE_ID, LOTTERY_NUMBER, LOTTERY_PERIODS FROM dbo.LOTTERY_NUMBER   ) lnu ON ord.LOTTERY_NUMBER = lnu.LOTTERY_NUMBER AND ord.LOTTERY_ROUND = lnu.LOTTERY_PERIODS "
	  	+ " LEFT JOIN (SELECT iwn.* FROM dbo.INPUT_WINNIN_NO iwn INNER JOIN dbo.PERIOD pe ON pe.PERIOD = iwn.WINNIN_PERIOD AND pe.PERIOD_STATE = '1' ) iwn ON ord.LOTTERY_ROUND = iwn.WINNIN_PERIOD AND (ord.LOTTERY_NUMBER = iwn.WINNIN_NUMBER OR (ord.LOTTERY_NUMBER LIKE iwn.WINNIN_NUMBER AND ISNUMERIC(SUBSTRING(ord.LOTTERY_NUMBER,CHARINDEX('%',iwn.WINNIN_NUMBER),1)) != 0)) WHERE ord.ORDER_NUMBER =:orderNumber ORDER BY iwn.WINNIN_SUM DESC, lnu.RULE_ID DESC";
  
  
  public static final String GET_CATEGORY_BY_TICKET_ID = "SELECT lr.LOTTERY_CATEGORY_NAME FROM dbo.LOTTERY_NUMBER ln LEFT JOIN dbo.LOTTERY_RULE lr ON ln.RULE_ID = lr.ID WHERE ln.ID = :id";
  //获取省
  public static final String GET_PROVINCE = "SELECT DISTINCT substring(ADDRESS, 1, patindex('%-%',ADDRESS)-1) as name,substring(ADDRESS, 1, patindex('%-%',ADDRESS)-1) as value FROM dbo.ANDROID_USER WHERE '1'= '1'";
  //获取市
  public static final String GET_CITY = "SELECT DISTINCT substring(ADDRESS, patindex('%-%',ADDRESS)+1, len(ADDRESS)) as name,substring(ADDRESS, patindex('%-%',ADDRESS)+1, len(ADDRESS)) as value FROM dbo.ANDROID_USER WHERE '1'= '1'";
  
  public static final String QUERY_SOLD_REGION_LIST1 = "SELECT DISTINCT substring( CA.ADDRESS, 1, patindex( '%-%', CA.ADDRESS )- 1 ) as province,"
		  											 + "substring( CA.ADDRESS, patindex( '%-%', CA.ADDRESS )+ 1, len( CA.ADDRESS )) as city,"
		  											 + "ISNULL(CA.Account,0) AS numberOfPeople,ISNULL(CB.Account,0) AS soldNumber "
		  											 + "FROM dbo.LOTTERY_NUMBER lnum1,(SELECT ADDRESS,COUNT(*) Account FROM dbo.ANDROID_USER aruser GROUP BY ADDRESS) CA "
		  											 + "LEFT JOIN (SELECT ADDRESS,COUNT(*) Account FROM dbo.LOTTERY_NUMBER lnum " 
		  											 + "LEFT JOIN dbo.ANDROID_USER aruser ON lnum.PURCHASE_BY_NAME = aruser.USERNAME WHERE PURCHASE_STATUS = '1' GROUP BY ADDRESS) CB "
		  											 + "ON CA.ADDRESS = CB.ADDRESS where 1=1 "
		  											 + "AND lnum1.LOTTERY_PERIODS =:periods ";

  public static final String QUERY_SOLD_REGION_LIST2 = "SELECT DISTINCT substring( CA.ADDRESS, 1, patindex( '%-%', CA.ADDRESS )- 1 ) as province,"
													 + "substring( CA.ADDRESS, patindex( '%-%', CA.ADDRESS )+ 1, len( CA.ADDRESS )) as city,"
													 + "ISNULL(CA.Account,0) AS numberOfPeople,ISNULL(CB.Account,0) AS soldNumber "
													 + "FROM dbo.DELETE_LOTTERY_NUMBER dnum, (SELECT ADDRESS,COUNT(*) Account FROM dbo.ANDROID_USER aruser GROUP BY ADDRESS) CA "
													 + "LEFT JOIN (SELECT ADDRESS,COUNT(*) Account FROM dbo.DELETE_LOTTERY_NUMBER lnum " 
													 + "LEFT JOIN dbo.ANDROID_USER aruser ON lnum.PURCHASE_BY_NAME = aruser.USERNAME WHERE PURCHASE_STATUS = '1' GROUP BY ADDRESS) CB "
													 + "ON CA.ADDRESS = CB.ADDRESS where 1=1 "
													 + "AND dnum.LOTTERY_PERIODS =:periods ";

  public static final String QUERY_ADVERTINFO_PRIORITY = "SELECT ar.PRIORITY_ID AS value,ar.PRIORITY_NAME AS "
  		+ "name FROM dbo.ADVERTINFO_PRIORITY ar";
  
  public static final String GET_ALL_ADVERTISEMENT = "SELECT info.ID AS id,info.ADVERT_NAME AS adverName,info.ADVERT_URL AS adverURL,info.SUPPLIER AS supplier,info.CREATE_BY_ID AS createById,info.APPROVE_STATE_NAME AS approveStateName,"
		  											 + "ap.PRIORITY_NAME AS priorityName,info.VALIDITY_START_DATE AS validityStartDate,info.VALIDITY_END_DATE AS validityEndDate,info.PRIORITY AS priority,"
		  											 + "info.APPROVE_STATE AS approveState,info.IMAGE_URL AS imageURL FROM ADVERTINFO info LEFT JOIN ADVERTINFO_PRIORITY ap ON ap.PRIORITY_ID = info.PRIORITY WHERE 1 = 1";
  
  public static final String GET_ALL_ADVERTISEMENT_ANDROID = "SELECT TOP 4 info.ID AS id,info.ADVERT_NAME AS adverName,info.ADVERT_URL AS adverURL,info.SUPPLIER AS supplier,info.CREATE_BY_ID AS createById,info.APPROVE_STATE_NAME AS approveStateName,"
			 + "ap.PRIORITY_NAME AS priorityName,info.VALIDITY_START_DATE AS validityStartDate,info.VALIDITY_END_DATE AS validityEndDate,info.PRIORITY AS priority,"
			 + "info.APPROVE_STATE AS approveState,info.IMAGE_URL AS imageURL FROM ADVERTINFO info LEFT JOIN ADVERTINFO_PRIORITY ap ON ap.PRIORITY_ID = info.PRIORITY WHERE 1 = 1";
  
//android用戶充值推送
  public final static String GET_ANDROID_USER_PUSH_BY_USERID =  "select a.PUSH_ID as pushId, a.THEME as theme, "
      + "a.CONTENT as content, a.USER_ID as userId, a.CREATED_DATE as createdDate, a.FAILURE_TIME as failureTime, "
      + "a.PUSH_STATE as pushState, a.PUSH_SUCCESS as pushSuccess, a.PUSH_TYPE as pushType, a.PUSH_IMG as pushImg, a.LOTTERY_ROUND as lotteryRound "
      + " from android_user_push a where 1=1 ";
  
//根据订单号查询用户ID
  public final static String QUERY_USERID_BY_ORDER_NUMBER = "SELECT a.ANDROID_USER_ID as androidUserId "
  		+ "FROM ORDER_RECORD a where 1=1";
  
  public final static String GET_ALL_TODAY_APPROVAL = "SELECT lr.ID AS id,lr.CREATE_BY_ID AS createById,lr.LOTTERY_PERIODS AS lotteryPeriods,lr.LETTER_ROLE AS letterRole,lr.NUMBER_ROLE AS numberRole,"
		  											+ "lr.END_NUMBER AS endNumber,lr.STATUS AS status,lr.BEGIN_LETTER AS beginLetter,lr.END_LETTER AS endLetter,lr.BEGIN_NUMBER AS beginNumber,lr.LOTTERY_NUMBER AS lotteryNumber,"
		  											+ "lr.STATUS_NAME AS statusName,lr.SUB_CATEGORY_ID AS subCategoryId,lr.LOTTERY_CATEGORY AS lotteryCategory FROM LOTTERY_RULE lr where lr.STATUS = 1 AND lr.BATCH_STATUS = 0 AND lr.LOTTERY_PERIODS = :period";
  public final static String GET_PURCHASE_RECORD = "SELECT au.USERNAME as androidUserName,ord.LOTTERY_NUMBER as lotteryNumber,ord.LOTTERY_ROUND as lotteryRound,ord.WINNIN_SUM as winninSum,ore.ANDROID_USER_ID as androidUserId," 
		  										 + "ord.CREATED_DATE as createdDate FROM ORDER_RECORD ore LEFT JOIN ANDROID_USER au ON ore.ANDROID_USER_ID = au.ID LEFT JOIN ORDER_RECORD_DETAILS ord"
  												 + " ON ord.ORDER_NUMBER = ore.ORDER_NUMBER where 1 = 1 ";
  public final static String LIST_MERCHANT_RECORD = "SELECT au.userName,tur.TOP_UP_AMOUNT AS topUpAmount,tur.DEALER_ID AS dealerId, ns.DEALER_NAME AS dealerName, tur.CREATED_DATE AS createdDate FROM TOP_UP_RECORD tur left join NEARBY_STORES ns ON"
		  										  + " tur.DEALER_ID = ns.ID LEFT JOIN ANDROID_USER au on au.ID = tur.ANDROID_USER_ID WHERE 1=1";
  public final static String QUERY_USER_TOP_UP_RECORD = "SELECT cbr.ITEMNAME AS dealerName, au.USERNAME as userName, au.FULL_NAME as fullName, cbr.AMOUNT AS topUpAmount,cbr.CREATED_DATE AS createdDate,cbr.PROCEED AS state FROM CB_BANK_RECORD cbr LEFT JOIN ANDROID_USER au ON au.id = cbr.ANDROID_USER_ID WHERE"
		  											  + " cbr.ANDROID_USER_ID in (SELECT au.ID FROM ANDROID_USER au WHERE au.USERNAME like :userName ) ";
  
  public final static String FIND_RULE_FOR_COPY = "SELECT lr.SUB_CATEGORY_ID AS subCategoryId,lr.SUB_CATEGORY_NAME AS subCategoryName,lr.LOTTERY_CATEGORY AS lotteryCategory,lr.CATEGORY_IMAGE_NAME AS categoryImageName, "
		  										+ "lr.LOTTERY_CATEGORY_NAME AS lotteryCategoryName,lr.EFFECTIVE_DATE AS effectiveDate,lr.SUBCATEGORY_EFFECTIVE_DATE AS subcategoryEffectiveDate "
  												+ " FROM (SELECT * FROM LOTTERY_RULE WHERE STATUS = 1) lr WHERE lr.OFF_SALE = (SELECT MAX( convert( datetime, lrn.OFF_SALE )) FROM (SELECT * FROM LOTTERY_RULE WHERE STATUS = 1) lrn)"
  												+ " AND lr.LOTTERY_CATEGORY != '' ";
  public final static String FIND_FOR_BATCH = "SELECT at.PERIOD AS period,at.ID AS id,at.OFF_SALE AS offSale,at.ON_SALE AS onSale,at.LOTTERY_TIMES AS lotteryTimes,at.DEAL_STATE AS dealState,at.UPDATED_BY_NAME AS updatedByName,"
		  									+ "at.DEAL_STATE_NAME AS dealStateName,at.CREATE_BY_ID AS createById,at.CREATED_BY_NAME AS createdByName,at.CREATED_DATE AS createdDate,at.UPDATED_BY_ID AS updatedById,at.UPDATED_DATE AS updatedDate "
  											+ "FROM APPROVE_TIMER at WHERE at.OFF_SALE = (SELECT MIN(OFF_SALE) FROM APPROVE_TIMER WHERE convert( datetime, OFF_SALE ) > :currentDate) ";
  public final static String FIND_FOR_MAX_OFF_DATE = "SELECT at.PERIOD AS period,at.ID AS id,at.OFF_SALE AS offSale,at.ON_SALE AS onSale,at.LOTTERY_TIMES AS lotteryTimes,at.DEAL_STATE AS dealState,at.UPDATED_BY_NAME AS updatedByName,"
		  + "at.DEAL_STATE_NAME AS dealStateName,at.CREATE_BY_ID AS createById,at.CREATED_BY_NAME AS createdByName,at.CREATED_DATE AS createdDate,at.UPDATED_BY_ID AS updatedById,at.UPDATED_DATE AS updatedDate "
		  + "FROM APPROVE_TIMER at WHERE at.OFF_SALE = (SELECT MAX(OFF_SALE) FROM APPROVE_TIMER) ";
  
  public final static String FIND_CURRENT_NUMBER_PERIOD = "SELECT p.OFF_SALE FROM PERIOD p WHERE p.PERIOD = (SELECT DISTINCT(ln.LOTTERY_PERIODS) FROM LOTTERY_NUMBER ln)";
  
  public final static String GET_EFFECTIVE_DATE =  "SELECT DISTINCT bpid.EFFECTIVE_DATE AS value,bpid.EFFECTIVE_DATE AS name "+
          "FROM dbo.BASE_PARAMETER_ITEM_DEF bpid ORDER BY bpid.EFFECTIVE_DATE DESC ";

  public final static String QUERY_BASE_PARAMETER_LIST = "SELECT bpid.BPTD_CODE AS bptdCode,bptd.PT_NAME AS ptName,bpid.EFFECTIVE_DATE AS effectiveDate,"+ 
          "bpid.APPROVED_FLAG AS approvedFlag,bpid.EXPIRATION_DATE AS expirationDate,bpid.UPDATED_BY_ID AS updatedById "+ 
          "FROM dbo.BASE_PARAMETER_ITEM_DEF bpid " + 
          "LEFT JOIN dbo.BASE_PARAMETER_TYPE_DEF  bptd ON bptd.BPTD_CODE=bpid.BPTD_CODE WHERE 1=1 "+ 
          "AND (:queryBptdCode IS NULL OR bpid.BPTD_CODE =:queryBptdCode) "+
          "AND (:queryEffectiveDate IS NULL OR (CONVERT(varchar(100), bpid.EFFECTIVE_DATE, 23))=:queryEffectiveDate) "+
          "GROUP BY bpid.BPTD_CODE,bpid.EFFECTIVE_DATE,bptd.PT_NAME,bpid.APPROVED_FLAG,bpid.EXPIRATION_DATE,bpid.UPDATED_BY_ID "+
          "ORDER BY bpid.EFFECTIVE_DATE DESC ";
  public final static String QUERY_BASE_PARAMETER_LIST_COUNT = "SELECT COUNT(d.EFFECTIVE_DATE) FROM ( SELECT bpid.EFFECTIVE_DATE "+ 
          "FROM dbo.BASE_PARAMETER_ITEM_DEF bpid LEFT JOIN dbo.BASE_PARAMETER_TYPE_DEF  bptd ON bptd.BPTD_CODE=bpid.BPTD_CODE WHERE 1=1 "+ 
          "AND (:queryBptdCode IS NULL OR bpid.BPTD_CODE =:queryBptdCode) "+
          "AND (:queryEffectiveDate IS NULL OR (CONVERT(varchar(100), bpid.EFFECTIVE_DATE, 23))=:queryEffectiveDate) "+
          "GROUP BY bpid.BPTD_CODE,bpid.EFFECTIVE_DATE,bptd.PT_NAME,bpid.APPROVED_FLAG,bpid.EXPIRATION_DATE,bpid.UPDATED_BY_ID ) d";

public final static String GET_BASE_PARAMETER_TYPE =  "SELECT bptd.BPTD_CODE AS value,bptd.PT_NAME AS name "+
               "FROM dbo.BASE_PARAMETER_TYPE_DEF bptd ";

public final static String GET_BASE_PARAMETER_ITEM_DEFS = "SELECT bpid.BPID_ID AS bpidId,bpid.BPTD_CODE AS bptdCode,bpid.EFFECTIVE_DATE AS effectiveDate,bpid.ITEM_NAME AS itemName,bpid.ITEM_VALUE AS itemValue,"+
                   "bpid.REFERENCE_CODE AS referenceCode,bpid.ITEM_DESC AS itemDesc,bpid.ITEM_ORDER AS itemOrder,"+
                   "bpid.ITEM_DEPTH AS itemDepth, bpid.TEXT_FIELD1 AS textField1,bpid.TEXT_FIELD2 AS textField2,"+
                   "bpid.TEXT_FIELD3 AS textField3,bpid.TEXT_FIELD4 AS textField4,bpid.TEXT_FIELD5 AS textField5,"+
                   "bpid.NUMBER_FIELD1 AS numberField1,bpid.NUMBER_FIELD2 AS numberField2,bpid.IMG_FLAG AS imgFlag,"+
                   "bpid.PARENT_BPID_ID AS parentBpidId,bpid.UPDATED_BY_ID AS updatedById, bpid.UPDATED_BY_NAME AS updatedByName,bpid.UPDATED_DATE AS updatedDate "+
                   "FROM dbo.BASE_PARAMETER_ITEM_DEF bpid WHERE 1=1 "+
                   "AND (:bptdCode IS NULL OR bpid.BPTD_CODE=:bptdCode) "+
                   "AND (:approvedFlag IS NULL OR bpid.APPROVED_FLAG=:approvedFlag)"+
                   "AND CONVERT(varchar(100), bpid.EFFECTIVE_DATE, 23)=( "+
                   "SELECT MAX(bpid1.EFFECTIVE_DATE) FROM dbo.BASE_PARAMETER_ITEM_DEF bpid1 WHERE 1=1 "+
                   "AND (:bptdCode IS NULL OR bpid1.BPTD_CODE=:bptdCode) "+
                   "AND (:approvedFlag IS NULL OR bpid1.APPROVED_FLAG=:approvedFlag)"+
                   "AND (:effectiveDate IS NULL OR (CONVERT(varchar(100), bpid1.EFFECTIVE_DATE, 23))<=:effectiveDate)) ORDER BY bpid.ITEM_ORDER";

public final static String GET_BASE_PARAMETER_BY_EFFECTIVE_DATE_AND_BPTDCODE = "SELECT bpid.BPID_ID AS bpidId,bpid.BPTD_CODE AS bptdCode,bpid.EFFECTIVE_DATE AS effectiveDate,bpid.ITEM_NAME AS itemName,bpid.ITEM_VALUE AS itemValue,"+
                                       "bpid.REFERENCE_CODE AS referenceCode,bpid.ITEM_DESC AS itemDesc,bpid.ITEM_ORDER AS itemOrder,"+
                                       "bpid.ITEM_DEPTH AS itemDepth, bpid.TEXT_FIELD1 AS textField1,bpid.TEXT_FIELD2 AS textField2,"+
                                       "bpid.TEXT_FIELD3 AS textField3,bpid.TEXT_FIELD4 AS textField4,bpid.TEXT_FIELD5 AS textField5,"+
                                       "bpid.NUMBER_FIELD1 AS numberField1,bpid.NUMBER_FIELD2 AS numberField2,bpid.IMG_FLAG AS imgFlag,"+
                                       "bpid.PARENT_BPID_ID AS parentBpidId,bpid.UPDATED_BY_ID AS updatedById, bpid.UPDATED_BY_NAME AS updatedByName,bpid.UPDATED_DATE AS updatedDate "+
                                       "FROM dbo.BASE_PARAMETER_ITEM_DEF bpid WHERE 1=1 "+
                                       "AND (:bptdCode IS NULL OR bpid.BPTD_CODE=:bptdCode) "+
                                       "AND (:approvedFlag IS NULL OR bpid.APPROVED_FLAG <>:approvedFlag)"+
                                       "AND (:effectiveDate IS NULL OR (CONVERT(varchar(100), bpid.EFFECTIVE_DATE, 23))=:effectiveDate) ORDER BY bpid.ITEM_ORDER";

public final static String DELETE_BASE_PARAMETER  = "DELETE FROM dbo.BASE_PARAMETER_ITEM_DEF "
         + "WHERE APPROVED_FLAG <> 'Y' "
         + "AND (:bptdCode IS NULL OR BPTD_CODE=:bptdCode) "
         + "AND (:effectiveDate IS NULL OR CONVERT(varchar(100), EFFECTIVE_DATE, 23)=:effectiveDate)";

public final static String APPROVED_BASE_PARAMETER = "UPDATE dbo.BASE_PARAMETER_ITEM_DEF SET APPROVED_FLAG = :approvedFlag "
            + "WHERE 1=1 "
            + "AND (:bptdCode IS NULL OR BPTD_CODE=:bptdCode) "
            + "AND (:effectiveDate IS NULL OR CONVERT(varchar(100), EFFECTIVE_DATE, 23)=:effectiveDate)";

public final static String UPDATE_BASE_PARAMETER = "UPDATE dbo.BASE_PARAMETER_ITEM_DEF SET EXPIRATION_DATE = :effectiveDate "
           + "WHERE 1=1 "
           + "AND (:bptdCode IS NULL OR BPTD_CODE=:bptdCode) "+
             "AND (:approvedFlag IS NULL OR APPROVED_FLAG=:approvedFlag)"+
             "AND CONVERT(varchar(100), EFFECTIVE_DATE, 23)=( "+
             "SELECT MAX(bpid1.EFFECTIVE_DATE) FROM dbo.BASE_PARAMETER_ITEM_DEF bpid1 WHERE 1=1 "+
             "AND (:bptdCode IS NULL OR bpid1.BPTD_CODE=:bptdCode) "+
             "AND (:approvedFlag IS NULL OR bpid1.APPROVED_FLAG=:approvedFlag)"+
             "AND (:effectiveDate IS NULL OR (CONVERT(varchar(100), bpid1.EFFECTIVE_DATE, 23))<=:effectiveDate))";

public final static String FIND_CATEGORY_IMAGE = "SELECT bpid.BPID_ID AS bpidId,bpid.BPTD_CODE AS bptdCode,bpid.EFFECTIVE_DATE AS effectiveDate" +
        " FROM BASE_PARAMETER_ITEM_DEF bpid LEFT JOIN  BASE_PARAMETER_TYPE_DEF bptd ON bpid.BPTD_CODE = bptd.BPTD_CODE WHERE bptd.BPTD_CODE = 'LOTTERY_CATEGORY' ";

	public final static String QUERY_BY_WINNIN_NAME = "SELECT bpid.BPID_ID AS bpidId,bpid.BPTD_CODE AS bptdCode,bpid.EFFECTIVE_DATE AS effectiveDate,bpid.ITEM_NAME AS itemName,bpid.ITEM_VALUE AS itemValue,"+
            "bpid.REFERENCE_CODE AS referenceCode,bpid.ITEM_DESC AS itemDesc,bpid.ITEM_ORDER AS itemOrder,"+
            "bpid.ITEM_DEPTH AS itemDepth, bpid.TEXT_FIELD1 AS textField1,bpid.TEXT_FIELD2 AS textField2,"+
            "bpid.TEXT_FIELD3 AS textField3,bpid.TEXT_FIELD4 AS textField4,bpid.TEXT_FIELD5 AS textField5,"+
            "bpid.NUMBER_FIELD1 AS numberField1,bpid.NUMBER_FIELD2 AS numberField2,bpid.IMG_FLAG AS imgFlag,"+
            "bpid.PARENT_BPID_ID AS parentBpidId,bpid.UPDATED_BY_ID AS updatedById, bpid.UPDATED_BY_NAME AS updatedByName,bpid.UPDATED_DATE AS updatedDate "+
            " FROM BASE_PARAMETER_ITEM_DEF bpid LEFT JOIN  BASE_PARAMETER_TYPE_DEF bptd ON bpid.BPTD_CODE = bptd.BPTD_CODE WHERE bptd.BPTD_CODE = 'LOTTERY_WINNIN_NAME' ";
	
	public final static String LIST_APPROVE_TIMER = "SELECT dai.ID AS id,dai.PERIOD AS period,dai.OFF_SALE AS offSale,dai.ON_SALE onSale,dai.LOTTERY_TIMES AS lotteryTimes," +
													"dai.DEAL_STATE AS dealState,dai.DEAL_STATE_NAME AS dealStateName FROM APPROVE_TIMER dai ORDER BY dai.CREATED_DATE DESC ";
	
	public final static String QUERY_COLUMN_AND_CONTENT = "SELECT qa.ID AS id,qa.QUESTION AS question,qa.ANSWER AS answer FROM dbo.QUESTIONS_AND_ANSWERS qa"
			+ " WHERE qa.UPDATED_DATE = (SELECT MAX(UPDATED_DATE) FROM dbo.QUESTIONS_AND_ANSWERS WHERE CATEGORY = :queryCategory) ";
	
	//客戶中奖信息查询
	  public final static String CUSTOMER_WINNING = "SELECT p.PERIOD AS period, au.USERNAME AS username, au.FULL_NAME AS fullName, "
	  		+ " au.NRC AS nrc, au.MOBILE_NO AS mobileNo, lr.LOTTERY_CATEGORY as lotteryCategory, lr.LOTTERY_CATEGORY_NAME AS lotteryCategoryName, "
	  		+ " lr.SUB_CATEGORY_ID AS subCategoryId, lr.SUB_CATEGORY_NAME AS subCategoryName, ltn.LOTTERY_NUMBER AS lotteryNumber, "
	  		+ " wn.WINNIN_GRADE AS winninGrade, wn.WINNIN_NAME_STRING AS winninName, wn.WINNIN_NUMBER AS winninNumber, wn.WINNIN_SUM AS winninSum, wn.WINNIN_ID AS winninId "
	  		+ " FROM dbo.INPUT_WINNIN_NO wn JOIN dbo.PERIOD p ON p.ID = wn.PERIOD_ID AND p.PERIOD_STATE = '1' "
			+ " INNER JOIN (SELECT lottery_number,PURCHASE_BY_NAME,RULE_ID FROM dbo.DELETE_LOTTERY_NUMBER WHERE LOTTERY_PERIODS =:period "
			+ " UNION SELECT lottery_number,PURCHASE_BY_NAME,RULE_ID FROM dbo.LOTTERY_NUMBER WHERE LOTTERY_PERIODS =:period ) ltn ON ltn.LOTTERY_NUMBER LIKE wn.WINNIN_NUMBER "
			+ " LEFT JOIN dbo.LOTTERY_RULE lr ON lr.ID = ltn.RULE_ID "
			+ " LEFT JOIN dbo.ANDROID_USER au ON au.USERNAME = ltn.PURCHASE_BY_NAME"
	  		+ " where 1 = 1 ";

	public static final String FIND_NEAR_STORE_BY_DEALER_NAME = "SELECT ns.ID AS id, ns.DEALER_ACCOUNT AS dealerAccount, ns.DEALER_NAME AS dealerName, ns.DEPOSIT_AMOUNT AS depositAmount,"
			+ " ns.RECHARGE_AMOUNT_CAP AS rechargeAmountCap, ns.RECHARGE_AMOUNT_CAP-ns.PREPAID_AMOUNT AS rechargeBalance, ns.PREPAID_AMOUNT AS prepaidAmount, "
			+ " ns.UPDATE_BY_NAME AS updateByName, CONVERT(varchar(100), ns.UPDATE_DATE, 120) AS updateDate, ns.STATUS AS status FROM NEARBY_STORES ns "
			+ "WHERE ns.DEALER_NAME like :dealerName ORDER BY ns.CREATED_DATE DESC";
	
	public static final String COUNT_FIND_NEAR_STORE_BY_DEALER_NAME = "SELECT count(ns.ID) FROM NEARBY_STORES ns WHERE ns.DEALER_NAME like :dealerName";
	
	public static final String FIND_DEALER_LOG_BY_DEALER_ID = "SELECT dal.TRANSACTION_NUMBER AS transactionNumber, dal.TRANSACTION_TYPE AS transactionType, "
			+ "dal.DEALER_ID AS dealerId, ns.DEALER_ACCOUNT AS dealerAccount, ns.DEALER_NAME AS dealerName, dal.DEPOSIT_AMOUNT AS depositAmount , dal.AMOUNT_CAP AS amountCap, "
			+ "dal.ADDED_AMOUNT AS addedAmount, dal.SURPLUS_AMOUNT AS surplusAmount, dal.PREPAID_AMOUNT AS prepaidAmount, dal.NOTE AS note , "
			+ "dal.TRANSACTION_BY_ID AS transactionById, dal.TRANSACTION_BY_NAME AS transactionByName, dal.TRANSACTION_DATE AS transactionDate, dal.LOG_TIME AS logTime "
			+ "FROM dbo.DEALER_ACCOUNT_LOG dal LEFT JOIN dbo.NEARBY_STORES ns ON ns.ID = dal.DEALER_ID WHERE DEALER_ID = :dealerId ORDER BY TRANSACTION_DATE ASC";
	
	public static final String GET_SALE_PRICE = "SELECT TOP 1 lr.SALE_PRICE FROM dbo.LOTTERY_RULE lr WHERE lr.LOTTERY_PERIODS = :round AND lr.LOTTERY_CATEGORY = :category AND lr.SUB_CATEGORY_ID = :subCategory ORDER BY lr.CREATED_DATE DESC";
}
