package com.bingkong.bkbase.upload.Listener;

public interface UpdateProgressListener {

    void onProgress(long currentBytesCount, long totalBytesCount, int sum, int index);
}
