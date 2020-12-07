package com.bingkong.bkbase.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.bingkong.bknet.http.retrofit.TokenManager;
import com.blankj.utilcode.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GenerateUUID {
    private static String TAG = GenerateUUID.class.getSimpleName();
    private String PATH;
    private static String UUID_UNOKIWI = null;
    private static GenerateUUID generateUUID = new GenerateUUID();

    public GenerateUUID() {
        if (!StringUtils.isEmpty(TokenManager.getInstance().getUUIDRootPath())) {
            PATH = TokenManager.getInstance().getUUIDRootPath();
        } else {
            PATH = Environment.getExternalStorageDirectory() + "/Android/data/com.android.unokiwi.uuid/";
        }
    }

    public static GenerateUUID getInstance() {
        return generateUUID;
    }

    public boolean checkIfDefaultPathChangedAndResave(Context context) {
        String savedPath = TokenManager.getInstance().getUUIDRootPath();
        if (!isUUIDExist()) return false;
        String newPath = Environment.getExternalStorageDirectory() + "/Android/data/com.android.unokiwi.uuid/";
        if (newPath.equals(TokenManager.getInstance().getUUIDRootPath())) {
            return false;
        }
        try {
            File file = new File(PATH);
            if (!file.exists()) {
                file.mkdir();
            }
            String UUID = getUUID(context);
            if (!TextUtils.isEmpty(UUID)) {
                ArrayList<String> list1 = new ArrayList<>();
                list1.add(UUID);
                TokenManager.getInstance().setUUIDRootPath(PATH);
                TextFileUtil fileUtil = new TextFileUtil();
                LogUtils.logd(TAG, "checkPath:  isUUIDExist write UUID to PATH!!");
                fileUtil.writeTextFile(PATH + "/uuid", list1);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUUIDExist() {
        if (!TextUtils.isEmpty(UUID_UNOKIWI)) {
            LogUtils.logd(TAG, "isUUIDExist: return true 111 UUID_UNOKIWI  path is" + PATH + " UUID " + UUID_UNOKIWI);
            return true;
        }
        try {
            File file = new File(PATH);
            if (!file.exists()) {
                return false;
            }
            TextFileUtil fileUtil = new TextFileUtil();
            List<String> list = fileUtil.readTextFile(PATH + "/uuid");
            if (list != null && list.size() > 0) {
                String savedUUID = list.get(0);
                if (!TextUtils.isEmpty(savedUUID)) {
                    LogUtils.logd(TAG, "isUUIDExist: return true 222");
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public String getUUID(Context context) {

        if (!TextUtils.isEmpty(UUID_UNOKIWI)) {
            return UUID_UNOKIWI;
        }
        File file = new File(PATH);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        TextFileUtil fileUtil = new TextFileUtil();
        List<String> list = fileUtil.readTextFile(PATH + "/uuid");
        if (list != null && list.size() > 0) {
            UUID_UNOKIWI = list.get(0);
        }
        if (TextUtils.isEmpty(UUID_UNOKIWI)) {
            UUID_UNOKIWI = UUID.randomUUID().toString();
            ArrayList<String> list1 = new ArrayList<>();
            list1.add(UUID_UNOKIWI);
            TokenManager.getInstance().setUUIDRootPath(PATH);
            LogUtils.logd(TAG, "getUUID:  isUUIDExist write UUID to PATH!!");
            fileUtil.writeTextFile(PATH + "/uuid", list1);
        }
        return UUID_UNOKIWI;
    }
}
