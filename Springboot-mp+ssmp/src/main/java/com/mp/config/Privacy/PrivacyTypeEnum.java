package com.mp.config.Privacy;

import lombok.Getter;

/**
 * @author : zzy
 * @date : 2023/4/23 9:52
 * 该文件为需要进行数据脱敏的对象
 */

@Getter
public enum PrivacyTypeEnum {
//    密码
    PASSWORD,

//    手机号
    PHONE,

//    邮箱
    EMAIL,

}
