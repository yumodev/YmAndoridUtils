package com.yumo.common.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by yumodev on 17/2/24.
 * App相关的工具类
 */

public class YmAppUtil {

    /**
     * 获取APP的版本号
     * YumoDev
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
}
