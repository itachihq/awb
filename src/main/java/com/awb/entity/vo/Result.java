package com.awb.entity.vo;

/**
 * Created by Administrator
 *
 * @Date 2018/2/2 0002
 * @Time 16:26
 */
public class Result {

    private boolean isSuccess = true;
    private String msg;
    private Object data;
    public Result() {
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public Result(boolean isSuccess, Object data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public Result(boolean isSuccess, String msg, Object data) {
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.data = data;
    }

    public Result(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
