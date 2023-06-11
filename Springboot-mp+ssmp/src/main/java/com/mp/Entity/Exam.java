package com.mp.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

/**
 * @author : zzy
 * @date : 2023/4/14 14:59
 */
public class Exam {
    private String id;
    private String subject;
    private String teaId;
    private String updateTime;
    private String updateBy;
    private String examTime;
}
