package com.bingkong.bkbase.upload.Listener;

public interface UploadListener<T> {

    void succeeful(T data, int type);

    void error(String errMsg);
}
