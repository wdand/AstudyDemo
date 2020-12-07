package com.bingkong.bkbase.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bingkong.bknet.http.Api.ServiceManager;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.jkb.fragment.rigger.rigger.Rigger;
import com.squareup.leakcanary.LeakCanary;
import com.bingkong.bkbase.utils.log.LogCache;

public class ComApp extends XBaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        bkStockApp.init(this);//记录全局context
        LogCache.getInstance().init();
        //要调试内存泄漏，请打开以下注释
        //initLeakCanary();
    }

    public void setDeBug(boolean isDebug) {
        Rigger.enableDebugLogging(isDebug);
        LogUtils.getConfig().setConsoleSwitch(isDebug);
//        TokenManager.getInstance().initOnApplicationCreate();
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ServiceManager.initServiceManager(this);
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    private void initLeakCanary() {
        //内存泄漏检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            com.bingkong.bkbase.utils.LogUtils.logw("ComApp", "This process is dedicated to LeakCanary for heap analysis.");
        } else {
            com.bingkong.bkbase.utils.LogUtils.logw("ComApp", "install LeakCanary for heap analysis.");
            LeakCanary.install(this);
        }
    }
}
