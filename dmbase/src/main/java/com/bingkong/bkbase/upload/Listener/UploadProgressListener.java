package com.bingkong.bkbase.upload.Listener;

public interface UploadProgressListener {
    void onProgress(long currentBytesCount, long totalBytesCount, int sum);
}
