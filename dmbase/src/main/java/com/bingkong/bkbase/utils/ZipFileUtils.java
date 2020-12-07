package com.bingkong.bkbase.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileUtils {
    private static final String TAG = "ZipFileUtils";

    public static String unzip(final String zipFile) {
        LogUtils.logi(TAG, "unzip " + zipFile);
        int endIndex = zipFile.lastIndexOf(".zip");
        if (endIndex < 0) {
            return "";
        }
        String path = zipFile.substring(0, endIndex);
        File fold = new File(path);

        fold.mkdirs();

        FileInputStream inputStream = null;
        ZipInputStream inZip = null;
        FileOutputStream out = null;
        try {
            inputStream = new FileInputStream(zipFile);
            inZip = new ZipInputStream(inputStream);
            ZipEntry zipEntry;
            String szName = "";
            while ((zipEntry = inZip.getNextEntry()) != null) {
                szName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    szName = szName.substring(0, szName.length() - 1);
                    File folder = new File(path + File.separator + szName);
                    folder.mkdirs();
                } else {
                    //make sure mkdirs.
                    int index = szName.lastIndexOf('/');
                    if (index > 0) {
                        File dir = new File(path + File.separator + szName.substring(0, index));
                        dir.mkdirs();
                    }
                    //create files
                    File file = new File(path + File.separator + szName);
                    if (file.exists()) {
                        file.delete();
                    }

                    boolean isSuccess = file.createNewFile();
                    if (!isSuccess) {
                        continue;
                    }

                    // get the output stream of the file
                    out = new FileOutputStream(file);
                    int len;
                    byte[] buffer = new byte[1024];
                    while ((len = inZip.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                        out.flush();
                    }
                    close(out);
                }
            }
            LogUtils.logi(TAG, "unzip file successfully!");
        } catch (Exception e) {
            LogUtils.loge(TAG, "unzip file failed: " + e.getMessage());
        } finally {
            close(out);
            close(inZip);
            close(inputStream);
        }

        return path;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Compress buffer data into a zip file.
     * @param  jsonString   the targe text to been zipped.
     * return: If operation finished successfully,
     *         true:  success
     *         false: failure
     ****/
    public static String compressAndSaveToFile(String jsonString, Context context) {
        try {
            //create a temp file.
            String tempFilename = "design.json";
            File tempOutputFile = new File(context.getFilesDir(), tempFilename);
            tempOutputFile.delete();
            Writer write = new OutputStreamWriter(new FileOutputStream(tempOutputFile), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
            String outFilename = "design.zip";
            String zipFileFullPath = ZipToFile(tempOutputFile.getName(), outFilename, context);
            tempOutputFile.delete();
            return zipFileFullPath;
        } catch (Exception e) {
            LogUtils.logd("error when zip " + e.getMessage());
            return "";
        }
    }

    public static String ZipToFile(String inputFileName, String outputFileName, Context context) {
        try {
            File inputFile = new File(context.getFilesDir(), inputFileName);
            File outputFile = new File(context.getFilesDir(), outputFileName);
            ZipOutputStream zipOutputStream = new ZipOutputStream(
                    new CheckedOutputStream(new FileOutputStream(outputFile), new CRC32()));
            zipOutputStream.putNextEntry(new ZipEntry(inputFile.getName()));
            FileInputStream input = new FileInputStream(inputFile);
            int temp = 0;
            while ((temp = input.read()) != -1) {
                zipOutputStream.write(temp);
            }
            input.close();
            zipOutputStream.close();

            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            LogUtils.logd(TAG, "zip file meet error" + e.getMessage());
            return "";
        }
    }

    /**
     * delete file
     *
     * @param sPath
     * @return
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return flag;
    }

    public static <T> String strToJson(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String getFilePath(String fileName) {
        return getDirectory() + fileName + ".json";
    }

    public static String getDirectory() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }

    /**
     * return the json String of a object.
     */
    private static <T> String getJsonString(T object) {
        boolean isCreateJson = true;
        String jsonString = strToJson(object);
        //LogUtils.loge("createFile============>" + jsonString);
        if (TextUtils.isEmpty(jsonString)) {
            return "";
        }
        try {
            if (jsonString.indexOf("'") != -1) {
                //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("'", "\\'");
            }
            if (jsonString.indexOf("\"") != -1) {
                //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("\"", "\\\"");
            }

            if (jsonString.indexOf("\r\n") != -1) {
                //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
                jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");
            }
            if (jsonString.indexOf("\n") != -1) {
                //将换行转换一下，因为JSON串中字符串不能出现显式的换行
                jsonString = jsonString.replaceAll("\n", "\\u000a");
            }
            // 格式化json字符串
            jsonString = toPrettyFormat(jsonString);
            // 将格式化后的字符串写入文件
            return jsonString;
        } catch (Exception e) {
            isCreateJson = false;
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Gson formatting
     *
     * @param json
     * @return
     */
    public static String toPrettyFormat(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }
}
