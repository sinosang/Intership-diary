package com.mp.Entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : zzy
 * @date : 2023/5/5 16:40
 */

@Data
public class Paper {
        private String subject;
        private String questionId;
        private String updateTime;
        private String updateBy;
}
