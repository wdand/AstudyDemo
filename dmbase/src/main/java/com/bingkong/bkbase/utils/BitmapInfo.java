package com.bingkong.bkbase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;

import com.blankj.utilcode.util.StringUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.TreeSet;

/***
 * This class is used to save the local file information and task.
 *
 **/
public class BitmapInfo {
    private static String TAG = "BitmapInfo";
    public static final int TYPE_PIECE_COMBINED = 0;
    public static final int TYPE_USER_TAKE_PHOTO = 1;
    public static final int TYPE_PREVIEW = 2;
    public static final int TYPE_PENCIL_DOODLE = 3;
    public static final int TYPE_EMBORIDERY = 4;
    public static final int TYPE_MESH_NO_PRODUCTCOLOR = 5;
    public static final int TYPE_TEXTITEM = 6;
    public static final int TYPE_TEST = 7;


    public static final String NAME_RESULT = "_result.png";
    public static final String NAME_PHOTO = "_photo.png";
    public static final String NAME_MESH = "_mesh.png";
    public static final String NAME_PENCIL_DOODLE = "_pencil_doodle.png";
    public static final String NAME_EMBORIDERY = "_embroidery.png";
    public static final String NAME_MESH_NO_PRODUCTCOLOR = "_noproductmesh.png";
    public static final String NAME_TEXT = "_text.png";

    public static final int SAVE_RET_ORGFILE_ERROR = -2;
    public static final int SAVE_RET_ORGFILE_SAVED_BEFORE = 0;
    public static final int SAVE_RET_NEWCOPYED = 1;
    public static final int SAVE_RET_NEW_FILE_EXIST = 2;
    public static final int SAVE_RET_NEW_FILE_CREATED = 3;
    public static final int SAVE_RET_ORGFILE_NOT_EXIST = -3;
    public static final int SAVE_RET_ORGFILE_NOT_SINGLE_FILE = -4;
    public static final int SAVE_RET_ORGFILE_CANNOT_READ = -5;
    public static final int SAVE_RET_UNKOWN = -6;

    public static final int UPLOAD_STATUS_NOTNEED = 0;
    public static final int UPLOAD_STATUS_ONGOING = 1;
    public static final int UPLOAD_STATUS_WAITFEED = 2;//Waitfor other bitmapInfo feed who start upload already
    public static final int UPLOAD_STATUS_FINISHED = 3;

    Bitmap bitmap;
    String sourceFileName = ""; //The filename where the bitmap copied from.
    Context mContext;

    String mediaId;
    String viewCode;
    String zOrder;
    String sha1Val;
    String mLocalFileName;
    String urlInServer;
    String thumbnailUrl;
    boolean needThumbnailWhenUpload = false;
    String mFullPath = "";
    long bitmapWidth = 0;
    long bitmapHeight = 0;
    int orgPicWidth = 0;
    int orgPicHeight = 0;
    boolean recycleAfterSaveToDisk = false;
    boolean bitmapIsReference = false;//to remember if the bitmap is reference from other.
    //If it is reference from other, the clear action is just set the bitmap to null but not recycle.
    long areaPixel;
    BitmapInfo feeder;//When feeder finish uploading, it url and status should sync to this.
    int type;

    public int getOrgPicWidth() {
        return orgPicWidth;
    }

    public void setOrgPicWidth(int orgPicWidth) {
        this.orgPicWidth = orgPicWidth;
    }

    public int getOrgPicHeight() {
        return orgPicHeight;
    }

    public void setOrgPicHeight(int orgPicHeight) {
        this.orgPicHeight = orgPicHeight;
    }

    public BitmapInfo(Context context, int bitmaptype, boolean isRecycleAfterSaveToDisk) {
        mContext = context;
        type = bitmaptype;
        setRecycleAfterSaveToDisk(isRecycleAfterSaveToDisk);
        switch (bitmaptype) {
            case BitmapInfo.TYPE_PREVIEW:
                needThumbnailWhenUpload = true;
                break;
            case BitmapInfo.TYPE_EMBORIDERY:
            case TYPE_USER_TAKE_PHOTO:
            case TYPE_PENCIL_DOODLE:
            case TYPE_TEXTITEM:
                ;
                setBitmapIsReference(true);
                break;
            case TYPE_MESH_NO_PRODUCTCOLOR:
                break;
        }
    }


    public void setSha1Val(String sha1Val) {
        if (!CommonUtil.strIsEmpty((sha1Val))) {
            this.sha1Val = sha1Val.toUpperCase();
        }
        this.sha1Val = sha1Val;
    }

    public boolean isBitmapIsReference() {
        return bitmapIsReference;
    }

    public void setBitmapIsReference(boolean bitmapIsReference) {
        this.bitmapIsReference = bitmapIsReference;
    }

    public boolean needThumbnailWhenUpload(String productPreviewViewCode) {
        boolean needThumbnail = false;
        if ((getType() == BitmapInfo.TYPE_PREVIEW) &&
                (!StringUtils.isEmpty(getViewCode())) &&
                (!StringUtils.isEmpty(productPreviewViewCode))) {
            if (getViewCode().equals(productPreviewViewCode)) {
                needThumbnail = true;
            }
        }
        return needThumbnail;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public boolean isNeedThumbnailWhenUpload() {
        return needThumbnailWhenUpload;
    }

    public void setNeedThumbnailWhenUpload(boolean needThumbnailWhenUpload) {
        this.needThumbnailWhenUpload = needThumbnailWhenUpload;
    }

    public long getAreaPixel() {
        return areaPixel;
    }

    public void setAreaPixel(long areaPixel) {
        this.areaPixel = areaPixel;
    }


    public long getBitmapWidth() {
        return bitmapWidth;
    }

    public void setBitmapWidth(long bitmapWidth) {
        this.bitmapWidth = bitmapWidth;
    }

    public long getBitmapHeight() {
        return bitmapHeight;
    }

    public void setBitmapHeight(long bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }


    public String getzOrder() {
        return zOrder;
    }

    public void setzOrder(String zOrder) {
        this.zOrder = zOrder;
    }

    public boolean isDeleteBitmapAfterUpload() {
        return deleteBitmapAfterUpload;
    }

    public void setDeleteBitmapAfterUpload(boolean deleteBitmapAfterUpload) {
        this.deleteBitmapAfterUpload = deleteBitmapAfterUpload;
    }

    boolean deleteBitmapAfterUpload = true;

    public boolean isRecycleAfterSaveToDisk() {
        return recycleAfterSaveToDisk;
    }

    public void setRecycleAfterSaveToDisk(boolean recycleAfterSaveToDisk) {
        this.recycleAfterSaveToDisk = recycleAfterSaveToDisk;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public BitmapInfo getFeeder() {
        return feeder;
    }

    public void setFeeder(BitmapInfo feeder) {
        this.feeder = feeder;
    }


    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int flag) {
        this.uploadStatus = flag;
    }

    int uploadStatus = UPLOAD_STATUS_NOTNEED;

    public String getFileName() {
        return mergeFileName(sha1Val, type);
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }


    public int getType() {
        return type;
    }

    public String getGroup() {
        String group;
        switch (type) {
            case TYPE_USER_TAKE_PHOTO:
                group = "private";
                break;
            default:
                group = "public";
                break;
        }
        return group;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getSha1val() {
        return sha1Val;
    }

    public String getLocalFullName() {
        return mFullPath;
    }

    public String getUrlInServer() {
        return urlInServer;
    }

    public void setUrlInServer(String urlInServer) {
        this.urlInServer = urlInServer;
        if (isDeleteBitmapAfterUpload()) {
            deletePermanent(mLocalFileName);
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public static byte[] bitmap2Byte(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        }
        return null;
    }

    public static byte[] bitmap2Byte(Bitmap bitmap, int quality) {
        if (bitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, baos);
            return baos.toByteArray();
        }
        return null;
    }

    /**
     * get the bitmap's draw area
     *
     * @param bitmap
     * @return the draw pixel count
     */
    public static long getPathPixelCountInBitmap(Bitmap bitmap) {
        LogUtils.logi("BitmapUtil", "  getPathPixelCountInBitmap bitmap == " + bitmap);
        long beginTime = System.currentTimeMillis();
        if ((bitmap != null) && (!bitmap.isRecycled())) {
            long transparentCount = 0;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            for (int w = 0; w < width; w++) {
                for (int h = 0; h < height; h++) {
                    int color = bitmap.getPixel(w, h);
                    int r = Color.red(color);
                    int g = Color.green(color);
                    int b = Color.blue(color);
                    int a = Color.alpha(color);
                    if (r == 0 && g == 0 && b == 0 && a == 0) {
                        transparentCount++;
                    }
                }
            }
            LogUtils.logi("BitmapUtil", " width == " + width + "  height == " + height + " transparentCount ==  " +
                    transparentCount + "  waste time " + (System.currentTimeMillis() - beginTime) + "  transparent percent == " + (float) ((float) transparentCount / (float) (width * height)));
            return (width * height - transparentCount);
        }
        return 0;
    }

    public void recycleBitmap() {
        if (bitmap != null && !bitmap.isRecycled()) {
            if (type == BitmapInfo.TYPE_EMBORIDERY) {
                bitmapWidth = bitmap.getWidth();
                bitmapHeight = bitmap.getHeight();
                areaPixel = getPathPixelCountInBitmap(bitmap);
            }
            bitmap.recycle();
            bitmap = null;
        }
    }

    /***-----------------------------------------------------
     ***  recycle the bitmap and delete the file saved in disk.
     ***/
    public void clear() {
        LogUtils.logd(TAG, "clear: filedebug clear");
        if ((sha1Val == null) || (TextUtils.isEmpty(sha1Val))) {
            return;
        }
        try {
            setUrlInServer("");
            deletePermanent(mLocalFileName);
            if (bitmap != null) {
                if (!isBitmapIsReference()) {
                    //For the referenced bitmap, it cannot been recycled since
                    // it may be used in other place.
                    bitmap.recycle();
                }
                bitmap = null;
            }
            setSourceFileName("");
        } catch (Exception e) {
            LogUtils.loge(TAG, "clear: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /************
     * This function will generate sha1 value and save the bitmap to disk.
     * Before saving bitmap to disk, this function will delete old bitmap data.
     ***********/
    public int saveBitmap(int bitmapType, Bitmap bitmap) {
        if (bitmap == null) {
            return SAVE_RET_UNKOWN;
        }
        this.bitmap = bitmap;
        try {
            String outputFileName = CommentTime.getStringDateHourMin() + "_" + Math.random() * 100;
            File outputFile = new File(mContext.getFilesDir(), outputFileName);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
            mFullPath = outputFile.getAbsolutePath();
            sha1Val = CommonUtil.getSHA1(mFullPath);
            deletePermanent(mLocalFileName);
            mLocalFileName = mergeFileName(sha1Val, type);

            File finalFile = new File(mContext.getFilesDir(), mergeFileName(sha1Val, bitmapType));
            mFullPath = finalFile.getAbsolutePath();
            LogUtils.logd(TAG, "saveBitmap: filedebug name from " + outputFile.getName() + " to " + finalFile.getName());
            outputFile.renameTo(finalFile);
            if (recycleAfterSaveToDisk && (!isBitmapIsReference())) {
                recycleBitmap();
            }
            return SAVE_RET_NEW_FILE_CREATED;
        } catch (Exception e) {
            LogUtils.loge(TAG, "setBitmap: filedebug ERROR" + e.getMessage());
            e.printStackTrace();
            deletePermanent(mLocalFileName);
            sha1Val = "";
            return SAVE_RET_UNKOWN;
        }
    }


    /*****************************************************************************************
     * This function will generate sha1 value and save the bitmap to disk.
     * Before saving bitmap to disk, this function will delete old bitmap data.
     * return
     *****************************************************************************************/
    public int saveBitmapFileByCopy(String bitmapFullPath) {
        try {
            /***
             * First to check if the shalVal is same as old one.
             * If it is same as old one, will return directly.
             ****/
            String newSHAl = CommonUtil.getSHA1(bitmapFullPath);
            if (newSHAl.equals(sha1Val)) {
                return SAVE_RET_ORGFILE_SAVED_BEFORE;
            }
            /**
             * before updating sha1Val , need remove the old data in disk.
             ***/
            sha1Val = newSHAl;
            deletePermanent(mLocalFileName);
            mLocalFileName = mergeFileName(sha1Val, type);
            sourceFileName = bitmapFullPath;
            int ret = copyAndChangeName(mLocalFileName, sourceFileName);
            if (recycleAfterSaveToDisk) {
                recycleBitmap();
            }
            return ret;
        } catch (Exception e) {
            LogUtils.loge(TAG, "setBitmap: ERROR" + e.getMessage());
            e.printStackTrace();
            return SAVE_RET_UNKOWN;
        }
    }

    public String mergeFileName(String sha1, int type) {
        //using "" now, all the pictures will been saved with sha1 as name.
        if ((sha1 == null) || (sha1.isEmpty())) {
            return null;
        }
        return sha1 + getExtention(type);
    }

    public static String getKey(String mediaId, String viewCode, String zOrder) {
        String key = mediaId + "#" + viewCode;
        if (!CommonUtil.strIsEmpty(zOrder)) {
            key = key + "#" + zOrder;
        }
        return key;
    }

    /****
     ** With the file name + extension, we can know what kind of file had been uploaded.
     ** And what kind of mediaId and viewCode the uploaded file belong to.
     * The name format should be mediaId+"#"+viewcode+"_"+Const.NAME_xxxxx
     * Const.Name_XXXX will be used to create picture property when preparing
     * form data of createDesign/updateDesignto interface.
     ****/
    public String getUploadRetrieveName() {
        String name = mediaId + "#" + viewCode;
        if (!StringUtils.isEmpty(zOrder)) {
            name = name + "#" + zOrder;
        }
        switch (type) {
            case TYPE_MESH_NO_PRODUCTCOLOR:
                name = name + NAME_MESH_NO_PRODUCTCOLOR;
                break;
            case TYPE_PIECE_COMBINED:
                name = name + NAME_RESULT;
                break;
            case TYPE_PREVIEW:
                name = name + NAME_MESH;
                break;
            case TYPE_USER_TAKE_PHOTO:
                name = name + NAME_PHOTO;
                break;
            case TYPE_PENCIL_DOODLE:
                name = name + NAME_PENCIL_DOODLE;
                break;
            case TYPE_EMBORIDERY:
                name = name + NAME_EMBORIDERY;
                break;
            case TYPE_TEXTITEM:
                name = name + NAME_TEXT;
                break;
        }
        return name;
    }

    private String mCustomizeExtentsion = "test";

    public String getExtention(int type) {
        String extention = ".bitmap" + type;
        if (type == TYPE_TEST) {
            extention = mCustomizeExtentsion + extention;
        }
        return extention;
    }

    /**
     * @param ctx
     * @return all the files with same bitmap type.
     */
    public String[] getBitmapDataFiles(Context ctx, final int fileType) {
        File filesDir = ctx.getFilesDir();
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(getExtention(fileType));
            }
        };
        return filesDir.list(filter);
    }

    /***
     * Delete the saved bitmap in disk of the type.
     * **/
    public void deleteAllMyFileType(Context context, int filetype) {
        String[] bitmapDataFiles = getBitmapDataFiles(context, filetype);
        if (bitmapDataFiles != null && bitmapDataFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(bitmapDataFiles));
            for (String fileName : sortedFiles) {
                File bitmap = new File(context.getFilesDir(), fileName);
                LogUtils.logd(TAG, "deleteAllMyFileType: filedebug delete file " + fileName);
                bitmap.delete();
            }
        }
        return;
    }

    /***
     * Delete the saved bitmap in disk.
     * **/
    private void deletePermanent(String deleteFileName) {
        if ((deleteFileName == null) || (TextUtils.isEmpty(deleteFileName))) {
            return;
        }
        String[] bitmapDataFiles = getBitmapDataFiles(mContext, getType());
        if (bitmapDataFiles != null && bitmapDataFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(bitmapDataFiles));
            for (String fileName : sortedFiles) {
                if (fileName.equals(deleteFileName)) {
                    File bitmap = new File(mContext.getFilesDir(), fileName);
                    LogUtils.logd(TAG, "deletePermanent: filedebug delete file " + fileName);
                    bitmap.delete();
                }
            }
        }
        return;
    }

    /***
     **
     ***/
    private void savePermanent(String fullname, byte[] bitmapContent) {
        try {
            String fileName = fullname;
            File file = new File(mContext.getFilesDir(), fileName);
            if (file != null && file.exists()) {
                return;
            }
            mFullPath = file.getAbsolutePath();
            FileOutputStream bitmapOutput = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            bitmapOutput.write(bitmapContent);
            bitmapOutput.close();
        } catch (Exception e) {
            LogUtils.loge(TAG, "savePermanent: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /****
     * Copy a single file
     *
     * @param oldPathName String Origin+fileName:data/user/0/com.test/files/abc.txt
     * @param newPathName String newPath+newfileName 如：data/user/0/com.test/cache/abc.txt
     * @return <0 the error REASON;
     *          >0 success with message.
     ****/
    public int copyFile(String oldPathName, String newPathName) {
        try {
            File oldFile = new File(oldPathName);
            if (!oldFile.exists()) {
                LogUtils.loge("--Method--", "copyFile:  oldFile not exist.");
                return SAVE_RET_ORGFILE_NOT_EXIST;
            } else if (!oldFile.isFile()) {
                LogUtils.loge("--Method--", "copyFile:  oldFile not file.");
                return SAVE_RET_ORGFILE_NOT_SINGLE_FILE;
            } else if (!oldFile.canRead()) {
                LogUtils.loge("--Method--", "copyFile:  oldFile cannot read.");
                return SAVE_RET_ORGFILE_CANNOT_READ;
            }

            File newFile = new File(newPathName);
            if (newFile.exists()) {
                return SAVE_RET_NEW_FILE_EXIST;
            }
            FileInputStream fileInputStream = new FileInputStream(oldPathName);
            FileOutputStream fileOutputStream = new FileOutputStream(newPathName);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return SAVE_RET_NEWCOPYED;
        } catch (Exception e) {
            e.printStackTrace();
            return SAVE_RET_UNKOWN;
        }
    }

    /***
     **
     ***/
    private int copyAndChangeName(String newFileName, String oldFullName) {
        try {
            File newFile = new File(mContext.getFilesDir(), newFileName);
            mFullPath = newFile.getAbsolutePath();
            return copyFile(oldFullName, mFullPath);
        } catch (Exception e) {
            LogUtils.loge(TAG, "savePermanent: " + e.getMessage());
            e.printStackTrace();
        }
        return SAVE_RET_UNKOWN;
    }

    /***
     * Delete all the bitmap of the same type.
     ***/
    public void deleteAllPermanent() {
        String[] bitmapDataFiles = getBitmapDataFiles(mContext, getType());
        if (bitmapDataFiles != null && bitmapDataFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(bitmapDataFiles));
            for (String fileName : sortedFiles) {
                File bitmap = new File(mContext.getFilesDir(), fileName);
                bitmap.delete();
            }
        }
        return;
    }
}
