package com.gun.common.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import com.gun.common.exception.BusinessException;
import com.gun.common.pojo.ExceptionType;

public class VerificationCodeUtils {
	
	private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
	
	
	public static String DEFUALT_PASSWORD_SALT = LotteryConstants.LOTTERY;
	public static String DATE_FORMAT = "yyyyMMddHHmmss";
	public static int DEFUALT_VERIFICATION_CODE_LENGTH = 5;
    public static long DEFUALT_EXPIRED_TIME = 1000L * 60L  * 60L * 1L;
	
	private static Random mRandom = new Random();
	
	/**
	 * 生成数字验证码，长度为5位
	 * @return
	 */
	public static String generateVerificationCode(){
		return generateVerificationCode(DEFUALT_VERIFICATION_CODE_LENGTH);
	}
	
	/**
	 * 生成length位的数字验证码
	 * @param length 长度
	 * @return 验证码
	 */
	public static String generateVerificationCode(int length){
		StringBuffer mBuilder = new StringBuffer();
		for (int i = 0; i < length; i++) {
            mBuilder.append(CHARS[mRandom.nextInt(CHARS.length)]);
        }
        return mBuilder.toString();
	}
	
	/**
	 * 验证码加密
	 * @param source
	 * @return
	 */
	public static String verificationCodeEncrypt(String source) {
		return verificationCodeEncrypt(source, DEFUALT_PASSWORD_SALT);
	}
	
	/**
	 * 给验证码加密并加入当前时间
	 * @param source 被加密的验证码
	 * @param passwordSalt 加密的盐值
	 * @return 加密后的验证码(带当前时间)
	 */
	public static String verificationCodeEncrypt(String source, String passwordSalt) {
		source = DateTimeUtils.format(new Date(), DATE_FORMAT) + source;
		return EncodeDecodeUtils.encryptDes(source, passwordSalt);
	}
	
	
	public static void checkVerificationCode(String source, String encryptSource) throws ParseException {
		checkVerificationCode(source, encryptSource, DEFUALT_PASSWORD_SALT, DEFUALT_EXPIRED_TIME);
	}
	
	public static void checkVerificationCode(String source, String encryptSource, long expiredTime) throws ParseException {
		checkVerificationCode(source, encryptSource, DEFUALT_PASSWORD_SALT, expiredTime);
	}
	
	/**
	 * 验证验证码的是否正确
	 * @param source 被验证的验证码
	 * @param encryptSource 加密的验证码
	 * @param passwordSalt  加密验证码时的盐值
	 * @param expiredTime	验证码的有效时间
	 * @throws ParseException 
	 */
	public static void checkVerificationCode(String source, String encryptSource, String passwordSalt, long expiredTime) throws ParseException {
		ValidateUtils.required(source);
		ValidateUtils.required(encryptSource);
		
		String decodeSource = EncodeDecodeUtils.decryptDes(encryptSource, passwordSalt);
        Date date = DateTimeUtils.parseDate(decodeSource.substring(0, DATE_FORMAT.length()), DATE_FORMAT);
        long time = System.currentTimeMillis() - date.getTime();
        if (expiredTime != 0 && time > expiredTime) {
        	throw new BusinessException(ExceptionType.VERIFICATION_CODE_EXPIRED);
        } 
    	if (!source.equals(decodeSource.substring(DATE_FORMAT.length(), decodeSource.length()))) {
    		throw new BusinessException(ExceptionType.VERIFY_CODE_ERROR);
        }
	}
	
	public static void main(String[] args) throws ParseException, InterruptedException {
		String source = generateVerificationCode();
		System.out.println("您的验证码为： " + source);
		String encryptSource = verificationCodeEncrypt(source);
		System.out.println("您的加密验证码为： " + encryptSource);
		checkVerificationCode(source, encryptSource);
	}
}
