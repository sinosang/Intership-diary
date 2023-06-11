package com.mp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mp.config.Privacy.PrivacyEncrypt;
import com.mp.config.Privacy.PrivacyTypeEnum;
import lombok.Data;

/**
 * @author : zzy
 * @date : 2023/4/11 10:00
 */
@Data
public class Stu {
    private String id;
    private String username;
    @PrivacyEncrypt( type = PrivacyTypeEnum.PASSWORD)  //加密
    private String password;
    private String teaId;
    @JsonIgnore //返回Json的时候忽视返回
    private int del_flag;
    @JsonProperty("权限") //改写返回Json是该字段的数据名称
    private int auth;
    private String examSubject;
    private String examPaper;
}
