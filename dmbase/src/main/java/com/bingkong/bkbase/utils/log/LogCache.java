package com.bingkong.bkbase.utils.log;

import android.os.Process;

import android.text.TextUtils;
import android.util.Log;


import com.blankj.utilcode.util.FileUtils;
import com.bingkong.bkbase.app.bkStockApp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class LogCache {
    private static final String TAG = "LogCache";
    private static ConcurrentLinkedQueue<String> mLogCache = new ConcurrentLinkedQueue<String>();
    private static final int MAX_LENGTH = 40;//40条log，则存到文件一次
    private static final int LIMIT_SIZE = 3 * 1024 * 1024;

    private volatile static LogCache instance;
    private String saveFilePath = "/sdcard/unokiwi/unokiwi.log";
    private ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS, new
            LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "DebugLogCache");
        }
    });

    private boolean isDebug = true;

    public boolean isDebug() {
        return isDebug;
    }


    private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss:SSS");
    private static StringBuffer mStringBuffer = new StringBuffer();


    public static LogCache getInstance() {
        if (instance == null) {
            synchronized (LogCache.class) {
                if (instance == null) {
                    instance = new LogCache();
                }
            }
        }
        return instance;
    }

    public void init() {
        if (!FileUtils.isFileExists(saveFilePath)) {
            saveFilePath = bkStockApp.getContext().getExternalFilesDir("log") + "/unokiwi.log";
        }
        Log.i(TAG, "init, saveFilePath " + saveFilePath);

    }

    public String getFilePath() {
        return saveFilePath;
    }

    /**
     * 设置debug模式
     *
     * @param debug 是否debug
     */
    public void setDebug(boolean debug) {
        this.isDebug = debug;
        Log.i(TAG, "Log cache :" + (isDebug ? "Start!!" : "End!!"));
        if (!isDebug) {
            pollAllCacheToFile();
        }
    }

    /**
     * 将log日志加入cache，cache栈满后持久化
     */
    public void addToCache(String tag, String prior, Object... msg) {
        if (!isDebug) return;
        String log = concatString(msg);
        addToCache(tag, prior, log);
    }

    /**
     * 将log日志加入cache，cache栈满后持久化
     *
     * @param msg 逐条日志
     */
    public void addToCache(String tag, String prior, String msg) {
        if (!isDebug) return;
        if (msg != null) {
            mLogCache.add(buildLog(tag, prior, msg));
        }
        if (mLogCache.size() >= MAX_LENGTH) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < MAX_LENGTH; i++) {
                stringBuffer.append(mLogCache.poll());
            }
            if (stringBuffer.length() != 0) {
                saveLogToFile(stringBuffer);
            }
        }
    }

    /**
     * 将内存cache中的数据清空，全部持久化
     */
    public void pollAllCacheToFile() {
        if (mLogCache.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < mLogCache.size(); i++) {
                stringBuffer.append(mLogCache.poll());
            }
            if (stringBuffer.length() != 0) {
                saveLogToFile(stringBuffer);
            }
        }
    }

    /**
     * 存储日志至文件
     *
     * @param stringBuffer 日志buffer
     */
    private void saveLogToFile(final StringBuffer stringBuffer) {

        if (!TextUtils.isEmpty(saveFilePath)) {
            Log.i(TAG, "Log cache save to file");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    checkFileAndSave(stringBuffer.toString(), saveFilePath);
                }
            });
        }

    }

    private static boolean checkFileAndSave(final String input, final String filePath) {
        if (!FileUtils.isFileExists(filePath)) {
            FileUtils.createFileByDeleteOldFile(filePath);
        }
        if (!FileUtils.isFileExists(filePath)) {
            Log.e(TAG, "fail to create log file");
            return false;
        }
        //文件超过限制，则保留一份，然后重新写
        if (FileUtils.getFileLength(filePath) > LIMIT_SIZE) {
            FileUtils.copyFile(filePath, filePath + ".1", new FileUtils.OnReplaceListener() {
                @Override
                public boolean onReplace() {
                    return true;//replace old file
                }
            });
            FileUtils.createFileByDeleteOldFile(filePath);
        }
        //写入文件
        return input2File(input, filePath);
    }

    private static boolean input2File(final String input, final String filePath) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(input);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 组合log
     */
    private String buildLog(String tag, String prior, String msg) {
        long time = System.currentTimeMillis();
        int pid = Process.myPid();
        int tid = Process.myTid();

        StringBuilder sb = new StringBuilder();
        String logTime = formatter.format(time);
        sb.append(logTime);
        sb.append(" ");
        sb.append(pid);
        sb.append(" ");
        sb.append(tid);
        sb.append(" ");
        sb.append(prior);
        sb.append(" ");
        sb.append(tag);
        sb.append(" ");
        sb.append(msg);
        sb.append("\n");

        return sb.toString();
    }

    /**
     * 拼接log
     */
    private static String concatString(Object... msg) {
        if (msg.length == 0) {
            return "";
        } else if (msg.length == 1) {
            return String.valueOf(msg[0]);
        }
        if (mStringBuffer.length() != 0) {
            mStringBuffer.delete(0, mStringBuffer.length());
        }
        for (Object obj : msg) {
            if (obj != null) {
                mStringBuffer.append(String.valueOf(obj));
            }
        }
        return mStringBuffer.toString();
    }
}
