package com.gun.common.authorization;

import java.text.ParseException;
import java.util.Date;

import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.EncodeDecodeUtils;

/**
 * 权限帮助类,处理所有与权限相关的内容.
 *
 * @author Xiaolong Zuo
 * @since 1.0.0
 */
public class AuthorizationHelper {

    public static String DATE_FORMAT = "yyyyMMddHHmmss";

    public static long EXPIRED_TIME = 1000L * 60L  * 60L * 24L * 30L;

    public static String encodePassword(String password, String passwordSalt) {
        return EncodeDecodeUtils.encodeByMd5(password + passwordSalt);
    }

    public static String generateToken(String username, String password) {
        return EncodeDecodeUtils.encryptDes(DateTimeUtils.format(new Date(), DATE_FORMAT) + username, password);
    }

    public static boolean isTokenExpired(String token, String password) throws ParseException {
        String source = EncodeDecodeUtils.decryptDes(token, password);
        Date date = DateTimeUtils.parseDate(source.substring(0, DATE_FORMAT.length()), DATE_FORMAT);
        long time = System.currentTimeMillis() - date.getTime();
        if (time > EXPIRED_TIME) {
            return true;
        }
        return false;
    }

}
