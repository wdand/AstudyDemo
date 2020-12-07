package com.bingkong.bkbase.utils;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.text.format.Time;
import android.util.Log;

import com.bingkong.bknet.http.Api.CommonApi;
import com.bingkong.bknet.http.Api.ServiceManager;
import com.bingkong.bknet.http.retrofit.HttpResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.System.exit;

public class CrashRemoteHandle implements Thread.UncaughtExceptionHandler {
    /**
     * Debug Log tag
     */
    public static final String TAG = "CrashHandler";
    /**
     * 是否开启日志输出,在Debug状态下开启,
     * 在Release状态下关闭以提示程序性能
     */
    public static final boolean DEBUG = true;
    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * CrashHandler实例
     */
    private static CrashRemoteHandle INSTANCE;
    /**
     * 程序的Context对象
     */
    private Context mContext;
    /**
     * 使用Properties来保存设备的信息和错误堆栈信息
     */
    private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = "_crdebug.txt";

    private static Object syncRoot = new Object();

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashRemoteHandle() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashRemoteHandle getInstance() {
       /* if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;*/
        // 防止多线程访问安全，这里使用了双重锁
        if (INSTANCE == null) {

            synchronized (syncRoot) {

                if (INSTANCE == null) {
                    INSTANCE = new CrashRemoteHandle();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     *
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            //Sleep一会后结束程序
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                LogUtils.loge(TAG, "Error : " + e.getLocalizedMessage());
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            Log.w(TAG, "handleException --- ex==null");
            return true;
        }
        final String msg = ex.getLocalizedMessage();
        if (msg == null) {
            return false;
        }
        /*
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if(DEBUG){
                    LogUtils.logd(TAG, "异常信息->"+msg);
                    Toast toast = Toast.makeText(mContext, "程序出错，即将退出:\r\n" + msg,
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    //保存错误报告文件
                    LogToFile.w("my",msg);//这句话可以先注释掉，这是我单独写的一个log写入类,下面已提供了该类**
                }
//              MsgPrompt.showMsg(mContext, "程序出错啦", msg+"\n点确认退出");
                Looper.loop();
            }
        }.start();
        */
        //收集设备信息
        collectCrashDeviceInfo(mContext);
        //保存错误报告文件
        saveCrashInfoToFile(ex);
        saveCrashToLogCache(ex);
        //发送错误报告到服务器
        sendCrashReportsToServer(mContext);
        return false;
    }

    /**
     * 在程序启动时候, 可以调用该函数来发送以前没有发送的报告
     */
    public void sendPreviousReportsToServer() {
        sendCrashReportsToServer(mContext);
    }

    /**
     * 把错误报告发送给服务器,包含新产生的和以前没发送的.
     *
     * @param ctx
     */
    public void sendCrashReportsToServer(Context ctx) {
        String[] crFiles = getCrashReportFiles(ctx);
        if (crFiles != null && crFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(crFiles));
            for (String fileName : sortedFiles) {
                File cr = new File(ctx.getFilesDir(), fileName);
                postReport(cr);
            }
        }
    }

    public void postReport(final File file) {
        if (file == null) {
            return;
        }
        // TODO 发送错误报告到服务器
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("logFile", file.getName(), requestFile);
        CommonApi api = ServiceManager.create(CommonApi.class);
        final Call<HttpResponse<Object>> caller = (Call<HttpResponse<Object>>) api.reportAppLog(part);
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Response<HttpResponse<Object>> res = caller.execute();
                        LogUtils.loge(TAG, "doInBackground: " + res.isSuccessful());

                    } catch (Exception e) {
                        LogUtils.loge(TAG, "doInBackground: " + e.getMessage());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    if (file != null) {
                        file.delete();// 删除已发送的报告
                    }
                    deleteAllCrashFiles();
                }
            }.executeOnExecutor((ExecutorService) Executors.newFixedThreadPool(3));
        } catch (Exception e) {
            LogUtils.loge(TAG, "postReport: " + e.getMessage());
        }
    }

    /**
     * @param ctx
     * @return all the files with same bitmap type.
     */
    public static String[] getcrashLogFiles(Context ctx) {
        File filesDir = ctx.getFilesDir();
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        return filesDir.list(filter);
    }

    /***
     * Delete all the bitmap of the same type.
     ***/
    public void deleteAllCrashFiles() {
        String[] bitmapDataFiles = getcrashLogFiles(mContext);
        if (bitmapDataFiles != null && bitmapDataFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(bitmapDataFiles));
            for (String fileName : sortedFiles) {
                File crashlog = new File(mContext.getFilesDir(), fileName);
                crashlog.delete();
            }
        }
        return;
    }

    /**
     * 获取错误报告文件名
     *
     * @param ctx
     * @return
     */
    private String[] getCrashReportFiles(Context ctx) {
        File filesDir = ctx.getFilesDir();
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        return filesDir.list(filter);
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return
     */
    private String saveCrashInfoToFile(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = info.toString();
        printWriter.close();
        mDeviceCrashInfo.put("EXEPTION", ex.getLocalizedMessage());
        mDeviceCrashInfo.put(STACK_TRACE, result);
        try {
            //long timestamp = System.currentTimeMillis();
            Time t = new Time("GMT+8");
            t.setToNow(); // 取得系统时间
            int date = t.year * 10000 + t.month * 100 + t.monthDay;
            int time = t.hour * 10000 + t.minute * 100 + t.second;
            String fileName = "crash-" + date + "-" + time + CRASH_REPORTER_EXTENSION;
            FileOutputStream trace = mContext.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            mDeviceCrashInfo.store(trace, "");
            trace.flush();
            trace.close();
            return fileName;
        } catch (Exception e) {
            LogUtils.loge(TAG, "an error occured while writing report file..." + e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * 保存crash信息到LogCache统一管理
     */
    private void saveCrashToLogCache(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String crashStackTrace = info.toString();
        printWriter.close();
        LogUtils.loge(ex.getLocalizedMessage());
        LogUtils.loge(crashStackTrace);
    }

    /**
     * 保存错误信息到文件中
     *
     * @param testMsg
     * @return
     */
    public String saveTestCrashInfoToFile(String testMsg) {
        String result = testMsg;
        mDeviceCrashInfo.put("EXEPTION", "test1");
        mDeviceCrashInfo.put(STACK_TRACE, result);
        try {
            //long timestamp = System.currentTimeMillis();
            Time t = new Time("GMT+8");
            t.setToNow(); // 取得系统时间
            int date = t.year * 10000 + t.month * 100 + t.monthDay;
            int time = t.hour * 10000 + t.minute * 100 + t.second;
            String fileName = "crash-" + date + "-" + time + CRASH_REPORTER_EXTENSION;
            FileOutputStream trace = mContext.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            mDeviceCrashInfo.store(trace, "");
            trace.flush();
            trace.close();
            return fileName;
        } catch (Exception e) {
            LogUtils.loge(TAG, "an error occured while writing report file..." + e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * 收集程序崩溃的设备信息
     *
     * @param ctx
     */
    public void collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put(VERSION_NAME,
                        pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put(VERSION_CODE, "" + pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.loge(TAG, "Error while collect package info" + e.getLocalizedMessage());
        }
        //使用反射来收集设备信息.在Build类中包含各种设备信息,
        //例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
        //具体信息请参考后面的截图
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), "" + field.get(null));
                if (DEBUG) {
                    LogUtils.logd(TAG, field.getName() + " : " + field.get(null));
                }
            } catch (Exception e) {
                LogUtils.loge(TAG, "Error while collect crash info" + e.getLocalizedMessage());
            }
        }
    }

}
