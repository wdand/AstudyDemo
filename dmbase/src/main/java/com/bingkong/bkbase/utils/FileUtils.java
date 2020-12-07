package com.bingkong.bkbase.utils;


import android.os.Environment;

import java.io.File;

public class FileUtils {
    private static final String TAG = "FileUtils";

    // Create an image file name
    public static String getFileName() {
        String fileName = null;
        long currentTimeMillis = System.currentTimeMillis();
        String pathName = Environment.getExternalStorageDirectory() + "/DCIM/";
        File file = new File(pathName);
        file.mkdirs();
        fileName = pathName + currentTimeMillis + ".jpg";

        return fileName;

    }

    public static boolean createFileDir(String filePath) {
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                return dir.mkdirs();
            }
            return true;
        } catch (Exception x) {
            return false;
        }
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        return deleteFile(file);
    }

    public static boolean deleteFile(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (!file.exists()) {
                return false;
            }

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    deleteFile(f);
                }
            }
            file.delete();
            return true;
        } catch (Exception e) {
            LogUtils.loge(TAG, "deleteFile: " + e.getMessage());
            return false;
        }
    }
}
