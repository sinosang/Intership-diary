package com.mp.config.Privacy;

/**
 * @author : zzy
 * @date : 2023/4/23 9:55
 * 该文件为数据脱敏的格式，类型
 */
public class PrivacyUtil {
//    隐藏手机号中间4位
    public static String hidePhone (String phone){
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//        保留前三位，保留后四位，中间4位用*替换
    }

//    隐藏密码
    public static String hidePass (String password){
        return password.replaceAll("(\\w)\\w*(\\w)", "$1****$2");
    }

}
