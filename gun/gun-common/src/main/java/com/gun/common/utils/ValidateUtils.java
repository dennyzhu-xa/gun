package com.gun.common.utils;

import java.util.Collection;

import com.gun.common.exception.BusinessException;
import com.gun.common.pojo.ExceptionType;

public class ValidateUtils {

	public static void required(Object o){
		if (o == null || o.toString().length() == 0){
			throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
		}
	}
	
	public static void required(Object o, String message){
		if (o == null || o.toString().length() == 0){
			throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL, message);
		}
	}
	
	public static void required(Collection<?> o){
		if (o == null || o.size() == 0){
			throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
		}
	}

	public static void checkPhoneNo(String phoneNo) {
		
	}

	public static void text(String parameter) {
		pattern(parameter, "^[_A-z0-9]{6,36}$");
	}
	
	public static void email(String parameter) {
        pattern(parameter, "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$");
//        pattern(parameter, "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }
	
	public static void email(String parameter, String message) {
        pattern(parameter, "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$", message);
    }
	
	public static void match(String parameter1, String parameter2) {
        required(parameter1);
        required(parameter2);
        if (!parameter1.equals(parameter2)) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }
	
	public static void pattern(String parameter, String pattern) {
        if (!parameter.matches(pattern)) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL);
        }
    }
	
	public static void pattern(String parameter, String pattern, String message) {
        if (!parameter.matches(pattern)) {
            throw new BusinessException(ExceptionType.PARAMETER_ILLEGAL, message);
        }
    }
	
	/**
	 * Purpose: 专用于判断缅甸字母是否在给定的字母范围之内，只支持一个和两个连续的缅甸字母
	 * @author KevinShen
	 * @param startLetter
	 * @param endLetter
	 * @param containLetter
	 * @return boolean
	 */
	public static boolean burmaLetterContains(String startLetter, String endLetter, String containLetter){
		int startLetterNum = unincodeToInt(startLetter);
		int endLetterNum = unincodeToInt(endLetter);
		int containLetterNum = unincodeToInt(containLetter);
		if (containLetterNum >= startLetterNum && containLetterNum <= endLetterNum){
			return true;
		}
		return false;
	}
	
	public static int unincodeToInt(String letter){
		if (StringUtils.isEmpty(letter)) return 0;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < letter.length(); i++) {
			result.append((int)letter.charAt(i));
		}
		return Integer.parseInt(result.toString());
	}
	
	public static void main(String[] args) {
		System.out.println("ss".split(",")[0]);
	}
}
