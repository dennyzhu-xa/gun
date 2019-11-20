package com.gun.common.utils;

/**
 * Purpose:Message Code
 * @author felixli
 *
 */
public interface LotteryMessageCode {

	
  
  /**********************************Lottery SUCCESS START*******************************************/
  /**
   * 登入成功
   */
  public static final String LOGIN_SUCCESS                = "LOTTERY-MSG-I0001";
  /**
   * 註冊成功
   */
  public static final String REG_SUCCESS                  = "LOTTERY-MSG-I0002";
  /**
   * 初始化{0}資料成功
   */
  public static final String INIT_SUCCESS                 = "LOTTERY-MSG-I0003";
  /**
   * 查無資料
   */
  public static final String DATA_NOT_FOUND               = "LOTTERY-MSG-I0004";
  /**
   * 查詢{0}資料成功
   */
  public static final String QUERY_SUCCESS                = "LOTTERY-MSG-I0005";
  /**
   * 修改{0}資料成功
   */
  public static final String UPDATE_SUCCESS               = "LOTTERY-MSG-I0006";
  /**
   * 新增{0}資料成功
   */
  public static final String INSERT_SUCCESS               = "LOTTERY-MSG-I0007";
  /**
   * 刪除{0}資料成功
   */
  public static final String DELETE_SUCCESS               = "LOTTERY-MSG-I0008";
  /**
   * 儲存{0}資料成功
   */
  public static final String SAVE_SUCCESS                 = "LOTTERY-MSG-I0009";
  /**
   * 初始化页面成功
   */
  public static final String INIT_PAGE_SUCCESS            = "LOTTERY-MSG-I0010";
  /**
   * 审核成功
   */
  public static final String APPROVAL_SUCCESS			        = "LOTTERY-MSG-I0011";
  /**
   * 重置成功
   */
  public static final String RESET_SUCCESS                = "LOTTERY-MSG-I0012";
  /**
   * 处理成功
   */
  public static final String DEAL_SUCCESS			            = "LOTTERY-MSG-I0013";
  /**
   * 账号冻结成功
   */
  public static final String FROZEN_SUCCESS               = "LOTTERY-MSG-I0014";
  /**
   * 账号激活成功
   */
  public static final String ACTIVATION_SUCCESS           = "LOTTERY-MSG-I0015";
  /**
   * 期数已经被复核过
   */
  public static final String APPROVAL_ALREADY             = "LOTTERY-MSG-I0016";
  
  
  /**********************************Lottery SUCCESS END*******************************************/
  
  
  
  
  
  /**********************************Lottery FAILED START*******************************************/
  /**
   * 登入失敗
   */
  public static final String LOOGIN_FAILED                = "LOTTERY-MSG-E0001";
  /**
   * 使用者不存在
   */
  public static final String USER_NOT_EXIST               = "LOTTERY-MSG-E0002";
  /**
   * 密碼錯誤
   */
  public static final String PASSWORD_ERROR               = "LOTTERY-MSG-E0003";
  /**
   * 註冊失敗
   */
  public static final String REG_FAILED                   = "LOTTERY-MSG-E0004";
  /**
   * 该用户已存在
   */
  public static final String REG_EXISTS_FAILED            = "LOTTERY-MSG-E0005";
  /**
   * 初始化{0}資料失敗
   */
  public static final String INIT_FAIlED                  = "LOTTERY-MSG-E0006";
  /**
   * 查詢{0}資料失敗
   */
  public static final String QUERY_FAIlED                 = "LOTTERY-MSG-E0007";
  /**
   * 新增{0}資料失敗
   */
  public static final String INSERT_FAILED                = "LOTTERY-MSG-E0008";
  /**
   * 修改{0}資料失敗
   */
  public static final String UPDATE_FAILED                = "LOTTERY-MSG-E0009";
  /**
   * 刪除{0}資料失敗
   */
  public static final String DELETE_FAILED                = "LOTTERY-MSG-E0010";
  /**
   * 儲存{0}資料失敗
   */
  public static final String SAVE_FAILED                  = "LOTTERY-MSG-E0011";
  /**
   * 資料存取異常:{0}
   */
  public static final String DATA_ACCESS_FAILED           = "LOTTERY-MSG-E0012";
  /**
   * {0}功能已不存在
   */
  public static final String FUNCTION_NOT_EXIST           = "LOTTERY-MSG-E0013";
  /**
   * 輸入參數{0}必須有值
   */
  public static final String ILLEGAL_ARGUMENT             = "LOTTERY-MSG-E0014";
  /**
   * 未知的系統錯誤異常,請聯絡應用系統管理員
   */
  public static final String UNKNOWN_SYSTEM_ERROR         = "LOTTERY-MSG-E0015";
  /**
   * 頁面資料檢核失敗
   */
  public static final String PAGE_VALIDATE_FAILED         = "LOTTERY-MSG-E0016";
  /**
   * 初始化页面失败
   */
  public static final String INIT_PAGE_FAILED             = "LOTTERY-MSG-E0017";
  /**
   * 角色已不存在
   */
  public static final String UODATE_ROLE_FAILED           = "LOTTERY-MSG-E0018";
  /**
   * 角色不可刪除
   */
  public static final String DELETE_ROLE_FAILED           = "LOTTERY-MSG-E0019";
  /**
   * 角色不可刪除
   */
  public static final String PRESENCE_REF_FAILED           = "LOTTERY-MSG-E0020";
  /**
   * 系统管理员不可删除
   */
  public static final String Delete_SYSTEM_USER           = "LOTTERY-MSG-E0021";
  /**
   * 此function code已存在请重新输入
   */
  public static final String FUNCTION_CODE_EXISTS         = "LOTTERY-MSG-E0022";
  /**
   * 复核失敗
   */
  public static final String APPROVAL_FAILED              = "LOTTERY-MSG-E0023";
  /**
   * 角色已存在
   */
  public static final String ROLE_EXISTS_FAILED           = "LOTTERY-MSG-E0024";
  /**
   * 连接超时
   */
  public static final String TIMED_OUT_FAILED             = "LOTTERY-MSG-E0025";
  /**
   * HTTP客户端关闭失败
   */
  public static final String HTTP_CLIENT_LOSE_FAILED      = "LOTTERY-MSG-E0026";
  /**
   * 无效的搜索地址
   */
  public static final String INVALID_ADDRESS_FAILED       = "LOTTERY-MSG-E0027";
  /**
   * 经销商账号已存在
   */
  public static final String DEALER_ACCOUNT_EXISTS        = "LOTTERY-MSG-E0028";
  /**
   * 经销商地址已存在
   */
  public static final String DEALER_ADDRESS_EXISTS        = "LOTTERY-MSG-E0029";
  /**
   * 经销商地址檢核失败
   */
  public static final String CHECK_DEALER_ADDRESS_FAILED  = "LOTTERY-MSG-E0030";
  /**
   * 经销商已不存在
   */
  public static final String UPDATE_DEALER_FAILED         = "LOTTERY-MSG-E0031";
  /**
   * 非法操作
   */
  public static final String ILLEGAL_OPERATION_FAILED     = "LOTTERY-MSG-E0032";
  /**
   * 网路连接逾时
   */
  public static final String CONNECTION_TIMEOUT_FAILED    = "LOTTERY-MSG-E0033";
  /**
   * 重置密码失败
   */
  public static final String RESET_FAILED                 = "LOTTERY-MSG-E0034";
  /**
   * 处理失败
   */
  public static final String DEAL_FAILED        		      = "LOTTERY-MSG-E0035";
  /**
   * 强迫登出
   */
  public static final String FORCE_LOGOUT_FAILED          = "LOTTERY-MSG-E0036";
  /**
   * 账号设定失败
   */
  public static final String ACCTOUN_SETTING_FAILED       = "LOTTERY-MSG-E0037";
  
  
  
  /**********************************Lottery FAILED END*******************************************/
  
}
