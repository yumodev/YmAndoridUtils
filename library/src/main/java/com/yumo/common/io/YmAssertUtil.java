package com.yumo.common.io;

import android.content.Context;

import java.io.IOException;

/**
 * Created by yumodev on 17/2/24.
 * 操作Assert相关的工具类
 */

public class YmAssertUtil {

    /**
     * 从Assets 读取一个文件到String中
     * @param context
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String getAssertFileToString(Context context, String fileName) throws IOException{
        return YmIoUtil.getStringFromInput(context.getAssets().open(fileName));
    }
}
