package com.gun.common.utils;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Purpose: des加密、md5加密、cb-bank加密
 * @author KevinShen
 * @since  JDK 1.6
 * @date   2017年10月25日
 * @MaintenancePersonnel KevinShen
 */
public class EncodeDecodeUtils {

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String bytesToHex(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int integer = bytes[i] & 0xFF;
            String hexString = Integer.toHexString(integer);
            if (hexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hexString);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexToBytes(String hex) {
        if (hex == null || hex.equals("")) {
            return null;
        }
        hex = hex.toUpperCase();
        int length = hex.length() / 2;
        char[] hexChars = hex.toCharArray();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return bytes;
    }

    public static String encodeBase64(byte[] bytes) {
    	return Base64.encodeBase64String(bytes);
//        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decodeBase64(String source) {
    	return Base64.decodeBase64(source);
//        return Base64.getDecoder().decode(source);
    }

    public static String encryptDes(String source, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(StringUtils.toBytes(password));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            return bytesToHex(cipher.doFinal(StringUtils.toBytes(source)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptDes(String source, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(StringUtils.toBytes(password));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            return StringUtils.fromBytes(cipher.doFinal(hexToBytes(source)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeByMd5(String s) {
        return encodeByMd5(s.getBytes());
    }

    public static String encodeByMd5(byte[] bytes) {
        MessageDigest messageDigest;
        String result;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            result = encodeBase64(messageDigest.digest(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    /**
     * Purpose: 使用PBKDF2WithHmacSHA1方式对密钥进行加密
     * @author KevinShen
     * @param password 要加密的密钥
     * @param salt		盐值
     * @param count		加密次数
     * @param keyLength 加密长度
     * @throws GeneralSecurityException 异常
     * @return byte[] 加密结果
     */
    public static byte[] pbkdf2(String password, byte[] salt, int count, int keyLength) throws GeneralSecurityException{  
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, count, keyLength * 8);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return f.generateSecret(spec).getEncoded();  
    }  
    
    /**
     * Purpose: CB-Bank 资料加密
     * @author KevinShen
     * @param message 信息
     * @param passKey 密钥
     * @return String 加密结果
     */
    public static String encrypt128(String message, String passKey){
		try {
			byte[] mySalt = decodeBase64("rIkZOJxkiNNUQ2AKGG9wiQM=");
			byte[] dev = pbkdf2(passKey, mySalt, 1024, 48);
			byte[] key = Arrays.copyOf(dev, 16);
			byte[] vector = decodeBase64("TZuWY0W5Yn9l9F2DEiU0hg==");
			IvParameterSpec iv = new IvParameterSpec(vector);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(message.getBytes());
			return encodeBase64(encrypted);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Purpose: CB-Bank 资料解密
     * @author KevinShen
     * @param message 信息
     * @param passKey 密钥
     * @return String 解密结果
     */
    public static String decrypt128(String message, String passKey){
    	try {
			byte[] mySalt = decodeBase64("rIkZOJxkiNNUQ2AKGG9wiQM=");
			byte[] dev = pbkdf2(passKey, mySalt, 1024, 48);
			byte[] key = Arrays.copyOf(dev, 16);
			byte[] vector = decodeBase64("TZuWY0W5Yn9l9F2DEiU0hg==");
			IvParameterSpec iv = new IvParameterSpec(vector);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(decodeBase64(message));
			return new String(original);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
    	System.out.println(decryptDes("fca2b00a4f4495d2839de3b94f063916d98a228c5473e2b69d1a8f8b81ecf97df95180ccb7f7e509c86786e3f3f9942628f82e57b6234defc13a0b0ca54a294fbb0d1a7cfc9907cb", LotteryConstants.LOTTERY));
    	System.out.println(encryptDes("jdbc:sqlserver://192.168.93.123:1433;databaseName=LOTTERY", LotteryConstants.LOTTERY));
    	System.out.println(encryptDes("jdbc:sqlserver://moeyanlottery.database.windows.net:1433;databaseName=LOTTERY", LotteryConstants.LOTTERY));
		System.out.println(encryptDes("moeyan", LotteryConstants.LOTTERY));
		System.out.println(encryptDes("lotterym0ey@n", LotteryConstants.LOTTERY));
		System.out.println(encryptDes("asd123$%^", LotteryConstants.LOTTERY));
	}
    
}
