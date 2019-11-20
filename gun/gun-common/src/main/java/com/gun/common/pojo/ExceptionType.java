package com.gun.common.pojo;

public enum ExceptionType {

    AUTHORIZATION_ERROR(401, "Please login first"), // 请先登录
    DATA_NOT_FOUND(404, "NOT FOUND"),// 未找到
    PARAMETER_ILLEGAL(403, "Invalid parameter"),// 参数异常
    USERNAME_PASSWORD_ERROR(601, "Incorrect password"),// 密码错误
    USER_NOT_FOUND(602, "user does not exist"),// 用户不存在
    MAILBOX_IS_USED(603, "The mailbox is already in use"),// 邮箱已被使用
    ACCOUNT_DISABLE(604, "Your account has been disabled. If you have any questions or concerns, you can contact us"), // 账户被禁用
    USER_DISABLE(605, "user has been disabled"),// 用户已被禁用
    USER_ID_IS_USED(606,"UserAccount is already in use"),// 用户重复
    LOTTERY_SOLD_OUT(801, "The lottery has been sold out"),// 该彩票已售出
    BALANCE_NOT_ENOUGH(802, "Sorry, your credit is running low"),// 余额不足
    SALE_TIME_EXCEPTION(803, "sale time exception"),// 未开售或停止销售
    SALE_TIME_EXPIRE(804, "This round of lottery ends"),// 未开售或停止销售
    SALE_TIME_NOT_START(805, "This round of lottery is not on sale"),// 未开售或停止销售
    WINNIN_NO_NOT_FOUND(1001, "Winnin no not found"),
    WINNIN_NO_NOT_RELEASED(1002, "Winning no not released"),
    NOT_WINNINING(1003, "Not winning"),
    VERIFICATION_CODE_EXPIRED(901, "The verification code has expired"), // 验证码过期
    VERIFY_CODE_ERROR(902, "Verify code error"),
    UNIT_PRICE_ERROR(501, "Lottery price reading error")
    ;

    private int code;

    private String message;

    ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
	
}
