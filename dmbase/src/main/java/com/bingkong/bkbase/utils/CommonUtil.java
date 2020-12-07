package com.bingkong.bkbase.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.bingkong.bkbase.R;
import com.bingkong.bknet.http.retrofit.TokenManager;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jst
 */
public class CommonUtil {

    public static boolean strIsEmpty(String s) {
        return null == s || "".equals(s);
    }

    /**
     * Try to return the absolute file path from the given Uri  兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /*--------------------------------------------------------------------------------------
    * Get file name from full path such as file:///android_asset/emboridery/006/msa_3.1.png
    ---------------------------------------------------------------------------------------* */
    public static String getFileName(String namewithpath) {
        if (namewithpath == null) {
            return "";
        }
        int start = namewithpath.lastIndexOf("/");
        int end = namewithpath.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return namewithpath.substring(start + 1, end);
        } else {
            return null;
        }
    }


    /**
     * Lower case Hex Digits.
     */
    private static final String HEX_DIGITS = "0123456789abcdef";

    /**
     * Byte mask.
     */
    private static final int BYTE_MSK = 0xFF;

    /**
     * Hex digit mask.
     */
    private static final int HEX_DIGIT_MASK = 0xF;

    /**
     * Number of bits per Hex digit (4).
     */
    private static final int HEX_DIGIT_BITS = 4;

    /**
     * Compute a String in HexDigit from the input.
     *
     * @param byteArray a row byte array
     * @return a hex String
     */
    public static String toHexString(final byte[] byteArray) {
        StringBuilder sb = new StringBuilder(byteArray.length * 2);
        for (int i = 0; i < byteArray.length; i++) {
            int b = byteArray[i] & BYTE_MSK;
            sb.append(HEX_DIGITS.charAt(b >>> HEX_DIGIT_BITS)).append(
                    HEX_DIGITS.charAt(b & HEX_DIGIT_MASK));
        }
        return sb.toString();
    }


    public static String getSHA1(String path) {
        String pathName = path;
        String sha1 = "";
        try {
            File file = new File(pathName);
            FileInputStream ins = new FileInputStream(file);
            FileChannel ch = ins.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            sha.update(byteBuffer);
            ins.close();
            sha1 = byteHex(sha.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    public static String byteHex(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;

    }

    public static boolean isWorkOnProductServer(Context context) {
        String serverUrl = TokenManager.getInstance().getSpBaseUrl();
        if (context == null) {
            if (serverUrl.contains("api.unokiwi.com")) {
                return true;
            } else {
                return false;
            }
        }
        if (serverUrl.contains("https://api.unokiwi.com")) {
            return true;
        }
        return false;
    }
}

