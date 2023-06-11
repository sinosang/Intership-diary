package com.mp.Entity;

import com.mp.config.Privacy.PrivacyEncrypt;
import com.mp.config.Privacy.PrivacyTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author : zzy
 * @date : 2023/4/14 14:43
 */
@Data
public class Tea {
    private String id;
    private String username;
    @PrivacyEncrypt( type = PrivacyTypeEnum.PASSWORD)
    private String password;
    private int auth;
    private int del_flag;
}
