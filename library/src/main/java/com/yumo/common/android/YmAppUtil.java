package com.yumo.common.android;

import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by yumodev on 17/2/24.
 * App相关的工具类
 */

public class YmAppUtil {

    /**
     * 获取APP的版本号
     * yumodev
     * @param context
     * @return int
     * 2015-1-18
     */
    public static int getAppVersion(Context context) {
        if (context == null){
            return 0;
        }

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取App的名字
     * @param context
     * @return
     */
    public static String getAppLabel(Context context){
        if (context == null){
            return null;
        }

        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    /**
     * 获取一个APP的Uid
     * @param context
     * @param packageName
     * @return 返回-1标识获取失败
     */
    public static int getUid(Context context, String packageName){
        int uid = -1;
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            uid = applicationInfo.uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return uid;
    }


    /**
     * 实现文本复制
     * @param context
     * @param content
     */
    public static void copy(Context context, String content){
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content);
    }

    /**
     * 粘贴
     * @param context
     * @return
     */
    public static String paste(Context context){
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        String content = cmb.getText().toString().trim();
        return content;
    }

    /**
     * 分享
     * @param context
     * @param title
     * @param content
     * @return
     */
    public static boolean share(Context context, String title, String content){
        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("text/plain");
        send.putExtra(Intent.EXTRA_TEXT, content);
        send.putExtra(Intent.EXTRA_SUBJECT, title);
        try {
            context.startActivity(Intent.createChooser(send, content));
        } catch (android.content.ActivityNotFoundException ex) {
            return false;
        }
        return true;
    }


    /**
     * 是否是否正在运行。
     * @param context
     * @param className
     * @return
     */
    public static boolean isServiceRunning(Context context, String className) {
        if (context == null){
            return false;
        }

        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ;
        List<ActivityManager.RunningServiceInfo> lists = am.getRunningServices(1000);
        for (ActivityManager.RunningServiceInfo info : lists) {
            if (info.service.getClassName().equals(className)
                    && info.service.getPackageName().equals(context.getPackageName())) {
                isRunning = true;
                break;
            }
        }

        return isRunning;
    }
}
