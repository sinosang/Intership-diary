package com.mp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mp.config.Privacy.PrivacyEncrypt;
import com.mp.config.Privacy.PrivacyTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : zzy
 * @date : 2023/5/5 16:38
 */

@Data
public class Question {
    @JsonProperty("question_id")
    private String id;
    private String paper;
    private String subject;
    private String value;
    @JsonFormat(pattern = "yyyy年MM月dd日")    //格式化数据字段
    private LocalDateTime updateTime;
    @JsonProperty("修改者")
    private int updateBy;
}
