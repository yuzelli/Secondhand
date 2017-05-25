package com.example.yuzelli.secondhand.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机验证工具类
 */
public class LoginUtils {
    /**
     * 验证电话号码是否符合格式
     * @return true or false
     */
    public static boolean isPhoneEnable(String strPhone) {
        boolean b = false;
        if (strPhone.length() == 11) {
            Pattern pattern = null;
            Matcher matcher = null;
            pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
            matcher = pattern.matcher(strPhone);
            b = matcher.matches();
        }
        return b;
    }
}
