package com.yumo.common.thread;

import android.content.Context;
import android.os.Looper;

import com.yumo.common.io.YmIoUtil;

import java.io.FileInputStream;

/**
 * Created by yumodev on 18/3/22.
 * 处理进程相关的操作
 */

public class YmProcessUtil {



    /**
     * 获取当前的进程名字
     * @return
     */
    public static String getCurrentProcessName() {
        FileInputStream in = null;
        try {
            String fn = "/proc/self/cmdline";
            in = new FileInputStream(fn);
            byte[] buffer = new byte[256];
            int len = 0;
            int b;
            while ((b = in.read()) > 0 && len < buffer.length) {
                buffer[len++] = (byte) b;
            }
            if (len > 0) {
                String s = new String(buffer, 0, len, "UTF-8");
                return s;
            }
        } catch (Throwable e) {
           e.printStackTrace();
        } finally {
            YmIoUtil.close(in);
        }
        return null;
    }


    public static boolean isMainProcess(Context context){
        String packageName = context.getPackageName();
        if (packageName.equals(getCurrentProcessName())){
            return true;
        }

        return false;
    }
}
