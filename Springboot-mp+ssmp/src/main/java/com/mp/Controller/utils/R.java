package com.mp.Controller.utils;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

//用于统一前后端数据协议
//设计统一的返回值结果类型，便于前端开发读取数据
@Data
public class R<T> {

    private Integer code; //编码成功与否

    private String msg;  // 错误信息

    private T data;  // 数据

    private Map map = new HashMap(); //动态数据

    //    响应成功时
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.msg = "请求成功";
        r.code = 200;
        return r;
    }

    //    响应失败时
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.data = msg;
        r.code = 400;
        r.msg = "请求失败";
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
