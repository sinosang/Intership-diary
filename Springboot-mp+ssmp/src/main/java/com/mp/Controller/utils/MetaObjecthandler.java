package com.mp.Controller.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 元数据对象处理器
 * @author : zzy
 * @date : 2023/4/14 15:16
 */
@Component
public class MetaObjecthandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateBy", "1");
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
