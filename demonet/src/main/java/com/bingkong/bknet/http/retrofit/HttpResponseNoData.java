package com.bingkong.bknet.http.retrofit;

import com.google.gson.annotations.SerializedName;

public class HttpResponseNoData {

    /**
     * 描述信息
     */
    @SerializedName("message")
    private String msg;
    /**
     * 状态码
     */
    @SerializedName("code")
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
