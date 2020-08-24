package com.example.studydemo.clipUserIcon;

import android.os.Environment;

import java.io.File;

public class MyFileUtils {
    public static String getFileName(){
        String fileName  =null;
        long currentTimeMillis = System.currentTimeMillis();
        String pathName = Environment.getExternalStorageDirectory() + "/DCIM/";
        File file = new File(pathName);
        file.mkdirs();
        fileName = pathName + currentTimeMillis+".jpg";

        return fileName;

    }
}