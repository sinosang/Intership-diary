package com.mp.Controller.utils;

//用于统一前后端数据协议
//设计统一的返回值结果类型，便于前端开发读取数据
public class R {
    private Boolean flag;
    private Object data;

    public R(){}

    public R(Boolean flag){
        this.flag=flag;
    }
    public R(Boolean flag,Object data){
        this.flag=flag;
        this.data=data;
    }
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
    public Boolean getFlag() {
        return flag;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Object getData() {
        return data;
    }
}
