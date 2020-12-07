package com.bingkong.bkbase.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Data：2018/10/12-15:28
 * Author: satsuki
 */
public class TextFileUtil {

    //从文件中取值通过split转换成List返回显示
    public List<String> readTextFile(String filePath) {
        List<String> feedTypeList = new ArrayList<>();
        String feedType;
        String[] feedTypes;

        //StringBuffer初始化
        StringBuffer feedTypeStringBuffer = new StringBuffer();
//        feedTypeStringBuffer.append("");
        String lineTxt = null;
        File file = new File(filePath);
        //文件读写会产生异常所以要放在try catch中
        try {
            //判断文件存在
            if (file.isFile() && file.exists()) {
                LogUtils.loge("文件", "存在");
                //读取字节流 utf-8是字符编码方式 可以根据具体情况进行更改
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader bufferedReader = new BufferedReader(read);

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    feedTypeStringBuffer.append(lineTxt);
                    LogUtils.loge("读取的数据：", feedTypeStringBuffer.toString());
                }
                //通过split转换成list返回
                feedType = feedTypeStringBuffer.toString();
                feedTypes = feedType.split(" ");
                for (String feed : feedTypes) {
                    feedTypeList.add(feed);
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return feedTypeList;
    }

    public void writeTextFile(String filePath, List<String> feedTypeList) {
        File file = new File(filePath);
        String feedTypeString = null;
        StringBuffer feedTypeStringBuffer = new StringBuffer();
//        feedTypeStringBuffer.append("");


        try {
            //不存在则创建

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                LogUtils.loge("feedTypeFile", "创建成功");
            }
            //从list转换成带空格的String
            for (String feed : feedTypeList) {
                feedTypeStringBuffer.append(feed);
                feedTypeStringBuffer.append(" ");
            }
            feedTypeString = feedTypeStringBuffer.toString();
            /**
             * java FileOutputStream写入文件时会覆盖原来内容么
             * 看构造方法
             * FileInputStream inOne=new FileInputStream(fileX);这种覆盖
             * FileInputStream inOne=new FileInputStream(fileX，true);这种不覆盖
             */
            //向文件中写入数据 这里采用覆盖写入方式
            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] bytes = feedTypeString.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}